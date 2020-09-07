package dynamic;

import java.util.Arrays;

/**
 * 72.Edit Distance 编辑距离
 *
 Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

 You have the following 3 operations permitted on a word:

 Insert a character
 Delete a character
 Replace a character
 Example 1:

 Input: word1 = "horse", word2 = "ros"
 Output: 3
 Explanation:
 horse -> rorse (replace 'h' with 'r')
 rorse -> rose (remove 'r')
 rose -> ros (remove 'e')
 Example 2:

 Input: word1 = "intention", word2 = "execution"
 Output: 5
 Explanation:
 intention -> inention (remove 't')
 inention -> enention (replace 'i' with 'e')
 enention -> exention (replace 'n' with 'x')
 exention -> exection (replace 'n' with 'c')
 exection -> execution (insert 'u')
 */
public class Q72_EditDistance {

    private int minDistanceDp(String word1, String word2) {
        int len1 = word1.length(); //row
        int len2 = word2.length(); //col
        int[][] dp = new int[len1+1][len2+1];
        for (int i=1; i<=len1; i++) {
            dp[i][0] = i;
        }
        for (int j=1; j<=len2; j++) {
            dp[0][j] = j;
        }
        for (int i=1; i<=len1; i++) { //row
            for (int j=1; j<=len2; j++) { //col
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    /*
                    0 1 2 3
                    N t o e -j
                 N  0 1 2 3
                 h  1 1 2 3
                 o  2 2 1 x  x=2
                 r  3 3 2 2
                 s  4 4 3 3
                 e  5 5 4 3
                 i
                 求到x的位置最少操作步数
                 dp[i-1][j-1] 插入t
                 dp[i][j-1] 插入操作 word2[0,j-1]+1==word2[0,j] 1代表<word1插入了第i个元素>
                    word1达到了word2[0,j-1]标准，插入t
                 dp[i-1][j] 删除操作 word2[0,j]+1==word2[0,j] 1代表<word1删掉了第i个元素>
                    word1达到了word2[0,j]标准，删除h
                     */
                    dp[i][j] = Math.min(dp[i-1][j-1],
                            Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }
        for (int i=0; i<=len1; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        Q72_EditDistance q72_editDistance = new Q72_EditDistance();
        int ans = q72_editDistance.minDistanceDp("horse", "toe");
        System.out.println(ans);
    }
}
