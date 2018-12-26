package map;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/17 15:17
 **/
public interface Map<K,V> {
    void add(K key,V value);
    V remove(K key);
    void set(K key,V newValue);
    V get(K key);
    int getSize();
    boolean isContains(K key);
    boolean isEmpty(K Key);
}
