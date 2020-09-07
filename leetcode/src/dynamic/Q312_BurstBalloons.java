package dynamic;

import java.util.Arrays;

/**
 *
 312. Burst Balloons
 Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

 Find the maximum coins you can collect by bursting the balloons wisely.

 Note:

 You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 Example:

 Input: [3,1,5,8]
 Output: 167
 Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class Q312_BurstBalloons {

    private int maxCoinsRecord(int[] nums) {
        // initializing the array
        int[] newNums = recordInitializing(nums);
        int[][] record = new int[newNums.length][newNums.length];
        for (int i=0; i<newNums.length; i++) {
            Arrays.fill(record[i], -1);
        }

        // the core
        int ans = recordHelper(newNums, record,0, newNums.length-1);

        return ans;
    }

    private int recordHelper(int[] nums, int[][] record, int left, int right) {
        // the end condition
        if (left >= right - 1) {
            return 0;
        }
        if (record[left][right] != -1) {
            return record[left][right];
        }

        for (int i=left+1; i<right; i++) {
            int num = nums[left] * nums[i] * nums[right];
            num += recordHelper(nums, record, left, i)
                    + recordHelper(nums, record, i, right);
            record[left][right] = Math.max(record[left][right], num);
        }

        return record[left][right];
    }

    private int[] recordInitializing(int[] nums) {
        int len = nums.length+2;
        int[] newNums = new int[len];
        newNums[0] = 1;
        newNums[len-1] = 1;
        for (int i=1; i<=nums.length; i++) {
            newNums[i] = nums[i-1];
        }
        // initializing completed
        return newNums;
    }


    /**
     * dp
     */
    private int maxCoinsDp(int[] nums) {
        int[] newNums = recordInitializing(nums);
        int[][] dp = new int[newNums.length][newNums.length];
        int n = newNums.length-1;

        for (int i=n-2; i>=0; i--) {
            for (int j=i+2; j<=n; j++) {
                for (int k=i+1; k<j; k++) {
                    int sum = newNums[i] * newNums[k] * newNums[j];
                    sum += dp[i][k] + dp[k][j];
                    dp[i][j] = Math.max(dp[i][j], sum);
                }
            }
        }
        return dp[0][n];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{3,1,5,8};
        Q312_BurstBalloons q312_burstBalloons = new Q312_BurstBalloons();
        int ans = q312_burstBalloons.maxCoinsRecord(nums);
        System.out.println(ans);

        int ans1 = q312_burstBalloons.maxCoinsDp(nums);
        System.out.println("dp ans1:"+ans1);
    }
}
