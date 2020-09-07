package backtrackng;

public class Q10_RegularExpMatchng {

    /**
     *
     * 有问题
     * “ab" "ca."
     */
    private boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    public boolean isMatchOneDot(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();

        //判断 text 是否为空，防止越界，如果 text 为空， 表达式直接判为 false, text.charAt(0)就不会执行了
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        return first_match && isMatchOneDot(text.substring(1), pattern.substring(1));
    }

    public static void main(String[] args) {
        Q10_RegularExpMatchng q10_regularExpMatchng = new Q10_RegularExpMatchng();
        boolean res = q10_regularExpMatchng.isMatch("ab", "ca.");
        System.out.println(res);
        boolean res1 = q10_regularExpMatchng.isMatchOneDot("ab", "ca.");
        System.out.println(res1);
    }
}


