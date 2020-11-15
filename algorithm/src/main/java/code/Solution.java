package code;

import code.utils.ListNode;

import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2019/4/1 6:48 PM
 */
public class Solution {
    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        //进位
        int carry = 0;
        //当前的节点
        ListNode curr = listNode;
        while (l1 != null || l2 != null) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            int addVal = val1 + val2 + carry;
            carry = addVal / 10;
            //余数
            int remainder = addVal % 10;
            curr.next = new ListNode(remainder);
            curr = curr.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return listNode.next;
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     */
    public static int[] twoSum(int[] nums, int target) {
        //key:数组的值,value:数组的索引
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int difference = target - nums[i];
            if (map.containsKey(difference)) {
                return new int[]{i, map.get(difference)};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("没找到合适的位置");
    }


    public static int lengthOfLongestSubstring(String s) {
//        int length = s.length();
//        Set<Character> set = new HashSet<Character>();
//        int result = 0, start = 0, end = 0;
//        while (start < length && end < length) {
//            if (!set.contains(e)) {
//                //先使用end值后在执行end=end+1
//                //++i  是先执行   i=i+1  再使用 i 的值，而 i++ 是先使用 i 的值再执行 i=i+1。
//                set.add(s.charAt(end++));
//                result = Math.max(result, end - start);
//            } else {
//                set.remove(s.charAt(start++));
//            }
//        }
//        return result;
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;

    }

    public static int reverse(int x) {
        try {
            if (x > 0) {
                return Integer.parseInt(new StringBuilder(String.valueOf(x)).reverse().toString());
            } else {
                int a = Math.abs(x);
                return Integer.parseInt("-" + new StringBuilder(String.valueOf(a)).reverse().toString());
            }
        } catch (Exception e) {
            return 0;
        }


    }

    public int myAtoi(String str) {
        //str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        StringBuilder result = new StringBuilder();
        char[] chars = str.toCharArray();
        if (chars[0] != '-' && chars[0] != '+' && (chars[0] < '0' || chars[0] > '9')) {
            return 0;
        }
        boolean flag = false;
        for (int i = 0; i < str.length(); i++) {
            if (chars[i] == ' ') {
                continue;
            }
            if (chars[i] != '-' && chars[i] != '+' && (chars[i] < '0' || chars[i] > '9')) {
                break;
            }
            if (chars[i] == '-' || chars[i] == '+') {
                if (flag) {
                    break;
                } else {
                    if (result.length() == 0) {
                        result.append(chars[i]);
                        flag = true;
                    } else {
                        break;
                    }
                }
            } else {
                result.append(chars[i]);
            }

        }
        if ("-".equals(result.toString()) || "+".equals(result.toString())) {
            return 0;
        }
        double d = Double.valueOf(result.toString());
        if (d > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (d < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) d;
    }

//    public static void main(String[] args) {
////        System.out.println(reverse(123));
////        int[][] stones = new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}};
////        removeStones(stones);
//        String board[][] = new String[][]{
//                {".", ".", ".", ".", ".", ".", ".", "."}, {".", ".", ".", "p", ".", ".", ".", "."}, {".", ".", ".", "R", ".", ".", ".", "p"}, {".", ".", ".", ".", ".", ".", ".", "."}, {".", ".", ".", ".", ".", ".", ".", "."}, {".", ".", ".", "p", ".", ".", ".", "."}, {".", ".", ".", ".", ".", ".", ".", "."}, {".", ".", ".", ".", ".", ".", ".", "."}};
//        //numRookCaptures(board);
//        int[][] M = new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
//        System.out.println(findCircleNum(M));
//    }

    static int[] parent = new int[400];

    public static int findCircleNum(int[][] M) {
        if (M.length <= 1) {
            return 1;
        }
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] > 0) {
                    union(i, j + 200);
                }
            }

        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < M.length; i++) {
            set.add(find(i));
        }
        return set.size();
    }

    private static int find(int x) {
        int a = x;
        while (x != parent[x]) {
            x = parent[x];
        }
        while (a != parent[a]) {
            int temp = a;
            a = parent[a];
            parent[temp] = x;
        }
        return x;
    }

    private static void union(int x, int y) {
        int a = find(x), b = find(y);
        if (a != b) {
            parent[a] = b;
        }
    }

    //public static void main(String[] args) {
    // Double.valueOf("20000000000000000000");
    //int[] deck = new int[]{1, 2, 3, 4, 4, 3, 2, 1};
