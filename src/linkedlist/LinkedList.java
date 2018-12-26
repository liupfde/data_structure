package linkedlist;

/**
 * @author :liupf
 * @description :链表没有索引
 * @date :2018/11/09 11:40
 **/
public class LinkedList<E> {
    private class Node{
        public E e;
        public Node next;

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

    /**
     * 链表的头  虚拟头结点  0之前的结点
     */
    private Node dummyHead;
    /**
     * 链表的长度
     */
    private int size;

    public LinkedList(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向链表的指定"索引"位置添加元素
     * @param index
     * @param e
     */
    public void add(int index,E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("访问越界!!!");
        }
        //要插入位置的前一个元素
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        Node node = new Node(e);
        node.next = pre.next;
        pre.next = node;
//      pre.next = new Node(e,pre.next);
        size++;
    }

    /**
     * 向链表的头部添加元素
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    /**
     * 遍历找到结点中的元素
     * @param index
     * @return
     */
    public E get(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("访问越界!!!");
        }
        Node node = dummyHead;
        for (int i = 0; i < size+1; i++){
            node = node.next;
        }
        return node.e;
    }

    /**
     * 在指定的位置更新元素
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("访问越界!!!");
        }

        Node node = dummyHead.next;
        for(int i = 0; i < size; i++){
            node = node.next;
        }
        node.e = e;
    }

    /**
     * 查找链表中是否存在指定元素
     * @param e
     * @return
     */
    public boolean contains(E e){
        Node node = dummyHead.next;
        while (node != null){
            if (node.e.equals(e)){
                return true;
            }

            node = node.next;
        }
        return false;
    }

    /**
     * 删除元素
     * @param index
     * @return
     */
    public E delete(int index){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("访问越界!!!");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++){
            prev = prev.next;
        }

        Node node = prev.next;
        prev.next = node.next;
        node.next = null;
        size--;

        return node.e;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node node = dummyHead.next;
        while (node != null){
            res.append(node + "->");
            node = node.next;
        }

        res.append("NULL");
        return res.toString();
    }
}
