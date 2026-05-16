package Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class TreeNode{

    int value;
    TreeNode left;
    TreeNode right;
    public TreeNode(int value){
        this.value = value;
    }
    public TreeNode(int value,TreeNode left,TreeNode right){
        this.value=value;
        this.left=left;
        this.right=right;
    }
}
class Tree{
    public void inorder(TreeNode node, List<Integer> ans){
        if(node == null){
            return;
        }

        inorder(node.left, ans);
        ans.add(node.value);
        inorder(node.right, ans);
    }
    public List<Integer> inOrderTraversal(TreeNode root){
        List<Integer> ans=new ArrayList<>();

        if(root == null){
            return new ArrayList<>();
        }

        inorder(root, ans);
        return ans;
    }
    /*
    Iterative Approch for inorder traversal;
     */
    public List<Integer> inOrderTraversalByIterative(TreeNode root){
        List<Integer> ans=new ArrayList<>();
        Stack<TreeNode> st=new Stack<>();
        TreeNode node = root;


        while(true){
            if(node != null){
                st.add(node);
                node = node.left;
            }
            else{

                if(st.isEmpty()){
                    break;
                }

                node = st.pop();
                ans.add(node.value);
                node = node.right;
            }
        }

        return ans;
    }
}


public class InorderTraversal {

    public static void main(String[] args) {
        Tree tree = new Tree();

        TreeNode root =  new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);


        List<Integer> ans = tree.inOrderTraversal(root);
        System.out.println("Tree node in order state -> "+ ans);



    }
}
/*
Time Complexity of this inorder traversal is O(n) where n is number of node visited at each time
Space Complexity is O(N) because we store n nodes in list
Recusrsive Stack complexity is O(h) where we store h eight of data(height of tree)
 */