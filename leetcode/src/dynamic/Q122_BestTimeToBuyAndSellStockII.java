package dynamic;

/**
 * 122. Best Time to Buy and Sell Stock II
 *
 Say you have an array prices for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

 Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

 Example 1:

 Input: [7,1,5,3,6,4]
 Output: 7
 Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 Example 2:

 Input: [1,2,3,4,5]
 Output: 4
 Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 engaging multiple transactions at the same time. You must sell before buying again.
 */
public class Q122_BestTimeToBuyAndSellStockII {

    /**
     * 递归法
     */
    private int maxProfitRecursion(int[] prices) {
        return recursionHelper(prices, 0);
    }
    private int recursionHelper(int[] prices, int pos) {
        if (pos >= prices.length) {
            return 0;
        }
        int max = 0;
        for (int start=pos; start<prices.length-1; start++) {
            int maxProfit = 0;
            for (int end=start+1; end<prices.length; end++) {
                if (prices[start] < prices[end]) {
                    int profit = prices[end]-prices[start]+recursionHelper(prices, end+1);
                    maxProfit = Math.max(profit, maxProfit);
                }
            }
            max = Math.max(max, maxProfit);
        }
        return max;
        
    }

    /**
     * 峰谷法
     */
    private int maxProfit(int[] prices) {
        int i = 0;
        int valley = prices[0];
        int peak = prices[0];
        int maxProfit = 0;
        while (i < prices.length-1) {
            while (i < prices.length-1 && prices[i] >= prices[i+1]) {
                i++;
            }
            valley = prices[i];
            while (i < prices.length-1 && prices[i] <= prices[i+1]) {
                i++;
            }
            peak = prices[i];
            maxProfit += peak - valley;
        }
        return maxProfit;
    }


    private int maxProfitOpt(int[] prices) {
        int maxProfit = 0;
        for (int i=1; i<prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;
    }



    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        Q122_BestTimeToBuyAndSellStockII q122_bestTimeToBuyAndSellStockII = new Q122_BestTimeToBuyAndSellStockII();
        int ans = q122_bestTimeToBuyAndSellStockII.maxProfit(prices);
        System.out.println("ans:"+ans);

        int ans1 = q122_bestTimeToBuyAndSellStockII.maxProfitOpt(prices);
        System.out.println("opt ans1:"+ans1);

        int ans2 = q122_bestTimeToBuyAndSellStockII.maxProfitRecursion(prices);
        System.out.println("recursion ans2:"+ans2);
    }
}
