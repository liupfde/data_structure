package array;

/**
 * @author :liupf
 * @description :TODO
 * @date :2018/10/30 21:41
 **/
public class Student {
    private String studentName;
    private Integer scores;

    public Student(String studentName,Integer scores){
        this.studentName = studentName;
        this.scores = scores;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", scores=" + scores +
                '}';
    }
}
