package dynamicProgramming.knapsack0_1;


/**
 * LeetCode 650 - 2 Keys Keyboard
 *
 * Problem:
 * Initially there is only one character 'A' on the screen.
 *
 * Allowed Operations:
 * 1. Copy All
 * 2. Paste
 *
 * Find the minimum number of operations required to print exactly n 'A's.
 *
 * ------------------------------------------------------------
 * Dynamic Programming State
 * ------------------------------------------------------------
 * screen     = Current number of characters on the screen.
 * clipboard  = Current number of characters stored in clipboard.
 *
 * DP State:
 * dp[screen][clipboard]
 *
 * = Minimum operations required to reach exactly n characters
 *   from the current state.
 *
 * ------------------------------------------------------------
 * Choices
 * ------------------------------------------------------------
 *
 * 1. Copy All
 *      Clipboard becomes equal to screen.
 *
 * 2. Paste
 *      Screen becomes screen + clipboard.
 *
 */
public class TwoKeysKeyboard650 {

    // Large value representing impossible state
    static final int INF = (int) 1e9;

    /**
     * Bottom-Up Dynamic Programming (Tabulation)
     *
     * Time Complexity  : O(n²)
     * Space Complexity : O(n²)
     */
    public int minSteps(int n) {

        // Already have one 'A'
        if (n <= 1) {
            return 0;
        }

        /*
         * dp[screen][clipboard]
         *
         * screen     -> Current characters on screen
         * clipboard  -> Current clipboard content
         */
        int[][] dp = new int[n + 1][n + 1];

        /*
         * Base Case
         *
         * If screen == n,
         * no more operations are needed.
         */
        for (int clipboard = 0; clipboard <= n; clipboard++) {
            dp[n][clipboard] = 0;
        }

        /*
         * Build table from bottom to top.
         *
         * Since current state depends on larger screen values
         * (screen + clipboard),
         * we iterate backwards.
         */
        for (int screen = n - 1; screen >= 1; screen--) {

            for (int clipboard = n - 1; clipboard >= 0; clipboard--) {

                // Initialize both choices as impossible
                int copy = INF;
                int paste = INF;

                /*
                 * Choice 1:
                 * Perform Copy All.
                 *
                 * Avoid unnecessary copy if clipboard
                 * already contains same value.
                 */
                if (clipboard != screen) {
                    copy = 1 + dp[screen][screen];
                }

                /*
                 * Choice 2:
                 * Perform Paste.
                 *
                 * Conditions:
                 * 1. Clipboard should not be empty.
                 * 2. Result should not exceed n.
                 */
                if (clipboard > 0 && screen + clipboard <= n) {
                    paste = 1 + dp[screen + clipboard][clipboard];
                }

                // Store minimum operations
                dp[screen][clipboard] = Math.min(copy, paste);
            }
        }

        // Initial state:
        // One character on screen and empty clipboard.
        return dp[1][0];
    }

    /*
    =====================================================================
                        TOP-DOWN DP (MEMOIZATION)
    =====================================================================

    Time Complexity  : O(n²)
    Space Complexity : O(n²) + O(recursion stack)

    ---------------------------------------------------------------------

    static final int INF = (int)1e9;

    public int minSteps(int n) {

        if(n <= 1){
            return 0;
        }

        // Memo table
        int[][] dp = new int[n + 1][n + 1];

        // Mark every state as unvisited
        for(int i = 0; i <= n; i++){
            Arrays.fill(dp[i], -1);
        }

        // Start from:
        // Screen = 1
        // Clipboard = 0
        return solve(1, 0, n, dp);
    }

    private int solve(int screen,
                      int clipboard,
                      int n,
                      int[][] dp){

        // Successfully reached target
        if(screen == n){
            return 0;
        }

        // Invalid state
        if(screen > n){
            return INF;
        }

        // Already solved
        if(dp[screen][clipboard] != -1){
            return dp[screen][clipboard];
        }

        int copy = INF;
        int paste = INF;

        //----------------------------------------------------------
        // Choice 1 : Copy All
        //----------------------------------------------------------
        if(clipboard != screen){
            copy = 1 + solve(screen,
                             screen,
                             n,
                             dp);
        }

        //----------------------------------------------------------
        // Choice 2 : Paste
        //----------------------------------------------------------
        if(clipboard > 0){

            paste = solve(screen + clipboard,
                          clipboard,
                          n,
                          dp);

            if(paste != INF){
                paste += 1;
            }
        }

        return dp[screen][clipboard] =
                Math.min(copy, paste);
    }

    */

    /**
     * Driver Code
     */
    public static void main(String[] args) {

        TwoKeysKeyboard650 solution = new TwoKeysKeyboard650();

        int n = 9;

        System.out.println("Minimum Steps = " + solution.minSteps(n));
    }
}