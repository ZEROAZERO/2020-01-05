package dynamic;


import java.util.Arrays;

/**
 * 647. Palindromic Substrings
 *
 Given a string, your task is to count how many palindromic substrings in this string.

 The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

 Example 1:

 Input: "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".


 Example 2:

 Input: "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


 Note:

 The input string length won't exceed 1000.
 */
public class Q647_PalindromicSubstrings {

    /**
     * dp[i, j]代表[i,j]段的是否为回文串
     */
    private int countSubstrings(String s) {
        int[][] dp = new int[s.length()+1][s.length()+1];
        int ans1 = 0;
        for (int i=1; i<=s.length(); i++) {
            dp[i][i] = 1;
            ans1++;
        }

        // 从后向前查找
        for (int i=s.length()-2; i>=0; i--) {  // 倒序第2个元素
            for (int j=i+1; j<s.length(); j++) {
                if ((i+1==j || dp[i+1][j-1]>=1) && s.charAt(i)==s.charAt(j)) {
                    dp[i][j] = 1;
                    ans1++;
                }
            }
        }
        System.out.println(ans1);

        int ans = 0;
        for (int i=0; i<=s.length(); i++) {
            System.out.println(Arrays.toString(dp[i]));
            for (int j=1; j<=s.length(); j++) {
                if (dp[i][j] > 0) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "aabbaa";
        Q647_PalindromicSubstrings q647_palindromicSubstrings = new Q647_PalindromicSubstrings();
        int ans1 = q647_palindromicSubstrings.countSubstrings(s);
        System.out.println("dp ans1:"+ans1);
    }
}