//        int[][] grid = new int[][]{
//                {1, 0, 1},
//                {0, 0, 0},
//                {1, 0, 1}};
    // maxDistance(grid);
//        System.out.println(maxAreaOfIsland(grid));
//        List<Integer> list=new ArrayList<>();
//        list.add(20,1);
//        System.out.println(list);
//        String[] strs = new String[]{"", "dfdf", "dgf", "dgrdfgkjh", "df"};
//        Arrays.sort(strs, (a, b) -> b.length() - a.length());
//        System.out.println(strs);
//        lastRemaining(5, 3);
//        String[] words = new String[]{"hello", "world", "leetcode"};
//        String str = "welldonehoneyr";
//
//        System.out.println(countCharacters(words, str));
//        longestPalindrome("zeusnilemacaronimaisanitratetartinasiaminoracamelinsuez");
//        int[] nums = new int[]{-2, 3, -5};
//        sortArray(nums);
//        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
//        int[] nums2 = new int[]{2, 5, 6};
//        System.out.println(isValid("(]"));
//        char[][] board = new char[][]{{'a', 'b'}, {'c', 'd'}};
//        String word = "acdb";
//        System.out.println(exist(board, word));
    //System.out.println(tableSizeFor(14));
//        System.out.println(9 / 2);
////        System.out.println(pathInZigZagTree(14));
//        int[] A = new int[]{3, 2, 1, 2, 1, 7};
//        minIncrementForUnique(A);
    //System.out.println(Integer.valueOf("+1"));
//        int[] nums = new int[]{5,1,1};
//        nextPermutation(nums);
//        LinkedHashMap<Integer,Integer> linkedHashMap=new LinkedHashMap<>();
//        linkedHashMap.put(1,1);
//        linkedHashMap.put(3,3);
//        linkedHashMap.put(2,2);
//        linkedHashMap.remove(3);
//        linkedHashMap.put(3,30);
//        System.out.println(linkedHashMap.toString());
//        HashMap<Integer,Integer> map=new HashMap<>();
//        map.put(1,1);
//        map.put(3,3);
//        map.put(2,2);
//        System.out.println(map.toString());
//    }


    public static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] > nums[i + 1]) {
            i--;
        }
        int j = nums.length - 1;
        while (i >= 0 && i <= j) {
            if (nums[i] < nums[j]) {
                exchange(nums, i, j);
                break;
            } else {
                j--;
            }
        }
        reverse(nums, i);
    }

    private static void reverse(int[] nums, int i) {
        int left = i + 1, right = nums.length - 1;
        while (left < right) {
            exchange(nums, left, right);
            left++;
            right--;
        }
    }

    private static void exchange(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 9, 6, 0, 6, 6, 9};
//        System.out.println(largestRectangleArea(nums));
//        System.out.println(movingCount(38, 15, 9));
//        reverseWords("the sky is blue");
//        longestWPI(nums);
        TreeNode root = new Solution.TreeNode(1);
        TreeNode treeNode = new Solution.TreeNode(2);
        TreeNode treeNode2 = new Solution.TreeNode(3);
        TreeNode treeNode3 = new Solution.TreeNode(4);
        TreeNode treeNode4 = new Solution.TreeNode(5);
        TreeNode treeNode5 = new Solution.TreeNode(6);
        treeNode.left = treeNode2;
        treeNode.right = treeNode3;
        treeNode4.right = treeNode5;
        root.left = treeNode;
        root.right = treeNode4;
        //flatten(root);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

