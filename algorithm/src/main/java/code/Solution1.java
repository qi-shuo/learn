package code;

import java.util.*;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-04-13 14:09
 */
public class Solution1 {
    public static boolean isOneBitCharacter(int[] bits) {
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] == 1) {
                i++;
                if (i == bits.length - 1) {
                    return false;
                }
            }

        }
        return true;
    }


    public static int countDigitOne(int n) {
        int count = 0;
        for (int i = 1; i <= n; i = i * 10) {
            int xyzd = n / i;
            int abc = n % i;
            int d = xyzd % 10;
            int xyz = xyzd / 10;
            count = count + xyz * i;
            if (d == 1) {
                count = count + abc + 1;
            } else if (d > 1) {
                count = count + i;
            }
            //如果不加这句的话，虽然 i 一直乘以 10，但由于溢出的问题
            //i 本来要大于 n 的时候，却小于了 n 会再次进入循环
            //此时代表最高位是 1 的情况也考虑完成了
            if (xyz == 0) {
                break;
            }
        }
        return count;
    }

    public static int trailingZeroes(int n) {
        long count = 1;
        for (int i = 1; i <= n; i++) {
            count = count * i;
        }
        int result = 0;
        while (count % 10 == 0) {
            count = count / 10;
        }
        return result;
    }

    public static String removeKdigits(String num, int k) {
        char[] chars = num.toCharArray();
        if (k >= chars.length) {
            return "0";
        }
        Stack<Integer> stack = new Stack<>();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            while (!stack.isEmpty() && chars[stack.peek()] > chars[i]) {
                if (count < k) {
                    count++;
                } else {
                    break;
                }
            }
            stack.push(i);
        }
        for (int i = count; i < k; i++) {
            stack.pop();
        }
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        for (int digit : stack) {
            if (leadingZero && chars[digit] == '0') continue;
            leadingZero = false;
            ret.append(chars[digit]);
        }

        /* return the final string  */
        if (ret.length() == 0) return "0";
        return ret.toString();
        // StringBuilder sb = new StringBuilder();
        // for (int i = 0; i < chars.length; i++) {
        //     if (!output.contains(i)) {
        //         if(sb.toString().length()!=0||chars[i]!='0'||i==chars.length-1){
        //             sb.append(chars[i]);
        //         }
        //     }
        // }
        // String result = sb.toString();
        // if (output.size() == k) {
        //     return result;
        // } else {
        //     if(output.size()==0){
        //         return result.substring(0, result.length() - k);
        //     }else{
        //         return result.substring(0, result.length() - output.size());
        //     }
        // }
    }

    public static void main(String[] args) {
//        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
//        for(GarbageCollectorMXBean b : l) {
//            System.out.println(b.getName());
//        }
//        int[] bits = new int[]{1, 1, 1, 0};
//        System.out.println(isOneBitCharacter(bits));
        //System.out.println(countDigitOne(1410065408));
//        System.out.println(trailingZeroes(13));
//        System.out.println(Integer.valueOf("0200"));
        //       System.out.println(removeKdigits("1432219", 3));
//        char[][] nums = new char[][]{{'1', '1'},
//                {'1', '1'}};
//        System.out.println(maximalSquare(nums));
        //System.out.println(convert("AB", 1));
//        int[][] matrix = new int[][]{
//                {1, 0, 1, 1, 0, 0, 1, 0, 0, 1},
//                {0, 1, 1, 0, 1, 0, 1, 0, 1, 1},
//                {0, 0, 1, 0, 1, 0, 0, 1, 0, 0},
//                {1, 0, 1, 0, 1, 1, 1, 1, 1, 1},
//                {0, 1, 0, 1, 1, 0, 0, 0, 0, 1},
//                {0, 0, 1, 0, 1, 1, 1, 0, 1, 0},
//                {0, 1, 0, 1, 0, 1, 0, 0, 1, 1},
//                {1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
//                {1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
//                {1, 1, 1, 1, 0, 1, 0, 0, 1, 1}};
//        int[][] nums = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
//        //Arrays.sort(nums, Comparator.comparingInt(a -> a[1]));
//        System.out.println(merge(nums));
//        List<String> list = new ArrayList<>();
//        list.add("hot");
//        list.add("dot");
//        list.add("dog");
//        list.add("lot");
//        list.add("log");
//        list.add("cog");
//        ladderLength("hit", "cog", list);
//        int[] nums = new int[]{1, 1, 1, 2, 2, 2, 3, 3};
//        System.out.println(hasGroupsSizeX(nums));
        //System.out.println(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
        //System.out.println(getMaxRepetitions("acb", 4, "ab", 2));
        //System.out.println(getMaxRepetitions("acb", 4, "ab", 2));
        //System.out.println(minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"}));
        //System.out.println(divide(10, 3));
//        System.out.println(strStr("babba", "bbb"));
//        System.out.println(repeatedStringMatch("aaaaaaaaaaaaaaaaaaaaaab", "ba"));
//        System.out.println(numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
//        System.out.println(divide(10, 3));
//        System.out.println(Math.pow(2, 3));
//        System.out.println(2 << 2);


    }


    public static int divide(int dividend, int divisor) {
        int symbol = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0) ? 1 : -1;
        if (dividend == Integer.MAX_VALUE && divisor == 1) return dividend;
        if (dividend == Integer.MAX_VALUE && divisor == -1) return -dividend;
        if (dividend == Integer.MIN_VALUE && divisor == 1) return -dividend;
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        long a = Math.abs(dividend), b = Math.abs(divisor);
        int result = 0;
        int i = 0;
        long temp = b;
        while (a >= b) {
            b = b << 1;
            if (a <= b) {
                result = result + (2 << i - 1);
                a = a - (b >> 1);
                b = temp;
                i = 0;
            } else {
                i++;
            }
        }

        return symbol > 0 ? result : -result;
    }


    public static int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || len < k) {
            return 0;
        }
        int left = 0, right = 0, count = 0, result = 0;
        while (right < len) {
            if (nums[right++] % 2 > 0) {
                count++;
            }
            if (count == k) {
                int l = 0, r = 0;
                int temp = right;
                while (nums[left++] % 2 == 0) {
                    l++;
                }
                while (temp < len && nums[temp++] % 2 == 0) {
                    r++;
                }
                result = result + (l + 1) * (r + 1);
                count = count - 1;
            }
        }
        return result;
    }

    public static int strStr(String haystack, String needle) {
        int hayLen = haystack.length(), needleLen = needle.length();
        if (hayLen == 0 && needleLen == 0) {
            return 0;
        }
        if (hayLen == 0) {
            return -1;
        }
        if (needleLen == 0) {
            return 0;
        }
        int left = 0;
        while (left < hayLen - needleLen + 1) {
            while (haystack.charAt(left) != needle.charAt(0)) {
                left++;
                if (left >= hayLen - needleLen + 1) {
                    return -1;
                }
            }
            int right = left + needle.length() - 1;
            int i = 0;
            int j = needle.length() - 1;
            boolean flag = true;
            int result = left;
            while (left < right) {
                if (haystack.charAt(left) == needle.charAt(i)
                        && haystack.charAt(right) == needle.charAt(j)) {
                    i++;
                    j--;
                    left++;
                    right--;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return result;
            }
            left = result + 1;
        }
        return -1;
    }

//    public static int divide(int dividend, int divisor) {
//        int symbol = ((long) dividend > 0 && (long) divisor > 0) || ((long) dividend < 0 && (long) divisor < 0) ? 1 : -1;
//        if (Math.abs((long) dividend) < Math.abs((long) divisor)) {
//            return 0;
//        } else if (Math.abs((long) dividend) == Math.abs((long) divisor)) {
//            return symbol;
//        }
//        if (divisor == 1) {
//            return dividend;
//        }
//        if (divisor == -1) {
//            if (Math.abs(dividend) == dividend) {
//                return Integer.MAX_VALUE;
//            } else {
//                return symbol * (Math.abs(dividend) / 1);
//            }
//        }
//        int count = 0;
//        long a = Math.abs((long) dividend);
//        long b = Math.abs((long) divisor);
//        long temp = b;
//        double result = 0;
//        while (a > temp) {
//            temp = temp << 1;
//            if (temp >= a) {
//                long diff = a - (temp >> 1);
//                result = result + Math.pow(2, count - 1);
//                a = diff;
//                temp = b;
//            } else {
//                count++;
//            }
//        }
//        return symbol > 0 ? (int) result : -(int) result;
//    }

    public static int minMutation(String start, String end, String[] bank) {
        LinkedList<String> queue = new LinkedList<>();
        boolean[] flags = new boolean[bank.length];
        queue.add(start);
        int result = 0;
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (int j = 0; j < bank.length; j++) {
                    if (verification(poll, bank[j])) {
                        if (bank[j].equals(end)) {
                            return result;
                        }
                        queue.add(bank[j]);
                        flags[j] = true;
                    }
                }
            }
        }
        return -1;
    }

    static List<String> list;

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < wordList.size(); i++) {
            list = new ArrayList<>();
            if (verification(beginWord, wordList.get(i))) {
                dfs(beginWord, endWord, wordList);
                if (list.get(list.size() - 1).equals(endWord)) {
                    list.add(0, beginWord);
                    result.add(list);
                }
            }
        }
        return result;
    }

    private static void dfs(String beginWord, String endWord, List<String> wordList) {
        for (int i = 0; i < wordList.size(); i++) {
            String str1 = wordList.get(i);
            if ("".equals(str1)) {
                continue;
            }
            wordList.set(i, "");
            if (verification(beginWord, str1)) {
                if (str1.equals(endWord)) {
                    list.add(endWord);
                } else {
                    list.add(str1);
                    dfs(str1, endWord, wordList);
                }
            }
            wordList.set(i, str1);
        }
    }

    private static boolean verification(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    public static int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (s1.length() == 0 || s2.length() == 0 || n1 == 0 || n2 == 0) return 0;
        char[] s1Chars = s1.toCharArray();
        char[] s2Chars = s2.toCharArray();
        int count = 0;
        int index = 0;
        //存储在每个s1字符串中可以匹配出的s2字符串的索引
        int[] indexr = new int[s2Chars.length + 1];
        //存储在每个s1字符串时匹配出的s2字符串的个数，可能是包含了前面一个s1循环节的部分
        int[] countr = new int[s2Chars.length + 1];
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < s1Chars.length; j++) {
                if (s1Chars[j] == s2Chars[index]) {
                    if (index == s2Chars.length - 1) {
                        count++;
                        index = 0;
                    } else {
                        index++;
                    }
                }
            }
            countr[i] = count;
            indexr[i] = index;
            //剪枝，跳出循环的判断
            //从计数的数组里面去找是否已经出现过该索引。
            //意味着已经出现重复的循环节了。就可以直接计算了。
            for (int k = 0; k < i && indexr[k] == index; k++) {
                int prev_count = countr[k];
                int pattern_count = ((n1 - 1 - k) / (i - k)) * (countr[i] - countr[k]);
                int remain_count = countr[k + (n1 - 1 - k) % (i - k)] - countr[k];
                return (prev_count + pattern_count + remain_count) / n2;
            }
        }
        return countr[n1 - 1] / n2;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        LinkedList<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        queue.add(beginWord);
        set.add(beginWord);
        int result = 0;
        while (!queue.isEmpty()) {
            result++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    if (verification(poll, wordList.get(j)) && !set.contains(wordList.get(j))) {
                        if (wordList.get(j).equals(endWord)) {
                            return result + 1;
                        }
                        queue.add(wordList.get(j));
                        set.add(wordList.get(j));
                    }
                }
            }
        }
        return 0;
    }

