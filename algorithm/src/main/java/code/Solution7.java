package code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-05-26 16:16
 */
public class Solution7 {
    LinkedList<Integer> queue = new LinkedList<>();

    public synchronized void push() {
        synchronized (queue) {
            queue.push(1);
            queue.notifyAll();
        }

    }

    public synchronized int pop() throws InterruptedException {
        synchronized (queue) {
            if (queue.size() <= 0) {
                wait();
            }
            return queue.pop();
        }
    }

    static class User {
        int age;
        String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static String decodeString(String s) {
        if (s.length() == 0) {
            return "";
        }
        Stack<Character> stack = new Stack<>();
        StringBuffer resultSB = new StringBuffer();
        List<Character> list = new ArrayList<>();
        String temp = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ']') {
                StringBuffer sb = new StringBuffer(temp);
                while (!stack.isEmpty() && stack.peek() != '[') {
                    if (list.contains(stack.peek())) {
                        sb.append(stack.pop());
                    } else {
                        sb.insert(0, stack.pop());
                    }

                }
                list.clear();
                temp = "";
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                StringBuffer countSB = new StringBuffer();
                while (!stack.isEmpty()) {
                    if (!(stack.peek() >= 'A' && stack.peek() <= 'z')) {
                        countSB.insert(0, stack.pop());
                    } else {
                        break;
                    }
                }
                int count = countSB.length() == 0 ? 0 : Integer.valueOf(countSB.toString());
                StringBuffer sb1 = new StringBuffer();
                for (int j = 0; j < count; j++) {
                    sb1.append(sb.toString()).append(temp);
                }
                temp = sb1.toString();
                if (stack.isEmpty()) {
                    resultSB.append(temp);
                    temp = "";
                }
            } else {
                if(!"".equals(temp)){
                    list.add(c);
                }
                stack.push(c);
            }

        }
        for (int i = 0; i < stack.size(); i++) {
            resultSB.append(stack.get(i));
        }
        return resultSB.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
//
//        String str = "hello";
//        test(str);
//        System.out.println(str);
    }

    public static void test(String str) {
        str = str + "world";
        System.out.println(str);
        int[] a = new int[1024];
    }


}
