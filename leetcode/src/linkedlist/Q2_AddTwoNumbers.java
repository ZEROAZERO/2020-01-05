package linkedlist;

import util.ListNode;

/**
 2. Add Two Numbers
 Medium

 You are given two non-empty linked lists representing two non-negative integers.
 The digits are stored in reverse order and each of their nodes contain a single digit.
 Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 */
public class Q2_AddTwoNumbers {
    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur = new ListNode(0);
        ListNode res = cur;
        ListNode prev = cur;
        while (l1!=null || l2!=null) {
            int num1 = l1!=null? l1.val: 0;
            int num2 = l2!=null? l2.val: 0;
            int tmp = cur.val + num1 + num2;
            cur.val = tmp%10;//可能出现进位
            cur.next = new ListNode(tmp/10);
            prev = cur;
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (cur.val == 0) {
            prev.next = null;
        }
        return res;
    }

    /**
     * addTwoNumbers Optimization Version
     */
    private ListNode addTwoNumbersOpt(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        int carry = 0;
        while (l1!=null || l2!=null) {
            int num1 = l1!=null? l1.val: 0;
            int num2 = l2!=null? l2.val: 0;
            int tmp = carry + num1 + num2;
            cur.next = new ListNode(tmp%10);
            carry = tmp / 10;
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            cur.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(4);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        Q2_AddTwoNumbers q2_addTwoNumbers = new Q2_AddTwoNumbers();
        ListNode res = q2_addTwoNumbers.addTwoNumbers(l1, l2);
        while (res != null) {
            System.out.println(res.val+" ");
            res = res.next;
        }
        ListNode res1 = q2_addTwoNumbers.addTwoNumbersOpt(l1, l2);
        while (res1 != null) {
            System.out.println(res1.val+" ");
            res1 = res1.next;
        }
    }
}
