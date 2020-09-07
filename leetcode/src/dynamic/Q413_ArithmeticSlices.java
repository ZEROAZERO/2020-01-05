package dynamic;

public class Q413_ArithmeticSlices {

    private int numberOfArithmeticSlices1(int[] A) {

        int count = 0;

        // 1.计算差值
        for (int s=0; s<A.length-2; s++) {
            int d = A[s+1] - A[s];
            // 2.限定子数组数量
            for (int len=s+2; len<A.length; len++) {
                // 3.判断是否组成等差
                int i = s+1;
                for (; i<=len; i++) {
                    if (A[i] - A[i-1] != d) {
                        break;
                    }
                }
                if (i > len) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * 思路
     * 1 2 3 4 5 6
     * 计算 d = 2 - 1 = 1
     * 比较紧随其后元素的差值
     * 即 3 - 2 == d? count++: break; 若真代表又多了一个子集合
     * 依次类推
     */
    private int numberOfArithmeticSlices2(int[] A) {
        int count = 0;
        for (int s=0; s<A.length-2; s++) {
            int d = A[s+1] - A[s];
            for (int len=s+2; len<A.length; len++) {
                if (A[len] - A[len-1] == d) {
                    count++;
                } else {
                    break;
                }
            }
        }
        return count;
    }

    private int sum = 0;
    private int numberOfArithmeticSlices3(int[] A) {
        sliceHelper(A, A.length-1);
        return sum;
    }
    private int sliceHelper(int[] A, int pos) {
        if (pos < 2) {
            return 0;
        }

        int ap = 0;
        if (A[pos] - A[pos-1] == A[pos-1] - A[pos-2]) {
            ap = 1 + sliceHelper(A, pos-1);
            sum += ap;
        } else {
            sliceHelper(A, pos-1);
        }
        return ap;
    }

    /**
     * 1 3 5 7 8 9
     * 0 0 1 2 0 1
     * 连续的
     * 以 7 为例
     *  1 3 5 算1
     *  到达7时，缩短1开头 3 5 7算1
     *      1 3 5 7 算1
     *
     *
     * 这里不好思考的地方就是 为什么在(A[i]-A[i-1])==(A[i-1]-A[i-2])时，dp[i] = 1 + dp[i-1]；
     * 因为，如果A[i]满足等差数列的条件，那么以A[i]结尾的等差数列的最大长度就比以A[i-1]结尾的最大长度要多1位；
     * 从而以A[i]结尾的子等差序>列的头指针位置就比以A[i-1]结尾的子等差序列的头指针位置的选择多一位，
     * 这样也就dp[i] = 1 + dp[i-1]；
     */
    private int numberOfArithmeticSlicesDp(int[] A) {
        int[] dp = new int[A.length+1];

        int sum = 0;
        for (int i=2; i<A.length; i++) {
            if (A[i]-A[i-1] == A[i-1]-A[i-2]) {
                dp[i] = dp[i-1] + 1;
                sum += dp[i];
            }
        }

        return sum;
    }



    public static void main(String[] args) {
        int[] A = new int[]{1,3,5,7,8,9};
        Q413_ArithmeticSlices q413_arithmeticSlices = new Q413_ArithmeticSlices();
        int ans = q413_arithmeticSlices.numberOfArithmeticSlices1(A);
        System.out.println("for3 ans:"+ans);

        int ans1 = q413_arithmeticSlices.numberOfArithmeticSlices2(A);
        System.out.println("for2 ans1:"+ans1);

        int ans3 = q413_arithmeticSlices.numberOfArithmeticSlices3(A);
        System.out.println("recursion ans3:"+ans3);

        int ans4 = q413_arithmeticSlices.numberOfArithmeticSlicesDp(A);
        System.out.println("dp ans4:"+ans4);

    }

}
