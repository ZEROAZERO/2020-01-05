package dynamic;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 523. Continuous Subarray Sum
 *
 Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, that is, sums up to n*k where n is also an integer.



 Example 1:

 Input: [23, 2, 4, 6, 7],  k=6
 Output: True
 Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 Example 2:

 Input: [23, 2, 6, 4, 7],  k=6
 Output: True
 Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.


 Note:

 The length of the array won't exceed 10,000.
 You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */
public class Q523_ContinuousSubarraySum {

    /**
     * Time Limited Exceeded
     */
    private boolean checkSubarraySum(int[] nums, int k) {
        return checkHelper(nums, k, 0, 0, 0);
    }

    private boolean checkHelper(int[] nums, int k, int pos, int sum, int step) {
        if (k!=0 && step>=2 && sum%k==0) {
            return true;
        }
        if (k==0 && step>=2 && sum==k) {
            return true;
        }
        if (pos >= nums.length) {
            return false;
        }
        boolean flag1 = checkHelper(nums, k, pos+1, sum+nums[pos], step+1);
        boolean flag2 = checkHelper(nums, k, pos+1, nums[pos], 1);
        return flag1 || flag2;
    }

    /**
     * dp Memory Limit Exceeded
     * dp保存的是sum[i,j]的和
     */
    private boolean checkSubarraySumDp(int[] nums, int k) {
        int[][] dp = new int[nums.length+1][nums.length+1];
        for (int i=1; i<=nums.length; i++) {
            for (int j=1; j<=nums.length; j++) {
                if (i == j) {
                    dp[i][j] = nums[i-1];
                } else {
                    dp[i][j] = nums[j-1] + dp[i][j-1] - dp[0][i-1];
                }
            }
        }
        for (int i=1; i<=nums.length; i++) {
            for (int j=1; j<=nums.length; j++) {
                if (j-i>=1 && (k==dp[i][j] || (k!=0 && dp[i][j]%k==0))) {
                    return true;
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        return false;
    }

    /**
     * dp opt
     * dp代表前i个数的和
     */
    private boolean checkSubarraySumDpOpt(int[] nums, int k) {
        int[] dp = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            dp[i] += nums[i-1] + dp[i-1];
        }
        for (int i = 1; i <= nums.length-1; i++) {
            for (int j = i+1; j <= nums.length; j++) {
                int sum = dp[j] - dp[i-1];
                if (k == sum || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }
            System.out.println(Arrays.toString(dp));
        }
        return false;
    }



    /**
     * hash
     * 判断 (sum[j]−sum[i])%k 是否等于 0。
     * 故若想 (sum[j] - sum[i])%k = 0 则必有 sum[j]%k == sum[i]%k
     *
     * 等同于sum[i,j]=sum[0,j]-sum[0,i] (满足条件j-i>=2，即[i,j]中存在多于两个数字）
     * 若 sum[i,j]%k==0，则找到满足条件得一串子数组
     *
     * note：初始化标志位的选定
     */
    private boolean checkSubarraySumHash1(int[] nums, int k) {
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum = sum % k;
            }
            if (map.containsKey(sum)) {
                if (i - map.get(sum) > 1)
                    return true;
            } else {
                map.put(sum, i);
            }
        }
        return false;
    }

    private boolean checkSubarraySumHash(int[] nums, int k) {
        int N = nums.length, cache = 0;
        int[] sum = new int[N+1];
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            sum[i+1] = sum[i] + nums[i];
            int res = k == 0 ? sum[i+1] : sum[i+1] % k;
            if (set.contains(res)) {
                return true;
            }
            set.add(cache);
            cache = res;  // 保证个数是两个以上
            // 如nums{1，1} k=2; 最开始放入0，1，1+1=2后对k取余得0，与前端0匹配
            // nums={23, 2, 4, 6, 7} k=6; 最开始放入0，5，1，5{0，23，25，29...}后得到结果true
        }
        return false;
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{23, 2, 4, 6, 7};
        // int k = 6;

        int[] nums = new int[]{0};
        int k = 0;

        // int[] nums = new int[]{0, 0};
        // int k = 0;

        // int[] nums = new int[]{1, 1};
        // int k = 2;

        Q523_ContinuousSubarraySum q523_continuousSubarraySum = new Q523_ContinuousSubarraySum();
        boolean ans = q523_continuousSubarraySum.checkSubarraySum(nums, k);
        System.out.println("recursion ans:"+ans);

        boolean ans1 = q523_continuousSubarraySum.checkSubarraySumDp(nums, k);
        System.out.println("dp ans1:"+ans1);

        boolean ans2 = q523_continuousSubarraySum.checkSubarraySumDpOpt(nums, k);
        System.out.println("dp opt ans2:"+ans2);

        boolean ans3 = q523_continuousSubarraySum.checkSubarraySumHash(nums, k);
        System.out.println("hash ans3:"+ans3);

    }
}