//    public static void flatten(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        preorderTraversal(root, list);
//        TreeNode dummy = new TreeNode(0);
//        TreeNode temp = dummy;
//        for (Integer i : list) {
//            temp.right = new TreeNode(i);
//            temp.left = null;
//            temp = temp.right;
//        }
//        root = dummy.right;
//    }
//
//    private static void preorderTraversal(TreeNode root, List<Integer> result) {
//        if (root == null) {
//            return;
//        }
//        result.add(root.val);
//        preorderTraversal(root.left, result);
//        preorderTraversal(root.right, result);
//
//    }

    public static int longestWPI(int[] hours) {
        int[] prefixSrc = new int[hours.length + 1];
        prefixSrc[0] = 0;
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) {
                hours[i] = 1;
            } else {
                hours[i] = -1;
            }
            prefixSrc[i + 1] = prefixSrc[i] + hours[i];
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < prefixSrc.length; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                if (prefixSrc[stack.peek()] > prefixSrc[i]) {
                    stack.push(i);
                }
            }
        }
        int result = 0;
        for (int i = prefixSrc.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && prefixSrc[stack.peek()] < prefixSrc[i]) {
                result = Math.max(result, i - stack.pop());

            }
        }
        return result;
    }

    public static int maxWidthRamp(int[] A) {
        Stack<Integer> stacks = new Stack<>();
        stacks.push(0);
        int length = A.length;
        for (int i = 1; i < length; i++) {
            if (A[i] <= A[stacks.peek()]) {
                stacks.push(i);
            }
        }
        int max = 0;
        for (int i = length - 1; i >= 0; i--) {
            while (!stacks.isEmpty() && A[i] >= A[stacks.peek()]) {
                max = Math.max(max, i - stacks.pop());
            }
        }
        return max;
    }

    public int minimumLengthEncoding(String[] words) {
        Trie trie = new Trie();
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        int result = 0;
        for (int i = 0; i < words.length; i++) {
            if (!trie.search(words[i])) {
                result = result + words[i].length() + 1;
            }
        }
        return result;
    }

    class Trie {
        TrieNode root = new TrieNode();

        public boolean search(String str) {
            TrieNode[] node = root.node;
            boolean result = true;
            for (int i = str.length() - 1; i >= 0; i--) {
                if (node[str.charAt(i) - 'a'] == null) {
                    node[str.charAt(i) - 'a'] = new TrieNode();
                    result = false;
                }
                node = node[str.charAt(i) - 'a'].node;
            }
            return result;
        }
    }

    class TrieNode {
        TrieNode[] node = new TrieNode[26];
    }

    public static String reverseWords(String s) {
        String result = "";
        s = s.trim();
        int index = s.indexOf(" ");
        while (index > 0) {
            String temp = s.substring(0, index);
            result = temp + " " + result;
            s = s.trim();
            s = s.substring(index);
            index = s.indexOf(" ");
        }
        return s + " " + result;
    }

    public static int movingCount(int m, int n, int k) {
        Stack<int[]> stack = new Stack<>();
        int result = 0;
        stack.push(new int[]{0, 0});
        Set<Map.Entry<Integer, Integer>> set = new HashSet<>();
        set.add(new AbstractMap.SimpleEntry<>(0, 0));
        while (!stack.isEmpty()) {
            result++;
            int[] nums = stack.pop();
            int a = nums[0], b = nums[1];
            int sum = 0;
            int tempA = a, tempB = b;
            while (tempA >= 10 || tempB >= 10) {
                if (tempA < 10) {
                    sum = sum + tempB % 10;
                    tempB = tempB / 10;
                } else if (tempB < 10) {
                    sum = sum + tempA % 10;
                    tempA = tempA / 10;
                } else {
                    sum = sum + tempB % 10 + tempA % 10;
                    tempB = tempB / 10;
                    tempA = tempA / 10;
                }
            }
            sum = sum + tempA + tempB;
            if (a - 1 >= 0) {
                if (sum <= k) {
                    Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(a - 1, b);
                    if (!set.contains(entry)) {
                        stack.push(new int[]{a - 1, b});
                        set.add(entry);
                    }
                }
            }
            if (a + 1 < m) {
                if (sum + 1 <= k) {
                    Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(a + 1, b);
                    if (!set.contains(entry)) {
                        stack.push(new int[]{a + 1, b});
                        set.add(entry);
                    }
                }
            }
            if (b - 1 >= 0) {
                if (sum <= k) {
                    Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(a, b - 1);
                    if (!set.contains(entry)) {
                        stack.push(new int[]{a, b - 1});
                        set.add(entry);
                    }
                }
            }
            if (b + 1 < n) {
                if (sum + 1 <= k) {
                    Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(a, b + 1);
                    if (!set.contains(entry)) {
                        stack.push(new int[]{a, b + 1});
                        set.add(entry);
                    }
                }
            }
        }
        return result;
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        while (stack.peek() != -1) {
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }

        return maxarea;
    }


    public static int trap(int[] height) {
        int result = 0;
        for (int i = 0; i < height.length - 2; i++) {
            if (height[i] <= height[i + 1]) {
                continue;
            }
            int max = height[i + 1];
            for (int j = i + 2; j < height.length; j++) {
                if (height[j] >= height[i]) {
                    result = result + (height[i] - max) * (j - i - 1);
                    break;
                } else if (height[j] > max) {
                    if (j + 1 >= height.length) {
                        result = result + (height[j] - max) * (j - i - 1);
                        break;
                    } else {
                        result = result + (height[j] - max) * (j - i - 1);
                        max = height[j];
                    }
                }
            }
        }
        return result;
    }
