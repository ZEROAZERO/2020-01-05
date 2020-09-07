package dynamic;


/**
 *494. Target Sum
 *
 You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

 Find out how many ways to assign symbols to make sum of integers equal to target S.

 Example 1:
 Input: nums is [1, 1, 1, 1, 1], S is 3.
 Output: 5
 Explanation:

 -1+1+1+1+1 = 3
 +1-1+1+1+1 = 3
 +1+1-1+1+1 = 3
 +1+1+1-1+1 = 3
 +1+1+1+1-1 = 3

 There are 5 ways to assign symbols to make the sum of nums be target 3.
 Note:
 The length of the given array is positive and will not exceed 20. 长度不会超过20
 The sum of elements in the given array will not exceed 1000. 初始数据的和不超过1000
 Your output answer is guaranteed to be fitted in a 32-bit integer.

 compete pack
 */
public class Q494_TargetSum {
    /**
     * 最后得到匹配的结果
     */

    /**
     * 遍历所有的符号可能
     * timeout
     */
    private int findTargetSumWaysDfs(int[] nums, int S) {
        if (nums.length == 0) {
            return 0;
        }
        return dfsHelper(nums, S, new char[]{'+','-'}, "");
    }
    private int dfsHelper(int[] nums, int S, char[] symbol, String str) {
        if (str.length() == nums.length) {
            if (calSum(nums, str) == S) {
                System.out.println("dfs正确加1");
                return 1;
            }
            return 0;
        }
        int ans1 = 0;
        int ans2 = 0;
        ans1 += dfsHelper(nums, S, symbol, str+symbol[0]);
        ans2 += dfsHelper(nums, S, symbol, str+symbol[1]);
//        for (int i=0; i<2; i++) {
//            str += symbol[i];
//            ans += dfsHelper(nums, S, symbol, str);
//            str = str.substring(0, str.length() - 1);
//        }
        return ans1 + ans2;
    }
    private int calSum(int[] nums, String str) {
        int ans = 0;
        for (int i=0; i<str.length(); i++) {
            if (str.charAt(i) == '+') {
                ans += nums[i];
            } else {
                ans -= nums[i];
            }
        }
        return ans;
    }

    /**
     * recursion
     * 时间复杂度 2的N次方
     * 空间复杂度 N 递归的栈空间
     */
    private int findTargetSumWaysRecursion(int[] nums, int S) {
        return recHelper(nums, 0, 0, S);
    }
    private int recHelper(int[] nums, int i, int sum, int S) {
        if (i == nums.length && sum == S) {
            System.out.println("正确加1");
            return 1;
        }
        if (i >= nums.length) {
            return 0;
        }
        int ans = 0;
        int ans1 = recHelper(nums, i + 1, sum + nums[i], S);
        int ans2 = recHelper(nums, i + 1, sum - nums[i], S);
        ans = ans1 + ans2;
        return ans;
    }


    /**
     * dp[i][j] represents the sum of the i-th digit
     * dp[i][j] = dp[i-1][j-num[i]] + dp[i-1][j+nums[i]]
     * 前i个数组成的方案数 = 加上nums[i]的方案数 + 减去nums[i]的方案数
     *
     * 递推公式
     * dp[i][j + nums[i]] = dp[i][j + nums[i]] + dp[i - 1][j] // dp[i][j] = dp[i][j] +dp [i-1][j-nums[i]]
     * dp[i][j - nums[i]] = dp[i][j - nums[i]] + dp[i - 1][j] // dp[i][j] = dp[i][j] +dp [i-1][j+nums[i]]
     */
    private int findTargetSumWaysDp(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        // 可能存在nums[0]==0，因此第二步+1
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                // 若前置不存在方案数，则后缀不能形成方案
                if (dp[i - 1][sum + 1000] > 0) {
                    //System.out.println((sum + nums[i] + 1000)+" "+(sum - nums[i] + 1000));
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }

    /**
     * dp[i][j] 在第i个元素能组成的和
     * [0,999]为负数 [1000]为0 [1001,2000]为正数
     */
    private int findTargetSumWaysHandWrite2(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001]; //[0,999]为负数 [1000]为0 [1001,2000]为正数
        dp[0][1000-nums[0]] = 1;
        dp[0][1000+nums[0]] += 1;
        for (int i=1; i<nums.length; i++) {
            for (int sum=-1000; sum<=1000; sum++) {
                if (dp[i-1][sum+1000] != 0) {
                    if (sum-nums[i]+1000 >= 0) {
                        dp[i][sum-nums[i]+1000] += dp[i-1][sum+1000];
                    }
                    if (sum+nums[i]+1000 <= 2000) {
                        dp[i][sum+nums[i]+1000] += dp[i-1][sum+1000];
                    }
                }
            }
        }
        for (int i=0; i<=2000; i++) {
            if (dp[nums.length-1][i] != 0) {
                System.out.println(i+" "+dp[nums.length-1][i]);
            }
        }
        return S > 1000 ? 0 : dp[nums.length-1][S+1000];
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int S = 3;
        Q494_TargetSum q494_targetSum = new Q494_TargetSum();
        int ans = q494_targetSum.findTargetSumWaysDfs(nums, S);
        System.out.println("dfs:"+ans);

        int ans1 = q494_targetSum.findTargetSumWaysRecursion(nums, S);
        System.out.println("recursion:"+ans1);

        int ans2 = q494_targetSum.findTargetSumWaysDp(nums, S);
        System.out.println("dp:"+ans2);

        int ans4 = q494_targetSum.findTargetSumWaysHandWrite2(nums, S);
        System.out.println("dp hand write ans4:"+ans4);
    }
}
