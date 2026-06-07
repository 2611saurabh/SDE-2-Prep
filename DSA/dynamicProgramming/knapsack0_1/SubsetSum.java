package dynamicProgramming.knapsack0_1;



import java.util.Arrays;

public class SubsetSum{

    public static void main(String[] args) {

        int[] arr = {2, 3, 7, 8, 10};
        int target = 11;

        System.out.println("Recursion      : " +
                subsetSumRecursive(arr, target));

        System.out.println("Memoization    : " +
                subsetSumMemoization(arr, target));

        System.out.println("Tabulation     : " +
                subsetSumTabulation(arr, target));
    }

    // ============================================================
    // APPROACH 1 : PURE RECURSION
    // ============================================================

    /*
     * INTUITION:
     *
     * At every element we have two choices:
     *
     * 1. Pick the current element
     * 2. Do not pick the current element
     *
     * Since each element can be used only once,
     * we move to index - 1 in both cases.
     *
     * State:
     * solve(index, target)
     *
     * Meaning:
     * Can I make 'target' using elements from 0...index ?
     */

    public static boolean subsetSumRecursive(int[] arr, int target) {

        return solveRecursive(arr, arr.length - 1, target);
    }

    private static boolean solveRecursive(
            int[] arr,
            int index,
            int target) {

        /*
         * BASE CASE 1
         *
         * If target becomes 0,
         * we have successfully formed the required sum.
         */
        if (target == 0) {
            return true;
        }

        /*
         * BASE CASE 2
         *
         * We are at the first element.
         *
         * If the first element itself equals target,
         * return true.
         */
        if (index == 0) {
            return arr[0] == target;
        }

        /*
         * CHOICE 1 : NOT PICK
         *
         * Ignore current element.
         * Target remains same.
         */
        boolean notPick = solveRecursive(arr, index - 1, target);

        /*
         * CHOICE 2 : PICK
         *
         * We can pick only if current number
         * does not exceed remaining target.
         */
        boolean pick = false;

        if (arr[index] <= target) {

            pick = solveRecursive(arr, index - 1, target - arr[index]);
        }

        /*
         * If either choice works,
         * answer is true.
         */
        return pick || notPick;
    }


    // ============================================================
    // APPROACH 2 : MEMOIZATION (TOP DOWN DP)
    // ============================================================

    /*
     * WHY DP?
     *
     * Same states are computed repeatedly.
     *
     * State:
     * dp[index][target]
     *
     * index -> current element
     * target -> remaining target
     *
     * Since two variables change,
     * we need a 2D DP table.
     */

    public static boolean subsetSumMemoization(
            int[] arr,
            int target) {

        int n = arr.length;

        int[][] dp = new int[n][target + 1];

        /*
         * -1 = not computed
         *  0 = false
         *  1 = true
         */
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return solveMemo(arr,
                n - 1,
                target,
                dp);
    }

    private static boolean solveMemo(int[] arr, int index, int target, int[][] dp) {

        if (target == 0) {
            return true;
        }

        if (index == 0) {
            return arr[0] == target;
        }

        /*
         * If already computed,
         * return stored answer.
         */
        if (dp[index][target] != -1) {

            return dp[index][target] == 1;
        }

        boolean notPick = solveMemo(arr, index - 1, target, dp);
        boolean pick = false;

        if (arr[index] <= target) {

            pick = solveMemo(arr, index - 1, target - arr[index], dp);
        }

        boolean answer = pick || notPick;
        dp[index][target] = answer ? 1 : 0;

        return answer;
    }


    // ============================================================
    // APPROACH 3 : TABULATION (BOTTOM UP)
    // ============================================================

    /*
     * STATE:
     *
     * dp[i][j]
     *
     * Means:
     *
     * Can we make sum j
     * using first i elements ?
     */

    public static boolean subsetSumTabulation(int[] arr, int target) {

        int n = arr.length;

        boolean[][] dp = new boolean[n + 1][target + 1];

        /*
         * BASE CASE:
         *
         * Sum = 0 is always possible.
         *
         * Pick nothing.
         */
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        /*
         * With 0 elements,
         * positive sums are impossible.
         *
         * dp[0][j] = false
         *
         * Java already initializes boolean
         * arrays to false.
         */

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {

                /*
                 * Current element.
                 */
                int current = arr[i - 1];

                /*
                 * PICK
                 *
                 * If current element can fit
                 * inside current target.
                 */
                if (current <= j) {

                    boolean pick = dp[i - 1][j - current];

                    boolean notPick = dp[i - 1][j];

                    dp[i][j] = pick || notPick;
                }
                else {

                    /*
                     * Current element too large.
                     *
                     * Only option:
                     * do not pick.
                     */
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][target];
    }
}