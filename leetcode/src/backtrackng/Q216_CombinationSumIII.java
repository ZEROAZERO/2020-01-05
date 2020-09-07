package backtrackng;

import java.util.ArrayList;
import java.util.List;

public class Q216_CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        combinationHelper(k, n, 1, 0, 0, ans, new ArrayList<>());
        return ans;
    }
    private void combinationHelper(int k, int n, int stat, int sum, int step, List<List<Integer>> ans, List<Integer> part) {
        if (step == k && n == sum) {
            ans.add(new ArrayList<>(part));
            return;
        }

        for (int i = stat; i <= 9; i++) {
            //还可以剪枝
            if (n - sum < i) {
                break;
            }

            part.add(i);
            combinationHelper(k, n, i + 1, sum + i, step + 1, ans, part);
            part.remove(part.size() - 1);
        }
    }

    /**
     * error
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3Opt(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();

        // 一开始做一些特殊判断
        if (k <= 0 || n <= 0 || k >= n) {
            return ans;
        }
        //优化点全部数据累加和达不到n
        if (n > (19 - k) * k / 2) {
            return ans;
        }

        optHelper(k, n, 1, 0, 0, ans, new ArrayList<>());
        return ans;
    }
    private void optHelper(int k, int n, int stat, int sum, int step, List<List<Integer>> ans, List<Integer> part) {
        //优化点sum大于n
        if (sum > n) {
            return;
        }
        if (step == k && n == sum) {
            ans.add(new ArrayList<>(part));
            return;
        }

        for (int i = stat; i <= 9; i++) {
            //优化点加某值后大于n
            if (n - sum < i) {
                break;
            }

            part.add(i);
            optHelper(k, n, i + 1, sum + i, step + 1, ans, part);
            part.remove(part.size() - 1);
        }
    }


    public static void main(String[] args) {
        int k = 3;
        int n = 9;
        Q216_CombinationSumIII q216_combinationSumIII = new Q216_CombinationSumIII();
        List<List<Integer>> ans = q216_combinationSumIII.combinationSum3Opt(k, n);
        ans.stream().forEach(list->{
            list.stream().forEach(val->{
                System.out.print(val+" ");
            });
            System.out.println();
        });
    }

}

