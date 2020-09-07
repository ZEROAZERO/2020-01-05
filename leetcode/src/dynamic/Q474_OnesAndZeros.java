package dynamic;

/**
 * 474. Ones and Zeroes
 *
 In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

 For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

 Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

 Note:

 The given numbers of 0s and 1s will both not exceed 100
 The size of given string array won't exceed 600.


 Example 1:

 Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 Output: 4

 Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”


 Example 2:

 Input: Array = {"10", "0", "1"}, m = 1, n = 1
 Output: 2

 Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

 Input: ["10","0001","111001","1","0"] 3 4
 Output: 3


 note:
    the number of 0's and 1's does not necessarily satisfy m and n
 */
public class Q474_OnesAndZeros {

    /**
     * dfs and backtracking
     */
    private int findMaxForm(String[] strs, int m, int n) {
        if(strs.length == 0 || (m==0 && n==0)){
            return 0;
        }
        return tryFindMaxForm(strs,strs.length-1,m,n);
    }

    // 用m，n 拼出 strs[0,i] 的 最大个数
    private int tryFindMaxForm(String[] strs, int i, int m, int n) {
        if (i < 0) {
            return 0;
        }
        int numsOf0 = 0;
        int numsOf1 = 0;
        String str = strs[i];
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) == '0') {
                numsOf0++;
            } else {
                numsOf1++;
            }
        }
        if (m >= numsOf0 && n >= numsOf1) {
            return Math.max(tryFindMaxForm(strs, i - 1, m, n),
                    1 + tryFindMaxForm(strs, i - 1, m - numsOf0, n - numsOf1));
        } else {
            return tryFindMaxForm(strs, i - 1, m, n);
        }
    }

    /**
     * dp
     */
    private int[] countZeroAndOne(String str) {
        int[] cnt = new int[2];
        for (char c : str.toCharArray()) {
            cnt[c - '0']++;
        }
        return cnt;
    }
    private int findMaxFormDp(String[] strs, int m, int n) {
        int len = strs.length;
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        for (int i = 1; i <= len; i++) {
            // 注意：有一位偏移
            int[] cnt = countZeroAndOne(strs[i - 1]);
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    // 先把上一行抄下来
                    dp[i][j][k] = dp[i - 1][j][k];

                    int zeros = cnt[0];
                    int ones = cnt[1];

                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[len][m][n];
    }

    /**
     * dp optimization
     */
    private int[] calcZeroAndOne(String str) {
        int[] res = new int[2];
        for (char c : str.toCharArray()) {
            res[c - '0']++;
        }
        return res;
    }
    private int findMaxFormDpOpt(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        for (String s : strs) {
            int[] zeroAndOne = calcZeroAndOne(s);
            int zeros = zeroAndOne[0];
            int ones = zeroAndOne[1];
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
                }
            }
        }
        return dp[m][n];
    }



    public static void main(String[] args) {
        //"10","0001","111001","1","0" 3 4
        //"10","11" 1 2
        String[] Array = new String[]{"10","11"};
        int m = 1, n = 2;
        Q474_OnesAndZeros q474_onesAndZeros = new Q474_OnesAndZeros();
        int ans = q474_onesAndZeros.findMaxFormDfsError(Array, m, n);
        System.out.println("dfs error:"+ans);
        int ans1 = q474_onesAndZeros.findMaxForm(Array, m, n);
        System.out.println("dfs:"+ans1);
    }

    /**
     * dfs error 满足m，n
     */
    private int findMaxFormDfsError(String[] strs, int m, int n) {
        return dfsHelper(strs, m, n, "", new boolean[strs.length]);
    }
    private int dfsHelper(String[] strs, int m, int n,
                          String s, boolean[] flags) {
        if ((!s.startsWith("0")) && s.length() > m+n) {
            return 0;
        }
        if (s.length() == m+n && matchNum(s, m, n)) {
            int cnt = 0;
            for (int i=0; i<flags.length; i++) {
                if (flags[i]) {
                    cnt++;
                }
            }
            return cnt;
        }
        int max = 0;
        for (int i=0; i<strs.length; i++) {
            int res = 0;
            if (!flags[i]) {
                s += strs[i];
                flags[i] = true;
                res = dfsHelper(strs, m, n, s, flags);
                flags[i] = false;
                s = s.substring(0, s.length()-strs[i].length());
                if (res != 0) {
                    max = max<res? res:max;
                }
            }
        }
        return max;
    }
    private boolean matchNum(String s, int m, int n) {
        int cnt0 = 0;
        int cnt1 = 0;
        for (int i=0; i<s.length(); i++) {
            int num = s.charAt(i)-'0';
            if (num == 0) {
                cnt0++;
            } else if (num == 1) {
                cnt1++;
            }
        }
        return m==cnt0 && n==cnt1;
    }

}
