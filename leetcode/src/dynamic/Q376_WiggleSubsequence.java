package dynamic;

/**
 *
 376. Wiggle Subsequence
 A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence with fewer than two elements is trivially a wiggle sequence.

 For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

 Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

 Example 1:

 Input: [1,7,4,9,2,5]
 Output: 6
 Explanation: The entire sequence is a wiggle sequence.
 Example 2:

 Input: [1,17,5,10,13,15,10,5,16,8]
 Output: 7
 Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
 Example 3:

 Input: [1,2,3,4,5,6,7,8,9]
 Output: 2
 Follow up:
 Can you do it in O(n) time?
 */
public class Q376_WiggleSubsequence {

    /**
     * 超时
     */
    private int wiggleMaxLengthBacktracking(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }
        if (nums.length == 2) {
            if (nums[1] != nums[0]) {
                return nums.length;
            } else {
                return 1;
            }

        }
        int ans = Math.max(backtrackingHelper(nums, true, 0), backtrackingHelper(nums, false, 0));
        return ans+1;
    }
    /**
     * 设置前缀，避免复杂处理
     * 前缀 数据
     * +    ...
     * -    ...
     */
    private int backtrackingHelper(int[] nums, boolean isUp, int pos) {
        if (pos == nums.length-1) {
            return 0;
        }

        int ans = 0;
        for (int i=pos+1; i<nums.length; i++) {
            if ((isUp && nums[i]>nums[pos]) || (!isUp && nums[i]<nums[pos])) {
                ans = Math.max(ans, backtrackingHelper(nums, !isUp, i)+1);
            }
        }
        return ans;
    }

    /**
     * dp 表示i结尾的最长摆动长度
     */
    private int wiggleMaxLengthDp(int[] nums) {
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];

        for (int i=1; i<nums.length; i++) {
            for (int j=0; j<i; j++) {
                if (nums[j] < nums[i]) {
                    up[i] = Math.max(up[i], down[j]+1);
                } else if (nums[j] > nums[i]) {
                    down[i] = Math.max(down[i], up[j]+1);
                }
            }
        }

        return Math.max(down[nums.length-1], up[nums.length-1])+1;
    }


    /**
     * dp
     * up表示i结尾的位置上升元素的个数
     * down表示i结尾的位置下降元素的个数
     */
    private int wiggleMaxLengthDpOpt(int[] nums) {
        // 标记上升 和 下降元素
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = 1;
        down[0] = 1;

        for (int i=1; i<nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                up[i] = down[i-1]+1;
                down[i] = down[i-1];
            } else if (nums[i] < nums[i-1]) {
                down[i] = up[i-1] + 1;
                up[i] = up[i-1];
            } else {
                down[i] = down[i-1];
                up[i] = up[i-1];
            }
        }

        return Math.max(up[nums.length-1], down[nums.length-1]);
    }

    private int wiggleMaxLengthDpOpt1(int[] nums) {
        int down = 1;
        int up = 1;

        for (int i=1; i<nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                up = down+1;
            } else if (nums[i] < nums[i-1]) {
                down = up+1;
            }
        }

        return Math.max(down, up);
    }

    /**
     * 差值交错
     */
    private int wiggleMaxLengthGreedy(int[] nums) {
        int preDiff = nums[1] - nums[0];
        int count = preDiff==0? 1:2;

        for (int i=2; i<nums.length; i++) {
            int diff = nums[i] - nums[i - 1];
            // preDiff _=0 处理开始数据
            if ((diff>0 && preDiff<=0) || (diff<0 && preDiff>=0)) {
                count++;
                preDiff = diff;
            }
        }

        return count;
    }



    public static void main(String[] args) {
        // 26,13
        // 26

        // 1,7,4,9,2,5
        // 0,0,0
        // 1,17,5,10,13,15,10,5,16,8
        int[] nums = new int[]{1,17,5,10,13,15,10,5,16,8};
        Q376_WiggleSubsequence q376_wiggleSubsequence = new Q376_WiggleSubsequence();

        int ans = q376_wiggleSubsequence.wiggleMaxLengthBacktracking(nums);
        System.out.println("backtracking ans:"+ans);

        int ans1 = q376_wiggleSubsequence.wiggleMaxLengthDp(nums);
        System.out.println("dp ans1:"+ans1);

        int ans2 = q376_wiggleSubsequence.wiggleMaxLengthDpOpt(nums);
        System.out.println("dp opt ans2:"+ans2);

        int ans3 = q376_wiggleSubsequence.wiggleMaxLengthDpOpt1(nums);
        System.out.println("dp opt1 ans3:"+ans3);

        int ans4 = q376_wiggleSubsequence.wiggleMaxLengthGreedy(nums);
        System.out.println("greddy ans4:"+ans4);
    }

}
