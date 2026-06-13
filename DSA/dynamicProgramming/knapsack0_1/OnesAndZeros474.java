/*
=========================================================
LeetCode 474. Ones and Zeroes
Difficulty : Medium

Problem Link:
https://leetcode.com/problems/ones-and-zeroes/

Technique Used:
✓ Recursion
✓ Memoization (Top-Down DP)
✓ 0/1 Knapsack Pattern

Time Complexity  : O(L × m × n)
Space Complexity : O(L × m × n)

Author : Saurabh Kumar
=========================================================
*/


package dynamicProgramming.knapsack0_1;

import java.util.Arrays;

public class OnesAndZeros474 {

    /*
     * LeetCode 474. Ones and Zeroes
     *
     * Approach:
     * ----------
     * We use Top-Down Dynamic Programming (Memoization).
     *
     * At every index, we have two choices:
     * 1. Pick the current string (if enough zeros and ones are available).
     * 2. Skip the current string.
     *
     * State:
     * dp[index][m][n]
     * = Maximum number of strings that can be formed
     *   starting from 'index'
     *   with 'm' zeros and 'n' ones available.
     *
     * Time Complexity:
     * O(L × m × n)
     * where:
     * L = number of strings
     * m = available zeros
     * n = available ones
     *
     * Space Complexity:
     * O(L × m × n)
     * for memoization array.
     */

    public int findMaxForm(String[] strs, int m, int n) {

        // Edge case: if array is empty, no strings can be formed
        if (strs.length == 0) {
            return 0;
        }

        /*
         * dp[index][zeros][ones]
         *
         * index -> current position in string array
         * zeros -> remaining zeros available
         * ones -> remaining ones available
         */
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];

