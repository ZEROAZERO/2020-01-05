package dynamic;


import java.util.Arrays;
import java.util.List;

/**
 * 518. Coin Change 2
 *
 You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.



 Example 1:

 Input: amount = 5, coins = [1, 2, 5]
 Output: 4
 Explanation: there are four ways to make up the amount:
 5=5
 5=2+2+1
 5=2+1+1+1
 5=1+1+1+1+1
 Example 2:

 Input: amount = 3, coins = [2]
 Output: 0
 Explanation: the amount of 3 cannot be made up just with coins of 2.
 Example 3:

 Input: amount = 10, coins = [10]
 Output: 1


 Note:

 You can assume that

 0 <= amount <= 5000
 1 <= coin <= 5000
 the number of coins is less than 500
 the answer is guaranteed to fit into signed 32-bit integer
 */
public class Q518_CoinChangeII {

    /**
     * 错误原因是在for循环处
     * 将结果 1112、1121、1211、2111 这几种算作了不同种类的数据
     *
     * 解决方案
     * 使得choice数组记录数据，保证choice(pos-1)<=coins[i]，使得数据是升序
     */
    private int change(int amount, int[] coins) {
        int[] choice = new int[amount+1];
        Arrays.fill(choice, Integer.MAX_VALUE);
        return changeHelper(amount, coins, 0, choice);
    }
    private int changeHelper(int amount, int[] coins, int pos, int[] choice) {
        if (amount < 0) {
            return 0;
        }
        if (amount == 0) {
            System.out.println(Arrays.toString(choice));
            return 1;
        }
        int ans = 0;
        for (int i=0; i<coins.length; i++) {
            if (pos==0 || choice[pos-1]<=coins[i]) {
                choice[pos] = coins[i];
                ans += changeHelper(amount-coins[i], coins, pos+1, choice);
            }
        }
        return ans;
    }


    private int changeDp(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x <= amount; x++) {
                dp[x] += dp[x - coin];
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[amount];
    }


    public static void main(String[] args) {
//        int[] coins = new int[]{1, 2, 5};
//        int amount = 5;
        int[] coins = new int[]{2, 3, 5};
        int amount = 10;
        Q518_CoinChangeII q518_coinChangeII = new Q518_CoinChangeII();
        int ans1 = q518_coinChangeII.change(amount, coins);
        System.out.println("recursion ans1:"+ans1);

        int ans2 = q518_coinChangeII.changeDp(amount, coins);
        System.out.println("dp ans2:"+ans2);

    }
}
