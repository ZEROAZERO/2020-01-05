package backtrackng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 47. Permutations II
 Given a collection of numbers that might contain duplicates, return all possible unique permutations.

 Example:

 Input: [1,1,2]
 Output:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 */
public class Q47_PermutationsII {

    private List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        if (nums.length == 0) {
            return ans;
        }

        Arrays.sort(nums);
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        unniqueHelper(nums, used, path, ans);
        return ans;
    }

    private void unniqueHelper(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return ;
        }

        for (int i=0; i<nums.length; i++) {
            if (used[i]) {
                continue ;
            }
            /*
            保证重复的数据不会被多次记录
            如：1 1 3
                [ ]
             1   1   3
            1 3 1 3 1 1
            如果不控制会进行两次  1   数据会重复记录
                             1  3
             */
            if (i>0 && nums[i]==nums[i-1] && !used[i-1]) {
                continue ;
            }
            path.add(nums[i]);
            used[i] = true;
            unniqueHelper(nums, used, path, ans);
            used[i] = false;
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 3};
        Q47_PermutationsII q47_permutationsII = new Q47_PermutationsII();
        List<List<Integer>> ans = q47_permutationsII.permuteUnique(nums);
        ans.stream().forEach(list->{
            list.stream().forEach(val->{
                System.out.print(val+" ");
            });
            System.out.println();
        });
    }
}
