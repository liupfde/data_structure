package huffman_tree;

import java.util.ArrayList;
import java.util.List;

public class HuffmanTree {
    public static void main(String args[]){
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("A",12));
        nodes.add(new Node("B",20));
        nodes.add(new Node("C",1997));
        nodes.add(new Node("D",11));
        nodes.add(new Node("E",15));
        nodes.add(new Node("F",23));
        nodes.add(new Node("G",24));
        nodes.add(new Node("H",3));
        nodes.add(new Node("I",8));
        nodes.add(new Node("J",49));
        nodes.add(new Node("K",99));

        Node root = CreateTree.createTree(nodes);
        System.out.println(Traverse.breadthFirst(root));
    }
}
