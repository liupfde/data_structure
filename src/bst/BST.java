package bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author :liupf
 * @description :二分搜索树  其中的元素必须具有可比较性 所以继承Comparable接口
 *               此处实现的二分搜索树的元素都是不重复的
 * @date :2018/11/14 19:41
 **/
public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left,right;

        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 插入元素
     * @param e
     */
    public void add(E e){
        //方式1
//        if (root == null){
//            root = new Node(e);
//        } else {
//            add1(e,root);
//        }

        //方式2
        root = add2(e,root);
    }

    /**
     * 查看树中是否存在要查找的元素
     * @param e
     * @return
     */
    public boolean isContains(E e){
        return isContains1(e,root);
    }

    /**
     * 使用递归的方式 给根结点添加孩子结点 方式1  未优化版
     * @param e
     * @param node
     */
    private void add1(E e,Node node){
        //递归终结的条件
        if (e.equals(node.e)){
            return;
        } else if (e.compareTo(node.e)<0 && node.left==null){
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e)>0 && node.right == null){
            node.right = new Node(e);
            size++;
            return;
        }

        //递归
        if (e.compareTo(node.e) < 0){
            add1(e,node.left);
        } else if (e.compareTo(node.e) > 0){
            add1(e,node.right);
        }
    }

    /**
     * 方式2 优化版 递归到底 如果当前的结点为null 则一定是在当前结点创建一个新的结点
     * 方式一中并未递归到底
     * @param e
     * @param node
     * @return
     */
    private Node add2(E e,Node node){
        if (node == null){
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0){
            node.left = add2(e,node.left);
        } else if (e.compareTo(node.e) > 0){
            node.right = add2(e,node.right);
        }

        return node;
    }

    /**
     * 递归判断是否存在元素
     * @param e
     * @param node
     * @return
     */
    private boolean isContains1(E e,Node node){
        //递归终止条件
        if (node == null){
            return false;
        }
        if (e.compareTo(node.e) == 0){
            return true;
        }

        //递归
        if (e.compareTo(node.e) < 0){
            return isContains1(e,node.left);
        } else {
            //e.compareTo(node.e) > 0)
            return isContains1(e,node.right);
        }

    }

    /**
     * 前序遍历 :先打印 再递归遍历左右子树
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {

        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 前序遍历的非递归实现  使用栈实现
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历 输出的顺序正好是二分搜索树元素从小到大的顺序
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     * 后序遍历的一个应用 :为二分搜索树释放内存
     */
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if (node == null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 二分搜索树的层序遍历 使用队列实现
     * 层序遍历属于广度优先遍历
     * 以上三种遍历属于深度优先遍历
     */
    public void levelOrder(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (cur.left != null){
                queue.add(cur.left);
            }
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
    }

    /**
     * 寻找二叉搜索树的最小元素
     * @return
     */
    public E minimum(){
        if (isEmpty()){
            throw new IllegalArgumentException("数组为空");
        }

        return minimum(root);

    }

    /**
     * 递归实现
     * @param node
     * @return
     */
    private E minimum(Node node){
        if (node.left == null){
            return node.e;
        }

        return minimum(node.left);
    }

    /**
     * 寻找二叉搜索树的最大元素
     * @return
     */
    public E maximum(){
        if (isEmpty()){
            throw new IllegalArgumentException("数组为空");
        }

        return maximum(root).e;

    }

    /**
     * 递归实现
     * @param node
     * @return
     */
    private Node maximum(Node node){
        if (node.right == null){
            return node;
        }

        return maximum(node.right);
    }

    /**
     * 删除最小结点  返回该子树的根结点
     * @return
     */
    public E removeMin(){
        E e = minimum();
        root = removeMin(root);
        return e;
    }

    /**
     * 递归实现删除
     * 用非递归删除时 得到要删除结点的上一个结点麻烦  可以在结点中添加父节点指针解决
     * @param node
     * @return
     */
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

    /**
     * 删除最大的结点
     * @return
     */
    public E removeMax(){
        E e = maximum();
        root = removeMax(root);
        return e;
    }

    private Node removeMax(Node node) {
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除指定的结点e
     * 使用后继删除 即删除待删除元素的右子树的最小元素
     * 相应的还可以使用前驱删除 即删除待删除元素左子树的最大元素
     * @param e
     */
    public void remove(E e){
        remove(e,root);
    }

    /**
     * 返回删除结点后新的二分搜索🌲结点的根
     * @param e
     * @param node
     * @return
     */
    private Node remove(E e, Node node) {
        if (node == null){
            return null;
        }
        if (e.compareTo(node.e) < 0){
            node.left = remove(e,node.left);
            return node;
        }
        if (e.compareTo(node.e) > 0){
            node.right = remove(e,node.right);
            return node;
        }
        if (e.compareTo(node.e) == 0){
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

}
