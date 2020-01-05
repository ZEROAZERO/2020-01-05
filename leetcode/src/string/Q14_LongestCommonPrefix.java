package string;

/**
 编写一个函数来查找字符串数组中的最长公共前缀。

 如果不存在公共前缀，返回空字符串 ""。

 示例 1:

 输入: ["flower","flow","flight"]
 输出: "fl"
 示例 2:

 输入: ["dog","racecar","car"]
 输出: ""
 解释: 输入不存在公共前缀。

 来源：力扣（LeetCode）
 链接：https://leetcode-cn.com/problems/longest-common-prefix
 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q14_LongestCommonPrefix {
    /**
     * 以长度最小的串为终止
     * 1.选取第一个字符串为比较串
     * 2.与其他串 比较 长度？ 位置字符与比较字符匹配？
     * @param strArr
     * @return
     */
    private String solve(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        for (int i=0; i<strArr[0].length(); i++) {
            char ch = strArr[0].charAt(i);
            for (int j=1; j<strArr.length; j++) {
                if (i >= strArr[j].length() || ch != strArr[j].charAt(i)) {
                    return strArr[0].substring(0, i);
                }
            }
        }
        return strArr[0];
    }

    public static void main(String[] args) {
        String[] strArr = {"abc", "ab", ""};
        System.out.println(new Q14_LongestCommonPrefix().solve(strArr));
    }
}
