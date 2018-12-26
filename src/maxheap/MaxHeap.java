package maxheap;

import array.Array;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/17 22:20
 **/
public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        for (int i=parent(arr.length-1); i>0; i--){
            siftDown(i);
        }
    }


    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    /**
     * 删除堆中最大的元素
     */
    public E extractMax(){
        if (data.getSize() == 0){
            throw new IllegalArgumentException("数组为空");
        }
        E e = data.get(0);
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return e;
    }

    /**
     * 返回一个当前索引父节点的索引
     * @param index
     * @return
     */
    private int parent(int index){
        if (index<1 || index > data.getSize()-1){
            throw new IllegalArgumentException("越界");
        }
        return (index-1)/2;
    }

    /**
     * 返回当前索引结点的左孩子索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        if (index > data.getSize()-1){
            throw new IllegalArgumentException("越界");
        }
        return 2*index + 1;
    }

    /**
     * 返回当前索引结点的左孩子索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        if (index > data.getSize()-1){
            throw new IllegalArgumentException("越界");
        }
        return 2*index + 2;
    }

    private void siftUp(int k){
        while (k>0 && data.get(k).compareTo(data.get(parent(k)))>0){
            data.swap(k,parent(k));
            k = parent(k);
        }
    }

    private void siftDown(int k){
        while (leftChild(k) < data.getSize()){
            int x = leftChild(k);
            //如果左孩子比右孩子小 则l = 右孩子
            if (x+1<data.getSize() && data.get(x).compareTo(data.get(x+1))<0){
                x = x+1;
            }
            if (data.get(k).compareTo(data.get(x)) < 0){
                data.swap(k,x);
                k = x;
            } else {
                break;
            }
        }
    }

}