//    public static int trap(int[] height) {
//        int result = 0;
//        for (int i = 0; i < height.length - 2; i++) {
//            if (height[i] == 0) {
//                continue;
//            }
//            if (height[i] <= height[i + 1]) {
//                continue;
//            }
//            int max = height[i + 1];
//            for (int j = i + 2; j < height.length; j++) {
//                if (height[j] > max) {
//                    if (height[j] < height[i]) {
//                        result = result + (Math.min(height[j], height[i]) * (j - i - 1)) - (max * (j - i - 1));
//                        if (j + 1 >= height.length) {
//                            break;
//                        } else {
//                            max = height[j];
//                        }
//                    } else {
//                        result = result + (Math.min(height[j], height[i]) * (j - i - 1)) - (max * (j - i - 1));
//                        break;
//                    }
//                }
//            }
//        }
//        return result;
//    }

    public static int minIncrementForUnique(int[] A) {
        Arrays.sort(A);
        int result = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                int pre = A[i];
                A[i] = A[i - 1] + 1;
                result = result + A[i] - pre;
            }
        }
        return result;
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    public static boolean exist(char[][] board, String word) {
        int[] xMove = new int[]{1, -1, 0, 0};
        int[] yMove = new int[]{0, 0, 1, -1};
        int row = board.length;
        int column = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                char c = word.charAt(0);
                if (board[i][j] == c) {
                    int index = 0;
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[]{i, j});
                    while (!stack.isEmpty()) {
                        int[] nums = stack.pop();
                        int x = nums[0], y = nums[1];
                        board[x][y] = '.';
                        if (board[x][y] != word.charAt(index)) {
                            continue;
                        } else {
                            index++;
                        }

                        if (index == word.length()) {
                            return true;
                        }
                        for (int k = 0; k < 4; k++) {
                            int xNew = x + xMove[k], yNew = y + yMove[k];
                            if (xNew < 0 || xNew >= row || yNew < 0 || yNew >= column) {
                                continue;
                            }
                            stack.push(new int[]{xNew, yNew});
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isValid(String s) {

        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (map.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (map.get(stack.peek()) == null || map.get(stack.peek()) != c) {
                        return false;
                    }
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    public static int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int flag = nums[left];
            int l = left, r = right;
            while (l < r) {
                while (l < r && flag < nums[right]) {
                    r--;
                }
                if (l < r) {
                    nums[l] = nums[r];
                    l++;
                }
                while (l < r && flag > nums[left]) {
                    l++;
                }
                if (l < r) {
                    nums[r] = nums[l];
                    r--;
                }
            }
            nums[l] = flag;
            quickSort(nums, left, l - 1);
            quickSort(nums, r + 1, right);
        }
    }

    public static int longestPalindrome(String s) {
        int[] nums = new int[58];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'A']++;
        }
        int result = 0;
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                result = result + nums[i];
            } else {
                flag = false;
                result = result + nums[i] - 1;
            }

        }
        return flag ? result : result + 1;
    }

    public static int countCharacters(String[] words, String chars) {
        int result = 0;
        for (String str : words) {
            char[] charArray = str.toCharArray();
            String temp = chars;
            boolean flag = true;
            for (int i = 0; i < charArray.length; i++) {
                int index = temp.indexOf(String.valueOf(charArray[i]));
                if (index > -1) {
                    temp = temp.substring(0, index) + temp.substring(index + 1);
                } else {
                    flag = false;
                }
            }
            result = result + (flag ? str.length() : 0);
        }
        return result;
    }

    public static boolean hasGroupsSizeX(int[] deck) {
        Arrays.sort(deck);
        int length = deck.length;
        int l = length % 2 == 0 ? length / 2 : length / 2 - 1;
        for (int i = 1; i <= l; i++) {
            if (length % i != 0) {
                continue;
            }
            int temp = 1;
            int count = 0;
            int len = length / i;
            while (temp <= i) {
                if (deck[(temp - 1) * len] == deck[len * temp - 1]) {
                    count++;
                    temp++;
                } else {
                    break;
                }

            }
            if (count == i) {
                return true;
            }
        }
        return false;
    }

    //    private static int[] parent = new int[20000];
