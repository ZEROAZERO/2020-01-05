package dynamic;

import java.util.Arrays;

/**
 * 646. Maximum Length of Pair Chain
 *
 You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.

 Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.

 Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.

 Example 1:
 Input: [[1,2], [2,3], [3,4]]
 Output: 2
 Explanation: The longest chain is [1,2] -> [3,4]
 Note:
 The number of given pairs will be in the range [1, 1000].
 */
public class Q646_MaximumLengthOfPairChain {

    private int findLongestChainDp(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        int[] dp = new int[pairs.length];
        System.out.println(pairs.length);  // 行数
        Arrays.fill(dp, 1);

        for (int j=1; j<pairs.length; j++) {
            for (int i=0; i<j; i++) {
                if (pairs[i][1] < pairs[j][0]){
                    dp[j] = Math.max(dp[j], dp[i]+1);
                }
            }
        }
        return dp[pairs.length-1];
    }

    private int findLongestChainGreedy(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
        for (int i=0; i<pairs.length; i++) {
            System.out.println(pairs[i][1]);
        }
        int ans = 1;
        int pos = 0;
        for (int i=1; i<pairs.length; i++) {
            if (pairs[pos][1] < pairs[i][0]) {
                ans++;
                pos = i;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] pairs = new int[][]{
                {0,1},
                {2,7},
                {2,4},
                {3,4},
                {5,6}
        };

//        int[][] pairs = new int[][]{
//                {1,2},
//                {2,3},
//                {3,4}
//        };
        Q646_MaximumLengthOfPairChain q646_maximumLengthOfPairChain = new Q646_MaximumLengthOfPairChain();
        int ans1 = q646_maximumLengthOfPairChain.findLongestChainDp(pairs);
        System.out.println("dp ans1:"+ans1);

        int ans2 = q646_maximumLengthOfPairChain.findLongestChainGreedy(pairs);
        System.out.println("greedy ans2:"+ans2);
    }
}
