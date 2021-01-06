package string;

/**
 *
 1108. Defanging an IP Address
 Given a valid (IPv4) IP address, return a defanged version of that IP address.

 A defanged IP address replaces every period "." with "[.]".



 Example 1:

 Input: address = "1.1.1.1"
 Output: "1[.]1[.]1[.]1"
 Example 2:

 Input: address = "255.100.50.0"
 Output: "255[.]100[.]50[.]0"


 Constraints:

 The given address is a valid IPv4 address.
 */
public class Q1108_DefangingAnIPAddress {

    public String defangIPaddr(String address) {
        int len = address.length();
        int idx = len+5;
        char[] chs = new char[len+6];

        for (int i=len-1; i>=0; i--) {
            char ch = address.charAt(i);
            if (ch == '.') {
                chs[idx--] = ']';
                chs[idx--] = '.';
                chs[idx--] = '[';
            } else {
                chs[idx--] = ch;
            }
        }

        return String.valueOf(chs);
    }

    public static void main(String[] args) {
        String address = "1.1.1.1";
        Q1108_DefangingAnIPAddress q = new Q1108_DefangingAnIPAddress();
        String ans = q.defangIPaddr(address);
        System.out.println(ans);
    }
}
