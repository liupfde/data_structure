package stack.arraystack;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/07 11:35
 **/
public class StackMain {
    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(10);
        for (int i = 1;i<6;i++){
            arrayStack.push(i);
        }
        System.out.println(arrayStack);

        for (int i = 1;i<6;i++){
            System.out.println(arrayStack.pop());
        }
    }
}
