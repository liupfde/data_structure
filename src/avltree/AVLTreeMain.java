package avltree;

import java.util.Random;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/11/20 21:19
 **/
public class AVLTreeMain {
    public static void main(String[] args) {
        AVLTree<Integer,Integer> avlTree = new AVLTree<>();
        int n = 100;
        Random random = new Random();
        for (int i = 0; i < 4; i++){
            avlTree.add(i, random.nextInt(n));
        }

        for (int i = 0; i < avlTree.getSize(); i++){
            System.out.println(avlTree.get(i));
        }

        System.out.println(avlTree.isBST());
        System.out.println(avlTree.isBalanced());
    }
}
