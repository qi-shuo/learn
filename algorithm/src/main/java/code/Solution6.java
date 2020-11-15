package code;


import java.util.*;
import java.util.stream.Collectors;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-05-19 10:15
 */
public class Solution6 {
    public static void main(String[] args) {
//        String str = "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga";
//        System.out.println(validPalindrome(str));
//        System.out.println(isPalindrome("0P"));
//        int[][] nums = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
//        reconstructQueue(nums);
        //longestPalindrome("babad");
        //System.out.println(maxSlidingWindow(new int[]{9, 10, 9, -7, -4, -8, 2, -6}, 5));
        //System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
//        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
//        System.out.println(findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0}));
//        System.out.println(reverseWords("a good   example"));
//        System.out.println(simplifyPath("/a/../../b/../c//.//"));
        //System.out.println(search(new int[]{1, 3, 1, 1, 1}, 3));
        //System.out.println(getPermutation(4, 9));
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0'},
                {'1', '0', '1', '1'},
                {'1', '0', '1', '1'},
                {'1', '1', '1', '1'}};
        //char[][] matrix = new char[][]{{'0', '1'}};
//        System.out.println(maximalSquare(matrix));
//        List<List<Integer>> triangle = new ArrayList<>();
//        triangle.add(Collections.singletonList(2));
//        triangle.add(Arrays.asList(3, 4));
//        triangle.add(Arrays.asList(6, 5, 7));
//        triangle.add(Arrays.asList(4, 1, 8, 3));
//        System.out.println(minimumTotal(triangle));
//        System.out.println(mySqrt(2147483647));
//        System.out.println(numPairsDivisibleBy60(new int[]{60, 60, 60}));
        //System.out.println(smallestDivisor(new int[]{19}, 5));
        //System.out.println(removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
        // System.out.println(threeSumMulti(new int[]{1, 1, 2, 2, 2, 2}, 5));
        System.out.println(nextGreaterElement(1999999999));

    }

    public static int nextGreaterElement(int n) {
        String str = n + "";
        char[] chars = str.toCharArray();
        int index = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (i == 0) {
                return -1;
            }
            if (chars[i] > chars[i - 1]) {
                index = i - 1;
                break;
            }
        }
        for (int i = chars.length - 1; i > index; i--) {
            if (chars[i] > chars[index]) {
                char temp = chars[i];
                chars[i] = chars[index];
                chars[index] = temp;
                break;
            }
        }
        int j = 1;
        int len = index + (chars.length - 1 - index) / 2;
        for (int i = chars.length - 1; i > len; i--) {
            char temp = chars[i];
            chars[i] = chars[index + j];
            chars[index + j] = temp;
            j++;
        }
        return Long.valueOf(String.valueOf(chars)).intValue();
    }

    public char findTheDifference(String s, String t) {
        int[] nums = new int[56];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i) - 'A']++;
        }
        for (int i = 0; i < t.length(); i++) {
            nums[t.charAt(i) - 'A']--;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                return (char) (i + 'A');
            }
        }
        return '\n';
    }

    public int maxNumberOfBalloons(String text) {
        int[] nums = new int[26];
        for (int i = 0; i < text.length(); i++) {
            nums[text.charAt(i)]++;
        }
        int b = nums['b'];
        int a = nums['a'];
        int l = nums['l'];
        int o = nums['o'];
        int n = nums['n'];
        int result = 0;
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            if (b < i || a < i || l < 2 * i || o < 2 * i || n < i) {
                break;
            }
            result++;
        }
        return result;
    }

    //public static int threeSumMulti(int[] A, int target) {

