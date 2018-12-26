package array;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/10/30 19:41
 **/
public class Array<E> {
    /**
     * 数组的容量
     */
    private E[] data;
    /**
     * 数组实际存在的内容大小
     */
    private int size;

    /**
     * 初始化数组大小
     * @param capacity
     */
    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    /**
     * 默认初始化数组容量为10
     */
    public Array(){
        this(10);
    }

    public Array(E[] arr){
        data = (E[]) new Object[arr.length];
        for (int i=0; i<arr.length; i++){
            data[i] = arr[i];
        }
        size = data.length;
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 向数组的最后一个位置添加一个元素
     *
     * 第size的位置实际上是最后一个元素的下一个位置
     * 因为当size = 0时,什么元素都没有
     * @param e
     */
    public void addLast(E e){
        if(size == data.length){
            resize(2*data.length);
        }
        data[size] = e;
        size++;
    }

    /**
     * 向数组的第一个位置插入元素
     * 调用add()方法
     * 同理 上边那个向数组中最后一个位置插入元素也可以用add()方法实现
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 向数组的指定位置添加元素
     * @param index
     * @param e
     */
    public void add(int index,E e){

        if(index <0 || index > size){
            throw new IllegalArgumentException("插入的位置不合适");
        }
        //动态扩容
        if(size == data.length){
            resize(2*data.length);
        }
        for (int i = size - 1;i >= index;i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 得到指定位置数组的元素
     * @param index
     * @return
     */
    public E get(int index){
        if(index<0||index>size){
            throw new IllegalArgumentException("索引位置错误");
        }
        return data[index];
    }

    /**
     * 修改指定位置数组的元素
     * @param index
     * @param e
     */
    public void set(int index,E e){
        if(index<0||index>size){
            throw new IllegalArgumentException("索引位置错误");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否包含某元素
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0;i<size;i++){
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找指定元素在数组中的位置
     * 若不存在 则返回-1
     * @param e
     * @return
     */
    public int find(E e){
        for (int i = 0;i<size;i++){
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定位置的元素  并返回该元素
     * @param index
     */
    public E remove(int index){
        if(index<0 || index>size ){
            throw new IllegalArgumentException("索引位置错误");
        }
        if (isEmpty()){
            throw new IllegalArgumentException("数组以为空");
        }
        E ret = data[index];
        for (int i = index+1;i<size;i++){
            data[i-1] = data[i];
        }
        size--;
        //用了泛型以后 size指的就是引用  为了快速释放引用所指向的内存 设置为null;
        data[size] = null;

        /**
         * 当整个数组实际容量为数组大小的1/4时才进行缩容,这样做防止复杂度震荡
         * 因为如果是1/2的话，当添加最后一个元素后再删除最后一个元素，实际上是既扩容了又缩容了
         * 导致执行效率下降
         *
         * 还有 防止不断缩小的过程中把数组容量缩小为0
         */
        if(size==data.length/4&&(data.length/2!=0)){
            resize(data.length/2);
        }
        return ret;
    }

    /**
     * 删除数组的第一个元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除数组的最后一个元素
     * @return
     */
    public E removeLast(){
        return remove(size-1);
    }

    /**
     * 若数组存在该元素  则删除
     * @param e
     */
    public void removeElement(E e){
        if(find(e)!=-1){
            remove(find(e));
        }
    }

    public void swap(int i,int j){
        if (i<0 || i>=size || j<0 || j>= size){
            throw new IllegalArgumentException("越界l");
        }

        E temp = data[i];
        temp = data[j];
        data[j] = temp;
    }
    /**
     * 数组扩容 或者删除数组多余的部分
     * @param newCapacity
     */
    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0;i<size;i++){
            newData[i] = data[i];
        }
        //把引用指向新的数组,原来的旧数组之后就会被垃圾回收器回收
        data = newData;
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n",size,data.length));
        res.append('[');
        for (int i = 0;i<size;i++){
            res.append(data[i]);
            if(i!=size-1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }
}

