package string;

import java.util.*;

/**
 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



 示例:

 输入："23"
 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 和递归解决全排列类似
 */
public class Q17_LetterCombinations {
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    private List<String> output = new ArrayList<>();

    private void backTrack(String combination, String digits) {
        if (digits.length() == 0) {
            output.add(combination);
        } else {
            String digit = digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i=0; i<letters.length(); i++) {
                String letter = letters.substring(i, i + 1);
                backTrack(combination+letter, digits.substring(1));
            }
        }

    }

    private List<String> solve(String digits) {
        if (digits==null || "".equals(digits)) {
            return new ArrayList<>();
        }
        backTrack("", digits);
        return output;
    }

    public static void main(String[] args) {
        List<String> output = new Q17_LetterCombinations().solve("23");
        System.out.println(Arrays.asList(output));
    }
}
