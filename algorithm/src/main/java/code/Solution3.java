package code;

import java.util.*;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-04-24 23:19
 */
public class Solution3 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    List<Node> list;

    public TreeNode recoverFromPreorder(String S) {
        String[] strings = S.split("-");
        this.list = new ArrayList<>();
        Node root = new Node();
        root.val = Integer.valueOf(strings[0]);
        root.depth = 0;
        list.add(root);
        int depth = 1;
        for (int i = 1; i < strings.length; i++) {
            if ("".equals(strings[i])) {
                depth++;
            } else {
                Node node = new Node();
                node.val = Integer.valueOf(strings[i]);
                node.depth = depth;
                list.add(node);
                depth = 1;
            }
        }
        return helper(0, list.size() - 1);
    }

    private TreeNode helper(int left, int right) {
        if (left > right) {
            return null;
        }
        Node node = list.get(left);
        TreeNode treeNode = new TreeNode(node.val);
        if (left == right) {
            return treeNode;
        }
        Integer index = null;
        for (int i = left + 2; i <= right; i++) {
            if (list.get(left + 1).depth.equals(list.get(i).depth)) {
                index = i;
                break;
            }
        }
        if (index != null) {
            treeNode.left = helper(left + 1, index - 1);
            treeNode.right = helper(index, right);
        } else {
            treeNode.left = helper(left + 1, right);
        }
        return treeNode;
    }

    static class Node {
        Integer val;
        Integer depth;
    }

    public static int findMin(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[left]) {
                if (nums[mid] < nums[right]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < nums[right]) {
                    right = mid;

                } else {
                    left = mid + 1;

                }
            }
        }
        return nums[left];
    }
    static int result;
    public static int findKthLargest(int[] nums, int k){
        quickSort(nums,0,nums.length-1,k);
        return nums[nums.length-k];
    }
    private static void quickSort(int[] arr,int start,int end,int k){
        if(start<end){
            int flag=arr[start];
            int l=start,r=end;
            while(l<r){
                while(l<r&&flag<arr[r]){
                    r--;
                }
                if(l<r){
                    arr[l]=arr[r];
                    l++;
                }
                while(l<r&&flag>arr[l]){
                    l++;
                }
                if(l<r){
                    arr[r]=arr[l];
                }
            }
            arr[l]=flag;
            if(l!=arr.length-k){
                quickSort(arr,start,l-1,k);
                quickSort(arr,l+1,end,k);
            }

        }

    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{-1,2,0},2));
//        Solution3 solution3 = new Solution3();
//        System.out.println(solution3.recoverFromPreorder("1-401--349---90--88"));
//        String str = "1-401--349---90--88";
//        String[] strings = str.split("-");
//        List<Node> list = new ArrayList<>();
//        Node root = new Node();
//        root.val = Integer.valueOf(strings[0]);
//        root.depth = 0;
//        list.add(root);
//        int depth = 1;
//        for (int i = 1; i < strings.length; i++) {
//            if ("".equals(strings[i])) {
//                depth++;
//            } else {
//                Node node = new Node();
//                node.val = Integer.valueOf(strings[i]);
//                node.depth = depth;
//                list.add(node);
//                depth = 1;
//            }
//        }
        //System.out.println(intToRoman(1994));
        //System.out.println(waysToChange(6));
//        int[] A = new int[]{3, 1, 2};
//        System.out.println(findMin(A));
    }

    public static int peakIndexInMountainArray(int[] A) {
        // for(int i=0;i<A.length-1;i++){
        //     if(A[i]>A[i+1]){
        //         return i;
        //     }
        // }
        // return 0;
        int left = 0, right = A.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] < A[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static int waysToChange(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int[] coins = new int[]{1, 5, 10, 25};

        for (int coin : coins) {
            for (int i = 1; i <= n; i++) {
                if (i - coin >= 0) {
                    dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
                }
            }
        }
        return dp[n];
    }

    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        if (num <= 0) {
            return result.toString();
        }
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");
        int i = 1;
        while (num > 0) {
            int a = num % 10;
            num = num / 10;
            if (map.containsKey(a * i)) {
                result.insert(0, map.get(a * i));
            } else {
                if (a * i > i && a * i < 4 * i) {
                    for (int j = 0; j < a; j++) {
                        result.insert(0, map.get(i));
                    }
                } else if (a * i > 5 * i) {
                    for (int j = 0; j < a - 5; j++) {
                        result.insert(0, map.get(i));
                    }
                    result.insert(0, map.get(5 * i));
                }
            }
            i = i * 10;
        }
        return result.toString();
    }

    public int[] singleNumbers(int[] nums) {
        if (nums.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums);
        int index = 0;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length) {
                if (nums[i] != nums[i + 1]) {
                    result[index] = nums[i];
                    index++;
                } else {
                    i++;
                }
            } else {
                result[index] = nums[i];
                index++;
            }
            if (index > 1) {
                break;
            }
        }
        return result;
    }
}
