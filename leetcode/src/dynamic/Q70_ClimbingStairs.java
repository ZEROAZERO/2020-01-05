package dynamic;

/**
 * 70. Climbing Stairs
 *
 You are climbing a stair case. It takes n steps to reach to the top.

 Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 Note: Given n will be a positive integer.

 Example 1:

 Input: 2
 Output: 2
 Explanation: There are two ways to climb to the top.
 1. 1 step + 1 step
 2. 2 steps
 Example 2:

 Input: 3
 Output: 3
 Explanation: There are three ways to climb to the top.
 1. 1 step + 1 step + 1 step
 2. 1 step + 2 steps
 3. 2 steps + 1 step
 */
public class Q70_ClimbingStairs {

    /**
     * recursion
     * timeout
     */
    private int climbStairsSelf(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairsSelf(n-1) + climbStairsSelf(n-2);
    }

    private int climbStairsHelper(int n, int height) {
        if (n > height) {
            return 0;
        }
        if (n == height) {
            return 1;
        }
        return climbStairsHelper(n+1, height) + climbStairsHelper(n+2, height);
    }

    /**
     * recording search
     */
    private int climbStairsRecord(int n) {
        int[] record = new int[n+1];
        return recordCore(n,record);
    }

    private int recordCore(int n, int[] record) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (record[n] > 0) {
            return record[n];
        }
        record[n] = recordCore(n-1, record) + recordCore(n-2, record);
        return record[n];
    }

    /**
     * dp
     * state: dp[i] represents the total ways
     * equation of state transition: dp[i] = dp[i-1] + dp[i-2]
     */
    private int climbStairsDp(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i=3; i<=n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int num = 5;
        Q70_ClimbingStairs q70_climbingStairs = new Q70_ClimbingStairs();
        int ans = q70_climbingStairs.climbStairsSelf(num);
        System.out.println(ans);
        int ans1 = q70_climbingStairs.climbStairsRecord(num);
        System.out.println(ans1);
        int ans2 = q70_climbingStairs.climbStairsDp(num);
        System.out.println(ans2);

        int ans3 = q70_climbingStairs.climbStairsHelper(0, num);
        System.out.println("helper ans3:"+ans3);
    }
}
