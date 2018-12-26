package queue.arrayqueue;

/**
 * @author :liupf
 * @description : 循环队列 当首(front)尾(tail)相等 front == tail 时  队列为空
 *                       当(tail+1)%capacity == front时 队列为满  所以会浪费一个元素的空间
 * @date :2018/11/07 14:30
 **/
public class LoopQueue<E> implements Queue<E> {

    E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity){
        //因为会浪费一个元素的数组空间  所以创建时加一
        data = (E[]) new Object[capacity+1];
        tail = 0;
        front = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        //浪费一个空间
        return data.length-1;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if((tail+1)%data.length == front){
            //队列已满 扩容
            resize(getCapacity()*2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()){
            throw new IllegalArgumentException("队列已经为空...");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        //缩容
        if(size == getCapacity()/4 && getCapacity()/2 != 0){
            resize(getCapacity()/2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()){
            throw new IllegalArgumentException("队列已经为空...");
        }
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++){
            data[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d , capacity = %d\n",size,getCapacity()));
        res.append("front [");
        for (int i = front; i < tail; i = (i+1) % data.length){
            res.append(data[i]);
            if((i+1) % data.length != tail ){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
