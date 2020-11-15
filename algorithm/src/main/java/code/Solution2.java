package code;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-04-23 12:20
 */
public class Solution2 {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

//        @Override
//        public String toString() {
//            return "Node{" +
//                    "val=" + val +
//                    ", next=" + next +
//                    ", random=" + random +
//                    '}';
//        }

    }

    public static void main(String[] args) {
//        Node node1 = new Node(7);
//        Node node2 = new Node(13);
//        Node node3 = new Node(11);
//        Node node4 = new Node(10);
//        Node node5 = new Node(1);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node2.random = node1;
//        node3.random = node5;
//        node4.random = node3;
//        node5.random = node1;
//        System.out.println(copyRandomList(node1));
//        System.out.println(longestPalindrome("cbbd"));
//        System.out.println(10+5/2);
//        System.out.println((10+5)/2);
//        System.out.println(5+(10-5)/2);
//        System.out.println(BigDecimal.valueOf(9.374075538950052e20));
//        System.out.println("abbb".replace("b","c"));
        System.out.println(lengthOfLongestSubstring("ohvhjdml"));


    }

    public static int lengthOfLongestSubstring(String s) {
//        ReentrantLock lock=new ReentrantLock();
//        lock.lock();
//        lock.unlock();
        int len=s.length();
        if(len==0){
            return 0;
        }
        List<Character> list=new ArrayList<>();
        int result=0;
        int count=0;
        for(int i=0;i<len;i++){
            if(!list.contains(s.charAt(i))){
                list.add(s.charAt(i));
                count++;
            }else{
                result=Math.max(count,result);
                int index=list.indexOf(s.charAt(i));
                if(index==list.size()-1){
                    list=new ArrayList<>();
                    list.add(s.charAt(i));
                    count=1;
                }else{
                    list=list.subList(index+1,list.size());
                    list.add(s.charAt(i));
                    count=list.size();
                }
            }
        }
        return Math.max(result,count);
    }
    public static String longestPalindrome(String s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        int start = 0, end = 0;
        int i = 0;
        while (i < length) {
            int length1 = helper(s, i, i);
            int length2 = helper(s, i, i + 1);
            int len = Math.max(length1, length2);
            if (len > end - start) {
                if(len%2==0){
                    start = i - len / 2+1;
                    end = i + len / 2+1;
                }else{
                    start = i - len / 2;
                    end = i + len / 2+1;
                }
            }
            i++;
        }
        return s.substring(start, end);
    }

    private static int helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && left <= right) {
            if (s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return right - left - 1;
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node(0);
        Node temp = dummy;
        Map<Integer, Node> randomMap = new HashMap<>();
        Map<Node, Node> nodeMap = new HashMap<>();
        int index = 0;
        Node h = head;
        while (h != null) {
            Node node = new Node(h.val);
            temp.next = node;
            temp = node;
            if (h.random != null) {
                randomMap.put(index, h.random);
                nodeMap.put(h.random, node);
            }
            index++;
            h = h.next;
        }
        temp = dummy.next;
        index = 0;
        while (head != null) {
            if (head.random != null) {
                temp.random = nodeMap.get(randomMap.get(index));
            }
            temp = temp.next;
            head = head.next;
            index++;
        }
        return dummy.next;
    }
}
