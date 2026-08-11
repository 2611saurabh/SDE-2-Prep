package DailyProblem.leetcode.editor.en;
//Given an m x n 2D binary grid grid which represents a map of '1's (land) and
//'0's (water), return the number of islands. 
//
// An island is surrounded by water and is formed by connecting adjacent lands 
//horizontally or vertically. You may assume all four edges of the grid are all 
//surrounded by water. 
//
// 
// Example 1: 
//
// 
//Input: grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//Output: 1
// 
//
// Example 2: 
//
// 
//Input: grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//Output: 3
// 
//
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] is '0' or '1'. 
// 
//
// Related Topics Array Depth-First Search Breadth-First Search Union-Find 
//Matrix 👍 25266 👎 622


//leetcode submit region begin(Prohibit modification and deletion)
class Solution200 {
    public void dfs(char[][] grid, boolean[][] vis,int i, int j){

        vis[i][j] = true;
        int n = grid.length;
        int m = grid[0].length;

        // this array for horizontal and vertical traversal
        int[] row = {-1,0,1,0};
        int[] col = {0,1,0,-1};

        for(int r = 0; r < 4; r++){
            int row_i = row[r] + i;
            int col_i = col[r] + j;

            if(row_i >= 0 && row_i < n && col_i >= 0 && col_i < m && !vis[row_i][col_i] && grid[row_i][col_i] == '1'){
                dfs(grid,vis,row_i,col_i);
            }
        }

    }
    public int numIslands(char[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        //we run in for loop and call dfs for each block and put count after dfs method number of dfs call means
        //number of island if count is 1 then each blcok connected to another block who have value 1 vertically and horizonatally
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){

                if(!vis[i][j] && grid[i][j] == '1'){
                    count++;
                    dfs(grid,vis,i,j);
                }
            }
        }

        return count;

    }
}

public class NumberOfIsland{
    public static void main(String[] args) {

        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        Solution200 solution = new Solution200();

        int result = solution.numIslands(grid);

        System.out.println("Number of Islands: " + result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
