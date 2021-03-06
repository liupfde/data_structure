package set;

import bst.BST;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/17 13:14
 **/
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;
    public BSTSet(){
        bst = new BST();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean isContains(E e) {
        return bst.isContains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
