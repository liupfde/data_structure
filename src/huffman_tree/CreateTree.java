package huffman_tree;

import java.util.List;

/**
 * 构造哈夫曼树
 *
 * @param  //nodes
 *         节点集合
 * @return 构造出来的哈夫曼树的根节点
 */
public class CreateTree {
    public static Node createTree(List<Node> nodes){
        //只要nodes数组还有两个以上的节点
        while (nodes.size()>1){
            Sort.quickSort(nodes);

            //获取权值最小的两个节点
            Node left = nodes.get(nodes.size()-1);
            Node right = nodes.get(nodes.size()-2);

            //生成新节点  新节点为两个节点权值之和
            Node newNode = new Node(null,left.getWeight()+right.getWeight());
            //让新节点作为两个节点的父节点
            newNode.leftChild = left;
            newNode.rightChild = right;

            //删除两个最小节点   为什么
            nodes.remove(nodes.size()-1);
            nodes.remove(nodes.size()-1);
            //将新节点加入nodes集合
            nodes.add(newNode);
        }
        return nodes.get(0);
    }
}
