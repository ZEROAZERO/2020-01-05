package dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 638. Shopping Offers
 *
 In LeetCode Store, there are some kinds of items to sell. Each item has a price.

 However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

 You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.

 Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

 You could use any of special offers as many times as you want.

 Example 1:
 Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
 Output: 14
 Explanation:
 There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
 In special offer 1, you can pay $5 for 3A and 0B
 In special offer 2, you can pay $10 for 1A and 2B.
 You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
 Example 2:
 Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 Output: 11
 Explanation:
 The price of A is $2, and $3 for B, $4 for C.
 You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.
 You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.
 You cannot add more items, though only $9 for 2A ,2B and 1C.
 Note:
 There are at most 6 kinds of items, 100 special offers.
 For each item, you need to buy at most 6 of them.
 You are not allowed to buy more items than you want, even if that would lower the overall price.

 */
public class Q638_ShoppingOffers {

    private int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return shopping(price, special, needs);
    }

    private int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int res = dot(price, needs);
        int i = 0;
        for (List<Integer> s: special) {
            List<Integer> clone = new ArrayList<>(needs);
            // 每种套餐 对于 A B。。。类月饼的需求
            for (i=0; i<needs.size(); i++) {
                int diff = clone.get(i) - s.get(i);
                // 确保每个分量都不会超出范围
                if (diff < 0) {
                    break;
                }
                clone.set(i, diff);
            }
            if (i == needs.size()) {
                res = Math.min(res, s.get(i)+shopping(price, special, clone));
            }
        }
        return res;
    }

    private int dot(List<Integer> price, List<Integer> needs) {
        int ans = 0;
        for (int i=0; i<price.size(); i++) {
            ans += price.get(i) * needs.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        // [2,5], [[3,0,5],[1,2,10]], [3,2]
        List<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(5);

        List<List<Integer>> special = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(3);
        l1.add(0);
        l1.add(5);
        special.add(l1);
        List<Integer> l2 = new ArrayList<>();
        l2.add(1);
        l2.add(2);
        l2.add(10);
        special.add(l2);

        List<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);

        Q638_ShoppingOffers q = new Q638_ShoppingOffers();
        int ans = q.shoppingOffers(price, special, needs);
        System.out.println(ans);
    }



}
