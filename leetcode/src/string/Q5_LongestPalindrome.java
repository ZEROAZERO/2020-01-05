package string;

/**
 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

 示例 1：

 输入: "babad"
 输出: "bab"
 注意: "aba" 也是一个有效答案。
 示例 2：

 输入: "cbbd"
 输出: "bb"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q5_LongestPalindrome {
    //中心扩展发
    private String solve(String str) {
        if (str == null || str.length() == 0)
            return "";
        String resultStr = "";
        for (int i=0; i<str.length(); i++) {
            int len1 = expandAroundCenter(str, i, i);
            int len2 = expandAroundCenter(str, i, i+1);
            int len = len1 > len2? len1: len2;
            if (resultStr.length() < len) {
                /*
                例如：abccba len=6; abcba len=5;
                统一处理
                 1.截取中心（cc c）的左侧长度 (len - 1) / 2
                 2.i + len / 2
                 */
                int start = i - (len - 1) / 2;
                int end = len + i - (len - 1) / 2;//i+len/2;
                resultStr = str.substring(start, end);
            }
            //123456
        }
        return resultStr;
    }

    private int expandAroundCenter(String str, int midl, int midr) {
        while (midl >= 0 && midr < str.length()
                && str.charAt(midl) == str.charAt(midr)) {
            midl--;
            midr++;
        }
        return midr - midl - 1;
    }

    public static void main(String[] args) {
        String str = "eabccbaff";
        System.out.println(new Q5_LongestPalindrome().solve(str));
    }
}
