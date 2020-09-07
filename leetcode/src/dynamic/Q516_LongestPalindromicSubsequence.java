package dynamic;

import java.util.Arrays;

/**
 * 516. Longest Palindromic Subsequence
 *
 Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.

 Example 1:
 Input:

 "bbbab"
 Output:
 4
 One possible longest palindromic subsequence is "bbbb".
 Example 2:
 Input:

 "cbbd"
 Output:
 2
 One possible longest palindromic subsequence is "bb".
 */
public class Q516_LongestPalindromicSubsequence {

    private int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            f[i][i] = 1;
            //  逆序，保证每个子问题已经算好
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    // System.out.println("f["+i+"]["+j+"]="+f[i][j]);
                } else {
                    f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                }
            }
            System.out.println(Arrays.toString(f[i]));
        }
        return f[0][n - 1];
    }

    public static void main(String[] args) {
        String s = "ababb";
        Q516_LongestPalindromicSubsequence q516_longestPalindromicSubsequence = new Q516_LongestPalindromicSubsequence();
        int ans1 = q516_longestPalindromicSubsequence.longestPalindromeSubseq(s);
        System.out.println("dp:"+ans1);
    }
}
