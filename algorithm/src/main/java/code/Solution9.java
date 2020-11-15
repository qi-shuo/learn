package code;

import java.util.*;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-07-06 22:10
 */
public class Solution9 {
    static int row;
    static int column;
    static int result=0;

    public static void main(String[] args) {
        //System.out.println(respace(new String[]{"bc"},"abcd"));
        int[]nums=new int[]{1,5,2,4,6};
        PredictTheWinner(nums);
    }
    public static boolean PredictTheWinner(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] >= 0;
    }

    public static int respace(String[] dictionary, String sentence) {
        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
        int n = sentence.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int idx = 0; idx < i; idx++) {
                if (dict.contains(sentence.substring(idx, i))) {
                    dp[i] = Math.min(dp[i], dp[idx]);
                }
            }
        }
        return dp[n];
    }
    private List<List<Integer>> ans;
//    private void dfs(Solution.TreeNode root, int sum, int temp, List<Integer> list){
//        temp += root.val;
//        list.add(root.val);
//        if(sum == temp && root.left == null && root.right == null){
//            ans.add(new ArrayList(list));
//        }
//        if(root.left != null){
//            dfs(root.left, sum, temp,list);
//        }
//        if(root.right != null){
//            dfs(root.right, sum, temp,list);
//        }
//        list.remove(list.size() - 1);
//    }

     private void dfs(Solution.TreeNode root,int sum,int temp,List<Integer> list){

         //temp=temp+root.val;
         list.add(root.val);
         if(temp==sum && root.left == null && root.right == null){
             ans.add(new ArrayList(list));
         }
         if(root.left != null){
             dfs(root.left,sum,temp+root.val,list);
         }
         if(root.right != null){
             dfs(root.right,sum,temp+root.val,list);
         }
         list.remove(list.size()-1);
     }
}
