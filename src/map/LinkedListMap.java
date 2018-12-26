package map;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/17 15:20
 **/
public class LinkedListMap<K,V> implements Map<K,V> {

    class Node{
        public K key;
        public V value;
        public Node next;

        public Node(K key,V value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key,null,null);
        }

        public Node(){
            this(null,null,null);
        }

        @Override
        public String toString(){
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dymmyHead;
    private int size;

    public LinkedListMap(){
        dymmyHead = new Node();
        size = 0;
    }

    @Override
    public void add(K key, V value) {

        Node node = getNode(key);

        if (node == null){
            dymmyHead.next = new Node(key,value,dymmyHead.next);
            size++;
        } else {
            //如果已经存在这个结点 则更新这个结点的值
            node.value = value;
        }

    }

    @Override
    public V remove(K key) {

        Node prev = dymmyHead;
        while (prev.next != null){
            if (key.equals(prev.next.key)){
                Node cur = prev.next;
                prev.next = cur.next;
                cur.next = null;
                size--;
                return cur.value;
            }
            prev = prev.next;
        }

        return null;
    }

    @Override
    public void set(K key, V newValue) {

        Node node = getNode(key);

        if (node == null){
            throw new IllegalArgumentException("要更新的结点不存在");
        }

        add(key,newValue);
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isContains(K key) {
        return getNode(key) != null;
    }

    @Override
    public boolean isEmpty(K Key) {
        return size == 0;
    }

    private Node getNode(K key){
        Node cur = dymmyHead.next;
        while (cur.next != null){
            if (key.equals(cur.key)){
                return cur;
            }

            cur = cur.next;
        }

        return null;
    }
}
