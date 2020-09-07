package dynamic;

import java.util.Arrays;

/**
 * 673. Number of Longest Increasing Subsequence
 *
 Given an unsorted array of integers, find the number of longest increasing subsequence.

 Example 1:
 Input: [1,3,5,4,7]
 Output: 2
 Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 Example 2:
 Input: [2,2,2,2,2]
 Output: 5
 Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */
public class Q673_NumberOfLongestIncreasingSubsequence {

    /**
     * dynamic programming
     * 若 A[i] < A[j], lengths[i] > lengths[j], 那么数量以及长度以i处数据为准
     *              lengths[i] == lengths[j], 由于取长度较长的，那么数量以及长度以i处数据为准
     *              lengths[i]+1 == lengths[j] 那么i处附加A[j]后，与j处一致，所以数量取两处之和，长度取i处加1
     *
     *              lengths[i]+1 < lengths[j] 则以j处最长数据为准
     */
    private int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        if (N <= 1) {
            return N;
        }
        int[] lengths = new int[N]; //lengths[i] = length of longest ending in nums[i]
        int[] counts = new int[N]; //count[i] = number of longest ending in nums[i]
        Arrays.fill(counts, 1);

        /**
         * i.   nums[j] > nums[i]是必要条件，否则跳到下一个
         *                 ii.  第一种情况是length[j]<length[i]， 那么需要更新为最新的length[j] = length[i] + 1;count[j] = count[i]
         *                 iii. 第二种情况是length[j]==length[i]，那么需要更新为最新的length[j] = length[i] + 1;count[j] = count[i]
         *                 iv.  第三种情况是length[j]==length[i] + 1， 那么需要更新为最新的count[j] = count[i] + count[j]
         *                 v.   第四种情况是length[j]>length[i] + 1，表示nums[0],nums[1],...nums[i],nums[j]序列最长序列还没有上一个i-1的大，所以无需更新
         *     3. 然后再遍历动态状态变量里面的每个状态，求取其个数
         *
         */
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < j; i++) {
                if (nums[i] < nums[j]) {
                    if (lengths[i] >= lengths[j]) {
                        lengths[j] = lengths[i] + 1;
                        counts[j] = counts[i];
                    } else if (lengths[i] + 1 == lengths[j]) {
                        counts[j] += counts[i];
                    }
                }
            }
            System.out.println("lengths["+j+"]:"+Arrays.toString(lengths));
            System.out.println("counts["+j+"]:"+Arrays.toString(counts));
        }

        int longest = 0, ans = 0;
        for (int length : lengths) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < N; ++i) {
            if (lengths[i] == longest) {
                ans += counts[i];
            }
        }
        return ans;
    }

    private int findNumberOfLISHand(int[] nums) {
        int len = nums.length;
        int[] lengths = new int[len];  // 记录某处最长长度
        int[] counts = new int[len];  // 记录某处的最长的数量
        Arrays.fill(counts, 1);
        Arrays.fill(lengths, 1);

        for (int j=0; j<len; j++) {
            for (int i=0; i<j; i++) {
                if (nums[i] < nums[j]) {  // j元素可以附加在i元素后
                    if (lengths[i] > lengths[j]) {  // 以i为准
                        lengths[j] = lengths[i] + 1;
                        counts[j] = counts[i];
                    } else if (lengths[i] == lengths[j]) {  // 以i为准
                        lengths[j] = lengths[i] + 1;
                        counts[j] = counts[i];
                    } else if (lengths[i] + 1 == lengths[j]) {  // i和j处的数量能够合并
                        counts[j] += counts[i];
                    }
                }
            }
        }
        /*int ansPos = -1;错误
        for (int i=0; i<len; i++) {  // {2,2,2,2,2}这样查找会出错
            if (ansLen < lengths[i]) {
                ansLen = lengths[i];
                ansPos = i;
            }
        }*/
        int ansLen = Integer.MIN_VALUE;
        int ans = 0;
        for (int i=0; i<len; i++) {
            ansLen = Math.max(ansLen, lengths[i]);
        }
        for (int i=0; i<len; i++) {
            if (ansLen == lengths[i]) {
                ans += counts[i];
            }
        }
        System.out.println(Arrays.toString(lengths));
        System.out.println(Arrays.toString(counts));
        return ans;
    }

    //TODO 线段树


    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 4, 7};
        Q673_NumberOfLongestIncreasingSubsequence q673_numberOfLongestIncreasingSubsequence
                = new Q673_NumberOfLongestIncreasingSubsequence();
        int ans = q673_numberOfLongestIncreasingSubsequence.findNumberOfLIS(nums);
        System.out.println("dp:"+ans);

        int ans1 = q673_numberOfLongestIncreasingSubsequence.findNumberOfLISHand(nums);
        System.out.println("dp hand ans1:"+ans1);
    }
}
