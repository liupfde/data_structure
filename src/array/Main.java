package array;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/10/30 20:21
 **/
public class Main {
    public static void main(String[] args) {
        Array<Student> array = new Array<>(3);
        array.addLast(new Student("liupf",100));
        array.addLast(new Student("qq",59));
        array.addLast(new Student("rrr",20));
        System.out.println(array.getCapacity());
        array.addLast(new Student("sy",19));
        System.out.println(array.getCapacity());

        System.out.println(array.toString());
    }
}