//
//    public static int removeStones(int[][] stones) {A
//        int n = stones.length;
//        if (n == 0) {
//            return 0;
//        }
//        //初始化并查集
//        for (int i = 0; i < parent.length; i++) {
//            parent[i] = i;
//        }
//        for (int i = 0; i < n; i++) {
//            union(stones[i][0], stones[i][1] + 10000);
//        }
//        Set<Integer> set = new HashSet<>();
//        for (int i = 0; i < n; i++) {
//            set.add(findFather(stones[i][0]));
//        }
//        return n - set.size();
//    }
//
//    private static int findFather(int x) {
//        int a = x;
//        while (x != parent[x]) {
//            x = parent[x];
//        }
//        while (a != parent[a]) {
//            int z = a;
//            a = parent[a];
//            parent[z] = x;
//        }
//        return x;
//    }
//
//    private static void union(int a, int b) {
//        int aFather = findFather(a), bFather = findFather(b);
//        if (aFather != bFather) {
//            parent[aFather] = bFather;
//        }
//
//    }
    public static int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    Stack<int[]> stack = new Stack<>();
                    stack.push(new int[]{i, j});
                    grid[i][j] = 0;
                    int area = 1;
                    while (!stack.isEmpty()) {
                        int[] nums = stack.pop();
                        int x = nums[0], y = nums[1];
                        if (x - 1 >= 0 && grid[x - 1][y] == 1) {
                            grid[x - 1][y] = 0;
                            stack.push(new int[]{x - 1, y});
                            area++;
                        }
                        if (x + 1 < grid.length && grid[x + 1][y] == 1) {
                            grid[x + 1][y] = 0;
                            stack.push(new int[]{x + 1, y});
                            area++;
                        }
                        if (y - 1 >= 0 && grid[x][y - 1] == 1) {
                            grid[x][y - 1] = 0;
                            stack.push(new int[]{x, y - 1});
                            area++;
                        }
                        if (y + 1 < grid[i].length && grid[x][y + 1] == 1) {
                            grid[x][y + 1] = 0;
                            stack.push(new int[]{x, y + 1});
                            area++;
                        }
                    }
                    result = Math.max(result, area);
                }
            }
        }
        return result;
    }

    public static int maxAreaOfIsland1(int[][] grid) {
        int ans = 0;
        for (int i = 0; i != grid.length; ++i)
            for (int j = 0; j != grid[0].length; ++j) {
                int cur = 0;
                Stack<int[]> stack = new Stack<>();
                stack.push(new int[]{i, j});
                while (!stack.empty()) {
                    int[] nums = stack.pop();
                    int cur_i = nums[0], cur_j = nums[1];
                    if (cur_i < 0 || cur_j < 0 || cur_i == grid.length || cur_j == grid[0].length || grid[cur_i][cur_j] != 1)
                        continue;
                    ++cur;
                    grid[cur_i][cur_j] = 0;
                    int[] di = new int[]{0, 0, 1, -1};
                    int[] dj = new int[]{1, -1, 0, 0};
                    for (int index = 0; index != 4; ++index) {
                        int next_i = cur_i + di[index], next_j = cur_j + dj[index];
                        stack.push(new int[]{next_i, next_j});
                    }
                }
                ans = Math.max(ans, cur);
            }
        return ans;
    }

    // public int minimumLengthEncoding(String[] words) {
    //    Arrays.sort(words,(a,b)->b.length()-a.length());
    //    String sb="";
    //    for(String str:words){
    //        if(!sb.contains(str+"#")){
    //            sb=sb+str+"#";
    //        }
    //    }
    //    return sb.length();
    // }
    public static int maxDistance(int[][] grid) {
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }
        int row = grid.length;
        int column = grid[0].length;
        if (queue.size() == row * column || queue.isEmpty()) {
            return -1;
        }
        while (!queue.isEmpty()) {
            int[] nums = queue.poll();
            int x = nums[0], y = nums[1];
            if (x - 1 >= 0 && grid[x - 1][y] != 0) {
                queue.add(new int[]{x - 1, y});
                grid[x - 1][y] = grid[x][y] + 1;
            }
            if (x + 1 < row && grid[x + 1][y] != 0) {
                queue.add(new int[]{x + 1, y});
                grid[x + 1][y] = grid[x][y] + 1;
            }
            if (y - 1 >= 0 && grid[x][y - 1] != 0) {
                queue.add(new int[]{x, y - 1});
                grid[x - 1][y] = grid[x][y] + 1;
            }
            if (y + 1 < column && grid[x][y + 1] != 0) {
                queue.add(new int[]{x, y + 1});
                grid[x - 1][y] = grid[x][y] + 1;
            }
            if (queue.isEmpty()) {
                return grid[x][y] - 1;
            }
        }
        return 0;
    }

