package bitmanipulation;

/**
 *
 136. Single Number
 Given a non-empty array of integers, every element appears twice except for one. Find that single one.

 Note:

 Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

 Example 1:

 Input: [2,2,1]
 Output: 1
 Example 2:

 Input: [4,1,2,1,2]
 Output: 4
 */
public class Q136_SingleNumber {

    /**
     * every element appears twice
     *
     * 异或运算同0异1
     * num1 ^ 0 = num1
     * num2 ^ num2 = 0
     */
    private int singleNumber(int[] nums) {
        int single = 0;
        for (int num: nums) {
            single ^= num;
        }
        return single;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1};
        Q136_SingleNumber q136_singleNumber = new Q136_SingleNumber();
        int ans = q136_singleNumber.singleNumber(nums);
        System.out.println("bit manipulation ans:"+ans);
    }

}
