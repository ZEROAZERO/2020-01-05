package backtrackng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 39. Combination Sum
 Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

 The same repeated number may be chosen from candidates unlimited number of times.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7],
 [2,2,3]
 ]
 Example 2:

 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]
 */
public class Q39_CombinationSum {

    private List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        sumHelper(candidates, target, 0, 0, path, ans);
        return ans;
    }

    private void sumHelper(int[] candidates, int target, int stat, int sum, List<Integer> path, List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i=stat; i<candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            sumHelper(candidates, target, i, sum+candidates[i], path, ans);
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
        Q39_CombinationSum q39_combinationSum = new Q39_CombinationSum();
        List<List<Integer>> ans = q39_combinationSum.combinationSum(candidates, target);
        ans.stream().forEach(list-> {
            list.forEach(val-> System.out.print(val+" "));
            System.out.println();
        });
    }
}
