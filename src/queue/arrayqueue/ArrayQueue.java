package queue.arrayqueue;

import array.Array;

/**
 * @author :liupf
 * @description :数组队列  出队时的时间复杂度是O(n)  并不好 采用循环队列优化
 * @date :2018/11/07 13:11
 **/
public class ArrayQueue<E> implements Queue<E> {

    Array<E> array;

    public ArrayQueue(){
        array = new Array<>();
    }
    public ArrayQueue(int capacity){
        array = new Array<>(capacity);
    }

    public int getCapacity(){
        return array.getCapacity();
    }
    @Override
    public int size() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.get(0);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0;i<array.getSize();i++){
            res.append(array.get(i));
            if(i!=array.getSize()-1){
                res.append(",");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
