package linkedlist;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/09 18:29
 **/
public class LinkedListMain {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++){
            linkedList.addFirst(i);
            System.out.println(linkedList.toString());
        }

        linkedList.add(2,24);
        System.out.println(linkedList.toString());
        Integer i = linkedList.delete(2);
        System.out.println(i);
        System.out.println(linkedList.toString());
    }
}
