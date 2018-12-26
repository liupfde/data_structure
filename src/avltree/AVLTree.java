package avltree;

import map.Map;

import java.util.ArrayList;

/**
 * @author :liupf
 * @description :平衡二叉树🌲
 *               定义: 每个结点的左右子树的高度差不超过 1
 *               操作: 标注平衡因子 每个叶子结点的高度为 1, 其父亲爷爷结点的高度依次递增
 *                    每个结点左右孩子的高度差为平衡因子,平衡因子的大小不超过一
 * @date :2018/11/20 20:33
 **/
public class AVLTree<K extends Comparable<K>,V> implements Map<K,V> {
    class Node{
        public K key;
        public V value;
        /**
         * 高度  每创建一个叶子结,默认高度为 1
         */
        public int height;
        Node left,right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.height = 1;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public AVLTree(){
        root = null;
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        root = add(key, value, root);
    }

    /**
     * 在添加新的结点时,维护之前叶子结点的高度
     * 即左右子树最大高度+1
     * @param key
     * @param value
     * @param node
     * @return
     */
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

        //更新node.height
        node.height = Math.max(getHeight(node.left),getHeight(node.right)) + 1;

        int balanceFactor = getBalanceFactor(node);
        //右旋转维护平衡  左边子树比右边高 ll
        if (balanceFactor>1 && getBalanceFactor(node.left)>=0){
            return rightRotate(node);
        }

        //左旋转维护平衡  右边子树比左边高 rr
        if (balanceFactor<-1 && getBalanceFactor(node.right)<=0){
            return leftRotate(node);
        }

        //插入的结点在不平衡结点左侧的右侧  lr
        if (balanceFactor>1 && getBalanceFactor(node.right)<0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //插入的结点在不平衡结点右侧的左侧  rl  先右旋再左旋
        if (balanceFactor<-1 && getBalanceFactor(node.left)>0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
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

        Node retNode = null;
        if (key.compareTo(node.key) < 0){
            node.left = remove(key,node.left);
            retNode = node;
        }
        else if (key.compareTo(node.key) > 0){
            node.right = remove(key,node.right);
            retNode = node;
        }
        else if (key.compareTo(node.key) == 0){
            //删除只有右孩子的结点
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            //删除只有左孩子的结点
            else if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }
            //待删除的结点左右孩子都存在
            else {
                //1、找到待删除结点右子树的最小结点
                //2、用该结点代替待删除结点
                Node s = maximum(node.right);
                s.right = remove(s.key,s);
                s.left = node.left;
                node.left = node.right = null;
                retNode = s;
            }
        }
        //更新node.height
        retNode.height = Math.max(getHeight(retNode.left),getHeight(retNode.right)) + 1;

        int balanceFactor = getBalanceFactor(retNode);
        //右旋转维护平衡  左边子树比右边高 ll
        if (balanceFactor>1 && getBalanceFactor(retNode.left)>=0){
            return rightRotate(retNode);
        }

        //左旋转维护平衡  右边子树比左边高 rr
        if (balanceFactor<-1 && getBalanceFactor(retNode.right)<=0){
            return leftRotate(retNode);
        }

        //插入的结点在不平衡结点左侧的右侧  lr
        if (balanceFactor>1 && getBalanceFactor(retNode.right)<0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        //插入的结点在不平衡结点右侧的左侧  rl  先右旋再左旋
        if (balanceFactor<-1 && getBalanceFactor(retNode.left)>0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }

        return maximum(node.right);
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
     * 判断当前的树是否是一棵二分搜索树
     * 利用中序遍历
     * @return
     */
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root,keys);

        for (int i = 1; i < keys.size(); i++){
            if (keys.get(i-1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断该二叉树是否是一棵平衡二叉树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    /**
     * 返回以node为根结点,key所在的结点
     * @param key
     * @param node
     * @return
     */
    private Node getNode(K key, Node node){

        if (node == null){
            return null;
        }

        if (key.compareTo(node.key) < 0){
            return getNode(key,node.left);
        } else if (key.compareTo(node.key) > 0){
            return getNode(key,node.right);
        } else {
            //key.compareTo(node.key) == 0
            return node;
        }

    }

    /**
     * 返回当前结点的高度
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if (node == null){
            return 0;
        } else {
            return node.height;
        }
    }

    /**
     * 获得当前结点的平衡因子
     * @return
     */
    private int getBalanceFactor(Node node){
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private void inOrder(Node node,ArrayList<K> keys){
        if (node == null){
            return;
        }

        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    private boolean isBalanced(Node node){
        if (node == null){
            return true;
        }

        int balancedFactor = getBalanceFactor(node);
        if (Math.abs(balancedFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    /**
     * 插入的结点在不平衡结点左侧的左侧
     * 右旋操作 返回更新后的结点
     *
     * T1<z<T2<x<T3<y<T4不变
     *                   y                    x
     *                  / \                 /   \
     *                 x  T4               z    y
     *                / \     ------->    / \  / \
     *               z  T3               T1 T2 T3 T4
     *              / \
     *             T1 T2
     * @param y
     * @return
     */
    private Node rightRotate(Node y){
        Node x = y.left;
         Node T3 = x.right;

        //右旋
        x.right = y;
        y.left = T3;

        //更新height
        //先更新y 因为x依赖于y
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 插入的结点在不平衡结点右侧的右侧
     * 左旋转操作 返回更新后的结点
     * T4>z>T3>x>T2>y>T1
     *                   y                    x
     *                  / \                 /   \
     *                 T1 x                y    z
     *                   / \    ------->  / \  / \
     *                  T2 z             T1 T2 T3 T4
     *                    / \
     *                   T3 T4
     * @param y
     * @return
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        //更新height
        //先更新y 因为x依赖于y
        y.height = Math.max(getHeight(y.left),getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left),getHeight(x.right)) + 1;
        return x;
    }
}
