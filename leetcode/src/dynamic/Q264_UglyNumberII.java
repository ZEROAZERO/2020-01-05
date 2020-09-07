package dynamic;

import java.util.Arrays;

public class Q264_UglyNumberII {

    /**
     * 保证每个数据都经过三次的选择
     */
    private int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        int[] number = new int[n];
        int idx2 = 0, idx3 = 0, idx5 = 0;
        number[0] = 1;
        for (int i=1; i<n; i++) {
            int ugly = Math.min(number[idx2]*2, Math.min(number[idx3]*3, number[idx5]*5));
            number[i] = ugly;
            if (ugly == number[idx2]*2) {
                idx2++;
            }
            if (ugly == number[idx3]*3) {
                idx3++;
            }
            if (ugly == number[idx5]*5) {
                idx5++;
            }
        }
        System.out.println(Arrays.toString(number));
        return number[n-1];
    }

    public static void main(String[] args) {
        Q264_UglyNumberII q264_uglyNumberII = new Q264_UglyNumberII();
        int ans = q264_uglyNumberII.nthUglyNumber(11);
        System.out.println(ans);
    }
}
