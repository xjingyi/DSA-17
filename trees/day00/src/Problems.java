import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        for(Integer i : values) {
            tree.add(i);
        }
        List list = tree.inOrderTraversal();

        BinarySearchTree<Integer> tree2 = new BinarySearchTree<>();
        addSorted(tree2,list,0,list.size()-1);

        return tree2;
    }

    public static BinarySearchTree<Integer> addSorted(BinarySearchTree<Integer> tree,List<Integer> list, int start, int end) {
        /* Base Case */
        if (start > end) {
            return null;
        }

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        tree.add(list.get(mid));

        /* Recursively construct the left subtree and make it
         left child of root */
        addSorted(tree, list,start, mid - 1);

        /* Recursively construct the right subtree and make it
         right child of root */
        addSorted(tree, list,mid+1, end);

        return tree;
    }



    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // Both roots are NULL, trees isomorphic by definition
        if (n1 == null && n2 == null)
            return true;

        // Exactly one of the n1 and n2 is NULL, trees not isomorphic
        if (n1 == null || n2 == null)
            return false;

        if (n1.key != n2.key)
            return false;

        return (isIsomorphic(n1.leftChild, n2.leftChild) &&
                isIsomorphic(n1.rightChild, n2.rightChild))
                || (isIsomorphic(n1.leftChild, n2.rightChild) &&
                isIsomorphic(n1.rightChild, n2.leftChild));
    }
}
