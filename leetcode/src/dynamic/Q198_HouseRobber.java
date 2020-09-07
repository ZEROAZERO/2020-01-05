package dynamic;

import java.util.Arrays;

/**
 * 198. House Robber
 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 Example 1:

 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.
 Example 2:

 Input: [2,7,9,1,1,20,1]
 Output: 12
 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 Total amount you can rob = 2 + 9 + 1 = 12.
 2,1,1,2
 1,2,3,1
 */
public class Q198_HouseRobber {

    /**
     * 递归 the solution of recursion
     * timeout
     */
    private int rob(int[] nums) {
        return robHelper(nums, 0);
    }

    private int robHelper(int[] nums, int pos) {
        if (pos >= nums.length) {
            return 0;
        }
        int sum1 = 0;
        sum1 += nums[pos] + robHelper(nums, pos+2);
        int sum2 = 0;
        if (pos+1 < nums.length) {
            sum2 += nums[pos+1] + robHelper(nums, pos+3);
        }
        return Math.max(sum1, sum2);
    }

    /**
     * the solution of dynamic programming
     * dp[i] 表示[i,end]部分最大和
     */
    private int robDp(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int len = nums.length;
        int[] dp = new int[len+1];
        for (int i=0; i<len; i++) {
            dp[i] = nums[i];
        }
        for (int i=len-2; i>=0; i--) {
            dp[i] += dp[i+2];
            dp[i] = Math.max(dp[i], dp[i+1]); //每一次更新最大和
        }
        return dp[0];
    }

    private int robDpOpt(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int len = nums.length;
        int pre = 0;
        int cur = nums[len-1];
        int end = 0;
        for (int i=len-2; i>=0; i--) {
            pre = nums[i] + end;
            pre = Math.max(pre, cur);
            end = cur;
            cur = pre;
        }
        return cur;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        Q198_HouseRobber q198_houseRobber = new Q198_HouseRobber();
        int rob = q198_houseRobber.rob(nums);
        System.out.println("recursion:"+rob);
        int rob1 = q198_houseRobber.robDp(nums);
        System.out.println("dp:"+rob1);
        int rob2 = q198_houseRobber.robDpOpt(nums);
        System.out.println("dp opt:"+rob2);
    }
}
