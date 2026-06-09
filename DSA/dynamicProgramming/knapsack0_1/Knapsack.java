package dynamicProgramming.knapsack0_1;


import java.util.Arrays;

class Solution {

    // Main function called by the driver code
    public int knapsack(int W, int val[], int wt[]) {

        // Total number of items
        int n = val.length;
        int[][] t = new int[n + 1][W + 1];
        for(int[] row : t) Arrays.fill(row, -1);
        // Start recursion from the last item
        return solve(wt, val, W, n - 1, t);
    }

    /**
     * Recursive function to solve 0-1 Knapsack
     *
     * Parameters:
     * weight[] -> weights of items
     * value[]  -> values/profits of items
     * W        -> remaining capacity of knapsack
     * n        -> current item index
     */
    public int solve(int[] weight, int[] value, int W, int n, int[][] t) {

        /*
         * BASE CASE:
         * When only one item (index 0) is left.
         *
         * If its weight can fit in the remaining capacity,
         * take it and return its value.
         *
         * Otherwise return 0 because it cannot be included.
         */
        if (n == 0) {
            if (weight[0] <= W) {
                return value[0];
            }
            return 0;
        }

        /*
         * CHOICE 1: NOT PICK
         *
         * Skip the current item and move to the previous item.
         * Capacity remains unchanged.
         */
        int notPick = solve(weight, value, W, n - 1, t);

        /*
         * CHOICE 2: PICK
         *
         * Initialize with 0 because we may not be able to pick
         * the current item if its weight exceeds capacity.
         */
        int pick = 0;

        /*
         * We can only pick the current item if it fits
         * in the remaining capacity.
         */
        if (weight[n] <= W) {

            /*
             * Add current item's value and recursively solve
             * for remaining capacity using previous items.
             *
             * n - 1 is used because this is a 0-1 Knapsack:
             * each item can be picked at most once.
             */
            pick = value[n] + solve(weight, value, W - weight[n], n - 1, t);

        }

        /*
         * Return the maximum profit between:
         * 1. Picking the current item
         * 2. Not picking the current item
         */
        return Math.max(pick, notPick);
    }

    /**
     *  Memoization Approach intializing matrix to check recursive call already ahappend or not
     * @param weight
     * @param value
     * @param W
     * @param n
     * @param t
     * @return
     */
    public int solveMemoization(int[] weight, int[] value, int W, int n, int[][] t) {

        // no items left
        if (n < 0)
            return 0;

        if(t[n][W] != -1){
            return t[n][W];
        }




        if (weight[n] <= W) {
            t[n][W] = Math.max(value[n] + solveMemoization(weight, value, W - weight[n], n - 1, t),
                    solveMemoization(weight, value, W,n - 1,t));
        }
        else if(weight[n] > W){
            return t[n][W] = solveMemoization(weight, value, W, n - 1, t);
        }

        return t[n][W];
    }

    /**
     * Top - Down Approach
     * Here we see how to convert recursion in top-down
     * base condition in recursion is if(n == 0 || w == 0) -> we take matrix of size n +1 and W + 1
     * t[n + 1][W + 1] -- > same recursion base condtion we store zero wehre we found n == 0 || W == 0 means first row and
     * firs column
     * We taking 2D matrix because in recursion call 2 variable are changing their state
     * Knapsack(wt, val, W - wt[n - 1], n - 1) - >  this recursion call denoted as t[n - 1][w - wt[n - 1]] becuase we evaluting
     *  sub-problem at that index where maxprofit stores as per n and W
     */

    public int knapsackTopDown(int W, int[] val, int[] weight) {

        int n = val.length;

        // DP table:
        // rows -> items considered
        // cols -> capacity
        int[][] t = new int[n + 1][W + 1];

        // Base Case:
        // First row and first column are already 0 by default in Java

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {

                // Current item can fit
                if (weight[i - 1] <= j) {
                    int pick = val[i - 1] + t[i - 1][j - weight[i - 1]];

                    int notPick = t[i - 1][j];

                    t[i][j] = Math.max(pick, notPick);

                } else {
                    // Cannot include current item
                    t[i][j] = t[i - 1][j];
                }
            }
        }

        return t[n][W];
    }


}
public class Knapsack {

    public static void main(String[] args) {

        Solution s = new Solution();
    }

}
