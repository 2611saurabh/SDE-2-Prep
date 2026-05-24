package Tree;

import java.util.ArrayList;
import java.util.List;

class TraversalPostorder{
    public void postorderReccursion(Node node, List<Integer> ans){

        if(node == null) return;

        postorderReccursion(node.left, ans);
        postorderReccursion(node.right, ans);
        ans.add(node.value);

    }

    public List<Integer> postorder(Node node){

        List<Integer> ans = new ArrayList<>();

        if(node == null) return new ArrayList<>();
        postorderReccursion(node, ans);

        return ans;
    }
}
public class PostorderTraversal {

    public static void main(String[] args) {

        TraversalPostorder tree = new TraversalPostorder();

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);


        List<Integer> ans = tree.postorder(root);
        System.out.println("Tree node in order state -> " + ans);
    }
}
