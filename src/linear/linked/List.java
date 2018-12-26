package linear.linked;

public interface List {
    int size();//获得链表的长度

    void add(int index,Object o) throws Exception;//增加一个元素

    void delete(int index,Object o) throws Exception;//删除一个元素

    boolean isEmpty();//判断链表是否为空

    Object get(int index) throws Exception;//获得指定位置的元素

}
