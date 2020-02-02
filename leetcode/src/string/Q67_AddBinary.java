package string;

/**
 * 67.二进制相加
 给定两个二进制字符串，返回他们的和（用二进制表示）。

 输入为非空字符串且只包含数字 1 和 0。

 示例 1:

 输入: a = "11", b = "1"
 输出: "100"
 示例 2:

 输入: a = "1010", b = "1011"
 输出: "10101"

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/add-binary
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q67_AddBinary {
    /**
     * 0+0
     * 0+1
     * 11+11
     * 1+1
     */
    private String addBinary(String a, String b) {
        if ("0".equals(a)) {
            return b;
        }
        if ("0".equals(b)) {
            return a;
        }
        int len1 = a.length() - 1;
        int len2 = b.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (len1>=0 || len2>=0 || carry !=0) {
            int n1 = len1<0? 0: a.charAt(len1) - '0';
            int n2 = len2<0? 0: b.charAt(len2) - '0';
            int tmpRes = n1 + n2 + carry;
            sb.append(tmpRes % 2);
            carry = tmpRes / 2;
            len1--; len2--;
        }
        return sb.reverse().toString();
    }

    /**
     * 数组
     */
    private String addBinaryArr(String a, String b) {
        if ("0".equals(a)) {
            return b;
        }
        if ("0".equals(b)) {
            return a;
        }
        int len1 = a.length();
        int len2 = b.length();
        int len = len1>len2? len1: len2;
        int[] res = new int[len+1];
        StringBuilder sb = new StringBuilder();
        int idx1 = len1-1;
        int idx2 = len2-1;
        while (idx1>=0 || idx2>=0) {
            int n1 = idx1<0? 0: a.charAt(idx1) - '0';
            int n2 = idx2<0? 0: b.charAt(idx2) - '0';
            int tmpRes = res[len] + n1 + n2;
            res[len] = tmpRes % 2;
            res[len-1] = tmpRes / 2;
            idx1--; idx2--;len--;
        }
        for (int i=0; i<res.length; i++) {
            if (i==0 && res[i]==0) {
                continue;
            }
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String res = new Q67_AddBinary().addBinary("1010", "1011");
        System.out.println(res);
        String res1 = new Q67_AddBinary().addBinaryArr("11", "1");
        System.out.println(res1);
    }
}
