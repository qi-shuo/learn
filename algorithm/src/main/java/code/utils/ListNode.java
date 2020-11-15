package code.utils;

import java.util.List;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019/4/1 5:48 PM
 */
public class ListNode {

    public int val;

    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public static void main(String[] args) {
//        ListNode listNode1 = new ListNode(-2147483647);
//        ListNode listNode2 = new ListNode(-2147483648);
//        listNode1.next = listNode2;
////        ListNode listNode3 = new ListNode(1);
////        listNode2.next = listNode3;
////        listNode3.next = new ListNode(3);
//        System.out.println(insertionSortList(listNode1));
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(1);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(2);
        listNode2.next = listNode3;
        listNode3.next = new ListNode(1);
        System.out.println(isPalindrome(listNode1));
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode reverse = reverse(head);
        while (head != null) {
            if (reverse.val != head.val) {
                return false;
            }
            reverse = reverse.next;
            head = head.next;
        }
        return true;
    }

    private static ListNode reverse(ListNode node) {
        ListNode result = null;
        while (node != null) {
            ListNode temp = new ListNode(node.val);
            temp.next = result;
            result = temp;
            node = node.next;
        }
        return result;
    }


    public static ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;
        while (head != null) {
            int val = head.val;
            ListNode next = head.next;
            if (val >= tail.val) {
                tail.next = head;
                head.next = null;
                tail = tail.next;
            } else {
                ListNode temp = dummy;
                while (temp != null && temp.next != null) {
                    int tempVal = temp.val;
                    int tempNextVal = temp.next.val;
                    if (val >= tempVal && val <= tempNextVal) {
                        ListNode tempNext = temp.next;
                        ListNode listNode = new ListNode(val);
                        temp.next = listNode;
                        listNode.next = tempNext;
                        break;
                    } else {
                        temp = temp.next;
                    }
                }
            }
            head = next;
        }
        return dummy.next;
    }

}
