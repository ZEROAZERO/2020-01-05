package dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 *
 873. Length of Longest Fibonacci Subsequence
 A sequence X_1, X_2, ..., X_n is fibonacci-like if:

 n >= 3
 X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
 Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.

 (Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)



 Example 1:

 Input: [1,2,3,4,5,6,7,8]
 Output: 5
 Explanation:
 The longest subsequence that is fibonacci-like: [1,2,3,5,8].
 Example 2:

 Input: [1,3,7,11,12,14,18]
 Output: 3
 Explanation:
 The longest subsequence that is fibonacci-like:
 [1,11,12], [3,11,14] or [7,11,18].


 Note:

 3 <= A.length <= 1000
 1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
 (The time limit has been reduced by 50% for submissions in Java, C, and C++.)
 */
public class Q873_LengthOfLongestFibonacciSubsequence {

    /**
     *
     dp[i][j]: 以A[i],A[j] 结尾的最长的长度-2
     如果存在A[k]=A[j]-A[i] 且k<i 则 dp[i][j]=dp[k][i]+1
     */
    public int lenLongestFibSubseq(int[] A) {
        int n = A.length;
        int[][] dp = new int[n-1][n];

        Map<Integer, Integer> map = new HashMap<>();
        // 初始化每个数字的位置
        for (int i=0; i<n; i++) {
            map.put(A[i], i);
        }

        // 初始化位置 i<j 的长度
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                dp[i][j] = 2;
            }
        }

        int maxLen = 0;
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                int kLen = A[j] - A[i];
                if (map.containsKey(kLen) && map.get(kLen)<i) {
                    int kPos = map.get(kLen);
                    dp[i][j] = Math.max(dp[i][j], dp[kPos][i]+1);
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }

        return maxLen>2? maxLen:0;
    }

    public static void main(String[] args) {
        int[] A = new int[]{1,3,7,11,12,14,18};
        Q873_LengthOfLongestFibonacciSubsequence q = new Q873_LengthOfLongestFibonacciSubsequence();
        int ansDp = q.lenLongestFibSubseq(A);
        System.out.println("ansDp:"+ansDp);
    }
}
