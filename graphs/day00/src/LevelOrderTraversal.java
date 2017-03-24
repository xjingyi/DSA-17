import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static List<Integer> levelOrderTraversal(TreeNode<Integer> n) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(n);
        ArrayList list = new ArrayList();
        while (!queue.isEmpty())
        {
            TreeNode tempNode = queue.poll();
            list.add(tempNode.key);

            if (tempNode.leftChild != null) {
                queue.add(tempNode.leftChild);
            }

            if (tempNode.rightChild != null) {
                queue.add(tempNode.rightChild);
            }
        }
        return list;
    }
}
