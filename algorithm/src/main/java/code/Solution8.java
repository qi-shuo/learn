package code;

import java.util.*;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-06-04 21:24
 */
public class Solution8 {
    static int row;
    static int column;
    static String word;
    static char[][] board;


    public static boolean exist(char[][] board, String word) {
        row = board.length;

        if (row == 0) {
            return false;
        }
        Solution8.word = word;
        Solution8.board = board;
        column = board[0].length;
        boolean[][] flags = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (board[i][j] == word.charAt(i)) {
                    //flags[i][j] = true;
                    if (dfs(i, j, flags, 0)) {
                        return true;
                    }
                    //flags[i][j] = false;
                }
            }
        }
        return false;
    }

    private static boolean dfs(int x, int y, boolean[][] flags, int index) {
        if (index == word.length() - 1) {
            return true;
        }
        if (x < 0 || y < 0 || x >= row || y >= column || flags[x][y] || board[x][y] != word.charAt(index)) {
            return false;
        }
        flags[x][y] = true;
        boolean result = dfs(x + 1, y, flags, index + 1) || dfs(x - 1, y, flags, index + 1) ||
                dfs(x, y + 1, flags, index + 1) || dfs(x, y - 1, flags, index + 1);
        flags[x][y] = false;
        return result;
    }


    public static boolean equationsPossible(String[] equations) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (String str : equations) {
            if (str.charAt(1) == '=') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                union(parent, index1, index2);
            }
        }
        for (String str : equations) {
            if (str.charAt(1) == '!') {
                int index1 = str.charAt(0) - 'a';
                int index2 = str.charAt(3) - 'a';
                if (find(parent, index1) == find(parent, index2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public static int find(int[] parent, int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }

    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;

        public Employee(int id, int importance, List<Integer> subordinates) {
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }

    public static void main(String[] args) {
        //System.out.println("/a".split("/"));
//        List<Employee> list = new ArrayList<>();
//        list.add(new Employee(1, 2, Collections.singletonList(2)));
//        list.add(new Employee(2, 3, new ArrayList<>()));
//        System.out.println(getImportance(list, 2));
//        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
//        System.out.println(Arrays.asList(permutation("abd")));
//        System.out.println(findBestValue(new int[]{4, 9, 3}, 13));
//        System.out.println(convertToBase7(100));
        //System.out.println(removeSubfolders(new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}));
        System.out.println(addBinary("11","1"));
    }

    public static String addBinary(String a, String b) {
        if (a.length() == 0) {
            return b;
        }
        if (b.length() == 0) {
            return a;
        }
        int carry = 0;
        int i = a.length() - 1, j = b.length() - 1;
        StringBuffer sb = new StringBuffer();
        while (i >= 0 || j >= 0) {
            int sum;
            if (i < 0) {
                sum = carry + (b.charAt(j) - '0');
                j--;
            } else if (j < 0) {
                sum = carry + (a.charAt(i) - '0');
                i--;
            } else {
                sum = carry + (a.charAt(i) - '0') + (b.charAt(j) - '0');
                i--;
                j--;
            }
            carry = sum / 2;
            sb.insert(0, sum % 2);
        }
        if (carry > 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }

    public static List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, (s1, s2) -> s1.length() - s2.length());
        Set<String> set = new TreeSet<>((s1, s2) -> {
            if (s1.contains(s2 + "/"))
                return 0;
            return s1.compareTo(s2);
        });
        Collections.addAll(set, folder);
        return new ArrayList<>(set);
    }

    public static String convertToBase7(int num) {
        String ans = "";
        if (num < 0) {
            num = 0 - num;
            ans += "-";
        }

        StringBuilder sb = new StringBuilder();
        do {
            int mod = num % 7;
            sb.append(mod);
            num = num / 7;
        } while (num > 0);
        ans += sb.reverse().toString();
        return ans;

    }

    public static int findBestValue(int[] arr, int target) {
        int left = 0;
        int right = 0;
        // 注意：
        for (int num : arr) {
            right = Math.max(right, num);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            int sum = calculateSum(arr, mid);
            // 计算第 1 个使得转变后数组的和大于等于 target 的阈值 threshold
            if (sum < target) {
                // 严格小于的一定不是解
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 比较阈值线分别定在 left - 1 和 left 的时候与 target 的接近程度
        int sum1 = calculateSum(arr, left - 1);
        int sum2 = calculateSum(arr, left);
        if (target - sum1 <= sum2 - target) {
            return left - 1;
        }
        return left;
    }

    private static int calculateSum(int[] arr, int threshold) {
        int sum = 0;
        for (int num : arr) {
            sum += Math.min(num, threshold);
        }
        return sum;
    }


    public static String[] permutation(String S) {
        if (S.length() == 1) {
            return new String[]{S};
        }
        if (S.length() == 0) {
            return new String[0];
        }
        List<String> list = new ArrayList<>();
        char[] chas = S.toCharArray();
        Arrays.sort(chas);
        S = new String(chas);
        boolean[] flags = new boolean[S.length()];
        dfs(flags, list, S, "");
        return list.toArray(new String[0]);
    }

    private static void dfs(boolean[] flags, List<String> list, String S, String str) {
        if (str.length() == S.length()) {
            list.add(str);
            return;
        }
        for (int i = 0; i < S.length(); i++) {
            if (i > 0 && S.charAt(i) == S.charAt(i - 1) && !flags[i - 1]) {
                continue;
            }
            if (!flags[i]) {
                flags[i] = true;
                str = str + S.charAt(i);
                dfs(flags, list, S, str);
                str = str.substring(0, str.length() - 1);
                flags[i] = false;
            }
        }
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];
            ans += right - left + 1;
        }
        return ans;
    }


    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        String str = String.valueOf(x);
        int mid = str.length() / 2;
        for (int i = 0; i < mid; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int index = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s2.length(); i++) {
            sb.append(s2.charAt(i));
            String str = sb.toString();
            if (!s1.contains(str)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        return s1.equals(s2.substring(index, s2.length()) + sb.delete(index, sb.length()).toString());
    }

    public static int getImportance(List<Employee> employees, int id) {
        int result = 0;
        Employee em = null;
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            if (employee.id == id) {
                em = employee;
            }
            map.put(employee.id, employee);
        }
        if (em == null) {
            return result;
        }
        Stack<Employee> stack = new Stack<>();
        stack.push(em);
        while (!stack.isEmpty()) {
            Employee pop = stack.pop();
            result = result + pop.importance;
            for (Integer i : pop.subordinates) {
                stack.push(map.get(i));
            }
        }
        return result;
    }
}
