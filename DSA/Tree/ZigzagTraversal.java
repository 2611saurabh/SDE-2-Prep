package Tree;

import java.util.*;

// Definition for a binary tree node
class TreeNode103 {

    int val;                 // value of node
    TreeNode103 left;       // left child
    TreeNode103 right;      // right child

    TreeNode103() {}        // empty constructor

    TreeNode103(int val) {  // constructor with value
        this.val = val;
    }

    TreeNode103(int val, TreeNode103 left, TreeNode103 right) {
        this.val = val;         // assign value
        this.left = left;       // assign left child
        this.right = right;     // assign right child
    }
}

class Solution103 {

    // Zigzag level order traversal function
    public List<List<Integer>> zigzagLevelOrder(TreeNode103 root) {

        // result list to store all levels
        List<List<Integer>> ans = new ArrayList<>();

        // if tree is empty return empty list
        if (root == null) return ans;

        // queue for BFS traversal
        Queue<TreeNode103> q = new LinkedList<>();

        // add root node to queue
        q.offer(root);

        // flag to control direction of traversal
        boolean leftToRight = true;

        // loop until queue becomes empty
        while (!q.isEmpty()) {

            // number of nodes at current level
            int size = q.size();

            // list to store current level values
            List<Integer> level = new ArrayList<>();

            // process all nodes at current level
            for (int i = 0; i < size; i++) {

                // get current node
                TreeNode103 node = q.poll();

                // add left child if exists
                if (node.left != null) {
                    q.offer(node.left);
                }

                // add right child if exists
                if (node.right != null) {
                    q.offer(node.right);
                }

                // store node value
                level.add(node.val);
            }

            // if direction is right to left, reverse the level list
            if (!leftToRight) {
                Collections.reverse(level);
            }

            // add current level to result
            ans.add(level);

            // flip direction
            leftToRight = !leftToRight;
        }

        // return final zigzag order
        return ans;
    }
}

public class ZigzagTraversal {

    public static void main(String[] args) {

        /*
            Example Tree:
                    3
                   / \
                  9   20
                     /  \
                    15   7
        */

        // manually create nodes
        TreeNode103 root = new TreeNode103(3);
        root.left = new TreeNode103(9);
        root.right = new TreeNode103(20);
        root.right.left = new TreeNode103(15);
        root.right.right = new TreeNode103(7);

        // create solution object
        Solution103 sol = new Solution103();

        // call function
        List<List<Integer>> result = sol.zigzagLevelOrder(root);

        // print result
        for (List<Integer> level : result) {
            System.out.println(level);
        }
    }
}

/*
==============================
TIME COMPLEXITY:
==============================
O(N)

Each node is visited once.

==============================
SPACE COMPLEXITY:
==============================
O(N)

Queue + output storage.
*/
