package 基础语法.学生成绩管理系统;
import java.util.Arrays;
public class Postgraduate extends Student {
    private float paperGrade; // 论文分数
    private String paperTitle; // 论文题目

    public Postgraduate(String id, String name, CourseGrade[] grades, float paperGrade, String paperTitle) {
        setId(id);
        setName(name);
        setGrades(grades);
        setPaperGrade(paperGrade);
        setPaperTitle(paperTitle);
    }

    public float getPaperGrade() {
        return paperGrade;
    }

    public void setPaperGrade(float paperGrade) {
        this.paperGrade = paperGrade;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    @Override
    public float sum() {
        float total = 0;
        for (CourseGrade grade : getGrades()) {
            total += grade.getGrade();
        }
        return total + getPaperGrade();
    }

    @Override
    public float average() {
        return sum() / (getGrades().length + 1);
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

    @Override
    public String toString() {
        return "Postgraduate{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", grades=" + Arrays.toString(getGrades()) +
                ", paperGrade=" + getPaperGrade() +
                ", paperTitle='" + getPaperTitle() + '\'' +
                '}';
    }
}
