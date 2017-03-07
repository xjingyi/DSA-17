import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    public List<T> inOrderTraversal() {
        List<T> list = new ArrayList<T>();
        addInorder(root,list);
        return list;
    }

    void addInorder(TreeNode<T> node, List<T> list)
    {
        if (node == null)
            return;

        addInorder(node.leftChild,list);

        /* then print the data of node */
        list.add(node.key);

        /* now recur on right child */
        addInorder(node.rightChild,list);
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;

        TreeNode<T> replacement;

        if (n.isLeaf())
        { // Case 1: no children
            replacement = null;
        n.replaceWith(replacement);}
        else if (n.hasRightChild() != n.hasLeftChild())
            // Case 2: one child
        {replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
        n.replaceWith(replacement);}
        else {
            replacement = findSuccessor(n);
            n.key= replacement.key;
            delete(replacement);
        }
        // Put the replacement in its correct place, and set the parent.
        return replacement;
    }

    public T findPredecessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> predecessor = findPredecessor(n);
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> successor = findSuccessor(n);
            if (successor != null)
                return successor.key;
        }
        return null;
    }
    public TreeNode minValue(TreeNode node) {
        TreeNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current;
    }
    public TreeNode maxValue(TreeNode node) {
        TreeNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current;
    }


    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        if (n == null)
            return null;

        if (n.leftChild != null)
            return maxValue(n.leftChild);
        TreeNode pre = null;
        TreeNode<T> roott = root;
        while (n.isLeftChild())
        {   n=n.parent;
        }
         pre = n.parent;
        return pre;
    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        if (n.rightChild!=null){
            return minValue(n.rightChild);
        }
        TreeNode succ = null;
        TreeNode<T> roott = root;
        while (n.isRightChild())
        {   n=n.parent;
        }
        succ = n.parent;
        return succ;
    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
