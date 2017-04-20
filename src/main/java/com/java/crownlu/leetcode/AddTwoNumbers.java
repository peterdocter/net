package com.java.crownlu.leetcode;

import com.java.crownlu.wnys.AESUtils;

/**
 * Created by crownlu on 17/2/28.
 */
public class AddTwoNumbers {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static final ListNode VIRTUAL = new ListNode(0);

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        ListNode r = l1;
        ListNode l = l2;
        int needAdd = 0;
        int sum = 0;
        int count = 0;
        while(r != null || l != null) {
            if (r == null) {
                r = VIRTUAL;
            } else if (l == null) {
                l = VIRTUAL;
            }

            if (count != 0) {
                p.next = new ListNode(0);
                p = p.next;
            }

            sum = r.val + l.val + needAdd;
            if (sum > 9) {
                sum = sum - 10;
                needAdd = 1;
            } else {
                needAdd = 0;
            }

            p.val = sum;
            r = r.next;
            l = l.next;
            count++;
        }

        if (needAdd > 0) {
            p.next = new ListNode(needAdd);
        }

        return head;
    }


    public static void main(String [] args) {
        Double d = 150d;
        AESUtils.p((d.compareTo(0.0d) > 0 && d.compareTo(150d) < 1));
        ListNode l = new ListNode(1);
        l.next = new ListNode(8);
        ListNode r = new ListNode(0);
        addTwoNumbers(l, r);
    }
}
