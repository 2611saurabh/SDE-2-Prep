package DailyProblem.leetcode.editor.en;//Given the root of a binary tree, return the postorder traversal of its nodes'
//values. 
//
// 
// Example 1: 
//
// 
// Input: root = [1,null,2,3] 
// 
//
// Output: [3,2,1] 
//
// Explanation: 
//
// 
//
// Example 2: 
//
// 
// Input: root = [1,2,3,4,5,null,8,null,null,6,7,9] 
// 
//
// Output: [4,6,7,5,2,9,8,3,1] 
//
// Explanation: 
//
// 
//
// Example 3: 
//
// 
// Input: root = [] 
// 
//
// Output: [] 
//
// Example 4: 
//
// 
// Input: root = [1] 
// 
//
// Output: [1] 
//
// 
// Constraints: 
//
// 
// The number of the nodes in the tree is in the range [0, 100]. 
// -100 <= Node.val <= 100 
// 
//
// 
//Follow up: Recursive solution is trivial, could you do it iteratively?
//
// Related Topics Stack Tree Depth-First Search Binary Tree 👍 7874 👎 228


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


//Definition for a binary tree node.
/*
 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

 */

class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root;
        TreeNode previous = null;

        while (curr != null || !st.isEmpty()) {

            // 1. Go as far left as possible
            if (curr != null) {
                st.push(curr);
                curr = curr.left;
            }

            else {

                TreeNode node = st.peek();

                // 2. If right subtree exists and is NOT completed,
                //    go to right subtree
                if (node.right != null && previous != node.right) {
                    curr = node.right;
                }

                // 3. Otherwise, both subtrees are completed
                //    OR node has no right subtree.
                else {
                    ans.add(node.val);
                    previous = st.pop();
                }
            }
        }

        return ans;
    }
}

public class PostOrderTraversal{

    public static void main(String[] args) {

    /*
             1
           /   \
          2     3
         / \     \
        4   5     8
           / \
          6   7
    */

        Solution145 sol = new Solution145();
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        root.right.right = new TreeNode(8);

        Solution solution = new Solution();

        List<Integer> result = sol.postorderTraversal(root);

        System.out.println(result);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
