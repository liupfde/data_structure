package linear.linked;

public class Node {
    private Object value;//数据域
    private Node next;//指针域 指向下一个元素

    //头节点的构造方法
    public Node(Node next){
        this.next = next;
    }

    //非头节点的构造方法
    public Node(Node next,Object value){
        this.next = next;
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
