package stack_queue;

import java.util.Stack;
/**
 * 使用双栈实现队列
 * in栈用于元素进入
 * out栈用于元素弹出
 *
 * 元素在弹出之前 先从in栈进入out栈 这样就实现了和队列一样的元素顺讯 先进先出
 * */
public class DoubleStackToQueue {
    private static Stack<Integer> in = new Stack<>();
    private static Stack<Integer> out = new Stack<>();

    public void push(Integer i){
        in.push(i);
    }

    public Stack<Integer> pop() throws Exception {
        if (out.empty()) {
            while (!in.empty()) {
                //System.out.println(in.pop());
                out.push(in.pop());
            }
        }
        if(out.empty()){
            throw new Exception("the queue.arrayqueue is empty");
        }
        return out;
    }

    public static void main(String args[]) throws Exception {
        DoubleStackToQueue dst = new DoubleStackToQueue();
        for(int i = 0;i<18;i++){
            dst.push(i);
        }

        System.out.println(in);
        System.out.println("----------------------------------------------------");
        System.out.println(dst.pop());
    }
}
