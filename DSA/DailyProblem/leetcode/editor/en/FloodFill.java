package DailyProblem.leetcode.editor.en;//You are given an image represented by an m x n grid of integers image, where
//image[i][j] represents the pixel value of the image. You are also given three 
//integers sr, sc, and color. Your task is to perform a flood fill on the image 
//starting from the pixel image[sr][sc]. 
//
// To perform a flood fill: 
//
// 
// Begin with the starting pixel and change its color to color. 
// Perform the same process for each pixel that is directly adjacent (pixels 
//that share a side with the original pixel, either horizontally or vertically) and 
//shares the same color as the starting pixel. 
// Keep repeating this process by checking neighboring pixels of the updated 
//pixels and modifying their color if it matches the original color of the starting 
//pixel. 
// The process stops when there are no more adjacent pixels of the original 
//color to update. 
// 
//
// Return the modified image after performing the flood fill. 
//
// 
// Example 1: 
//
// 
// Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2 
// 
//
// Output: [[2,2,2],[2,2,0],[2,0,1]] 
//
// Explanation: 
//
// 
//
// From the center of the image with position (sr, sc) = (1, 1) (i.e., the red 
//pixel), all pixels connected by a path of the same color as the starting pixel (
//i.e., the blue pixels) are colored with the new color. 
//
// Note the bottom corner is not colored 2, because it is not horizontally or 
//vertically connected to the starting pixel. 
//
// Example 2: 
//
// 
// Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0 
// 
//
// Output: [[0,0,0],[0,0,0]] 
//
// Explanation: 
//
// The starting pixel is already colored with 0, which is the same as the 
//target color. Therefore, no changes are made to the image. 
//
// 
// Constraints: 
//
// 
// m == image.length 
// n == image[i].length 
// 1 <= m, n <= 50 
// 0 <= image[i][j], color < 2¹⁶ 
// 0 <= sr < m 
// 0 <= sc < n 
// 
//
// Related Topics Array Depth-First Search Breadth-First Search Matrix 👍 9624 ?
//? 964


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution733 {
    public void dfs(int[][] image, int sr,int sc, int newColor, int originalColor){

        image[sr][sc] = newColor;
        int n = image.length;
        int m = image[0].length;

        int[] row = {-1,0,1,0};
        int[] col = {0,1,0,-1};

        for(int r = 0; r < 4; r++){
            int row_i = row[r] + sr;
            int col_i = col[r] + sc;

            if(row_i >= 0 && row_i < n && col_i >= 0 && col_i < m  && image[row_i][col_i] == originalColor){
                dfs(image, row_i, col_i, newColor, originalColor);
            }
        }
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        int originalColor = image[sr][sc];
        if(originalColor == color){
            return image;
        }
        dfs(image,sr,sc,color, originalColor);
        return image;
    }
}

public class FloodFill{

    public static void main(String[] args) {

        int[][] image = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };

        int sr = 1;
        int sc = 1;
        int color = 222301;

        Solution733 solution = new Solution733();

        int[][] result = solution.floodFill(image, sr, sc, color);

        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
