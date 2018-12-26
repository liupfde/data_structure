package queue.linkedlistqueue;

public interface Queue<E> {
    void enqueue(E e);
    E dequeqe();
    int getSize();
    boolean isEmpty();
    E getFront();
}