//    private static boolean verification(String str1, String str2) {
//        int count = 0;
//        for (int i = 0; i < str1.length(); i++) {
//            if (str1.charAt(i) != str2.charAt(i)) {
//                count++;
//            }
//            if (count > 1) {
//                return false;
//            }
//        }
//        return true;
//    }

    public static boolean hasGroupsSizeX(int[] deck) {
        if (deck.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < deck.length; i++) {
            map.put(deck[i], map.getOrDefault(deck[i], 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        int gcd = list.get(0);
        for (Integer i : list) {
            gcd = gcd(i, gcd);
        }
        return gcd > 1;
    }

    private static int gcd(int a, int b) {
        while (a % b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = 1;
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                result = Math.max(result, stack.size());
                max = Math.max(max, nums[stack.pop()]);

            }
            if (max > Integer.MIN_VALUE && nums[i] > max) {
                result = result + 1;
            }
            stack.push(i);
        }
        return Math.max(result, stack.size());
    }


    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        List<int[]> list = new ArrayList<>();
        for (int i = intervals.length - 2; i >= 0; i--) {
            int[] nums1 = intervals[i];
            int[] nums2;
            if (list.size() != 0) {
                nums2 = list.get(list.size() - 1);
                if (nums1[1] >= nums2[0] && nums1[1] <= nums2[1]) {
                    list.remove(list.size() - 1);
                    list.add(new int[]{Math.min(nums1[0], nums2[0]), nums2[1]});
                } else {
                    list.add(nums1);
                }
            } else {
                nums2 = intervals[i + 1];
                if (nums1[1] >= nums2[0] && nums1[1] <= nums2[1]) {
                    list.add(new int[]{Math.min(nums1[0], nums2[0]), nums2[1]});
                } else {
                    list.add(nums2);
                    list.add(nums1);
                }
            }
        }
        int[][] result = new int[list.size()][];
        for (int i = list.size() - 1; i >= 0; i--) {
            result[list.size() - i - 1] = list.get(i);
        }
        return result;
    }

    public static String convert(String s, int numRows) {

        List<StringBuffer> list = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuffer());
        }
        boolean flag = true;
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            list.get(index).append(s.charAt(i));
            index = flag ? index + 1 : index - 1;
            if (index == numRows - 1 || index == 0) {
                flag = !flag;
            }
        }
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            result.append(list.get(i).toString());
        }
        return result.toString();
    }

    public static int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int column = matrix[0].length;
        int[][] dp = new int[row][column];
        int max = 0;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == '1') {
                max = 1;
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < column; i++) {
            if (matrix[0][i] == '1') {
                max = 1;
                dp[0][i] = 1;
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

}

