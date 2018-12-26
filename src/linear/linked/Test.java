package linear.linked;

public class Test {
    public static void main(String args[]) throws Exception {
        LinkedList list = new LinkedList();
        for(int i = 0;i<10;i++){
            int temp = ((int) (Math.random() * 100)) % 100;
            list.add(i,temp);
            System.out.println(list.get(5));
        }
    }
}
