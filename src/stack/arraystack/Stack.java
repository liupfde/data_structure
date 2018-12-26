package stack.arraystack;

/**
 * @author liupfde
 */
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    E pop();
    void push(E e);

    /**
     * 查看栈顶元素
     * @return
     */
    E peek();
}
