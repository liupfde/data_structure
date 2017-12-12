package huffman_tree;

import java.util.List;

import static java.util.Collections.swap;

public class Sort {
    /**
     * 实现快速排序算法 用于对节点进行排序
     *
     * @param nodes
     * @param start
     * @param end
     */
    private static void subSort(List<Node> nodes,int start,int end){
        if (start<end){
            //以第一个元素作为分界值
            Node base = nodes.get(start);

            //i从左边搜索 搜索大于分界值的元素索引
            int i = start;
            //j从右边开始搜索 搜索小于分界值的元素索引
            int j = end + 1;
            while (true) {
                //找到大于分界值的元素点或者 i 已经到了end处
                while (i < end && nodes.get(++i).getWeight() > base.getWeight()) ;
                //找到小于分界值元素的点或者 j 已经到了start处
                while (j > start && nodes.get(--j).getWeight() < base.getWeight()) ;

                if (i < j) {
                    swap(nodes, i, j);
                } else {
                    break;
                }
            }
            swap(nodes,start,end);//将指定集合中的i和j索引处的元素交换  什么意思
            //递归左边子序列
            subSort(nodes,start,j-1);//   什么意思
            //递归遍历右子序列
            subSort(nodes,j+1,end);//   什么意思
        }
    }
    public static void quickSort(List<Node> nodes){
        subSort(nodes,0,nodes.size()-1);
    }
}
