package bst;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/15 20:17
 **/
public class BSTMain {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5,3,6,8,4,2};
        for (int i:nums){
            bst.add(i);
        }
        //每一个结点有三次机会访问
        //三种遍历的区别只是访问结点的第几次时输出结点元素
        System.out.println("前序遍历");
        bst.preOrderNR();
        System.out.println();
        System.out.println();
        System.out.println("中序遍历");
        bst.inOrder();
        System.out.println();
        System.out.println();
        System.out.println("后序遍历");
        bst.postOrder();
        System.out.println();
        System.out.println();
        System.out.println("层序遍历");
        bst.levelOrder();

        Random random = new Random();
        int n = 1000;
        for (int i = 0; i < n; i++){
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (!bst.isEmpty()){
            list.add(bst.removeMin());
        }

        System.out.println(list);
        for (int i = 1; i < list.size(); i++){
            if (list.get(i-1) > list.get(i)){
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("成功");

    }
}
