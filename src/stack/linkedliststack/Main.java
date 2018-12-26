package stack.linkedliststack;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/12 19:13
 **/
public class Main {
    public static void main(String[] args) {
        LinkedListStack<Integer> linkedListStack = new LinkedListStack();
        for (int i = 0; i < 5; i++){
            linkedListStack.push(i);
            System.out.println(linkedListStack.toString());
        }
        for (int i = 0; i < 5; i++){
            System.out.println(linkedListStack.pop());
        }
    }
}
