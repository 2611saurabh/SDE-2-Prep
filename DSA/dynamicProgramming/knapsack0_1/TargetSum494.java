package dynamicProgramming.knapsack0_1;

/**
 * LeetCode 494 - Target Sum
 *
 * Problem: Given an integer array nums and an integer target,
 * assign '+' or '-' to each number and count how many ways
 * the expression evaluates to target.
 *
 * Approach : Pure Recursion (Brute Force)
 * Pattern  : 0-1 Knapsack — pick with + sign or pick with - sign
 *
 * Recurrence:
 *   solve(i, currSum) = solve(i+1, currSum + nums[i])   // assign +
 *                     + solve(i+1, currSum - nums[i])   // assign -
 *
 * Base Case:
 *   when i == nums.length → check if currSum == target
 *
 * Time  : O(2^n)  — every index has 2 choices
 * Space : O(n)    — recursion stack depth
 *
 * Sample used in comments:
 *   nums   = [1, 1, 1, 1, 1]
 *   target = 3
 *   Output = 5
 */
public class TargetSum494 {

    /**
     * Entry point for the problem.
     * Initialises currSum to 0 and starts recursion from index 0.
     *
     * @param nums   input array
     * @param target expression must evaluate to this value
     * @return       number of ways to reach target
     */
    public int findTargetSumWays(int[] nums, int target) {

        // Start from index 0 with current running sum = 0
        // Sample: solve(nums, 0, 0, 3)
        return solve(nums, 0, 0, target);
    }

    /**
     * Recursive helper — at each index decide + or - for nums[i].
     *
     * @param nums    input array
     * @param i       current index being processed
     * @param currSum running sum built so far by + / - assignments
     * @param target  value we want currSum to equal at the end
     * @return        count of valid expressions from index i onward
     */
    public int solve(int[] nums, int i, int currSum, int target) {

        // ── BASE CASE ──────────────────────────────────────────────
        // All numbers have been assigned a sign.
        // If the running sum equals target we found 1 valid way.
        // Otherwise this path contributes 0.
        //
        // Sample path: +1 -1 +1 +1 +1  →  currSum = 3 == target → return 1
        // Sample path: +1 +1 -1 -1 +1  →  currSum = 1 != target → return 0
        if (i == nums.length) {
            return currSum == target ? 1 : 0;
        }

        // ── CHOICE 1 : assign '+' to nums[i] ──────────────────────
        // Add nums[i] to currSum and move to next index.
        //
        // Sample iteration i=0, currSum=0:
        //   plusPick = solve(nums, 1, 0 + 1, 3)
        //            = solve(nums, 1, 1, 3)
        int plusPick = solve(nums, i + 1, currSum + nums[i], target);

        // ── CHOICE 2 : assign '-' to nums[i] ──────────────────────
        // Subtract nums[i] from currSum and move to next index.
        //
        // Sample iteration i=0, currSum=0:
        //   minusPick = solve(nums, 1, 0 - 1, 3)
        //             = solve(nums, 1, -1, 3)
        int minusPick = solve(nums, i + 1, currSum - nums[i], target);

        // ── COMBINE ───────────────────────────────────────────────
        // Total valid ways from this index = ways via '+' + ways via '-'
        // We SUM (not max) because we are COUNTING ways, not maximising.
        //
        // Sample i=0: count = ways starting with +1  +  ways starting with -1
        int count = plusPick + minusPick;

        return count;
    }

    // ── MAIN ──────────────────────────────────────────────────────────
    public static void main(String[] args) {

        TargetSum494 sol = new TargetSum494();

        // ── Test 1 ──
        // nums=[1,1,1,1,1], target=3
        // All 5 ways:
        //  -1+1+1+1+1 = 3
        //  +1-1+1+1+1 = 3
        //  +1+1-1+1+1 = 3
        //  +1+1+1-1+1 = 3
        //  +1+1+1+1-1 = 3
        // Expected: 5
        int[] nums1 = {1, 1, 1, 1, 1};
        int result1 = sol.findTargetSumWays(nums1, 3);
        System.out.println("Test 1 → " + result1); // 5

        // ── Test 2 ──
        // nums=[1], target=1
        // Only way: +1 = 1
        // Expected: 1
        int[] nums2 = {1};
        int result2 = sol.findTargetSumWays(nums2, 1);
        System.out.println("Test 2 → " + result2); // 1

        // ── Test 3 ──
        // nums=[1,0], target=1
        // +1+0 = 1  ✓
        // +1-0 = 1  ✓   (0 and -0 are same)
        // Expected: 2
        int[] nums3 = {1, 0};
        int result3 = sol.findTargetSumWays(nums3, 1);
        System.out.println("Test 3 → " + result3); // 2
    }
}