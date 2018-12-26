package queue.linkedlistqueue;

/**
 * @author :liupf
 * @description :给链表加上尾指针 改进链表实现队列
 *               其中 链表的头部用来当队首 链表的尾部用来当队尾
 *               并且因为删除和插入分别在连链表的两端 所以取消虚拟头结点
 * @date :2018/11/12 20:40
 **/
public class LinkedListQueue<E> implements Queue<E>{

    private class Node{
        E e;
        Node next;
        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private int size;
    private Node head;
    private Node tail;

    public LinkedListQueue(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null){
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeqe() {
        if (isEmpty()){
            throw new IllegalArgumentException("队列为空,无法出队");
        }
        Node retNode = head;
        /**
         * 下面两行的代码不可以交换 要先把head移到下一个位置再 让原来的head为空
         */
        head = head.next;
        retNode.next = null;
        if (head == null){
            //假设出队的是最后一个元素,不能出队完成后 tail还指向原来的元素 即tail还存在
            tail = null;
        }
        size--;
        return retNode.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            System.out.println("队列为空");
        }
        return head.e;
    }
}
