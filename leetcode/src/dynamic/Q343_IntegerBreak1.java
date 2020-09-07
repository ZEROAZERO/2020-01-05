package dynamic;

import java.util.Arrays;

public class Q343_IntegerBreak1 {


    public int integerBreak(int n) {
        int[] memory = new int[n+1];
        return breakHelper(n, memory);
    }


    public int breakHelper(int n, int[] memory) {
        if (n == 0) {
            return 1;
        }
        if (memory[n] != 0) {
            return memory[n];
        }

        int ans = 1;
        for (int i=1; i<=n; i++) {
            if (n-i < 0) {
                break;
            }
            ans = Math.max(ans, i * breakHelper(n-i, memory));
        }
        memory[n] = ans;
        return ans;
    }


    /**
     * dp[i] 代表最大乘积，但是dp[j] = dp[i-j]*j不一定是最大值
     * 最大值可能出现在 (i-j)*j
     */
    private int integerBreakDp(int n) {
        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i=3; i<=n; i++) {
            for (int j=1; j<i; j++) {
                // i-j 与 dp[i-j] 大小无法确定
                dp[i] = Math.max(dp[i], Math.max(dp[i-j]*j, j*(i-j)));
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }

    private int breakGreedy(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        // a的含义：n能拆成的3的个数
        int a = (int)Math.floor(n / 3);
        int b = n % 3;

        // n是3的倍数
        if (b == 0) return (int) Math.pow(3, a);
        // n是 3k + 1，例如7。拆成3、3、1。由于有1对结果无法有贡献，所以最后的3、1换成4
        if (b == 1) return (int) Math.pow(3, a - 1) * 4;
        return (int) Math.pow(3, a) * 2;
    }

    public static void main(String[] args) {
        int n = 10;
        Q343_IntegerBreak1 q343_integerBreak1 = new Q343_IntegerBreak1();
        int ans = q343_integerBreak1.integerBreak(n);
        System.out.println(ans);

        int ans1 = q343_integerBreak1.integerBreakDp(n);
        System.out.println(ans1);
    }

}
