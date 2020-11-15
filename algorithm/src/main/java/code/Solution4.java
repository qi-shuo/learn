package code;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author QiShuo
 * @version 1.0
 * @create 2020-04-30 18:02
 */
public class Solution4 implements Comparable {
    static int count = 0;
    static boolean result;

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
//        System.out.println(canPartitionKSubsets(nums, 5));
//        TreeMap<Solution4,Integer> treeMap=new TreeMap<>();
//        treeMap.put(new Solution4(),1);
//        treeMap.put(new Solution4(),null);
//        System.out.println(treeMap.get(new Solution4()));
//        TreeMap<String, Integer> treeMap = new TreeMap<>((o1, o2) -> {
//            if (o1 == null) {
//                return 1;
//            } else {
//                return o2.charAt(0) - o1.charAt(0);
//            }
//        });
//        treeMap.put("1", 1);
//        treeMap.put(null, 12);
//        treeMap.put("2", 2);
//        System.out.println(treeMap.get(null));
//        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());
//        }
        int[] nums = new int[]{2147483647, 2147483647, 2147483647, 2147483647, 2147483647, 2147483647};
        System.out.println(reversePairs(nums));

    }

    public static int reversePairs(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int[] temp = new int[nums.length];
        return sort(nums, 0, nums.length - 1, temp);
    }

    private static int sort(int[] nums, int left, int right, int[] temp) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int leftCount = sort(nums, left, mid, temp);
        int rightCount = sort(nums, mid + 1, right, temp);
        int merge = merge(nums, left, mid, right, temp);
        return leftCount + rightCount + merge;
    }

    private static int merge(int[] nums, int left, int mid, int right, int[] temp) {
        int index = 0, l = left, r = mid + 1, result = 0;
        while (l <= mid || r <= right) {
            if (l > mid) {
                temp[index++] = nums[r++];
            } else if (r > right) {
                temp[index++] = nums[l++];
            } else if (nums[r] > nums[l]) {
                temp[index++] = nums[l++];
            } else {
                for (int i = 0; i <= mid - l + 1; i++) {
                    if ((long) nums[l + i] > 2 * (long) nums[r]) {
                        result++;
                    }
                }
                temp[index++] = nums[r++];
            }
        }
        for (int i = 0; i < index; i++) {
            nums[left + i] = temp[i];
        }
        return result;
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int sum = 0;
        for (int i : nums) {
            sum = sum + i;
        }
        if (sum % k > 0) {
            return false;
        }
        int target = sum / k;
        Arrays.sort(nums);
        dfs(new boolean[nums.length], nums, 0, target, k, 0);
        return result;
    }

    private static boolean dfs(boolean[] flags, int[] nums, int sum, int target, int k, int index) {
        if (sum == target) {
            count++;
            if (count == k - 1) {
                result = true;
            }
            return true;
        }
        if (sum > target) {
            return false;
        }
        int co = 1;
        for (int i = index; i < nums.length; i++) {
            if (result) {
                return true;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !flags[i - 1]) {
                co++;
                if (sum + nums[i - 1] * co == target) {
                    count++;
                    if (count == k - 1) {
                        result = true;
                    }
                    break;
                }
                continue;
            }
            if (!flags[i]) {
                flags[i] = true;
                if (i > 0 && co > 1) {
                    dfs(flags, nums, sum + nums[i] + nums[i - 1] * co, target, k, i);
                } else {
                    dfs(flags, nums, sum + nums[i], target, k, i);
                }
                co = 1;
                flags[i] = false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }


}
