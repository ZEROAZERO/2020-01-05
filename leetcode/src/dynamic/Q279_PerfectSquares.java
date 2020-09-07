package dynamic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 279. Perfect Squares
 *
 Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 Example 1:

 Input: n = 12
 Output: 3
 Explanation: 12 = 4 + 4 + 4.
 Example 2:

 Input: n = 13
 Output: 2
 Explanation: 13 = 4 + 9.
 */
public class Q279_PerfectSquares {

    private int numSquares(int n) {
        int[] dp = new int[n+1];
        Set<Integer> record = new HashSet<>();
        for (int i=1; i<=n; i++) {
            dp[i] = i;
            for (int j=1; j<=Math.sqrt(i); j++) {
                //dp[i] = Math.min(dp[i-j*j]+1, dp[i]);
                if (dp[i] > dp[i-j*j]+1) {
                    dp[i] = dp[i-j*j]+1;
                }
            }
        }
        record.stream().forEach(key-> System.out.print(key+" "));
        System.out.println("\n"+Arrays.toString(dp));
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 23;
        Q279_PerfectSquares q279_perfectSquares = new Q279_PerfectSquares();
        int ans = q279_perfectSquares.numSquares(n);
        System.out.println(ans);
    }
}
