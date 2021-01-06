package string;

import java.util.Stack;

/**
 *
 1003. Check If Word Is Valid After Substitutions
 Given a string s, determine if it is valid.

 A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the following operation any number of times:

 Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft + tright. Note that tleft and tright may be empty.
 Return true if s is a valid string, otherwise, return false.



 Example 1:

 Input: s = "aabcbc"
 Output: true
 Explanation:
 "" -> "abc" -> "aabcbc"
 Thus, "aabcbc" is valid.
 Example 2:

 Input: s = "abcabcababcc"
 Output: true
 Explanation:
 "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 Thus, "abcabcababcc" is valid.
 Example 3:

 Input: s = "abccba"
 Output: false
 Explanation: It is impossible to get "abccba" using the operation.
 Example 4:

 Input: s = "cababc"
 Output: false
 Explanation: It is impossible to get "cababc" using the operation.


 Constraints:

 1 <= s.length <= 2 * 104
 s consists of letters 'a', 'b', and 'c'
 */
public class Q1003_CheckIfWordIsValidAfterSubstitutions {

    /**
     * 模拟栈
     */
    public boolean isValid(String s) {
        int sLen = s.length();
        // 存储元素从1开始
        char[] stack = new char[sLen+1];
        int size = 0;
        for (int i=0; i<sLen; i++) {
            stack[++size] = s.charAt(i);
            if (size >= 3 && stack[size]=='c' && stack[size-1]=='b' && stack[size-2]=='a') {
                size -= 3;
            }
        }
        return size == 0;
    }

    public static void main(String[] args) {
        String s = "abcaabcbc";
        Q1003_CheckIfWordIsValidAfterSubstitutions q = new Q1003_CheckIfWordIsValidAfterSubstitutions();
        boolean ans = q.isValid(s);
        System.out.println(ans);
    }
}
