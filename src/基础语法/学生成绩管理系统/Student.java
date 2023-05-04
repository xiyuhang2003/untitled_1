package 基础语法.学生成绩管理系统;
import java.util.Arrays;

public abstract class Student {
    private String id; // 学号
    private String name; // 姓名
    private CourseGrade[] grades; // 多门课程的成绩

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseGrade[] getGrades() {
        return grades;
    }

    public void setGrades(CourseGrade[] grades) {
        this.grades = grades;
    }

    public abstract float sum(); // 计算总成绩

    public abstract float average(); // 计算平均成绩

    public abstract int compare(Student s); // 比较两个学生各个科目的成绩

    public abstract boolean equals(Student s); // 比较两个学生总成绩是否相等

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", grades=" + Arrays.toString(grades) +
                '}';
    }
}