//    public int minimumLengthEncoding(String[] words) {
//        int len = 0;
//        Trie trie = new Trie();
//        for (String str : words) {
//            len = len + trie.insert(str);
//        }
//        return len;
//    }
//
//    class Trie {
//        private TrieNode trieNode = new TrieNode();
//
//        public int insert(String str) {
//            TrieNode[] trieRoot = trieNode.trieRoot;
//            boolean isNew = false;
//            for (int i = str.length() - 1; i >= 0; i++) {
//                char c = str.charAt(i);
//                if (trieRoot[c - 'a'] == null) {
//                    isNew = true;
//                    trieRoot[c - 'a'] = new TrieNode();
//                }
//                trieRoot = trieRoot[c - 'a'].trieRoot;
//            }
//            return isNew ? str.length() + 1 : 0;
//        }
//    }
//
//    class TrieNode {
//        TrieNode[] trieRoot = new TrieNode[26];
//    }


    public static int lastRemaining(int n, int m) {
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = (m + result) % i;
        }
        return result;
    }

    //["LFUCache","put","put","get","put","get","get","put","get","get","get"]
    //[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
    //["LFUCache","put","put","put","put","get"]
    //[[2],[3,1],[2,1],[2,2],[4,4],[2]]
    //["LFUCache","put","put","get","get","put","get","get","get"]
    //[[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]
    //["LFUCache","put","put","put","put","put","get","put","get","get","put","get","put","put","put","get","put","get","get","get","get","put","put","get","get","get","put","put","get","put","get","put","get","get","get","put","put","put","get","put","get","get","put","put","get","put","put","put","put","get","put","put","get","put","put","get","put","put","put","put","put","get","put","put","get","put","get","get","get","put","get","get","put","put","put","put","get","put","put","put","put","get","get","get","put","put","put","get","put","put","put","get","put","put","put","get","get","get","put","put","put","put","get","put","put","put","put","put","put","put"]
    //[[10],[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],
    // [4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],
    //public static void main(String[] args) {
//        LFUCache lfuCache = new Solution().new LFUCache(2);
//        lfuCache.put(1, 1);
//        lfuCache.put(2, 2);
//        System.out.println(lfuCache.get(1));
//        lfuCache.put(3, 3);
//        System.out.println(lfuCache.get(2));
//        System.out.println(lfuCache.get(3));
//        lfuCache.put(4, 4);
//        System.out.println(lfuCache.get(1));
//        System.out.println(lfuCache.get(3));
//        System.out.println(lfuCache.get(4));
    // [3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],
    // [8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],
    // [4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]

