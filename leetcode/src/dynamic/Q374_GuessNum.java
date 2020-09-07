package dynamic;

/**
 *
 374. Guess Number Higher or Lower
 We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number is higher or lower.

 You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

 -1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
 Example :

 Input: n = 10, pick = 6
 Output: 6
 */
public class Q374_GuessNum {
    /**
     * tips: 二分法（left+right）可能造成整型溢出
     *
     * int mid = left + (right - left) / 2;
     * (left+right)>>>1
     */

    private int pick = 10;

    /**
     * StackOverFlow
     */
    private int guessNumber(int n) {
        return numberHelper(n, 1, n);
    }
    private int numberHelper(int n, int i, int j) {
        if (i > j) return -1;
        int mid = (i+j)>>>1;
        int guessNum = guess(mid);
        System.out.println(i+" "+j+" "+mid);
        if (guessNum == 0) return mid;
        else if (guessNum == -1) return numberHelper(n, i, mid-1);
        return numberHelper(n, mid+1, j);
    }

    /**
     * my number > your number that I guess
     */
    private int guess(int n) {
        if (n > pick) {
            return -1;
        } else if (n < pick) {
            return 1;
        } else {
            return 0;
        }
    }

    private int guessNumberDivided(int n) {
        int start = 1;
        int end = n;
        while (start <= end) {
            int mid = (start + end)>>>1;
            int guessNum = guess(mid);
            if (guessNum == 1) {
                mid++;
                start = mid;
            } else if (guessNum == -1) {
                mid--;
                end = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 10;

        Q374_GuessNum q374_guessNum1 = new Q374_GuessNum();
        int ans = q374_guessNum1.guessNumber(n);
        System.out.println("divided recursion ans:"+ans);

        int ans1 = q374_guessNum1.guessNumberDivided(n);
        System.out.println("divided ans1:"+ans1);
    }
}
