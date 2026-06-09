package Tree;

import java.util.*;

// Definition for a binary tree node
class TreeNode102 {
    int val;              // Value stored in the node
    TreeNode102 left;        // Reference to left child
    TreeNode102 right;       // Reference to right child

    TreeNode102() {}

    TreeNode102(int val) {
        this.val = val;
    }

    TreeNode102(int val, TreeNode102 left, TreeNode102 right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution102 {

    public List<List<Integer>> levelOrder(TreeNode102 root) {

        // Queue is used for BFS traversal
        Queue<TreeNode102> q = new LinkedList<>();

        // Stores final answer level by level
        List<List<Integer>> ans = new ArrayList<>();

        // If tree is empty return empty list
        if (root == null) {
            return ans;
        }

        // Insert root node into queue
        q.add(root);

        // Continue until queue becomes empty
        while (!q.isEmpty()) {

            // Number of nodes present at current level
            int levelNum = q.size();

            // Stores all node values of current level
            List<Integer> levelElement = new ArrayList<>();

            // Process exactly 'levelNum' nodes
            for (int i = 0; i < levelNum; i++) {

                // Get front node without removing it
                TreeNode102 node = q.peek();

                // Add left child into queue if it exists
                if (node.left != null) {
                    q.add(node.left);
                }

                // Add right child into queue if it exists
                if (node.right != null) {
                    q.add(node.right);
                }

                // Store current node value
                levelElement.add(node.val);

                // Remove processed node from queue
                q.poll();
            }

            // Add current level result into final answer
            ans.add(levelElement);
        }

        // Return level order traversal
        return ans;
    }
}

public class LevelOrderTraversal {

    // Helper method to build tree from level-order input
    // Example input:
    // 3 9 20 -1 -1 15 7
    //
    // Tree:
    //        3
    //       / \
    //      9   20
    //         /  \
    //        15   7
    //
    // Use -1 for null nodes.
    public static TreeNode102 buildTree(Scanner sc) {

        // Read root value
        int rootVal = sc.nextInt();

        // If root itself is null
        if (rootVal == -1) {
            return null;
        }

        // Create root node
        TreeNode102 root = new TreeNode102(rootVal);

        // Queue for building tree level by level
        Queue<TreeNode102> q = new LinkedList<>();

        q.offer(root);

        while (!q.isEmpty()) {

            // Current parent node
            TreeNode102 current = q.poll();

            // If no more input exists stop
            if (!sc.hasNextInt()) break;
            int leftVal = sc.nextInt();

            // Create left child if not null
            if (leftVal != -1) {
                current.left = new TreeNode102(leftVal);
                q.offer(current.left);
            }

            // If no more input exists stop
            if (!sc.hasNextInt()) break;
            int rightVal = sc.nextInt();

            // Create right child if not null
            if (rightVal != -1) {
                current.right = new TreeNode102(rightVal);
                q.offer(current.right);
            }
        }

        return root;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter tree in level order (-1 for null):");
        System.out.println("Example: 3 9 20 -1 -1 15 7");

        // Build binary tree
        TreeNode102 root = buildTree(sc);

        // Create solution object
        Solution102 sol = new Solution102();

        // Get level order traversal
        List<List<Integer>> result = sol.levelOrder(root);

        // Print result
        System.out.println("Level Order Traversal:");

        for (List<Integer> level : result) {
            System.out.println(level);
        }

        sc.close();
    }
}

/*
========================================
Example Input
========================================

3 9 20 -1 -1 15 7

Tree:

        3
       / \
      9   20
         /  \
        15   7

========================================
Output
========================================

[3]
[9, 20]
[15, 7]

========================================
Time Complexity
========================================

O(N)

Reason:
Each node is inserted into the queue once
and removed from the queue once.

========================================
Space Complexity
========================================

O(N)

Reason:
Queue may contain up to one complete level
of the tree in the worst case.
The answer list also stores all N nodes.
*/
