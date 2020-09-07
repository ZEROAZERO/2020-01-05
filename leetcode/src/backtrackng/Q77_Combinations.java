package backtrackng;

import java.util.ArrayList;
import java.util.List;

/**
 *
 77. Combinations
 Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 Example:

 Input: n = 4, k = 2
 Output:
 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 */
public class Q77_Combinations {

    private List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (n <= 0 || k <= 0 || n < k) {
            return ans;
        }
        List<Integer> combination = new ArrayList<>();
        helper(n, k, 1, ans, combination);
        return ans;
    }
    private void helper(int n, int k, int pos, List<List<Integer>> ans, List<Integer> combination) {
        if (combination.size() == k) {
            ans.add(new ArrayList<>(combination));
            return ;
        }

        //make sure the remaining elements that can make up the combination
        for (int i=pos; i<=n-(k-combination.size())+1; i++) {
            combination.add(i);
            helper(n, k, i+1, ans, combination);
            combination.remove(combination.size()-1);
        }
    }

    public static void main(String[] args) {
        int n=4, k=2;
        Q77_Combinations q77_combinations = new Q77_Combinations();
        List<List<Integer>> ans = q77_combinations.combine(n, k);
        ans.stream().forEach(list->{
            list.forEach(val-> System.out.print(val+" "));
            System.out.println();
        });
    }
}
