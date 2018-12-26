package queue.arrayqueue;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/07 13:02
 **/
public interface Queue<E> {
    int size();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();

    /**
     * 查看队列第一个出队元素
     * @return
     */
    E getFront();
}
