package dynamic;

import java.util.Arrays;

/**
 * 64. Minimum Path Sum 最小路径和
 Medium
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.

 Example:

 Input:
 [
 [1,3,1],
 [1,5,1],
 [4,2,1]
 ]
 Output: 7
 Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 */
public class Q64_MinimumPathSum {

    /**
     * dp
     */
    private int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        if (row==1 && col==1) { //处理单个元素
            return grid[0][0];
        }
        int[] dp = new int[col+1];
        dp[0] = 0;
        for (int i=1; i<=col; i++) {
            dp[i] = dp[i-1] + grid[0][i-1]; //初始化第一行元素距离
        }
        System.out.println(Arrays.toString(dp));
        dp[0] = Integer.MAX_VALUE;
        for (int i=2; i<=row; i++) {
            for (int j=1; j<=col; j++) {
                int up = dp[j];
                int left = dp[j-1];
                dp[j] = grid[i-1][j-1] + Math.min(left, up);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[col];
    }

    /**
     * dfs
     */
    private int minPathSumDfs(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        if (row==1 && col==1) { //处理单个元素
            return grid[0][0];
        }
        int[][] dir = new int[][]{
                {1, 0},
                {0, 1}
        };
        boolean[][] flag = new boolean[row][col];
        flag[0][0] = true;
        return dfsCore(0, 0, dir, grid, flag, grid[0][0], Integer.MAX_VALUE);
    }

    private int dfsCore(int r, int c, int[][] dir, int[][] grid, boolean[][] flag, int ans, int min) {
        if (r==grid.length-1 && c==grid[0].length-1) {
            min = ans<min? ans: min;
            for (int i=0; i<grid.length; i++) {
                System.out.println(Arrays.toString(flag[i]));
            }
            System.out.println(min+" "+ans);
            return min;
        }
        int ans1 = 0;
        for (int i=0; i<2; i++) {
            int tr = r + dir[i][0];
            int tc = c + dir[i][1];
            if (tr>=0 && tr<grid.length && tc>=0 && tc<grid[0].length
                    && !flag[tr][tc]) {

                flag[tr][tc] = true;
                ans1 = dfsCore(tr, tc, dir, grid, flag, ans+grid[tr][tc], min);
                flag[tr][tc] = false;
            }
        }
        return ans1;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        Q64_MinimumPathSum q64_minimumPathSum = new Q64_MinimumPathSum();
        int ans = q64_minimumPathSum.minPathSum(grid);
        System.out.println(ans);
        int ans1 = q64_minimumPathSum.minPathSumDfs(grid);
        System.out.println(ans1);
    }
}
