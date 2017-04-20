package com.java.crownlu.leetcode;

import com.java.crownlu.wnys.AESUtils;

/**
 * Created by crownlu on 17/3/6.
 */
public class MergeTwoSortedLists {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(val);
            ListNode p = next;
            while (p != null) {
                sb.append(", ").append(p.val);
                p = p.next;
            }
            return sb.toString();
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        ListNode head = new ListNode(0);
        ListNode p    = head;

        while (l1 != null && l2 != null) {
            while (l1 != null && l1.val <= l2.val) {
                p.next = l1;
                p      = l1;
                l1     = l1.next;
            }

            while (l1 != null && l2 != null && l2.val <= l1.val) {
                p.next = l2;
                p      = l2;
                l2     = l2.next;
            }
        }

        if (l1 != null) {
            p.next = l1;
        } else if (l2 != null) {
            p.next = l2;
        }
        return head.next;
    }

    public static void main(String [] args) {
        AESUtils.p(mergeTwoLists(new ListNode(2), new ListNode(1)));
    }
}
