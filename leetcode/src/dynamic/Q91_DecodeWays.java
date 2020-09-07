package dynamic;

import java.util.Arrays;

/**
 * 91.Decode Ways  解码方法
 *
 A message containing letters from A-Z is being encoded to numbers using the following mapping:

 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26
 Given a non-empty string containing only digits, determine the total number of ways to decode it.

 Example 1:

 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 Example 2:

 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class Q91_DecodeWays {
    /**
     * recursion means
     */
    private int numDecodingsSelf1(String s) {
        return selfCore1(0, s);
    }

    private int selfCore1(int idx, String s) {
        if (idx == s.length()) {
            return 1;
        }
        if (s.charAt(idx) == '0') {
            return 0;
        }
        int ans1 = selfCore1(idx+1, s);
        int ans2 = 0;
        if (idx+1 < s.length()) {
            int one = s.charAt(idx+1) - '0';
            int ten = (s.charAt(idx) - '0') * 10;
            int num = ten + one;
            //System.out.println(idx+" "+num);
            if (num>=10 && num<=26) {
                ans2 = selfCore1(idx+2, s);
            }
        }
        return ans1 + ans2;
    }

    /**
     * timeout
     */
    private int numDecodingsSelf2(String s) {
        return selfCore2(s);
    }
    private int selfCore2(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        if ("".equals(s) || s.length() == 1) {
            return 1;
        }
        int ans = 0;
        for (int i=0; i<=1 && i<s.length(); i++) {
            if (i == 1) {
                int ten = (s.charAt(i-1) - '0') * 10;
                int one = s.charAt(i) - '0';
                if (ten+one < 10 || ten+one > 26) {
                    break;
                }
            }
            ans += selfCore2(s.substring(i+1)); //substring(length)==""
        }
        return ans;
    }

    /**
     * dp
     */
    private int numDecodingsDp1(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2; i<=s.length(); i++) {

            int ans1 = 0;
            if (s.charAt(i-1) != '0') { //if single character
                ans1 = dp[i-1];
            }

            int ans2 = 0;
            int ten = (s.charAt(i-2) - '0') * 10;
            int one = s.charAt(i-1) - '0';
            int num = ten + one;
            if (num>=10 && num<=26) {
                ans2 = dp[i-2];
            }

            dp[i] = ans1 + ans2;
        }
        System.out.println(Arrays.toString(dp));
        return dp[len];
    }

    private int numDecodingsDp1Opt(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        int pre = 1;
        int cur = 1;
        for (int i=2; i<=s.length(); i++) {

            int ans1 = 0;
            if (s.charAt(i-1) != '0') { //if single character
                ans1 = cur;
            }

            int ans2 = 0;
            int ten = (s.charAt(i-2) - '0') * 10;
            int one = s.charAt(i-1) - '0';
            int num = ten + one;
            if (num>=10 && num<=26) {
                ans2 = pre;
            }

            pre = cur;
            cur = ans1 + ans2;
        }
        return cur;
    }


    /**
     * dp
     */
    private int numDecodingsDp2(String s) {
        if (s.startsWith("0")) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i=2; i<=s.length(); i++) {
            if (s.charAt(i-1) == '0') { //合并译码
                if (s.charAt(i-1)=='1' || s.charAt(i-2)=='2') {
                    dp[i] = dp[i-2];
                } else { //2306
                    return 0;
                }
            } else if (s.charAt(i-2)=='1') {
                // dp[i-1] 分开译码
                // dp[i-2] 合并译码
                dp[i] = dp[i-1] + dp[i-2];
            } else if (s.charAt(i-2)=='2' && s.charAt(i-1)>='1' && s.charAt(i-1)<='6') {
                dp[i] = dp[i-1] + dp[i-2];
            } else { //2276 <27>
                dp[i] = dp[i-1];
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[len];
    }


    public static void main(String[] args) {
        //12120 27 2026 2276 2206
        String num = "12120";
        Q91_DecodeWays q91_decodeWays = new Q91_DecodeWays();
        int ans = q91_decodeWays.numDecodingsSelf1(num);
        System.out.println(ans);
        int ans1 = q91_decodeWays.numDecodingsSelf2(num);
        System.out.println(ans1);
        int ans2 = q91_decodeWays.numDecodingsDp1(num);
        System.out.println(ans2);
        int ans4 = q91_decodeWays.numDecodingsDp1Opt(num);
        System.out.println(ans4);
        int ans3 = q91_decodeWays.numDecodingsDp2(num);
        System.out.println(ans3);
    }

}
