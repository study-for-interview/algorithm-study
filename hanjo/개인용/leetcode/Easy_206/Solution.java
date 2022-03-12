// https://leetcode.com/problems/reverse-linked-list/

package leetcode.Easy_206;

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode before = null;
        ListNode now = head;

        while(now != null){
            ListNode next = now.next;
            now.next = before;
            before = now;
            now = next;
        }

        return before;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}