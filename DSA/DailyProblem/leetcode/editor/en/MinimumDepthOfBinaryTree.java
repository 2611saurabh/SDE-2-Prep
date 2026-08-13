package DailyProblem.leetcode.editor.en;//Given a binary tree, find its minimum depth.
//
// The minimum depth is the number of nodes along the shortest path from the 
//root node down to the nearest leaf node. 
//
// Note: A leaf is a node with no children. 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: 2
// 
//
// Example 2: 
//
// 
//Input: root = [2,null,3,null,4,null,5,null,6]
//Output: 5
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 10⁵]. 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 79
//25 👎 1370


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution111 {
    public int dfs(TreeNode root){
        if(root.left == null && root.right == null){
            return 1;
        }

        int left = 0;
        int right = 0;

        if(root.left == null){
            left = 1 + dfs(root.right);
        }
        else if(root.right == null){
            right = 1 + dfs(root.left);
        }
        else{
            left = 1 + dfs(root.left);
            right = 1 + dfs(root.right);
        }

        int count;

        if(left == 0){
            count = right;
        }
        else if(right == 0){
            count = left;
        }
        else{
            count = Math.min(left, right);
        }

        return count;
    }
    public int minDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int count = dfs(root);
        return count;
    }
}

public class MinimumDepthOfBinaryTree{

    public static void main(String[] args) {

    /*
            3
           / \
          9   20
             /  \
            15   7

        Minimum depth = 2
    */

        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(9);

        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution111 solution = new Solution111();

        int result = solution.minDepth(root);

        System.out.println("Minimum Depth: " + result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
