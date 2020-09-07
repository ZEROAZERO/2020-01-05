package backtrackng;

/**
 *
 306. Additive Number
 Additive number is a string whose digits can form additive sequence.

 A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

 Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

 Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.



 Example 1:

 Input: "112358"
 Output: true
 Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 Example 2:

 Input: "199100199"
 Output: true
 Explanation: The additive sequence is: 1, 99, 100, 199.
 1 + 99 = 100, 99 + 100 = 199


 Constraints:

 num consists only of digits '0'-'9'.
 1 <= num.length <= 35
 Follow up:
 How would you handle overflow for very large input integers?
 */
public class Q306_AdditiveNumber {

    /**
     * dfs 遍历完返回的是最后的结果
     * 特殊用例的处理 单0可以单独成数
     *
     * time limited exceeded
     */
    private boolean isAdditiveNumber(String num) {
        return additiveHelper(num, 0, "", 0);
    }

    private boolean additiveHelper(String num, int pos, String str, int part) {
        if (str.length() == (num.length()+part) && part >= 3) {
            if (checkAns(str.substring(0, str.length()-1))) {
                return true;
            }
            return false;
        }

        boolean endAns = false;
        for (int i=pos+1; i<=num.length(); i++) {
            if (num.charAt(pos) == '0' && i!=pos+1) {
                break ;
            }

            String temp = num.substring(pos, i);
            str += temp;
            str += "#";
            part += 1;
            endAns = additiveHelper(num, i, str, part);
            // 达到目标值就结束
            if (endAns) {
                break;
            }
            part -= 1;
            str = str.substring(0, str.length()-temp.length()-1);
        }
        return endAns;
    }

    private boolean checkAns(String ans) {
        System.out.println(ans);
        String[] strs = ans.split("#");
        int pos1 = 0, pos2 = 1;
        String pre = addString(strs[0], strs[1]);

        while (pos1<strs.length-2 && pos2<strs.length-1) {
            if (pre.equals(strs[pos2+1])) {
                pos1 += 1;
                pos2 += 1;
                pre = addString(strs[pos1], strs[pos2]);
                System.out.println(ans+" "+pre+" "+strs[pos1]+" "+strs[pos2]);
            } else {
                return false;
            }
        }
        return true;
    }

    private String addString(String n1, String n2) {
        int len1 = n1.length()-1;
        int len2 = n2.length()-1;
        StringBuilder sb = new StringBuilder();
        int pre = 0;
        while (len1>=0 || len2>=0 || pre!=0) {
            int num1 = len1>=0? n1.charAt(len1)-'0': 0;
            int num2 = len2>=0? n2.charAt(len2)-'0': 0;

            int sum = num1 + num2 + pre;
            pre = sum / 10;
            sb.append(sum % 10);

            len1--;
            len2--;
        }
        return sb.reverse().toString();
    }

    private boolean isAdditiveNumberOpt(String num) {
        int n = num.length();
        if (n < 3) return false;
        for (int j = 1; j < n; j++) {
            for (int k = j + 1; k < n; k++) {
                if (backTrace(num, 0, j, k)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean backTrace(String num, int i, int j, int k) {
        // 0开头的两位数以上的情况
        if (num.charAt(i) == '0' && j - i > 1 || num.charAt(j) == '0' && k - j > 1) {
            return false;
        }
        String strI = num.substring(i, j);
        String strJ = num.substring(j, k);
        String strK = num.substring(k);
        String tempSum = addString(strI, strJ);
        if (tempSum.length() > strK.length() || !tempSum.equals(num.substring(k, k+tempSum.length()))) {
            return false;
        }
        // 上述if不成立，才会进入此if；即tempSum==num.substring(k, k+tempSum)
        // 其中长度满足剩余的长度则return true;
        if (tempSum.length() == strK.length()) {
            return true;
        }
        // 如果长度没有满足，继续进行递归
        return backTrace(num, j, k, k+tempSum.length());
    }

    public static void main(String[] args) {
        //String num = "112358";
        String num = "199100199";
        //String num = "101";
        Q306_AdditiveNumber q306_additiveNumber = new Q306_AdditiveNumber();
        boolean ans = q306_additiveNumber.isAdditiveNumber(num);
        System.out.println(ans);

        boolean ans1 = q306_additiveNumber.isAdditiveNumberOpt(num);
        System.out.println(ans1);
    }

    public String add(String s1, String s2) {
        int n1 = s1.length() - 1;
        int n2 = s2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (n1 >= 0 || n2 >= 0 || carry > 0) {
            int t1 = n1 >= 0 ? (int)(s1.charAt(n1) - '0') : 0;
            int t2 = n2 >= 0 ? (int)(s2.charAt(n2) - '0') : 0;
            int sum = t1 + t2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            n1--;
            n2--;
        }

        return sb.reverse().toString();
    }
}
