package dynamic;

import java.util.Arrays;

/**
 * 63. Unique Paths II
 A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 Now consider if some obstacles are added to the grids. How many unique paths would there be?



 An obstacle and empty space is marked as 1 and 0 respectively in the grid.

 Note: m and n will be at most 100.

 Example 1:

 Input:
 [
 [0,0,0],
 [0,1,0],
 [0,0,0]
 ]
 Output: 2
 Explanation:
 There is one obstacle in the middle of the 3x3 grid above.
 There are two ways to reach the bottom-right corner:
 1. Right -> Right -> Down -> Down
 2. Down -> Down -> Right -> Right
 */
public class Q63_UniquePathsII {

    /**
     * dynamic programming
     * two-dimensional array
     */
    private int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length==1 && obstacleGrid[0].length==1 && obstacleGrid[0][0]==1) {
            return 0;
        }
        if (obstacleGrid.length==1 && obstacleGrid[0].length==1 && obstacleGrid[0][0]==0) {
            return 1;
        }
        int xLen = obstacleGrid.length;
        int yLen = obstacleGrid[0].length;
        int[][] dp = new int[xLen+1][yLen+1];
        dp[1][1] = 1;
        for (int i=1; i<=xLen; i++) {
            for (int j=1; j<=yLen; j++) {
                if (dp[i][j]==0 && obstacleGrid[i-1][j-1]==0) {
                    int left = 0, up = 0;
                    if (i-2>=0 && j-1>=0 && obstacleGrid[i-2][j-1] == 0) {
                        left = dp[i-1][j];
                    }
                    if (i-1>=0 && j-2>=0 && obstacleGrid[i-1][j-2] == 0) {
                        up = dp[i][j-1];
                    }
                    dp[i][j] = left + up;
                }
            }
        }
        return dp[xLen][yLen];
    }

    /**
     * compression space
     */
    private int uniquePathsWithObstaclesDpOne(int[][] obstacleGrid) {
        if (obstacleGrid.length==1 && obstacleGrid[0].length==1 && obstacleGrid[0][0]==1) {
            return 0;
        }
        if (obstacleGrid.length==1 && obstacleGrid[0].length==1 && obstacleGrid[0][0]==0) {
            return 1;
        }
        int xLen = obstacleGrid[0].length; //列
        int yLen = obstacleGrid.length; //行
        int[] dp = new int[xLen+1];
        dp[1] = 1;
        for (int i=1; i<=yLen; i++) {
            for (int j=1; j<=xLen; j++) {
                if (!(i==1 && j==1) && obstacleGrid[i-1][j-1]==0) {
                    int left = 0, up = 0;
                    if (i-1>=0 && j-2>=0 && obstacleGrid[i-1][j-2] == 0) {
                        left = dp[j-1];
                    }
                    if (i-2>=0 && j-1>=0 && obstacleGrid[i-2][j-1] == 0) {
                        up = dp[j];
                    }
                    dp[j] = left + up;
                } else if (obstacleGrid[i-1][j-1] == 1){
                    //{{0},{1}} 遇到ob（1，0）时，不会进入判断，会直接选用（0，1）结果
                    //如果使用 else 会更新 dp[1]=0，导致结果不正确
                    dp[j] = 0;
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[xLen];
    }

    /**
     * dp optimization version
     */
    private int uniquePathsWithObstaclesOpt(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;

        // If the starting cell has an obstacle, then simply return as there would be
        // no paths to the destination.
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // Number of ways of reaching the starting cell = 1.
        obstacleGrid[0][0] = 1;

        // Initializing the border of two-dimensional array
        // Filling the values for the first column
        for (int i = 1; i < row; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        // Filling the values for the first row
        for (int i = 1; i < col; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        // Starting from cell(1,1) fill up the values
        // No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
        // i.e. From above and left.
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }

        // Return value stored in rightmost bottommost cell. That is the destination.
        return obstacleGrid[row - 1][col - 1];
    }


    public static void main(String[] args) {
        int[][] obstacleGrid = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] ob = new int[][]{
                {0,1}
        };
        int[][] ob1 = new int[][]{
                {0},
                {1}
        };
        Q63_UniquePathsII q63_uniquePathsII = new Q63_UniquePathsII();
        int ans = q63_uniquePathsII.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(ans);
        int ans1 = q63_uniquePathsII.uniquePathsWithObstaclesDpOne(obstacleGrid);
        System.out.println(ans1);
    }
}
