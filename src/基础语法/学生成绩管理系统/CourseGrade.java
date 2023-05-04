package 基础语法.学生成绩管理系统;

public class CourseGrade {
    private int id; // 课程ID
    private float grade; // 课程分数

    public  CourseGrade(int id,float grade){
        this.id=id;
        this.grade=grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}
