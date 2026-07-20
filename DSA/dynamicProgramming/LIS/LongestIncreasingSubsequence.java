package dynamicProgramming.LIS;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println("Recursion      : " + recursionLIS(nums));
        System.out.println("Memoization    : " + memoizationLIS(nums));
        System.out.println("1D DP Approach : " + oneDDPLIS(nums));
    }

    // ==========================================================
    // Approach 1 : Pure Recursion
    //
    // State:
    // solve(index, previousIndex)
    //
    // Time Complexity  : O(2^n)
    // Space Complexity : O(n) (Recursion Stack)
    // ==========================================================

    public static int recursionLIS(int[] nums) {
        return solve(0, -1, nums);
    }

    private static int solve(int index, int previous, int[] nums) {

        if (index == nums.length) {
            return 0;
        }

        // Skip current element
        int skip = solve(index + 1, previous, nums);

        // Pick current element
        int pick = 0;

        if (previous == -1 || nums[index] > nums[previous]) {
            pick = 1 + solve(index + 1, index, nums);
        }

        return Math.max(skip, pick);
    }

    // ==========================================================
    // Approach 2 : Memoization
    //
    // State:
    // solve(index, previousIndex)
    //
    // dp[index][previous + 1]
    //
    // Time Complexity  : O(n²)
    // Space Complexity : O(n²) + O(n) recursion stack
    // ==========================================================

    public static int memoizationLIS(int[] nums) {

        int n = nums.length;

        int[][] dp = new int[n + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveMemo(0, -1, nums, dp);
    }

    private static int solveMemo(int index, int previous, int[] nums, int[][] dp) {

        if (index == nums.length) {
            return 0;
        }

        if (dp[index][previous + 1] != -1) {
            return dp[index][previous + 1];
        }

        int skip = solveMemo(index + 1, previous, nums, dp);

        int pick = 0;

        if (previous == -1 || nums[index] > nums[previous]) {
            pick = 1 + solveMemo(index + 1, index, nums, dp);
        }

        return dp[index][previous + 1] = Math.max(skip, pick);
    }

    // ==========================================================
    // Approach 3 : 1D DP (Different State Definition)
    //
    // State:
    // dp[i] = Length of LIS ending at index i
    //
    // Transition:
    // dp[i] = max(dp[j] + 1)
    // where j < i and nums[j] < nums[i]
    //
    // Time Complexity  : O(n²)
    // Space Complexity : O(n)
    // ==========================================================

    public static int oneDDPLIS(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];

        // Every element itself forms an LIS of length 1
        Arrays.fill(dp, 1);

        int maxLIS = 1;

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLIS = Math.max(maxLIS, dp[i]);
        }

        return maxLIS;
    }
}