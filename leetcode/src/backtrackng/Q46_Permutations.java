package backtrackng;

import java.util.ArrayList;
import java.util.List;

/**
 *
 46. Permutations
 Given a collection of distinct integers, return all possible permutations.

 Example:

 Input: [1,2,3]
 Output:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]
 */
public class Q46_Permutations {

    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        if (nums.length == 0) {
            return ans;
        }

        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        permuteHelper(nums, used, path, ans);
        return ans;
    }

    private void permuteHelper(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> ans) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return ;
        }

        for (int i=0; i<nums.length; i++) {
            if (used[i]) {
                continue ;
            }
            path.add(nums[i]);
            used[i] = true;
            permuteHelper(nums, used, path, ans);
            used[i] = false;
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Q46_Permutations q46_permutations = new Q46_Permutations();
        List<List<Integer>> ans = q46_permutations.permute(nums);
        ans.stream().forEach(list->{
            list.stream().forEach(val->{
                System.out.print(val+" ");
            });
            System.out.println();
        });
    }
}
