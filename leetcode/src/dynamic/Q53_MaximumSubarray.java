package dynamic;

/**
 * 53.Maximum Subarray 最大子序列之和
 *
 Given an integer array nums,
 find the contiguous subarray (containing at least one number)
 which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:

 If you have figured out the O(n) solution,
 try coding another solution using the divide and conquer approach,
 which is more subtle.
 */
public class Q53_MaximumSubarray {

    /**
     * greedy
     */
    private int maxSubArrayGreed(int[] nums) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            maxSum = maxSum>sum ? maxSum: sum;
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    /**
     * greedy
     * impove code neatness
     */
    private int maxSubArrayGreed2(int[] nums) {
        int curSum = nums[0];
        int maxSum = nums[0];
        for (int i=1; i<nums.length; i++) {
            curSum = (curSum+nums[i])>nums[i]? curSum+nums[i]: nums[i];
            maxSum = maxSum>curSum? maxSum: curSum;
        }
        return maxSum;
    }


    /**
     * divide and conquer
     */
    private int maxSubArrayDivide(int[] nums) {
        return helper(nums, 0, nums.length-1);
    }

    private int crossSum(int[] nums, int left, int right, int p) {
        if (left == right) return nums[left];

        int leftSubsum = Integer.MIN_VALUE;
        int currSum = 0;
        for(int i = p; i > left - 1; --i) {
            currSum += nums[i];
            leftSubsum = Math.max(leftSubsum, currSum);
        }

        int rightSubsum = Integer.MIN_VALUE;
        currSum = 0;
        for(int i = p + 1; i < right + 1; ++i) {
            currSum += nums[i];
            rightSubsum = Math.max(rightSubsum, currSum);
        }

        return leftSubsum + rightSubsum;
    }

    private int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];

        int p = (left + right) / 2;

        int leftSum = helper(nums, left, p);
        int rightSum = helper(nums, p + 1, right);
        int crossSum = crossSum(nums, left, right, p);

        return Math.max(Math.max(leftSum, rightSum), crossSum);
    }

    /**
     * dynamic programming
     */
    private int maxSubArrayDp(int[] nums) {
        int maxSum = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (nums[i-1] > 0) {
                nums[i] += nums[i-1];
            }
            maxSum = Math.max(nums[i], maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        Q53_MaximumSubarray q53_maximumSubarray = new Q53_MaximumSubarray();
        int ans = q53_maximumSubarray.maxSubArrayDp(nums);
        System.out.println(ans);
    }
}
