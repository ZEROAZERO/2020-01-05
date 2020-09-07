package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 120. Triangle
 *
 Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

 For example, given the following triangle

 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:

 Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */
public class Q120_Triangle {

    /**
     * dp from top to bottom
     */
    private int minimumTotalDp(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = Integer.MIN_VALUE;
        for (List<Integer> tri: triangle) {
            col = tri.size()>col? tri.size(): col;
        }
        int[][] dp = new int[row][col];
        dp[0][0] = triangle.get(0).get(0);
        for (int i=1; i<row; i++) {
            int jLen = triangle.get(i).size();
            List<Integer> list = triangle.get(i);
            for (int j=0; j<jLen; j++) {
                if (j == 0) {
                    dp[i][j] += dp[i-1][j] + list.get(j);
                } else if (j == jLen-1) {
                    dp[i][j] += dp[i-1][j-1] + list.get(j);
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]) + list.get(j);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i=0; i<col; i++) {
            min = dp[row-1][i]<min? dp[row-1][i]: min;
        }
        for (int i=0; i<row; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return min;
    }

    /**
     * dp optimization
     * changing state from end to begin,
     * avoiding the value of the front edge being overwritten.
     */
    private int minimumTotalDpOpt(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = Integer.MIN_VALUE;
        for (List<Integer> tri: triangle) {
            col = tri.size()>col? tri.size(): col;
        }
        int[] dp = new int[col];
        dp[0] = triangle.get(0).get(0);
        for (int i=1; i<row; i++) {
            int jLen = triangle.get(i).size();
            List<Integer> list = triangle.get(i);
            for (int j=jLen-1; j>=0; j--) {
                if (j == 0) {
                    dp[0] += list.get(0);
                } else if (j == jLen-1) {
                    dp[j] = dp[j-1] + list.get(j);
                } else {
                    dp[j] = Math.min(dp[j-1], dp[j]) + list.get(j);
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        int min = dp[0];
        for (int i=1; i<col; i++) {
            min = dp[i]<min? dp[i]: min;
        }
        return min;
    }

    /**
     * dp from bottom to top
     */
    private int minimumTotalDpB2T(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = Integer.MIN_VALUE;
        for (List<Integer> tri: triangle) {
            col = tri.size()>col? tri.size(): col;
        }
        int[][] dp = new int[row][col];
        List<Integer> bottomList = triangle.get(row-1);
        for (int i=0; i<col; i++) {
            dp[row-1][i] = bottomList.get(i);
        }
        for (int i=row-2; i>=0; i--) {
            int jLen = triangle.get(i).size();
            List<Integer> list = triangle.get(i);
            for (int j=0; j<jLen; j++) {
                dp[i][j] += Math.min(dp[i+1][j], dp[i+1][j+1]) + list.get(j);
            }
        }
        for (int i=0; i<row; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][0];
    }

    /**
     * dp from bottom to top
     * optimization
     * changing state from begin to end
     */
    private int minimumTotalDpOptB2T(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = Integer.MIN_VALUE;
        for (List<Integer> tri: triangle) {
            col = tri.size()>col? tri.size(): col;
        }
        int[] dp = new int[col];
        List<Integer> bottomList = triangle.get(row-1);
        for (int i=0; i<col; i++) {
            dp[i] = bottomList.get(i);
        }
        for (int i=row-2; i>=0; i--) {
            int jLen = triangle.get(i).size();
            List<Integer> list = triangle.get(i);
            for (int j=0; j<jLen; j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + list.get(j);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[0];
    }


    public static void main(String[] args) {
        Q120_Triangle q120_triangle = new Q120_Triangle();
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);
        List<Integer> l3 = new ArrayList<>();
        l3.add(6);
        l3.add(5);
        l3.add(7);
        List<Integer> l4 = new ArrayList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);
        List<List<Integer>> ll = new ArrayList<>();
        ll.add(l1);
        ll.add(l2);
        ll.add(l3);
        ll.add(l4);
        int ans = q120_triangle.minimumTotalDp(ll);
        System.out.println(ans);
        int ans1 = q120_triangle.minimumTotalDpOpt(ll);
        System.out.println(ans1);
        int ans2 = q120_triangle.minimumTotalDpB2T(ll);
        System.out.println(ans2);
        int ans3 = q120_triangle.minimumTotalDpOptB2T(ll);
        System.out.println(ans3);
    }
}
