package dynamic;

public class Q377_CombinationSumIV {

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;  // 单个数字时使用
        for (int i=1; i<=target; i++) {
            for (int num: nums) {
                if (num <= i) {
                    // 表示以 num 开头的排列有多少个数据组合
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int target = 4;
        Q377_CombinationSumIV q377_combinationSumIV = new Q377_CombinationSumIV();
        int ans = q377_combinationSumIV.combinationSum4(nums, target);
        System.out.println(ans);
    }
}
