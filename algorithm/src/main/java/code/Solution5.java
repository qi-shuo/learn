package code;

import code.utils.ListNode;

import java.util.*;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-05-04 16:25
 */
public class Solution5 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

    }

    public static ListNode sortList(ListNode head) {
        Integer[] array = new Integer[Short.MAX_VALUE];
        getList(head, array);
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        for (int i = 0; i < array.length; i++) {
            Integer val = array[i];
            if (val == null) {
                continue;
            }
            int diff = val - i;
            for (int j = 0; j <= diff; j++) {
                temp.next = new ListNode(i);
                temp = temp.next;
            }
        }
        return dummy.next;

    }

    private static void getList(ListNode head, Integer[] array) {
        if (array[head.val] != null) {
            array[head.val] = array[head.val] + 1;
        } else {
            array[head.val] = head.val;
        }
        ListNode next = head.next;
        if (next == null) {
            return;
        }
        getList(next, array);
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static Map<Integer, Integer> map = new HashMap<>();

    public static int integerReplacement(int n) {
        if (n == 1) {
            return 0;
        }
//        if (map.containsKey(n)) {
//            return map.get(n);
//        }
//        int result;
//        if (n % 2 == 0) {
//            if (map.containsKey(n / 2)) {
//                result = map.get(n / 2) + 1;
//            } else {
//                result = integerReplacement(n / 2) + 1;
//            }
//
//        } else {
//            int a, b;
//            if (map.containsKey((n + 1) / 2)) {
//                a = map.get((n + 1) / 2);
//            } else {
//                a = integerReplacement((n + 1) / 2);
//            }
//            if (map.containsKey(n - 1)) {
//                b = map.get(n - 1);
//            } else {
//                b = integerReplacement(n - 1);
//            }
//            result = Math.min(a + 2, b + 1);
//        }
//        map.put(n, result);
//        return result;
        //int[] dp = new int[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 0);
        int add = 0, reduce = 0, mid = 0;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                map.put(i, map.get(i / 2) + 1);
                //dp[i] = dp[i / 2] + 1;
            } else {
                map.put(i, Math.min(map.get((i + 1) / 2) + 2, map.get(i - 1) + 1));
                //dp[i] = Math.min(dp[((i + 1) / 2)] + 2, dp[i - 1] + 1);
            }
        }
        return map.get(n);
    }

    public static int longestValidParentheses(String s) {
        if (s.length() < 2) {
            return 0;
        }
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = i - 2 >= 0 ? dp[i - 2] + 2 : 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 2 >= 0) ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        // System.out.println(longestValidParentheses("(()())"));
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(subarraySum(new int[]{1, 2, 3}, 3));
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(4);
//        head.next.next = new ListNode(1);
//        head.next.next.next = new ListNode(3);
//        head.next.next.next.next = new ListNode(2);
//        ListNode node = sortList(head);
//        TreeNode treeNode = new TreeNode(1);
//
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode5 = new TreeNode(5);
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode7 = new TreeNode(7);
//        treeNode.left = treeNode2;
//        treeNode.right = treeNode3;
//        treeNode2.left = treeNode4;
//        treeNode2.right = treeNode5;
//        treeNode3.left = treeNode6;
//        treeNode3.right = treeNode7;
//
//        System.out.println(preorderTraversal(treeNode));
//        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        System.out.println(spiralOrder(matrix));
        //System.out.println(convert("LEETCODEISHIRING", 3));
