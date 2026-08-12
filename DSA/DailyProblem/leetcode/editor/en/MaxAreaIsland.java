package DailyProblem.leetcode.editor.en;//You are given an m x n binary matrix grid. An island is a group of 1's (
//representing land) connected 4-directionally (horizontal or vertical.) You may assume 
//all four edges of the grid are surrounded by water. 
//
// The area of an island is the number of cells with a value 1 in the island. 
//
// Return the maximum area of an island in grid. If there is no island, return 0
//. 
//
// 
// Example 1: 
// 
// 
//Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,
//0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,
//0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]
//]
//Output: 6
//Explanation: The answer is not 11, because the island must be connected 4-
//directionally.
// 
//
// Example 2: 
//
// 
//Input: grid = [[0,0,0,0,0,0,0,0]]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 50 
// grid[i][j] is either 0 or 1. 
// 
//
// Related Topics Array Depth-First Search Breadth-First Search Union-Find 
//Matrix 👍 10732 👎 223


//leetcode submit region begin(Prohibit modification and deletion)
class Solution495 {
    public int maxAreaOfIsland(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        int max_count = 0;


        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){

                if(grid[i][j] == 1 && !visited[i][j]){

                    int area = dfs(grid,i, j,visited);

                    max_count = Math.max(max_count,area);


                }

            }
        }

        // for(Integer it: ans){
        //     System.out.println(it);
        // }
        return max_count;

    }

    private int dfs(int[][] grid, int row, int col,boolean[][] visited){

        visited[row][col] = true;

        int n = grid.length;
        int m = grid[0].length;

        int[] del_row = {-1,0,1,0};
        int[] del_col = {0,1,0,-1};
        int area = 1;



        for(int i = 0; i < 4; i++){
            int nrow = row + del_row[i];
            int ncol = col + del_col[i];

            if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m && !visited[nrow][ncol] && grid[nrow][ncol] == 1){

                area += dfs(grid, nrow, ncol, visited);
            }
        }

        return area;
    }
}

public class MaxAreaIsland{

    public static void main(String[] args) {

        Solution495 solution = new Solution495();

        int[][] grid = {
                {0, 0, 1, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {1, 1, 0, 0, 0}
        };



        int result = solution.maxAreaOfIsland(grid);

        System.out.println("Maximum Area of Island: " + result);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
