package linear.linked;

import java.util.Stack;

public class LinkedList implements List {

    private Node head;//头指针
    private Node current;//当前节点的对象
    private int size;//节点的个数

    //初始化链表
    public LinkedList(){

        //初始化头结点，让头指针指向头结点。并且让当前结点对象等于头结点。
        this.head = current = new Node(null);
        this.size = 0;//初始长度为0
    }

    //定位函数,操作当前节点前,先获取当前节点的前一节点
    public void getIndex(int index){
        if(index<-1||index>size-1){
            try {
                throw new Exception("超出范围");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(index==-1){
            return;
        }
        current = head.getNext();
        int j = 0;
        while(current!=null&&j<index){
            current = current.getNext();
            j++;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(int index, Object o) throws Exception {
        if(index<0||index>size){
            throw new Exception("超出范围");
        }

        //定位到当前元素的前一个元素
        getIndex(index-1);
        current.setNext(new Node(current.getNext(),o));
        size++;
    }

    @Override
    public void delete(int index, Object o) throws Exception {
        if(isEmpty()){
            throw new Exception("链表为空,无法删除");
        }

        if(index<0||index>size){
            throw new Exception("超出范围");
        }
        getIndex(index-1);
        current.setNext(current.getNext().getNext());
        size--;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Object get(int index) throws Exception {
        if(index<1||index>size){
            throw new Exception("超出范围");
        }

        getIndex(index);

        return current.getValue();
    }
}
