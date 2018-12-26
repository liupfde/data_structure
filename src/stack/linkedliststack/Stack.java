package stack.linkedliststack;

public interface Stack<E> {

    void push(E e);
    E pop();

    /**
     *查看当前栈顶元素
     */
    E peek();
    boolean isEmpty();
    int getSize();

}
