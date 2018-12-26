package set;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/17 13:11
 **/
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean isContains(E e);
    int getSize();
    boolean isEmpty();
}
