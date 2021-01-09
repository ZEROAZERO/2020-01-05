package string;

import java.util.HashSet;
import java.util.Set;

/**
 *
 3. Longest Substring Without Repeating Characters
 Given a string s, find the length of the longest substring without repeating characters.



 Example 1:

 Input: s = "abcabcbb"
 Output: 3
 Explanation: The answer is "abc", with the length of 3.
 Example 2:

 Input: s = "bbbbb"
 Output: 1
 Explanation: The answer is "b", with the length of 1.
 Example 3:

 Input: s = "pwwkew"
 Output: 3
 Explanation: The answer is "wke", with the length of 3.
 Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 Example 4:

 Input: s = ""
 Output: 0

 */
public class Q3_LongestSubstringWithoutRepeatingCharacters {

    /**
     * 双指针
     1. 初始化窗口指针rx=ry=0, 存储元素set, max记录结果长度
     2. 遍历：
        2.1 若 set存在重复元素 rx++ set移除重复元素位置之前的元素
        2.2 若 set 不存在重复元素 ry++ 并将元素放入set
     3. return max
     */
    public int lengthOfLongestSubstringSet(String s) {
        int len = s.length();
        int rx = 0;
        int ry = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        while (rx<len && ry<len) {
            char ch = s.charAt(ry);
            if (!set.contains(ch)) {
                set.add(ch);
            } else {
                while (s.charAt(rx) != ch) {
                    // remembering to remove the previous element before the duplicated one
                    set.remove(s.charAt((rx)));
                    rx++;
                }
                // a new beginning position
                rx++;
            }
            // making sure the boundary of the result is [rx, ry)
            ry++;
            max = Math.max(max, ry-rx);
        }
        return max;
    }

    public static void main(String[] args) {
        String s = "tmmzuxt";
        Q3_LongestSubstringWithoutRepeatingCharacters q = new Q3_LongestSubstringWithoutRepeatingCharacters();
        int ans = q.lengthOfLongestSubstringSet(s);
        System.out.println(ans);
    }

}
