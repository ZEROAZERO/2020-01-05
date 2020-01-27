package string;

/**
 * 415.字符串相加
 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。

 注意：

 num1 和num2 的长度都小于 5100.
 num1 和num2 都只包含数字 0-9.
 num1 和num2 都不包含任何前导零。
 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/add-strings
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q415_AddStrings {
    /**
     * 大佬
     * 1.相同位置，剩余部分统一处理，其余位置补零
     *   2345
     * + 0001
     * ------
     * + 2346
     *
     * tips：脑袋瓦特了，字符串从左到右标起 012345，
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder("");
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while(i >= 0 || j >= 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp % 10);
            i--; j--;
        }
        if(carry == 1) res.append(1);
        return res.reverse().toString();
    }

    /**
     * 我
     * 1.判断长度计算相同位置数值
     * 2.计算剩余部分
     * 3.判断是否进位
     * 4.反转得出结果
     * tips：清理输出 leetcode效率更高
     * @param num1
     * @param num2
     * @return
     */
    public String addStringsCore(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int len1 = num1.length();
        int len2 = num2.length();
        String n1 = num1, n2 = num2;
        if (len1 != len2) {
            n1 = len1 > len2? num1: num2;
            n2 = len1 < len2? num1: num2;
        }
        System.out.println(n1+ " "+ n2);
        int carry = 0;
        /*ERROR 这样位置接不上
        for (int i=n2.length()-1; i>=0; i--) {
            int tmp = n2.charAt(i) + n1.charAt(i) + carry;
            carry = tmp / 10;
            res.append(tmp%10);
        }
        */
        int pos1=n1.length()-1, pos2=n2.length()-1;
        while (pos1>=0 && pos2>=0) {
            int tmp = n1.charAt(pos1) + n2.charAt(pos2) + carry - '0' - '0';
            System.out.println(n1.charAt(pos1) +" "+ n2.charAt(pos2));
            carry = tmp / 10;
            res.append(tmp%10);
            pos1--;
            pos2--;
        }
        /* ERROR 1 + 9 位置错误
        if (carry == 1) {
            res.append(carry);
        }
        */
        // 较长字符串剩余部分
        for (int idx=pos1; idx>=0; idx--) {
            int tmp = n1.charAt(idx) + carry - '0';
            carry = tmp / 10;
            res.append(tmp % 10);
            System.out.println(tmp+" " + carry);
        }
        // 是否有进位
        if (carry == 1) {
            res.append(carry);
        }
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String res = new Q415_AddStrings().addStrings("23", "1");
        System.out.println(res);
        String res1 = new Q415_AddStrings().addStringsCore("9", "789");
        System.out.println(res1);
        //1+9 9+99 9+789
    }
    /**
     * 你很菜，
     * 有道无术，术尚可学，有术无道，止于术。
     * tips:先想测试用例，再代码。
     */
}