        // Initialize every cell with -1
        // -1 means answer for that state is not calculated yet
        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, -1);
            }
        }

        // Start recursion from index 0
        return solve(strs, m, n, 0, dp);
    }

    /*
     * Recursive function
     *
     * Parameters:
     * s      -> input string array
     * m      -> remaining zeros available
     * n      -> remaining ones available
     * index  -> current string index
     * dp     -> memoization table
     */
    public int solve(String[] s, int m, int n, int index, int[][][] dp) {

        // Base Case:
        // Reached end of array
        if (index == s.length) {
            return 0;
        }

        // If already computed, return stored value
        if (dp[index][m][n] != -1) {
            return dp[index][m][n];
        }

        // Count number of zeros and ones in current string
        int[] count = oneZerosCount(s[index]);

        int zeros = count[0];
        int ones = count[1];

        // Option 1 : Pick current string
        int pick = 0;

        // Pick only if sufficient zeros and ones are available
        if (m >= zeros && n >= ones) {

            pick = 1 + solve(
                    s,
                    m - zeros,
                    n - ones,
                    index + 1,
                    dp
            );
        }

        // Option 2 : Skip current string
        int notPick = solve(
                s,
                m,
                n,
                index + 1,
                dp
        );

        // Store and return maximum of both choices
        return dp[index][m][n] = Math.max(pick, notPick);
    }

    /*
     * Function to count number of zeros and ones
     * in a given binary string.
     *
     * Returns:
     * res[0] -> number of zeros
     * res[1] -> number of ones
     */
    /**
     * Main function — entry point for the problem.
     * Calls the bottom-up DP solver and returns the answer.
     */
    public int findMaxFormBottomUp(String[] strs, int m, int n) {

        // Total number of strings in the input array
        int len = strs.length;

        // -----------------------------------------------------------
        // CREATE DP TABLE
        // dp[index][j][k] = max strings pickable from index..end
        //                   with j zeros and k ones remaining
        //
        // Size: (len+1) x (m+1) x (n+1)
        //   len+1  → we need index = len as the base case (0 strings left)
        //   m+1    → j ranges from 0 to m  (inclusive)
        //   n+1    → k ranges from 0 to n  (inclusive)
        //
        // Sample: strs.length=3, m=1, n=1
        //   → dp = new int[4][2][2]
        // -----------------------------------------------------------
        int[][][] dp = new int[len + 1][m + 1][n + 1];

        // -----------------------------------------------------------
        // BASE CASE
        // dp[len][j][k] = 0 for all j, k
        // Meaning: when index == len (no strings left), answer is 0
        // Java int arrays are initialized to 0 by default, so this
        // base case is already handled automatically.
        // -----------------------------------------------------------

        // -----------------------------------------------------------
        // FILL THE TABLE (bottom-up = fill from last index to first)
        //
        // Why reverse order?
        // dp[index] depends on dp[index+1]
        // So we must fill index+1 BEFORE index.
        // Therefore we go: index = len-1 → len-2 → ... → 0
        //
        // Sample: index goes 2 → 1 → 0
        // -----------------------------------------------------------
        for (int index = len - 1; index >= 0; index--) {

            // -------------------------------------------------------
            // COUNT zeros and ones in the current string
            // This tells us the "cost" of picking strs[index]
            //
            // Sample iteration index=0: strs[0] = "10"
            //   zeros = 1, ones = 1
            //
            // Sample iteration index=1: strs[1] = "0"
            //   zeros = 1, ones = 0
            //
            // Sample iteration index=2: strs[2] = "1"
            //   zeros = 0, ones = 1
            // -------------------------------------------------------
            int[] count = oneZerosCount(strs[index]);
            int zeros = count[0]; // number of '0's in strs[index]
            int ones  = count[1]; // number of '1's in strs[index]

            // -------------------------------------------------------
            // ITERATE over every possible remaining capacity (j, k)
            // j = remaining zeros budget  (0 to m)
            // k = remaining ones  budget  (0 to n)
            //
            // We try every state so that dp[index][j][k] is filled
            // for all possible j and k values.
            // -------------------------------------------------------
            for (int j = 0; j <= m; j++) {       // j = zeros remaining
                for (int k = 0; k <= n; k++) {   // k = ones  remaining

                    // -----------------------------------------------
                    // OPTION 1: DON'T PICK strs[index]
                    // Move to next index with same capacity unchanged.
                    // This is always available regardless of j, k.
                    //
                    // Sample: index=2 ("1"), j=1, k=1
                    //   notPick = dp[3][1][1] = 0  (base case)
                    // -----------------------------------------------
                    int notPick = dp[index + 1][j][k];

                    // -----------------------------------------------
                    // OPTION 2: PICK strs[index]  (only if it fits)
                    // Condition: current string must fit in budget
                    //   j >= zeros  AND  k >= ones
                    //
                    // If we pick it:
                    //   - gain 1 string
                    //   - remaining zeros becomes j - zeros
                    //   - remaining ones  becomes k - ones
                    //   - move to next index
                    //
                    // Sample: index=2 ("1"), j=1, k=1
                    //   zeros=0, ones=1
                    //   j>=zeros (1>=0) ✓  k>=ones (1>=1) ✓  → fits
                    //   pick = 1 + dp[3][1][0] = 1 + 0 = 1
                    // -----------------------------------------------
                    int pick = 0; // default: can't pick (doesn't fit)
                    if (j >= zeros && k >= ones) {
                        pick = 1 + dp[index + 1][j - zeros][k - ones];
                    }

                    // -----------------------------------------------
                    // STORE the best of pick vs notPick
                    //
                    // Sample: index=2, j=1, k=1
                    //   pick=1, notPick=0
                    //   dp[2][1][1] = max(1, 0) = 1
                    // -----------------------------------------------
                    dp[index][j][k] = Math.max(pick, notPick);
                }
            }
        }

        // -----------------------------------------------------------
        // ANSWER
        // dp[0][m][n] = max strings pickable starting from index 0
        //               with full budget of m zeros and n ones
        //
        // Sample: dp[0][1][1]
        //   After all iterations this will be 2
        //   (we can pick "0" and "1" → 1 zero + 1 one = within budget)
        // -----------------------------------------------------------
        return dp[0][m][n];
    }

    /**
     * Helper: counts zeros and ones in a binary string.
     *
     * Returns int[2] where:
     *   result[0] = number of '0's
     *   result[1] = number of '1's
     *
     * Sample: "10" → [1, 1]
     *         "0"  → [1, 0]
     *         "1"  → [0, 1]
     */
    public int[] oneZerosCount(String s) {
        int[] res = new int[2]; // res[0]=zeros, res[1]=ones

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                res[1]++; // found a '1'
            } else {
                res[0]++; // found a '0'
            }
        }
        return res;
    }




    public static void main(String[] args) {

        OnesAndZeros474 obj = new OnesAndZeros474();

        // Input
        String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;

        // Find maximum number of strings
        int ans = obj.findMaxForm(strs, m, n);

        // Output
        System.out.println("Maximum number of strings formed = " + ans);
    }
}