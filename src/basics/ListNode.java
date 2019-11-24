package basics;

import java.util.ArrayList;
import java.util.List;

public class ListNode {

    public int val;
    public ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }

    public static ListNode create(int... nums) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }
        return head.next;
    }

    @Override
    public String toString() {
        List<Integer> values = new ArrayList<>();
        ListNode cur = this;
        while (cur != null) {
            values.add(cur.val);
            cur = cur.next;
        }
        return values.toString();
    }
}
