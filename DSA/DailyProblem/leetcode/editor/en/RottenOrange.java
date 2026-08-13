package DailyProblem.leetcode.editor.en;//You are given an m x n grid where each cell can have one of three values:
//
// 
// 0 representing an empty cell, 
// 1 representing a fresh orange, or 
// 2 representing a rotten orange. 
// 
//
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten 
//orange becomes rotten. 
//
// Return the minimum number of minutes that must elapse until no cell has a 
//fresh orange. If this is impossible, return -1. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
//Output: 4
// 
//
// Example 2: 
//
// 
//Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
//Output: -1
//Explanation: The orange in the bottom left corner (row 2, column 0) is never 
//rotten, because rotting only happens 4-directionally.
// 
//
// Example 3: 
//
// 
//Input: grid = [[0,2]]
//Output: 0
//Explanation: Since there are already no fresh oranges at minute 0, the answer 
//is just 0.
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 10 
// grid[i][j] is 0, 1, or 2. 
// 
//
// Related Topics Array Breadth-First Search Matrix 👍 15476 👎 480


import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Pair {
    int row;
    int col;

    Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution994 {

    public int orangesRotting(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        int fresh = 0;

        // Add all initially rotten oranges
        // and count fresh oranges.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j));
                }

                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        int time = 0;

        int[] delRow = {-1, 0, 1, 0};
        int[] delCol = {0, 1, 0, -1};

        while (!q.isEmpty()) {

            int len = q.size();
            boolean rotted = false;

            // Process all oranges at the current minute.
            for (int k = 0; k < len; k++) {

                Pair current = q.poll();

                int row = current.row;
                int col = current.col;

                for (int i = 0; i < 4; i++) {

                    int row_i = row + delRow[i];
                    int col_i = col + delCol[i];

                    if (row_i >= 0 && row_i < n
                            && col_i >= 0 && col_i < m
                            && grid[row_i][col_i] == 1) {

                        grid[row_i][col_i] = 2;
                        q.add(new Pair(row_i, col_i));

                        fresh--;
                        rotted = true;
                    }
                }
            }

            if (rotted) {
                time++;
            }
        }

        // Fresh oranges still remaining means
        // they were unreachable.
        if (fresh > 0) {
            return -1;
        }

        return time;
    }
}

public class RottenOrange{

    public static void main(String[] args) {

        int[][] grid = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };

        Solution994 solution = new Solution994();

        int result = solution.orangesRotting(grid);

        System.out.println("Minimum minutes: " + result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