//        Arrays.sort(A);
//        int result = 0;
//        for (int i = 0; i < A.length; i++) {
//            int l = i + 1, r = A.length - 1;
//            while (l < r) {
//                int sum = A[i] + A[l] + A[r];
//                if (sum == target) {
//                    int temp = r;
//                    int rightCount = 1;
//                    while (l < temp && A[temp - 1] == A[temp]) {
//                        if (temp == l + 1) {
//                            break;
//                        }
//                        rightCount++;
//                        temp--;
//                    }
//                    temp = l;
//                    int leftCount = 1;
//                    while (temp < r && A[temp + 1] == A[temp]) {
//                        if (r == temp + 1) {
//                            break;
//                        }
//                        leftCount++;
//                        temp++;
//                    }
//                    l = l + leftCount;
//                    r = r - rightCount;
//                    result = result + rightCount * leftCount;
//                } else if (sum > target) {
//                    r--;
//                } else {
//                    l++;
//                }
//            }
//        }
//        return result;
    //}

    public static int removeDuplicates(int[] nums) {

        int j = 1, count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;

            } else {
                count = 1;
            }

            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }

    public static int smallestDivisor(int[] nums, int threshold) {
        Arrays.sort(nums);
        int l = 1, r = nums[nums.length - 1];
        int result = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            int sum = 0;
            for (int i : nums) {
                sum = sum + (int) Math.ceil(i / (double) mid);
            }
            if (sum <= threshold) {
                result = Math.min(mid, result);
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return result;
    }

    public static int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int i = 0; i < time.length; i++) {
            if (map.containsKey(60 - time[i] % 60)) {
                result = result + map.get(60 - time[i] % 60);
            }
            map.put(time[i] % 60, map.getOrDefault(time[i] % 60, 0) + 1);
        }
        return result;
    }

    public static int mySqrt(int x) {
        int left = 1, right = x;
        while (left < right) {
            long mid = left + (right - left) / 2;
            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid > x) {
                right = (int) mid - 1;
            } else {
                left = (int) mid + 1;
            }
        }
        return ((long) left * (long) left) > x ? left - 1 : left;
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        int pre = 0;
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> list = triangle.get(i);
            if (pre + 1 == list.size()) {
                dp[i] = list.get(pre);
            } else {
                if (list.get(pre) <= list.get(pre + 1)) {
                    dp[i] = list.get(pre);
                } else {
                    dp[i] = list.get(pre + 1);
                    pre = pre + 1;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < dp.length; i++) {
            sum = sum + dp[i];
        }
        return sum;
    }

    public static int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int column = matrix[0].length;
        int[][] dp = new int[row][column];
        int result = 0;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == '1') {
                result = 1;
            }
            dp[i][0] = matrix[i][0] - '0';
        }
        for (int i = 0; i < column; i++) {
            if (matrix[0][i] == '1') {
                result = 1;
            }
            dp[0][i] = matrix[0][i] - '0';
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }
        return result * result;
    }

    public static String getPermutation(int n, int k) {
        List<String> list = new ArrayList();
        boolean[] flags = new boolean[n];
        for (int i = 0; i < n; i++) {
            flags[i] = true;
            dfs(i, n, flags, list, i + 1 + "");
            flags[i] = false;
        }
        list.stream().map(str -> Integer.valueOf(str)).sorted((a, b) -> a - b).collect(Collectors.toList());
        return String.valueOf(list.get(k - 1));
    }

    private static void dfs(int start, int n, boolean[] flags, List<String> list, String str) {
        if (str.length() == n) {
            list.add(str);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!flags[i]) {
                flags[i] = true;
                String temp = str;
                str = str + (i + 1);
                dfs(i, n, flags, list, str);
                str = temp;
                flags[i] = false;
            }
        }
    }

    public static boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        //数组长度是1的直接判断元素即可
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            //中间位置
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //判断是左端还是右段
            if (nums[mid] >= nums[left]) {
                //target >= nums[left]&&nums[mid]>target才会在左边,否则在右边
                if (target >= nums[left] && nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                //nums[mid]<target&&target <= nums[right]才会在右边，否则就是左边
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    int row;
    int column;

    public int maxAreaOfIsland(int[][] grid) {
        this.row = grid.length;
        if (row == 0) {
            return 0;
        }
        this.column = grid[0].length;
        int result = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (grid[i][j] == 1) {
                    int dfs = dfs(i, j, grid);
                    result = Math.max(result, dfs);
                }
            }

        }
        return result;
    }

    private int dfs(int x, int y, int[][] grid) {

        if (x < 0 || x >= row || y < 0 || y >= column || grid[x][y] == 0) {
            return 0;
        }
        grid[x][y] = 0;
        return dfs(x + 1, y, grid) + dfs(x - 1, y, grid) + dfs(x, y + 1, grid) + dfs(x, y - 1, grid) + 1;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 3) {
            return list;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int temp = nums[i];
            int left = i + 1, right = i + 2;
            while (left < right && right < nums.length) {
                int sum = temp + nums[left] + nums[right];
                if (sum == 0) {
                    list.add(Arrays.asList(i, left, right));
                    left++;
                    right++;
                } else if (sum > 0) {
                    left++;
                } else {
                    right++;
                }
            }
        }
        return list;
    }

    public static String simplifyPath(String path) {
        String[] strs = path.split("/");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < strs.length; i++) {
            if ("".equals(strs[i])) {
                continue;
            }
            if (stack.isEmpty() && "..".equals(strs[i])) {
                continue;
            }
            if (".".equals(strs[i])) {
                continue;
            }
            if (!stack.isEmpty() && "..".equals(strs[i])) {
                stack.pop();
                continue;
            }
            stack.push(strs[i]);
        }
        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            return "/";
        }
        while (!stack.isEmpty()) {
            sb.insert(0, "/" + stack.pop());
        }
        return sb.toString();
    }


    public static String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        String[] strs = s.split(" ");
        for (int i = strs.length - 1; i >= 0; i--) {
            if (" ".equals(strs[i])) {
                continue;
            }
            if (i > 0) {
                sb.append(strs[i]).append(" ");
            } else {
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        int a = 0, b = 0, index = 0;
        while (a < nums1.length || b < nums2.length) {
            if (a >= nums1.length) {
                nums[index++] = nums2[b++];
            } else if (b >= nums2.length) {
                nums[index++] = nums1[a++];
            } else if (nums1[a] > nums2[b]) {
                nums[index++] = nums2[b++];
            } else {
                nums[index++] = nums1[a++];
            }
        }
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        return len % 2 == 0 ? (nums[len / 2] + nums[(len / 2) - 1]) / 2.0 : nums[len / 2];
    }

    public String addStrings(String num1, String num2) {
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();
        while (index1 >= 0 || index2 >= 0) {
            int a = 0, b = 0;
            if (index1 >= 0) {
                a = Integer.valueOf(String.valueOf(num1.charAt(index1)));
            }
            if (index2 >= 0) {
                b = Integer.valueOf(String.valueOf(num2.charAt(index2)));
            }
            int temp = a + b + carry;
            carry = temp / 10;
            result.insert(0, temp % 10);
            index1--;
            index2--;
        }
        if (carry > 0) {
            result.insert(0, carry);
        }
        return result.toString();
    }

    public static String minWindow(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen < tLen || sLen == 0) {
            return "";
        }
        int[] sNums = new int[56];
        int[] tNums = new int[56];
        for (int i = 0; i < tLen; i++) {
            tNums[t.charAt(i) - 'A']++;
        }
        int l = 0, r = 0, count = 0, minLen = Integer.MAX_VALUE, start = 0, end = 0;
        while (r < sLen) {
            char c = s.charAt(r);
            sNums[c - 'A']++;
            if (tNums[c - 'A'] > 0 && sNums[c - 'A'] <= tNums[c - 'A']) {
                count++;
            }
            r++;
            while (count == tLen) {
                char ch = s.charAt(l);
                if (sNums[ch - 'A'] == tNums[ch - 'A']) {
                    if (r - l < minLen) {
                        start = l;
                        end = r;
                        minLen = r - l;
                    }
                    count--;
                }
                sNums[ch - 'A']--;
                l++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, end);
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] nums1 = new int[26];
        int[] nums2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            nums1[s1.charAt(i) - 'a']++;
        }
        int l = 0, r = 0, count = 0;
        while (r < s2.length()) {
            char c = s2.charAt(r);
            nums2[c - 'a']++;
            if (nums1[c - 'a'] > 0 && nums2[c - 'a'] <= nums1[c - 'a']) {
                count++;
            }
            r++;
            while (r - l >= s1.length()) {
                char ch = s2.charAt(l);
                if (count == s1.length()) {
                    return true;
                }
                if (nums2[ch - 'a'] <= nums1[ch - 'a']) {
                    count--;
                }
                nums2[ch - 'a']--;
                l++;
            }
        }
        return false;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> b - a);
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        result[0] = map.firstKey();
        int index = 1;
        for (int i = k; i < nums.length; i++) {
            if (map.containsKey(nums[i - k])) {
                int value = map.get(nums[i - k]) - 1;
                if (value == 0) {
                    map.remove(nums[i - k]);
                } else {
                    map.put(nums[i - k], value);
                }
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            result[index++] = map.firstKey();
        }
        return result;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }


    public static String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int max = 0, l = 0;
        String result = "";
        while (l < s.length()) {
            int len0 = getLength(s, l, l);
            int len1 = getLength(s, l, l + 1);
            int len = Math.max(len0, len1);
            if (len > max) {
                max = len;
                int left = l - (len - 1) / 2;
                int right = l + len / 2;
                result = s.substring(left, right + 1);
            }
            l++;
        }
        return result;
    }

    private static int getLength(String s, int l, int r) {
        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            } else {
                break;
            }
        }
        return r - l - 1;
    }

    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (o1, o2) -> {
            // if the heights are equal, compare k-values
            return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
        });

        List<int[]> output = new LinkedList<>();
        for (int[] p : people) {
            output.add(p[1], p);
        }

        int n = people.length;
        return output.toArray(new int[n][2]);
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.add(entry);
            } else {
                Map.Entry<Integer, Integer> peek = queue.peek();
                if (peek.getValue() < entry.getValue()) {
                    queue.poll();
                    queue.add(entry);
                }
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }

    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
                continue;
            } else if (Character.toLowerCase(s.charAt(l)) ==
                    Character.toLowerCase(s.charAt(r))) {
                l++;
                r--;
                continue;
            }
            if ((!Character.isLowerCase(s.charAt(l))
                    && !Character.isUpperCase(s.charAt(l)))
                    && (s.charAt(l) < '0' || s.charAt(l) > '9')) {
                l++;
                continue;
            }
            if (!Character.isLowerCase(s.charAt(r))
                    && !Character.isUpperCase(s.charAt(r))
                    && (s.charAt(r) < '0' || s.charAt(r) > '9')) {
                r--;
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean validPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                if (l + 1 == r) {
                    return true;
                }
                return valid(s, l + 1, r) || valid(s, l, r - 1);
            }
        }
        return true;

    }

    private static boolean valid(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}
