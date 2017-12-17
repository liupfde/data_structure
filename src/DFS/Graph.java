package DFS;

/**
 * 图的深度优先遍历
 */


public class Graph {
    public static final boolean UNDIRECTED_GRAPH = false;//无向图标志
    public static final boolean DIRECTED_GRAPH = true;//有向图标志

    public static final boolean ADJACENCY_MATRIX = true;//邻接矩阵实现
    public static final boolean ADJACENCY__LISt = false;//邻接表实现

    public final int MAX_VALUE = Integer.MAX_VALUE;
    private boolean graphType;
    private boolean method;
    private int vertexSize;
    private int matrixMaxVertex;

    //存储所有顶点信息的一维数组
    private Object vertexesArray[];
    //存储顶点之间关联关系的二维数组 即边的关系
    private int edgesMatrix[][];

    //记录第i个节点是否被访问过
    private boolean visited[];

    /**
     * @param graphType 图的类型:有向图/无向图
     * @param method 图的实现方式:邻接矩阵/邻接表
     */

    public Graph(boolean graphType,boolean method,int size){
        this.graphType = graphType;
        this.method = method;
        this.vertexSize = 0;
        this.matrixMaxVertex = size;
        if(this.method){
            visited = new boolean[matrixMaxVertex];
            vertexesArray = new Object[matrixMaxVertex];
            edgesMatrix = new int[matrixMaxVertex][matrixMaxVertex];

            //对数组进行初始化,定点间，没有边关联的值为Integer的最大值
            for(int row = 0;row<edgesMatrix.length;row++){
                for(int column = 0;column<edgesMatrix.length;column++){
                    edgesMatrix[row][column] = MAX_VALUE;
                }
            }
        }
    }
    /**
     * 深度优先搜索(dfs) 递归
     */
    public void DFS(){
        //从第一行添加的元素开始搜索
        DFS(vertexesArray[0]);
    }
    public void DFS(Object o){
        int index = -1;
        for(int i = 0;i<vertexSize;i++){
            if(vertexesArray[i].equals(o)){
                index = i;
                break;
            }
        }
        if(index == -1){
            throw new NullPointerException("没有这个值");
        }
        for (int i = 0;i<vertexSize;i++){
            vertexesArray[i] = false;
        }
        traverse(index);
        //graphType为true是有向图
        if(graphType){
            for(int i =0;i<vertexSize;i++){
                if(!visited[i]){
                    traverse(i);
                }
            }
        }
    }
    private void traverse(int index) {
        visited[index] = true;
        System.out.println(vertexesArray[index]+" ");
        //递归 若index=-1,该方法会执行,会回溯到上一级定点
        for(int j = firstAdjVex(index);j>=0;j = nextAdjVex(index,j)){
            if(!visited[j]){
                traverse(j);
            }
        }
    }

    private int firstAdjVex(int row) {
        for(int column = 0;column<vertexSize;column++){
            if(edgesMatrix[row][column]==1){
                return column;
            }
        }
        return -1;
    }
    private int nextAdjVex(int row, int k) {
        for(int j = k+1;j<vertexSize;j++){
            if(edgesMatrix[row][j]==1){
                return j;
            }
        }
        return -1;
    }
    public boolean addVertex(Object val){
        assert(val!=null);
        vertexesArray[vertexSize] = val;
        vertexSize++;
        return true;
    }
    public boolean addEdge(int vnum1,int vnum2){
        assert(vnum1>=0&&vnum2>=0&&vnum1!=vnum2);
        if(graphType){//有向图
            edgesMatrix[vnum1][vnum2]=1;
        }else {//无向图
            edgesMatrix[vnum1][vnum2]=1;
            edgesMatrix[vnum2][vnum1]=1;
        }
        return true;
    }
}


































