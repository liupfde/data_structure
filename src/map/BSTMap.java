package map;

import bst.BST;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/17 17:05
 **/
public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

    class Node{
        public K key;
        public V value;
        Node left,right;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(key, value, root);
    }

    private Node add(K key, V value, Node node){
        if (node == null){
            size++;
            return new Node(key,value);
        }

        if (key.compareTo(node.key) < 0){
            node.left = add(key,value,node.left);
        } else if (key.compareTo(node.key) > 0){
            node.right = add(key,value,node.right);
        } else {
            node.value = value;
        }

        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(key,root);
        if (node != null){
            root = remove(key,root);
        }
        return null;
    }

    private Node remove(K key, Node node) {
        if (node == null){
            return null;
        }
        if (key.compareTo(node.key) < 0){
            node.left = remove(key,node.left);
            return node;
        }
        if (key.compareTo(node.key) > 0){
            node.right = remove(key,node.right);
            return node;
        }
        if (key.compareTo(node.key) == 0){
            //删除只有右孩子的结点
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            //删除只有左孩子的结点
            else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            //待删除的结点左右孩子都存在
            else {
                //1、找到待删除结点右子树的最小结点
                //2、用该结点代替待删除结点
                Node s = maximum(node.right);
                s.right = removeMin(node.right);
                s.left = node.left;
                node.left = node.right = null;
                return s;
            }
        }
        return null;
    }

    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }

        return maximum(node.right);
    }

    private Node removeMin(Node node) {
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key,root);
        if (node != null){
            node.value = newValue;
        } else {
            throw new IllegalArgumentException(key + "并不存在");
        }
    }

    @Override
    public V get(K key) {
        Node node = getNode(key,root);
        return node != null ? node.value : null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isContains(K key) {
        return getNode(key,root) != null;
    }

    @Override
    public boolean isEmpty(K Key) {
        return size == 0;
    }

    /**
     * 返回以node为根结点,key所在的结点
     * @param key
     * @param node
     * @return
     */
    private Node getNode(K key,Node node){

        if (node == null){
            return null;
        }

        if (key.compareTo(node.key) < 0){
            return node.left = getNode(key,node.left);
        } else if (key.compareTo(node.key) > 0){
            return node.right = getNode(key,node.right);
        } else {
            //key.compareTo(node.key) == 0
            return node;
        }

    }
}
