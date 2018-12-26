package stack.linkedliststack;

import linkedlist.LinkedList;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/12 15:58
 **/
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack(){
        linkedList = new LinkedList<>();
    }
    @Override
    public void push(E o) {
        linkedList.addFirst(o);
    }

    @Override
    public E pop() {
        return linkedList.delete(0);
    }

    @Override
    public E peek() {
        return linkedList.get(0);
    }

    @Override
    public boolean isEmpty() {
        return linkedList.getSize() == 0;
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Stack :top ");
        res.append(linkedList);
        return res.toString();
    }
}