//        System.out.println(canPartition(new int[]{1, 1, 1, 1}));
//        System.out.println(lengthOfLongestSubstring("pwwkew"));
//        System.out.println(mincostTickets(new int[]{4, 5, 9, 11, 14, 16, 17, 19, 21, 22, 24}, new int[]{1, 4, 18}));
//
//        byte a = 127;
//        byte b = 127;
//        a += b;
//        System.out.println(a);
//
//        //System.out.println(test("12881616"));
//        System.out.println(isUgly(-2147483648));
//        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
//        System.out.println(threeSum(nums));
        //System.out.println(numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
        System.out.println(subarraySum(new int[]{0, -1, 1, 0}, 0));
        System.out.println(findOrder(2, new int[][]{{1, 0}}));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int[] nums = prerequisites[i];
            if (map.containsKey(nums[1])) {
                map.get(nums[1]).add(nums[0]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(nums[0]);
                map.put(nums[1], list);
            }
        }
        int[] nums = new int[numCourses];
        int[] result = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int[] temp = prerequisites[i];
            nums[temp[0]] = nums[temp[0]] + 1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                result[count++] = poll;
                List<Integer> list = map.get(poll);
                if (list == null || list.isEmpty()) {
                    continue;
                }
                for (Integer index : list) {
                    nums[index] = nums[index] - 1;
                    if (nums[index] == 0) {
                        queue.add(index);
                    }
                }
            }
        }
        return count == numCourses - 1 ? result : new int[0];
    }

    public static int subarraySum(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    public static int maxProduct(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(nums[i] * dpMax[i - 1], Math.max(nums[i], nums[i] * dpMin[i - 1]));
            dpMin[i] = Math.min(nums[i] * dpMax[i - 1], Math.min(nums[i], nums[i] * dpMin[i - 1]));
        }
        Arrays.sort(dpMax);
        return dpMax[dpMax.length - 1];
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        int l = 0, r = 0, result = 0, count = 0;
        while (r < nums.length) {
            if (nums[r] % 2 > 0) {
                count++;
            }
            if (count == k) {
                int left = l;
                while (nums[l] % 2 == 0) {
                    l++;
                }
                int right = r;
                while (r + 1 < nums.length && nums[r + 1] % 2 == 0) {
                    r++;
                }
                result = result + (l - left + 1) * (r - right + 1);
                r = right;
                l++;
                count = count - 1;
            }
            r++;
        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums) {


        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }
        Arrays.sort(nums);
        if (nums[0] > 0) {
            return result;
        }
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while (right >= 1 && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    right--;
                    while (left <= nums.length - 2 && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public static boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }
        long n = Math.abs((long) num);
        if (n == 1 || n == 2 || n == 3 || n == 5) {
            return true;
        }
        while (n % 2 == 0) {
            n = n / 2;
        }
        while (n % 3 == 0) {
            n = n / 3;
        }
        while (n % 5 == 0) {
            n = n / 5;
        }
        return n == 1;
    }

    public static int test(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.push(s.charAt(i));
            } else {
                if (s.charAt(i) == '6') {
                    Character c1, c2 = null;
                    if (stack.peek() == '1') {
                        c1 = stack.pop();
                        if (!stack.isEmpty() && stack.peek() == '8') {
                            c2 = stack.pop();
                        }
                        if (c2 == null) {
                            stack.push(c1);
                        }
                    } else {
                        stack.push(s.charAt(i));
                    }
                } else {
                    stack.push(s.charAt(i));


                }
            }
        }
        return 0;
    }

    public static int mincostTickets(int[] days, int[] costs) {
        if (days.length < 1) {
            return 0;
        }
        if (days.length == 1) {
            return costs[0];
        }
        int[] dp = new int[days[days.length - 1] + 1];
        for (int day : days) {
            dp[day] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] == 0) {
                dp[i] = dp[i - 1];
                continue;
            }
            int n1 = dp[i - 1] + costs[0];
            int n2 = i > 7 ? dp[i - 7] + costs[1] : costs[1];
            int n3 = i > 30 ? dp[i - 30] + costs[2] : costs[2];
            dp[i] = Math.min(n1, Math.min(n2, n3));
        }
        return dp[dp.length - 1];
    }

    public static int lengthOfLongestSubstring(String s) {
        List<Character> set = new ArrayList<>();
        int r = 0;
        int i = 0;
        int result = 0;
        while (i < s.length() && r < s.length()) {
            if (!set.contains(s.charAt(r))) {
                set.add(s.charAt(r++));
                result = Math.max(result, r - i);
            } else {
                set.remove(0);
                i++;
            }
        }
        return result;
    }

    static boolean result;

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        Arrays.sort(nums);
        dfs(new boolean[nums.length], nums, sum, 0);
        return result;
    }

    private static void dfs(boolean[] flags, int[] nums, int sum, int count) {
        if (count == sum) {
            result = true;
            return;
        }
        if (count > sum) {
            return;
        }
        int c = 1;
        for (int i = 0; i < nums.length; i++) {
            if (result) {
                return;
            }
            if (i > 0 && nums[i] == nums[i - 1] && flags[i - 1] == false) {
                c++;
                continue;
            }
            if (!flags[i]) {
                flags[i] = true;
                if (i > 0 && c > 1) {
                    //直接一次性将重复元素统计好sum=sum+nums[i-1]*count，
                    //因为上面continue，所以没有统计重复元素的和，
                    //sum=sum+nums[i-1]*count直接将所有重复元素统计起来
                    //最终传入的参数是sum=sum+nums[i]+nums[i-1]*count
                    dfs(flags, nums, sum, count + nums[i] + nums[i - 1] * c);
                } else {
                    dfs(flags, nums, sum, count + nums[i]);
                }
                flags[i] = false;
                c = 1;
            }
        }
    }

    public static String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        List<StringBuffer> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuffer());
        }
        boolean flag = true;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (flag) {
                list.get(index++).append(s.charAt(i));
            } else {
                list.get(index--).append(s.charAt(i));
            }
            flag = (index == numRows || index == -1) != flag;
            if (index == numRows) {
                index = numRows - 1;
            }
            if (index == -1) {
                index = 0;
            }
        }
        StringBuffer result = new StringBuffer();
        for (StringBuffer sb : list) {
            if (sb.length() > 0) {
                result.append(sb.toString());
            }
        }
        return result.toString();
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int row = matrix.length;
        if (row == 0) {
            return result;
        }
        int column = matrix[0].length;
        int leftBoun = 0, upBoun = 0;
        int size = row * column;
        int i = 0, j = 0;
        boolean up = false, down = false, left = false, right = true;
        for (int index = 0; index < size; index++) {
            if (right) {
                if (j < column) {
                    result.add(matrix[i][j++]);
                }
                if (j == column) {
                    upBoun++;
                    i++;
                    j = j - 1;
                    right = false;
                    down = true;
                }
                continue;
            }
            if (down) {
                if (i < row) {
                    result.add(matrix[i++][j]);
                }
                if (i == row) {
                    column--;
                    j--;
                    i = i - 1;
                    down = false;
                    left = true;
                }
                continue;
            }
            if (left) {
                if (j >= leftBoun) {
                    result.add(matrix[i][j--]);
                }
                if (j == leftBoun - 1) {
                    leftBoun++;
                    i--;
                    j = j + 1;
                    left = false;
                    up = true;
                }
                continue;
            }
            if (up) {
                if (i >= upBoun) {
                    result.add(matrix[i--][j]);
                }
                if (i == upBoun - 1) {
                    row--;
                    j++;
                    i = i + 1;
                    up = false;
                    right = true;
                }
            }
        }
        return result;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> output = new LinkedList<>();

        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                output.add(node.val);
                node = node.right;
            } else {
                TreeNode predecessor = node.left;
                while ((predecessor.right != null) && (predecessor.right != node)) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    output.add(node.val);
                    predecessor.right = node;
                    node = node.left;
                } else {
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
        return output;
    }

}