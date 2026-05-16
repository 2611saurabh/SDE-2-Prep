package Tree;

import java.util.ArrayList;
import java.util.List;

class Node{
    int value;
    Node left;
    Node right;

    Node(int value){
        this.value = value;
    }
    Node(int value, Node left, Node right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
class Traversal {
    private void preorder(Node node, List<Integer> ans) {

        if (node == null) return;

        ans.add(node.value);
        preorder(node.left, ans);
        preorder(node.right, ans);
    }

    public List<Integer> preorder(Node node){
        List<Integer> ans = new ArrayList<>();

        if(node == null) return new ArrayList<>();

        preorder(node, ans);
        return ans;
    }
}
public class PreorderTraversal {

    public static void main(String[] args) {


        Traversal tree = new Traversal();

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);


        List<Integer> ans = tree.preorder(root);
        System.out.println("Tree node in order state -> " + ans);
    }
}
