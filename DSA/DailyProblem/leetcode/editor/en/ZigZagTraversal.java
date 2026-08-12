package DailyProblem.leetcode.editor.en;//Given the root of a binary tree, return the zigzag level order traversal of
//its nodes' values. (i.e., from left to right, then right to left for the next 
//level and alternate between). 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: [[3],[20,9],[15,7]]
// 
//
// Example 2: 
//
// 
//Input: root = [1]
//Output: [[1]]
// 
//
// Example 3: 
//
// 
//Input: root = []
//Output: []
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 2000]. 
// -100 <= Node.val <= 100 
// 
//
// Related Topics Tree Breadth-First Search Binary Tree 👍 12239 👎 363


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

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
class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        boolean flag = false;

        Queue<TreeNode> q = new LinkedList<>();

        List<List<Integer>> ans = new ArrayList<>();

        if(root == null) return new ArrayList<>();
        q.add(root);

        while(!q.isEmpty()){

            int sizeQ = q.size();

            List<Integer> levelElement = new ArrayList<>();

            for(int i = 0;i < sizeQ; i++){

                TreeNode node = q.poll();

                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
                levelElement.add(node.val);
            }

            if(flag == false){
                ans.add(levelElement);

                flag = true;
            }
            else{
                Collections.reverse(levelElement);

                ans.add(levelElement);
                flag = false;
            }
        }


        return ans;

    }
}


public class ZigZagTraversal{

    public static void main(String[] args) {
        Solution103 sol = new Solution103();

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

        List<List<Integer>> result = sol.zigzagLevelOrder(root);

        System.out.println(result);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
