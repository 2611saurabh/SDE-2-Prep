package DailyProblem.leetcode.editor.en;//Given the root of a binary tree, return its maximum depth.
//
// A binary tree's maximum depth is the number of nodes along the longest path 
//from the root node down to the farthest leaf node. 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: 3
// 
//
// Example 2: 
//
// 
//Input: root = [1,null,2]
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 10⁴]. 
// -100 <= Node.val <= 100 
// 
//
// Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 14
//485 👎 298


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
class Solution104 {
    //recursive aaproach
    public int dfs(TreeNode root){
        if(root == null){
            return 0;
        }

        int count = 1 + Math.max(dfs(root.left),dfs(root.right));
        return count;
    }

    public int maxDepth(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();

        if(root == null){
            return 0;
        }

        q.add(root);
        int count = 0;

        while(!q.isEmpty()){



            int len = q.size();

            for(int i = 0; i < len; i++){

                TreeNode node = q.remove();

                if(node.left != null){
                    q.add(node.left);
                }
                if(node.right != null){
                    q.add(node.right);
                }
            }
            count++;

        }

        return count;

    }
}

public class MaximumDepthOfBinaryTree{

    public static void main(String[] args) {
        Solution104 sol = new Solution104();

    /*
             1
           /   \
          2     3
         / \   / \
        4   5 6   7
    */

        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        Solution solution = new Solution();

        int result = sol.maxDepth(root);

        System.out.println(result);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
