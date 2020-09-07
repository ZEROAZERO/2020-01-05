package dynamic;

import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 *
 Given an unsorted array of integers, find the length of longest increasing subsequence.

 Example:

 Input: [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 Note:

 There may be more than one LIS combination, it is only necessary for you to return the length.
 Your algorithm should run in O(n2) complexity.
 Follow up: Could you improve it to O(n log n) time complexity?
 */
public class Q300_LongestIncreasingSubsequence {

    private int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        int ans = 1;
        for (int i=1; i<nums.length; i++) {
            for (int j=0; j<i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    ans = Math.max(dp[i], ans);
                }
            }
        }
        return ans;
    }

    /**
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/
     * 贪心 + 二分查找
     */

    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,7,101,18};
        Q300_LongestIncreasingSubsequence q300_longestIncreasingSubsequence = new Q300_LongestIncreasingSubsequence();
        int ans = q300_longestIncreasingSubsequence.lengthOfLIS(nums);
        System.out.println("dp ans:"+ans);
    }
}
