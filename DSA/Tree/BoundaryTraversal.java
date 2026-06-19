package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Boundary Traversal of Binary Tree
 *
 * Boundary Traversal Order:
 * 1. Root Node
 * 2. Left Boundary (excluding leaf nodes)
 * 3. All Leaf Nodes (left to right)
 * 4. Right Boundary (excluding leaf nodes, in reverse order)
 *
 * Example:
 *
 *              1
 *            /   \
 *           2     3
 *          / \   / \
 *         4   5 6   7
 *
 * Boundary Traversal:
 * [1, 2, 4, 5, 6, 7, 3]
 *
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 *
 * n = number of nodes
 * h = height of tree
 */
public class BoundaryTraversal {

    /**
     * Binary Tree Node
     */
    static class Node {

        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {

        /*
                 1
               /   \
              2     3
             / \   / \
            4   5 6   7

            Expected Output:
            [1, 2, 4, 5, 6, 7, 3]
         */

        Node root = new Node(1);

        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(6);
        root.right.right = new Node(7);

        BoundaryTraversal solution = new BoundaryTraversal();

        ArrayList<Integer> result = solution.boundaryTraversal(root);

        System.out.println("Boundary Traversal : " + result);
    }

    /**
     * Main Function
     *
     * Returns boundary traversal of binary tree.
     */
    public ArrayList<Integer> boundaryTraversal(Node root) {

        // Stores final answer
        ArrayList<Integer> ans = new ArrayList<>();

        // Empty tree
        if (root == null) {
            return ans;
        }

        // Single node tree
        if (isLeaf(root)) {
            ans.add(root.data);
            return ans;
        }

        // Step 1 : Add root
        ans.add(root.data);

        // Step 2 : Add left boundary
        leftBoundary(root, ans);

        // Step 3 : Add all leaf nodes
        leafNodeDFS(root, ans);

        // Step 4 : Store right boundary separately
        ArrayList<Integer> temp = new ArrayList<>();

        rightBoundary(root, temp);

        // Reverse because boundary requires bottom-up order
        Collections.reverse(temp);

        // Merge right boundary into final answer
        ans.addAll(temp);

        return ans;
    }

    /**
     * Checks whether current node is leaf node.
     *
     * Leaf Node:
     * left == null && right == null
     */
    private boolean isLeaf(Node node) {

        return node.left == null && node.right == null;
    }

    /**
     * Adds Left Boundary
     *
     * Rules:
     * - Exclude leaf nodes
     * - Prefer left child
     * - If left child doesn't exist, move right
     */
    private void leftBoundary(Node root, List<Integer> ans) {

        // Start from root's left child
        Node curr = root.left;

        while (curr != null) {

            // Do not include leaf nodes
            if (!isLeaf(curr)) {
                ans.add(curr.data);
            }

            // Prefer left child
            if (curr.left != null) {
                curr = curr.left;
            }
            // Otherwise move right
            else {
                curr = curr.right;
            }
        }
    }

    /**
     * DFS to collect all leaf nodes.
     *
     * Traversal:
     * Left subtree first
     * Then right subtree
     *
     * This guarantees left-to-right leaf order.
     */
    private void leafNodeDFS(Node node, List<Integer> ans) {

        // Base Case
        if (node == null) {
            return;
        }

        // If leaf node found, store it
        if (isLeaf(node)) {
            ans.add(node.data);
            return;
        }

        // Explore left subtree
        leafNodeDFS(node.left, ans);

        // Explore right subtree
        leafNodeDFS(node.right, ans);
    }

    /**
     * Adds Right Boundary
     *
     * Rules:
     * - Exclude leaf nodes
     * - Prefer right child
     * - If right child doesn't exist, move left
     *
     * Stored in temp list and reversed later.
     */
    private void rightBoundary(Node root, List<Integer> temp) {

        // Start from root's right child
        Node curr = root.right;

        while (curr != null) {

            // Exclude leaf nodes
            if (!isLeaf(curr)) {
                temp.add(curr.data);
            }

            // Prefer right child
            if (curr.right != null) {
                curr = curr.right;
            }
            // Otherwise move left
            else {
                curr = curr.left;
            }
        }
    }
}