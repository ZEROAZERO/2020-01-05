package dynamic;

/**
 *
 375. Guess Number Higher or Lower II
 We are playing the Guess Game. The game is as follows:

 I pick a number from 1 to n. You have to guess which number I picked.

 Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

 However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

 Example:

 n = 10, I pick 8.

 First round:  You guess 5, I tell you that it's higher. You pay $5.
 Second round: You guess 7, I tell you that it's higher. You pay $7.
 Third round:  You guess 9, I tell you that it's lower. You pay $9.

 Game over. 8 is the number I picked.

 You end up paying $5 + $7 + $9 = $21.
 Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

 https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/solution/cai-shu-zi-da-xiao-ii-by-leetcode/
 */
public class Q375_GuessNumII {

    private int pick = 1;

    private int getMoneyAmount(int n) {

    }
    private int amountHelper(int left, int right) {
        if (left >= right) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int i=left; i<=right; i++) {
            int pay = i + Math.max(amountHelper(left, i-1), amountHelper(i+1, right));
            ans = Math.min(ans, pay);
        }

        return ans;
    }

    private int guess(int n) {
        if (n > pick) {
            return -1;
        } else if (n < pick) {
            return 1;
        } else {
            return 0;
        }
    }
}
