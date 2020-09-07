package dynamic;

/**
 * 303. Range Sum Query - Immutable
 *
 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 Note:
 You may assume that the array does not change.
 There are many calls to sumRange function.
 */
public class Q303_RangeSumQuery {

    private int[] sum;

    public Q303_RangeSumQuery(int[] nums) {
        sum = new int[nums.length+1];
        for (int i=1; i<=nums.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j+1] - sum[i];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        Q303_RangeSumQuery q303_rangeSumQuery = new Q303_RangeSumQuery(nums);
        int ans = q303_rangeSumQuery.sumRange(0, 2);
        System.out.println(ans);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

