package dynamic;

import java.util.Arrays;

/**
 * 97. Interleaving String
 *
 Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

 Example 1:

 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 Output: true
 Example 2:

 Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 Output: false
 */
public class Q97_InterleavingString {

    /**
     * 时间复杂度：O(2^{m+n})
     * 空间复杂度：O(m+n) 递归栈的深度
     *
     * 二叉树从根节点到叶子节点的路径代表一个组成结果的可能
     */
    private boolean isInterleaveBacktracking(String s1, String s2, String s3) {
        return backTrackingHelper(s1, 0, s2, 0, "", s3);
    }
    private boolean backTrackingHelper(String s1, int i, String s2, int j, String res, String s3) {
        if(res.equals(s3) && i==s1.length() && j==s2.length()) {
            return true;
        }
        boolean ans=false;
        if(i<s1.length()) {
            ans |= backTrackingHelper(s1, i + 1, s2, j, res + s1.charAt(i), s3);
        }
        if(j<s2.length()) {
            ans |= backTrackingHelper(s1, i, s2, j + 1, res + s2.charAt(j), s3);
        }
        return ans;
    }


    /**
     * memo[i,j] 代表的是s1以及s2处理完成的位置
     *
     * 时间复杂度：O(2^{m+n}) m是s1的长度 n是s2的长度。
     * 空间复杂度：O(m∗n)。记忆化数组需要 m*n的空间。
     *
     */
    private boolean isInterleaveRecord(String s1, String s2, String s3) {
        int memo[][] = new int[s1.length()][s2.length()];
        for (int i=0; i<s1.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        return recordHelper(s1, 0, s2, 0, s3, 0, memo);
    }
    private boolean recordHelper(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1;
        }
        boolean ans = false;
        if (s3.charAt(k) == s1.charAt(i) && recordHelper(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && recordHelper(s1, i, s2, j + 1, s3, k + 1, memo)) {
            ans = true;
        }
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }

    /**
     * dp[i][j] 代表s1的[0,i]和s2[0,j]组合符合s3[0,i+j]的字符串
     *
     */
    private boolean isInterleaveDp(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[s1.length()][s2.length()];
    }


    public static void main(String[] args) {
//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc";

//        String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";

//        String s1 = "aabcc", s2 = "dbbca", s3 = "dbbcaaabcc";

        String s1 = "abad" , s2 = "aabc", s3 = "aabcabad";
        Q97_InterleavingString q97_interleavingString = new Q97_InterleavingString();

        boolean ans = q97_interleavingString.isInterleaveBacktracking(s1, s2, s3);
        System.out.println("recursion ans:"+ans);

        boolean ans1 = q97_interleavingString.isInterleaveRecord(s1, s2, s3);
        System.out.println("record ans1:"+ans1);

        boolean ans2 = q97_interleavingString.isInterleaveDp(s1, s2, s3);
        System.out.println("dp ans:"+ans2);
    }


    /**
     * error 因为出现的前后顺序不一定
     *
     * 错误案例：
     * 如：s1 = "abad" , s2 = "aabc", s3 = "aabcabad";---×
     *    s1 = "aabc" , s2 = "abad", s3 = "aabcabad";---√
     *
     * 错误原因
     *  两个方向需要同步进行，哪一个在前无所谓
     */
    private boolean isInterleaveError(String s1, String s2, String s3) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int pos = 0;
        int i;
        for (i=0; i<s3.length(); i++) {
            if (pos == s1.length()) {
                break;
            }
            if (s1.charAt(pos) != s3.charAt(i)) {
                sb.append(s3.charAt(i));
            } else {
                sb2.append(s3.charAt(i));
                pos++;
            }
        }
        System.out.println(sb2.toString()+" "+pos);
        if (pos != s1.length()) {  // 1.s3中的部分字符串凑不齐s2
            return false;
        }
        if (sb.length() == 0 || i != s3.length()) {  // 2.s2的全部元素都在头部 或 部分元素在头部 以s1的部分字符串结尾
            sb.append(s3.substring(i));
        }
        System.out.println(sb.toString());
        if (sb.toString().equals(s2)) {  // 最后一步判断剩余部分是否和 s1 匹配
            return true;
        }
        return false;
    }

}
