package dynamic;

/**
 * 121. Best Time to Buy and Sell Stock
 Say you have an array for which the ith element is the price of a given stock on day i.

 If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

 Note that you cannot sell a stock before you buy one.

 Example 1:

 Input: [7,1,5,3,6,4]
 Output: 5
 Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 Not 7-1 = 6, as selling price needs to be larger than buying price.
 Example 2:

 Input: [7,6,4,3,1]
 Output: 0
 Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class Q121_BestTimeToBuyAndSellStock {

    /**
     * 我的目标是寻找最大和最小点并且保证前后顺序。
     *
     * 题解换了另一种思路，进行减法，直接求出数据作比较。
     */
    private int maxProfitGreeedy(int[] prices) {
        int min = prices[0];
        int max = 0;
        for (int i=1; i<prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
            } else if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    /**
     * [7,1,5,3,6,4]
     */
    private int maxProfitHandWrite(int[] prices) {
        int min = prices[0];
        int minIdx = 0;
        int max = prices[0];
        int maxIdx = 0;
        int maxProfit = 0;
        for (int i=1; i<prices.length; i++) {
            if (min > prices[i]) {
                min = prices[i];
                minIdx = i;
            } //else if (min < prices[i] || max<prices[i]) { 错误因为min<=max,因此max<prices[i]永不成立
            else if (min < prices[i]) {  // 修改如下
                max = prices[i];
                maxIdx = i;
            }
            if (minIdx < maxIdx) {
                maxProfit = Math.max(maxProfit, max-min);
                System.out.println(min+" "+max+" "+maxProfit);
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
//        int[] nums = new int[]{7,2,6,1};
        int[] nums = new int[]{7,1,5,3,6,4};
        Q121_BestTimeToBuyAndSellStock q121_bestTimeToBuyAndSellStock = new Q121_BestTimeToBuyAndSellStock();
        int ans = q121_bestTimeToBuyAndSellStock.maxProfitGreeedy(nums);
        System.out.println("ans:"+ans);

        int ans1 = q121_bestTimeToBuyAndSellStock.maxProfitHandWrite(nums);
        System.out.println("hand write ans1:"+ans1);
    }
}
