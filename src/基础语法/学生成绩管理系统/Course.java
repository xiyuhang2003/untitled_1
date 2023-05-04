package 基础语法.学生成绩管理系统;

public class Course {
    private int id; // 课程ID
    private String name; // 课程名，如english, math, computer等

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
