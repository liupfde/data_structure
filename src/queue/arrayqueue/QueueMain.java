
package queue.arrayqueue;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/07 13:16
 **/
public class QueueMain {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 1;i<6;i++){
            queue.enqueue(i);
        }
        System.out.println(queue);

        for (int i = 1;i<6;i++){
            System.out.println(queue.dequeue());
        }
    }
}
