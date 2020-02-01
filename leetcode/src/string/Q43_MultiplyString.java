package string;

public class Q43_MultiplyString {
    /**
     * 1.我
     * 问题：
     *  123*456
     *  没有想到什么怎么处理 123*6
     * @param num1
     * @param num2
     * @return
     */
    private String multiplyCore(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        String res =  "0";
        int carry = 0;
        for (int idx1=len1; idx1>=0; idx1--) {
            StringBuilder tmp = new StringBuilder();
            int n1 = num1.charAt(idx1) - '0';
            for (int i=0; i<len1-idx1; i++) {
                tmp.append(0);
            }
            for (int idx2=len2; idx2>=0 || carry!=0; idx2--) {
                int n2 = idx2<0? 0: num2.charAt(idx2) -'0';
                int tmpRes = n1 * n2 + carry;
                carry = tmpRes / 10;
                tmp.append(tmpRes % 10);
            }
            res = addStrings(res, tmp.reverse().toString());
        }
        /**
         *  123
         * *456
         * ----
         */
        return res;
    }

    public static void main(String[] args) {
        String res1 = new Q43_MultiplyString().multiplyCore("123", "456");
        System.out.println(res1);
        String res = new Q43_MultiplyString().multiply("99", "99");
        System.out.println(res);
        String res2 = new Q43_MultiplyString().multiply("123", "456");
        System.out.println(res2);
        String res3 = new Q43_MultiplyString().multiply("456", "123");
        System.out.println(res3);
        String res4 = new Q43_MultiplyString().multiplyStringOpt("456", "123");
        System.out.println(res4);
    }

    /**
     * 2.大佬
     * 1.123*456
     * 2.循环操作
     *  3*6=18 carry=1, temp=""->"8", res=0 res=res+temp.reverse=8
     *  2*6=12 carry=1, temp="0"->2+1="03", res=8 res=res+temp.reverse=38
     *  ...
     * 计算形式
     *    num1
     *  x num2
     *  ------
     *  result
     */
    private String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";
        int carry = 0;
        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {

            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0 : 个位无许补0,temp="",十位个位补0，temp="0"。。。
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j<0?0:num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        /*ERROR carry数据需要在每一次相乘后成为第一个和
         还有相加可能进位1，而判断carry的情况会导致数据变大
         123
        *456
        ----

        if (carry > 0) {
            return carry+res;
        }*/
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    private String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        int i = num1.length()-1;
        int j = num2.length()-1;
        //carry!=0 保证最后的进位能被加入res
        while(i >= 0 || j >= 0 || carry != 0) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
            i--;
            j--;
        }
        return builder.reverse().toString();
    }

    /**
     * 3.大佬
     * 数组存储计算结果
     */
    private String multiplyStringOpt(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        int[] num = new int[len1 + len2];
        /*
         11
        *11
        ---
         11
     pos(23)
        11
    pos(12)
    --------
        121
         */
        for (int idx1=len1-1; idx1>=0; idx1--) {
            int n1 = num1.charAt(idx1) - '0';
            for (int idx2=len2-1; idx2>=0; idx2--) {
                int n2 = num2.charAt(idx2)-'0';
                int tmpRes = num[idx1+idx2+1] + n1 * n2;
                num[idx1+idx2+1] = tmpRes % 10;
                num[idx1+idx2] += tmpRes / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<len1+len2; i++) {
            //两位 * 两位 = 结果位数 大于等于 三位
            if (i == 0 && num[i] == 0) {
                continue;
            }
            sb.append(num[i]);
        }
        return sb.toString();
    }
}