//        System.out.println(lfuCache.get(3));
//        System.out.println(lfuCache.get(4));
    // System.out.println(lfuCache.get(2));
//        lfuCache.put(4,4);
//        lfuCache.get(1);
//        lfuCache.get(3);
//        lfuCache.get(4);
//        System.out.println(System.currentTimeMillis());
//        System.out.println(System.currentTimeMillis());
//        ListNode listNode = new ListNode(1);
//        listNode.next = new ListNode(2);
//        reverseKGroup(listNode, 2);
//    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        int i = 0;
        ListNode h = head;
        ListNode temp = new ListNode(0);
        ListNode curr = temp;
        ListNode result = new ListNode(0);
        ListNode currResult = result;
        while (head != null || i == k) {
            if (i == k) {
                ListNode reverse = reverse(temp.next);
                currResult.next = reverse;
                currResult = h;
                h = head;
                temp = new ListNode(0);
                curr = temp;
                i = 0;
            } else {
                ListNode next = head.next;
                head.next = null;
                curr.next = head;
                curr = curr.next;
                head = next;
                i++;
            }
        }
        currResult.next = temp.next;
        return result.next;

    }

    private static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode listNode = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return listNode;
    }

    class LFUCache {
        Map<Integer, Node> map = new HashMap<>();
        Map<Integer, LinkedList<Node>> linkedListMap = new HashMap<>();
        //最大容量
        int capacity;
        // //最小次数
        int minTime = 1;

        public LFUCache(int capacity) {
            this.capacity = capacity;
        }


        public int get(int key) {
            if (this.capacity == 0) {
                return -1;
            }
            if (map.containsKey(key)) {
                Node node = map.get(key);
                Integer count = node.count;
                LinkedList<Node> linkedList = linkedListMap.get(count);
                linkedList.remove(node);
                if (linkedList.size() == 0) {
                    linkedListMap.remove(count);
                }
                if (linkedListMap.get(minTime) == null) {
                    minTime++;
                }
                count++;
                node.count = count;
                if (linkedListMap.containsKey(count)) {
                    LinkedList<Node> list = linkedListMap.get(count);
                    list.addFirst(node);
                    linkedListMap.put(count, list);
                } else {
                    LinkedList<Node> list = new LinkedList<>();
                    list.addFirst(node);
                    linkedListMap.put(count, list);
                }
                return node.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (this.capacity == 0) {
                return;
            } else if (map.containsKey(key)) {
                Node node = map.get(key);
                int count = node.count;
                LinkedList<Node> linkedList = linkedListMap.get(count);
                linkedList.remove(node);
                if (linkedList.size() == 0) {
                    linkedListMap.remove(count);
                }
                if (linkedListMap.get(minTime).size() == 0) {
                    minTime++;
                }
                count++;
                node.count = count;
                node.value = value;
                if (linkedListMap.containsKey(count)) {
                    LinkedList<Node> list = linkedListMap.get(count);
                    list.addFirst(node);
                    linkedListMap.put(count, list);
                } else {
                    LinkedList<Node> list = new LinkedList<>();
                    list.addFirst(node);
                    linkedListMap.put(count, list);
                }
                map.put(key, node);
            } else {
                if (map.size() < capacity) {
                    Node node = new Node(key, value, 1);
                    map.put(key, node);
                    minTime = 1;
                    if (linkedListMap.containsKey(node.count)) {
                        LinkedList<Node> list = linkedListMap.get(node.count);
                        list.addFirst(node);
                        linkedListMap.put(node.count, list);
                    } else {
                        LinkedList<Node> list = new LinkedList<>();
                        list.addFirst(node);
                        linkedListMap.put(node.count, list);
                    }
                } else {
//                     int min = linkedListMap.keySet().stream().sorted(Comparator.comparingInt(a -> a))
//                             .collect(Collectors.toList()).get(0);
                    LinkedList<Node> list = linkedListMap.get(minTime);
                    Node removeLast = list.removeLast();
                    map.remove(removeLast.key);
                    if (list.size() == 0) {
                        linkedListMap.remove(minTime);
                    }
                    Node node = new Node(key, value, 1);
                    minTime = 1;
                    map.put(key, node);
                    if (linkedListMap.containsKey(node.count)) {
                        LinkedList<Node> linkedList = linkedListMap.get(node.count);
                        linkedList.addFirst(node);
                        linkedListMap.put(node.count, linkedList);
                    } else {
                        LinkedList<Node> linkedList = new LinkedList<>();
                        linkedList.addFirst(node);
                        linkedListMap.put(node.count, linkedList);
                    }
                }
            }
        }

        class Node {
            int key;
            int value;
            int count;

            public Node(Integer key, Integer value, Integer count) {
                this.key = key;
                this.value = value;
                this.count = count;
            }
        }
    }

    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int x1 = start1[0], y1 = start1[1];
        int x2 = end1[0], y2 = end1[1];

        int x3 = start2[0], y3 = start2[1];
        int x4 = end2[0], y4 = end2[1];

        double[] ans = new double[2];
        Arrays.fill(ans, Double.MAX_VALUE);
        // 判断两直线是否平行
        if ((y4 - y3) * (x2 - x1) == (y2 - y1) * (x4 - x3)) {
            // 判断两直线是否重叠
            if ((y2 - y1) * (x3 - x1) == (y3 - y1) * (x2 - x1)) {
                // 判断 (x3, y3) 是否在「线段」(x1, y1)~(x2, y2) 上
                if (isInside(x1, y1, x2, y2, x3, y3)) {
                    updateRes(ans, x3, y3);
                }
                // 判断 (x4, y4) 是否在「线段」(x1, y1)~(x2, y2) 上
                if (isInside(x1, y1, x2, y2, x4, y4)) {
                    updateRes(ans, (double) x4, (double) y4);
                }
                // 判断 (x1, y1) 是否在「线段」(x3, y3)~(x4, y4) 上
                if (isInside(x3, y3, x4, y4, x1, y1)) {
                    updateRes(ans, (double) x1, (double) y1);
                }
                // 判断 (x2, y2) 是否在「线段」(x3, y3)~(x4, y4) 上
                if (isInside(x3, y3, x4, y4, x2, y2)) {
                    updateRes(ans, (double) x2, (double) y2);
                }
            }
        } else {
            // 联立方程得到 t1 和 t2 的值
            double t1 = (double) (x3 * (y4 - y3) + y1 * (x4 - x3) - y3 * (x4 - x3) - x1 * (y4 - y3)) / ((x2 - x1) * (y4 - y3) - (x4 - x3) * (y2 - y1));
            double t2 = (double) (x1 * (y2 - y1) + y3 * (x2 - x1) - y1 * (x2 - x1) - x3 * (y2 - y1)) / ((x4 - x3) * (y2 - y1) - (x2 - x1) * (y4 - y3));
            // 判断 t1 和 t2 是否均在 [0, 1] 之间
            if (t1 >= 0.0 && t1 <= 1.0 && t2 >= 0.0 && t2 <= 1.0) {
                ans[0] = x1 + t1 * (x2 - x1);
                ans[1] = y1 + t1 * (y2 - y1);
            }
        }
        if (ans[0] == Double.MAX_VALUE) {
            return new double[0];
        }
        return ans;
    }

    // 判断 (x, y) 是否在「线段」(x1, y1)~(x2, y2) 上
    // 这里的前提是 (x, y) 一定在「直线」(x1, y1)~(x2, y2) 上
    private boolean isInside(int x1, int y1, int x2, int y2, int x, int y) {
        // 若与 x 轴平行，只需要判断 x 的部分
        // 若与 y 轴平行，只需要判断 y 的部分
        // 若为普通线段，则都要判断
        return (x1 == x2 || (Math.min(x1, x2) <= x && x <= Math.max(x1, x2)))
                && (y1 == y2 || (Math.min(y1, y2) <= y && y <= Math.max(y1, y2)));
    }

    private void updateRes(double[] ans, double x, double y) {
        if (x < ans[0] || (x == ans[0] && y < ans[1])) {
            ans[0] = x;
            ans[1] = y;
        }
    }
}
