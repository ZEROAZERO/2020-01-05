package dynamic;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 983. Minimum Cost For Tickets
 *
 In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

 Train tickets are sold in 3 different ways:

 a 1-day pass is sold for costs[0] dollars;
 a 7-day pass is sold for costs[1] dollars;
 a 30-day pass is sold for costs[2] dollars.
 The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

 Return the minimum number of dollars you need to travel every day in the given list of days.



 Example 1:

 Input: days = [1,4,6,7,8,20], costs = [2,7,15]
 Output: 11
 Explanation:
 For example, here is one way to buy passes that lets you travel your travel plan:
 On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
 On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
 On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
 In total you spent $11 and covered all the days of your travel.
 Example 2:

 Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
 Output: 17
 Explanation:
 For example, here is one way to buy passes that lets you travel your travel plan:
 On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
 On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
 In total you spent $17 and covered all the days of your travel.


 Note:

 1 <= days.length <= 365
 1 <= days[i] <= 365
 days is in strictly increasing order.
 costs.length == 3
 1 <= costs[i] <= 1000
 */
public class Q983_MinimumCostForTickets {

    private int minCostTicketsRecord(int[] days, int[] costs) {
        int[] memo = new int[366];
        Arrays.fill(memo, Integer.MAX_VALUE);
        Set<Integer> daySet = new HashSet<>();
        Arrays.stream(days).forEach(val->daySet.add(val));
        int ans = recordHelper(1, memo, daySet, costs);
        System.out.println(Arrays.toString(memo));
        return ans;
    }
    private int recordHelper(int pos, int[] memo, Set<Integer> daySet, int[] costs) {
        if (pos > 365) {
            return 0;
        }
        if (memo[pos] != Integer.MAX_VALUE) {
            return memo[pos];
        }
        if (daySet.contains(pos)) {
            memo[pos] = Math.min(Math.min(recordHelper(pos + 1, memo, daySet, costs) + costs[0],
                    recordHelper(pos + 7, memo, daySet, costs) + costs[1]),
                    recordHelper(pos + 30, memo, daySet, costs) + costs[2]);
        } else {
            memo[pos] = recordHelper(pos + 1, memo, daySet, costs);
        }
        return memo[pos];
    }

    private int minCostTicketsRecordOpt(int[] days, int[] costs) {
        int[] duration = new int[]{1, 7 ,30};
        int[] memo = new int[days.length];
        Arrays.fill(memo, Integer.MAX_VALUE);
        int ans = optHelper(0, days, costs, memo, duration);
        System.out.println(Arrays.toString(memo));
        return ans;
    }
    private int optHelper(int pos, int[] days, int[] costs, int[] memo, int[] duration) {
        if (pos >= days.length) {
            return 0;
        }
        if (memo[pos] != Integer.MAX_VALUE) {
            return memo[pos];
        }
        int j = pos;
        for (int k=0; k<3; k++) {
            while (j < days.length && days[j] < days[pos] + duration[k]) {
                j++;
            }
            memo[pos] = Math.min(memo[pos], optHelper(j, days, costs, memo, duration) + costs[k]);
        }
        return memo[pos];
    }

    public static void main(String[] args) {
        int[] days = new int[]{1,4,6,7,8,20};
        int[] costs = new int[]{2, 7, 15};  // 1天 7天 30天
        Q983_MinimumCostForTickets q983_minimumCostForTickets = new Q983_MinimumCostForTickets();
        int ans = q983_minimumCostForTickets.minCostTicketsRecord(days, costs);
        System.out.println("record ans:"+ans);

        int ans1 = q983_minimumCostForTickets.minCostTicketsRecordOpt(days, costs);
        System.out.println("record opt ans1:"+ans1);
    }
}
