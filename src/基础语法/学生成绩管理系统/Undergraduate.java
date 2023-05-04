package 基础语法.学生成绩管理系统;

public class Undergraduate extends Student {

    public Undergraduate(String id, String name, CourseGrade[] grades) {
        setId(id);
        setName(name);
        setGrades(grades);
    }

    @Override
    public float sum() {
        float total = 0;
        for (CourseGrade grade : getGrades()) {
            total += grade.getGrade();
        }
        return total;
    }

    @Override
    public float average() {
        return sum() / getGrades().length;
    }

    @Override
    public int compare(Student s) {
        float thisTotal = sum();
        float thatTotal = s.sum();
        return Float.compare(thisTotal, thatTotal);
    }

    @Override
    public boolean equals(Student s) {
        float thisTotal = sum();
        float thatTotal = s.sum();
        return Float.compare(thisTotal, thatTotal) == 0;
    }
}
