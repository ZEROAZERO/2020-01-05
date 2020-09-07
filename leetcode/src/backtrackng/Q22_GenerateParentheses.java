package backtrackng;

import java.util.ArrayList;
import java.util.List;

/**
 *
 22. Generate Parentheses
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 */
public class Q22_GenerateParentheses {
    /*拥有一个最好的机会，要珍惜！*/
    private List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        generateHelper(n, 0, 0, ans, "");
        return ans;
    }

    private void generateHelper(int n, int ln, int rn, List<String> ans, String str) {
        System.out.println(str+" "+ln+" "+rn);
        if (ln + rn == n*2) {
            ans.add(str);
            return ;
        }

        // 同一个位置有多种选择
        if (ln < n && ln >= rn) {
            str += "(";
            generateHelper(n, ln+1, rn, ans, str);
            // 回退是为了此位置能够选择后续的其他数据
            str = str.substring(0, str.length()-1);
        }
        if (rn < n && ln > rn) {
            str += ")";
            generateHelper(n, ln, rn+1, ans, str);
        }
        /*错误
        i的位置选择了数据
        if (ln < n && ln >= rn) {
            str += "(";
            ln++;
            generateHelper(n, ln, rn, ans, str);
        }
        i+1的位置选择了数据
        if (rn < n && ln > rn) {
            str += ")";
            rn++;
            generateHelper(n, ln, rn, ans, str);
        }
        */
    }

    /**
     * 上述可以优化为下列
     */
    private List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList<>();
        generateHelper1(n, 0, 0, ans, "");
        return ans;
    }
    private void generateHelper1(int n, int ln, int rn, List<String> ans, String str) {
        if (ln + rn == n*2) {
            ans.add(str);
            return ;
        }

        // 同一个位置有多种选择
        if (ln < n && ln >= rn) {
            generateHelper1(n,ln+1, rn, ans, str+"(");
        }
        if (rn < n && ln > rn) {
            generateHelper1(n, ln, rn+1, ans, str+")");
        }
    }

    //TODO dp

    public static void main(String[] args) {
        int n = 2;
        Q22_GenerateParentheses q22_generateParentheses = new Q22_GenerateParentheses();
        List<String> ans = q22_generateParentheses.generateParenthesis(n);
        ans.stream().forEach(val-> System.out.println(val));

        List<String> ans1 = q22_generateParentheses.generateParenthesis1(n);
        ans1.stream().forEach(val-> System.out.println(val));
    }
}
