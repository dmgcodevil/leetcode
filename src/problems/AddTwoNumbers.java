package problems;

import basics.ListNode;
import basics.Problem;
import basics.Tag;

/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order
and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 */
@Problem(value = "https://leetcode.com/problems/add-two-numbers/", tags = {Tag.LinkedList, Tag.Math})
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode sentinel = new ListNode(-1);
        ListNode head = sentinel;
        while (l1 != null || l2 != null) {
            int l1_val = l1 != null ? l1.val : 0;
            int l2_val = l2 != null ? l2.val : 0;
            int n = l1_val + l2_val + carry;
            carry = n / 10;
            head.next = new ListNode(n % 10);
            head = head.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry != 0) head.next = new ListNode(carry);


        return sentinel.next;
    }
}
