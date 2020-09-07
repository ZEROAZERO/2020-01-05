package divide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 241. Different Ways to Add Parentheses
 Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

 Example 1:

 Input: "2-1-1"
 Output: [0, 2]
 Explanation:
 ((2-1)-1) = 0
 (2-(1-1)) = 2
 Example 2:

 Input: "2*3-4*5"
 Output: [-34, -14, -10, -10, 10]
 Explanation:
 (2*(3-(4*5))) = -34
 ((2*3)-(4*5)) = -14
 ((2*(3-4))*5) = -10
 (2*((3-4)*5)) = -10
 (((2*3)-4)*5) = 10
 */
public class Q241_DifferentWaystoAddParentheses {

    private List<Integer> diffWaysToCompute(String input) {
        List<Integer> ans = new ArrayList<>();
        ans = computeHelper(input);
        return ans;
    }
    private List<Integer> computeHelper(String input) {
        if (input.length() == 1) {
            List<Integer> temp = new ArrayList<>();
            temp.add(Integer.valueOf(input));
            return temp;
        }

        List<Integer> res = new ArrayList<>();
        for (int i=0; i<input.length(); i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-'
                    || input.charAt(i) == '*') {
                // left包含的结果是多个可能出现的结果
                List<Integer> left = computeHelper(input.substring(0, i));
                List<Integer> right = computeHelper(input.substring(i + 1));
                System.out.println(i+" "+input+" "+ Arrays.toString(left.toArray())+" "+Arrays.toString(right.toArray()));

                for (int l : left) {
                    for (int r : right) {
                        if (input.charAt(i) == '+') {
                            res.add(l+r);
                        } else if (input.charAt(i) == '-') {
                            res.add(l-r);
                        } else {
                            res.add(l-r);
                        }
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String input = "2*3-4*5";
        Q241_DifferentWaystoAddParentheses q241_differentWaystoAddParentheses
                = new Q241_DifferentWaystoAddParentheses();
        List<Integer> ans = q241_differentWaystoAddParentheses.diffWaysToCompute(input);
        ans.forEach(val-> System.out.println(val));
    }
}
