package array;

import java.util.HashMap;
import java.util.Map;

public class Q1_AddTwoNumbers {

    /**
     * array may be out-of-order
     * array may exist negative numbers
     *
     * solution:
     *
     *  1.brute force
     */
    private int[] twoSumBruteForce(int[] nums, int target) {
        for (int i=0; i<nums.length-1; i++) {
            for (int j=i+1; j<nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    /**
     * 2.hash table
     */
    private int[] twoSumHashTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i=0; i<nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement)!=i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[]{};
    }

    /**
     * 3.hash table optimization
     */
    private int[] twoSumHashTableOpt(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        Q1_AddTwoNumbers q1_addTwoNumbers = new Q1_AddTwoNumbers();
        int[] ans = q1_addTwoNumbers.twoSumBruteForce(nums, target);
        for (int i=0; i<ans.length; i++) {
            System.out.print(ans[i]+" ");
        }
        System.out.println();
        int[] ans1 = q1_addTwoNumbers.twoSumHashTable(nums, target);
        for (int i=0; i<ans1.length; i++) {
            System.out.print(ans1[i]+" ");
        }
        System.out.println();
        int[] ans2 = q1_addTwoNumbers.twoSumHashTableOpt(nums, target);
        for (int i=0; i<ans2.length; i++) {
            System.out.print(ans2[i]+" ");
        }
        System.out.println();
    }
}
