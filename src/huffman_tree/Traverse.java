package huffman_tree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 广度优先遍历
 *
 * @author reference
 */
public class Traverse {
    public static List<Node> breadthFirst(Node root){
        Queue<Node> queue = new ArrayDeque<>();
        List<Node> list = new ArrayList<>();

        if (root!=null){
            //将根元素加入队列
            queue.offer(root);
        }

        while(!queue.isEmpty()){
            //将该队列的队尾元素加入到list中
            list.add(queue.peek());
            Node node = queue.poll();

            //如果左子节点不为null,将该节点加入队列
            if (node.leftChild!=null){
                queue.offer(node.leftChild);
            }
            //如果右子节点不为null,将该节点加入到队列
            if (node.rightChild!=null){
                queue.offer(node.rightChild);
            }
        }
        return list;
    }
}
