package set;

import linkedlist.LinkedList;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/17 13:39
 **/
public class LinkedListSet<E> implements Set<E> {

    LinkedList<E> linkedList;

    public LinkedListSet(){
        linkedList = new LinkedList<>();
    }
    @Override
    public void add(E e) {
        if (!linkedList.contains(e)){
            linkedList.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        linkedList.delete(0);
    }

    @Override
    public boolean isContains(E e) {
        return linkedList.contains(e);
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
