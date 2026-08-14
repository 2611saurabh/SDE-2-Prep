package DailyProblem.leetcode.editor.en;//Given a binary tree, determine if it is height-balanced.
//
// 
// Example 1: 
// 
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: true
// 
//
// Example 2: 
// 
// 
//Input: root = [1,2,2,3,3,null,null,4,4]
//Output: false
// 
//
// Example 3: 
//
// 
//Input: root = []
//Output: true
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 5000]. 
// -10⁴ <= Node.val <= 10⁴ 
// 
//
// Related Topics Tree Depth-First Search Binary Tree 👍 12363 👎 848


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
class Solution110 {
    public int dfs(TreeNode root){

        if(root == null){
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        if (left == -1 || right == -1) {
            return -1;
        }

        int diff = Math.abs(left - right);

        if(diff > 1){
            return -1;
        }
        return 1 + Math.max(left, right);
    }
    public boolean isBalanced(TreeNode root) {

        return dfs(root) != -1;
    }
}

public class BalancedBinaryTree{
    public static void main(String[] args) {

        Solution110 sol = new Solution110();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(sol.isBalanced(root));

    }
}
//leetcode submit region end(Prohibit modification and deletion)
