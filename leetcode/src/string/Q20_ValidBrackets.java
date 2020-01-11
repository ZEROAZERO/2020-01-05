package string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20 有效的括号
 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

 有效字符串需满足：

 左括号必须用相同类型的右括号闭合。
 左括号必须以正确的顺序闭合。
 注意空字符串可被认为是有效字符串。

 示例 1:

 输入: "()"
 输出: true
 示例 2:

 输入: "()[]{}"
 输出: true
 示例 3:

 输入: "(]"
 输出: false
 示例 4:

 输入: "([)]"
 输出: false
 示例 5:

 输入: "{[]}"
 输出: true

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/valid-parentheses
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q20_ValidBrackets {

    /**
     * 1.测试用例
     *  {} is true
     *  {)(} is false
     *  ( is false
     *  "" is true
     *  {(}) is false
     * @param str
     * @return
     */
    private boolean solve(String str) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');
        for (int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if (!map.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (!stack.isEmpty() && map.get(ch) == stack.peek()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        boolean result = new Q20_ValidBrackets().solve("");
        System.out.println(result);
    }
    /**
     * 听说敏捷，了解一下。
     *
     * 有道无术，术尚可学，有术无道，止于术
     */
}
