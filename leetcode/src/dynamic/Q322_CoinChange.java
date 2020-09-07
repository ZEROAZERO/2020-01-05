package dynamic;

import java.util.Arrays;

/**
 * 322. Coin Change
 You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

 Example 1:

 Input: coins = [1, 2, 5], amount = 11
 Output: 3
 Explanation: 11 = 5 + 5 + 1
 Example 2:

 Input: coins = [2], amount = 3
 Output: -1

 Input: coins = 14 13 8  amount = 24
 Output: 3ge8

 Input: 1 Integer.MAX_VALUE
 Output: 2
 */
public class Q322_CoinChange {

    /***********************dfs*************************/

    /**
     * dfs + backtracking
     *
     * note:
     *  1.递归返回值，
     *      若是累加值可以内部声明ans+=helper()，
     *      若接收返回值，不能在内部声明，可通过传参；全局变量声明
     *  2.1+2147483647 溢出int类型范围
     *
     *  Time Limit Exceeded
     */
    private int coinChangeDfs(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        if (coins.length == 0) {
            return -1;
        }
        Arrays.sort(coins);
        return dfsHelper(coins, amount, 0, 0, -1);
    }
    private int dfsHelper(int[] coins, int amount, long total, int ans, int endAns) {
        if (total == amount) {
            return endAns!=-1 && endAns<ans? endAns: ans;
        }
        for (int i=coins.length-1; i>=0; i--) {
            if (total + coins[i] <= amount) {
                total = total + coins[i];
                ans++;
                endAns = dfsHelper(coins, amount, total, ans, endAns);
                ans--;
                total = total - coins[i];
            }
        }
        return endAns;
    }

    /**
     * Time Limit Exceeded
     */
    private int coinChangeDfs1(int[] coins, int amount) {
        return dfs1Helper(0, coins, amount);
    }

    private int dfs1Helper(int idxCoin, int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (idxCoin < coins.length && amount > 0) {
            int maxSum = amount / coins[idxCoin]; //某个硬币的最大数量
            int minCost = Integer.MAX_VALUE; //最终结果
            for (int x = 0; x <= maxSum; x++) {
                if (amount >= x * coins[idxCoin]) {
                    int res = dfs1Helper(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                    if (res != -1) {
                        minCost = Math.min(minCost, res + x);
                    }
                }
            }
            return (minCost == Integer.MAX_VALUE)? -1: minCost;
        }
        return -1;
    }

    /**
     * 记忆化搜索
     * note:
     *  递归每一个分叉独立，只有return结果是影响的
     *
     * from top to bottom
     */
    private int coinChangeDfsOpt(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        if (coins.length == 0) {
            return -1;
        }
        Arrays.sort(coins);
        int[] memo = new int[amount];
        return dfsOptHelper(coins, amount, memo);
    }
    private int dfsOptHelper(int[] coins, int amount, int[] memo) {
        if (amount < 0){
            return -1;
        }
        if (amount == 0){
            return 0;
        }
        // 记忆化的处理，memo[n]用赋予了值，就不用继续下面的循环
        // 直接的返回memo[n] 的最优值
        if (memo[amount-1] != 0){
            return memo[amount-1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++){
            int res = dfsOptHelper(coins,amount-coins[i], memo);
            System.out.println(res);
            if (res >= 0 && res < min){
                min = res + 1; // 加1，是为了加上得到res结果的那个步骤中，兑换的一个硬币
                System.out.println("min:"+min);
            }
        }
        memo[amount-1] = (min == Integer.MAX_VALUE ? -1 : min);
        System.out.println(Arrays.toString(memo));
        return memo[amount-1];
    }

    /**
     * dp[i] 表示凑成i元的最小硬币数
     *
     * Input: 8,13,14
     * dp[i] = Math.min(dp[i-8], dp[i-13], dp[i-14])+1
     * 如：21元：dp[13]+1（15元+6元）, dp[8]+1（8元+13元）, dp[7]+1（7元+14元）
     *
     * from bottom to top
     * */
    private int coinChangeDp(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int max = amount + 1; //tips: max=amount+1, not Integer.MAX_VALUE
        Arrays.fill(dp, max); //细节 Integer.MAX_VALUE+1=Integer.MIN_VALUE
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) { //because the condition，causing the bound can‘t be out.
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount] > amount ? -1 : dp[amount];
    }


    private int coinChangeHandWrite(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i=1; i<=amount; i++) {
            for (int j=0; j<coins.length; j++) {
                if (i >= coins[j]) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }
        return dp[amount] >= amount+1? -1: dp[amount];
    }



    /**
     * 完全背包
     * coints 代表物品的价值
     * amount 代表商品容量
     * dp 代表硬币的数量
     */

    public static void main(String[] args) {
        int[] coins = new int[]{14,13,8};
        int amount = 24;
        Q322_CoinChange q322_coinChange = new Q322_CoinChange();
        int ans = q322_coinChange.coinChangeDfs(coins, amount);
        System.out.println("dfs:"+ans);

        int ans3 = q322_coinChange.coinChangeDfs1(coins, amount);
        System.out.println("dfs1:"+ans3);

        int ans1 = q322_coinChange.coinChangeDfsOpt(coins, amount);
        System.out.println("dfs opt:"+ans1);

        int ans2 = q322_coinChange.coinChangeDp(coins, amount);
        System.out.println("dp:"+ans2);

        int ans4 = q322_coinChange.coinChangeHandWrite(coins, amount);
        System.out.println("dp handwrite ans4:"+ans4);

    }

    // 24-14 13 8
    private int coinChangeError(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        if (coins.length == 0) {
            return -1;
        }
        Arrays.sort(coins);
        int total = 0;
        int ans = 0;
        for (int i=coins.length-1; i>=0; i--) {
            int cnt = 1;
            while (total < amount) {
                total += coins[i] * cnt;
                ans++;
                if (total == amount) {
                    return ans;
                }
                if (total > amount) {
                    ans--;
                    total -= coins[i];
                    break;
                }
            }
        }
        return -1;
    }
}
