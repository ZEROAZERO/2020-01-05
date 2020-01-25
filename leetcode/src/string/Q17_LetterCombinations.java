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

        int[] nums = new int[4];
        new Q17_LetterCombinations().permutations(nums, 0,4);

        System.out.println("--");

        String str = "0123";
        new Q17_LetterCombinations().permutationCore(str.toCharArray(), 0, str.length()-1);
    }

    /**
     * 全排列
     * @param nums 结果
     * @param num 开始数字 [0, range-1]
     * @param range 数字个数
     */
    private int[] flags = new int[4];
    private void permutations(int[] nums, int num, int range) {
        if (num >= range) {
            for(int i=0; i<nums.length; i++) {
                if (nums[0] == 0 && i == 0) {
                    continue;
                }
                System.out.print(nums[i]);
            }
            System.out.println();
            return;
        }
        for (int i=0; i<range; i++) {
            if (flags[i] != 1) {
                nums[i] = num;
                flags[i] = 1;
                permutations(nums, num+1, range);
                flags[i] = 0;
            }
        }
    }

    /**
     * 全排列
     * 以第一个元素与其他元素交换，abcd（a与bcd交换）-》bcd（b与cd交换）
     * @param str 字符数组
     * @param begin 起始位置
     * @param end 数组的结束位置
     */
    private void permutationCore(char[] str, int begin, int end) {

        if (begin > end) {
            for(int i=0; i<str.length; i++) {
                if (str[0] - '0' == 0 && i == 0) {
                    continue;
                }
                System.out.print(str[i]);
            }
            System.out.println();
            return;
        }
        for (int i=begin; i<=end; i++) {
            swap(str, begin, i);
            permutationCore(str, begin+1, end);
            swap(str, begin, i);

        }
    }
    private void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

}
