package util;

public class GeneratePrimeNumUtil {

    private static void generate() {
        boolean flag = false;
        long start = System.currentTimeMillis();
        for (int i = 2; i <= 100000; i++) {
            for (int j = 2; j < Math.sqrt(i); j++) {
                if(i % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(i);
            }
            flag = false;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);//76
    }

    public static int[] generatePrimeArr(int cnt) {
        int count = 2;
        int num = 5;
        boolean flag;
        int[] prime = new int[cnt];
        prime[0] = 2;
        prime[1] = 3;
        while (true) {
            flag = false;
            for (int j = 2; j <= Math.sqrt(num); j++) {
                if(num % j == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                prime[count++] = num;
            }
            num++;
            if (count == cnt) {
                break;
            }
        }
        return prime;
    }
    public static void main(String[] args) {
        int[] ints = GeneratePrimeNumUtil.generatePrimeArr(6);
        for (int i=0; i<ints.length; i++)
            System.out.println(ints[i]);
    }
}
