package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 368. Largest Divisible Subset
 Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

 Si % Sj = 0 or Sj % Si = 0.

 If there are multiple solutions, return any subset is fine.

 Example 1:

 Input: [1,2,3]
 Output: [1,2] (of course, [1,3] will also be ok)
 Example 2:

 Input: [1,2,4,8]
 Output: [1,2,4,8]
 */
public class Q368_LargestDivisibleSubset {

    // 7:09 ~ 8:00
    private List<Integer> largestDivisibleSubset(int[] nums) {

        // all lists
        List<List<Integer>> EDS = new ArrayList<>();
        for (int i=0; i<nums.length; i++) {
            EDS.add(new ArrayList<>());
        }

        Arrays.sort(nums);

        for (int i=0; i<nums.length; i++) {
            List<Integer> maxSubSet = new ArrayList<>();
            // search the previous list of the maximum size
            for (int j=0; j<i; j++) {
                if (nums[i]%nums[j]==0 && maxSubSet.size()<EDS.get(j).size()) {
                    maxSubSet = EDS.get(j);
                }
            }
            EDS.get(i).addAll(maxSubSet);
            // initializing every list of the single number
            EDS.get(i).add(nums[i]);
        }

        // find the end result
        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<nums.length; i++) {
            ans = ans.size()<EDS.get(i).size()? EDS.get(i): ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,4,6,8};
        Q368_LargestDivisibleSubset q368_largestDivisibleSubset = new Q368_LargestDivisibleSubset();
        List<Integer> ans = q368_largestDivisibleSubset.largestDivisibleSubset(nums);
        ans.forEach(val-> System.out.print(val+" "));
    }

}
