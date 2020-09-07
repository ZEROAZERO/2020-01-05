package backtrackng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 40. Combination Sum II
 Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

 Each number in candidates may only be used once in the combination.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [10,1,2,7,6,1,5], target = 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]
 Example 2:

 Input: candidates = [2,5,2,1,2], target = 5,
 A solution set is:
 [
 [1,2,2],
 [5]
 ]
 */
public class Q40_CombinationSumII {

    private List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        sum2Helper(candidates, target, 0, 0, path, ans);
        return ans;
    }

    private void sum2Helper(int[] candidates, int target, int stat, int sum, List<Integer> path, List<List<Integer>> ans) {
        if (sum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i=stat; i<candidates.length; i++) {
            if (sum + candidates[i] > target) {
                break;
            }

            // 避免重复数据
            if (i>stat && candidates[i]==candidates[i-1]) {
                continue;
            }

            path.add(candidates[i]);
            sum2Helper(candidates, target, i+1, sum+candidates[i], path, ans);
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[]{2,5,2,1,2};
        int target = 5;
        Q40_CombinationSumII q40_combinationSumII = new Q40_CombinationSumII();
        List<List<Integer>> ans = q40_combinationSumII.combinationSum2(candidates, target);
        ans.stream().forEach(list-> {
            list.forEach(val-> System.out.print(val+" "));
            System.out.println();
        });
    }

}
