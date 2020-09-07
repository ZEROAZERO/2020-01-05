package dynamic;

import java.util.Arrays;

/**
 * 213. House Robber II
 *
 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 Example 1:

 Input: [2,3,2]
 Output: 3
 Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 because they are adjacent houses.
 Example 2:

 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.
 */
public class Q213_HouseRobberII {

    /**
     * 更新每一个值
     * 如：0...i-1, nums[i] nums[i+1] nums[i+2] 用到的nums[i],会更新为最大的值max
     */
    private int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(robHelper(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                robHelper(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    private int robHelper(int[] nums) {
        int max = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (i-2 >= 0) {
                nums[i] += nums[i-2];  // 可能越界
            }
            if (max < nums[i]) {
                max = nums[i];
            } else {
                nums[i] = max;
            }
        }
        return max;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{2, 3, 2};
//        int[] nums = new int[]{3,1,3,1,3};

        int[] nums = new int[]{1,3,1,3,100};
        Q213_HouseRobberII q213_houseRobberII = new Q213_HouseRobberII();
        int ans = q213_houseRobberII.rob(nums);
        System.out.println("dp ans:"+ans);
    }
}
