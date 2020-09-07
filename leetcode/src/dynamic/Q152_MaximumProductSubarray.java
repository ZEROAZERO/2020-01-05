package dynamic;

/**
 * 152. Maximum Product Subarray
 *
 Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

 Example 1:

 Input: [2,3,-2,4]
 Output: 6
 Explanation: [2,3] has the largest product 6.
 Example 2:

 Input: [-2,0,-1]
 Output: 0
 Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class Q152_MaximumProductSubarray {
    /**
     * brute force
     */
    private int maxProductBF(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i=0; i<nums.length; i++) {
            int ans = nums[i], tmp = nums[i];
            for (int j=i+1; j<nums.length; j++) {
                ans *= nums[j];
                max = max>ans? max: ans;
            }
            max = max>tmp? max: tmp;
        }
        return max;
    }

    /**
     * dp
     * state: dp[i] 表示以i结尾的子串的最大值
     * maxDP[i + 1] = max(maxDP[i] * A[i + 1], A[i + 1], minDP[i] * A[i + 1])
     * minDP[i + 1] = min(minDP[i] * A[i + 1], A[i + 1], maxDP[i] * A[i + 1])
     * dp[i + 1] = max(dp[i], maxDP[i + 1])
     */
    private int maxProductDp(int[] nums) {
        int max = Integer.MIN_VALUE;
        int imax = nums[0];
        int imin = nums[0];
        for (int i=1; i<nums.length; i++) {
            int tmp = imax;
            imax = Math.max(imax*nums[i], Math.max(nums[i], imin*nums[i]));
            imin = Math.min(tmp*nums[i], Math.min(nums[i], imin*nums[i]));
            max = Math.max(max, imax);
        }
        return max;
    }

    /**
     * dp
     * 遇到负数，最大值与最小值交换
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){  // 负数乘负数得最大
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);  // 遇正取最大
            imin = Math.min(imin*nums[i], nums[i]);  // 遇负取最小

            max = Math.max(max, imax);
        }
        return max;
    }

    /**
     * dp
     * state: dp[i] 表示位置i结尾的子串的最大或最小值
     * 遇到负数，最大值与最小值交换
     * maxDP[i + 1] = max(maxDP[i] * A[i + 1], A[i + 1])
     * minDP[i + 1] = min(minDP[i] * A[i + 1], A[i + 1])
     */
    public int maxProductDpArr(int[] nums) {
        int[] dp_max = new int[nums.length+1];
        int[] dp_min = new int[nums.length+1];
        if(nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        // 由于存在负数，所以需要维护两个数组
        // dp_max[i] 指的是以第 i 个数结尾的 乘积最大 的连续子序列
        // dp_min[i] 指的是以第 i 个数结尾的 乘积最小 的连续子序列
        dp_max[0] = 1;
        dp_min[0] = 1;
        for (int i = 1;i <= nums.length;i++){
            // 如果数组的数是负数，那么会导致 max 变成 min，min 变成 max
            // 故需要交换dp
            if(nums[i-1] < 0){
                int temp = dp_min[i-1];
                dp_min[i-1] = dp_max[i-1];
                dp_max[i-1] = temp;
            }
            dp_min[i] = Math.min(nums[i-1], dp_min[i-1]*nums[i-1]);
            dp_max[i] = Math.max(nums[i-1], dp_max[i-1]*nums[i-1]);
            max = Math.max(max, dp_max[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        // {2,3,-2,4}
        // {-2,0,-1}
        int[] nums = new int[]{-3,0,1,-2};
        Q152_MaximumProductSubarray q152_maximumProductSubarray = new Q152_MaximumProductSubarray();
        int ans = q152_maximumProductSubarray.maxProductBF(nums);
        System.out.println("bf:"+ans);
        int ans1 = q152_maximumProductSubarray.maxProductDp(nums);
        System.out.println("dp:"+ans1);
        int ans2 = q152_maximumProductSubarray.maxProduct(nums);
        System.out.println("dp swap(max, min):"+ans2);
    }

}
