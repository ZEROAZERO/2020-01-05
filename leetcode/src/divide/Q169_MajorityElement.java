package divide;

/**
 *
 169. Majority Element
 Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

 You may assume that the array is non-empty and the majority element always exist in the array.

 Example 1:

 Input: [3,2,3]
 Output: 3
 Example 2:

 Input: [2,2,1,1,1,2,2]
 Output: 2
 */
public class Q169_MajorityElement {

    /**
     * divide
     */
    private int majorityElementDivide(int[] nums) {
        return divideHelper(nums, 0, nums.length-1);
    }
    private int divideHelper(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        int mid = (start + end) /2;
        int left = divideHelper(nums, start, mid);
        int right = divideHelper(nums, mid+1, end);
        if (left == right) {
            return left;
        }

        int leftCount = countingNum(nums, left, start, end);
        int rightCount = countingNum(nums, right, start, end);
        return leftCount>rightCount? left: right;
    }

    private int countingNum(int[] nums, int num, int start, int end) {
        int count = 0;
        for (int i=start; i<=end; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }

    /**
     * 摩尔投票
     * 因为一定存在超过一半的数据，采取相互抵消
     */
    private int majorityElementVote(int[] nums) {
        int num = nums[0];
        int count = 1;
        for (int i=1; i<nums.length; i++) {
            if (num == nums[i]) {
                count++;
            } else if (--count == 0) {
                num = nums[i];
                count = 1;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,1,1,2,2};
        Q169_MajorityElement q169_majorityElement = new Q169_MajorityElement();
        int ans = q169_majorityElement.majorityElementDivide(nums);
        System.out.println("divide ans:"+ans);

        int ans1 = q169_majorityElement.majorityElementVote(nums);
        System.out.println("vote ans1:"+ans1);
    }
}
