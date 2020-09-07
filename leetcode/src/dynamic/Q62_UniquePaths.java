package dynamic;

/**
 *  62. Unique Paths 不同路径
 Medium
 A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?


 Above is a 7 x 3 grid. How many possible unique paths are there?

 Note: m and n will be at most 100.

 Example 1:

 Input: m = 3, n = 2
 Output: 3
 Explanation:
 From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 1. Right -> Right -> Down
 2. Right -> Down -> Right
 3. Down -> Right -> Right
 Example 2:

 Input: m = 7, n = 3
 Output: 28
 */
public class Q62_UniquePaths {

    /**
     * recursion backtracking
     * define the method array
     * int[][] dir = new int[x][y];
     * 行：y = dir.length, 列：x = dir[0].length
     */
    private int uniquePathsBack(int m, int n) {
        int[][] dir = new int[][]{
                {1, 0}, // down
                {0, 1} // right
        };
        int[][] flag = new int[m][n];
        // 2列3行
        System.out.println(flag[0].length+" "+flag.length);
        flag[0][0] = 1;
        return uniquePathsCore(0, 0, dir, flag);
    }

    private int uniquePathsCore(int m, int n, int[][] dir, int[][] flag) {
        if (m==flag.length-1 && n==flag[0].length-1) {
            return 1;
        }
        int ans = 0;
        for (int i=0; i<2; i++) {
            int x = m + dir[i][0];
            int y = n + dir[i][1];
            if (x<flag.length && x>=0 &&
                    y>=0 && y<flag[0].length && flag[x][y]==0) {
                flag[x][y] = 1;
                ans += uniquePathsCore(x, y, dir, flag);
                flag[x][y] = 0;
            }
        }
        return ans;
    }

    /**
     * dynamic programming
     * two-dimensional array
     */
    private int uniquePathsDp(int m, int n) {
        if (m==1 && n==1) {
            return 1;
        }
        int[][] dp = new int[m+1][n+1];
        dp[1][1] = 1;
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (dp[i][j] == 0) {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }

    /**
     * compression space
     */
    private int uniquePathsDpOne(int m, int n) {
        if (m==1 && n==1) {
            return 1;
        }
        int[] dp = new int[m+1];
        dp[1] = 1;
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                int up = dp[j]; //dp[i][j-1]
                int left = dp[j-1]; //dp[i-1][j]
                dp[j] = left + up;
            }
        }
        return dp[m];
    }

    public static void main(String[] args) {
        Q62_UniquePaths q62_uniquePaths = new Q62_UniquePaths();
        int ans = q62_uniquePaths.uniquePathsBack(3, 2);
        System.out.println(ans);
        int ans1 = q62_uniquePaths.uniquePathsDp(2, 2);
        System.out.println(ans1);
        int ans2 = q62_uniquePaths.uniquePathsDpOne(2, 2);
        System.out.println(ans2);
    }
}
