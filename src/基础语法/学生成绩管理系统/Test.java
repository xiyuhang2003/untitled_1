package 基础语法.学生成绩管理系统;
import java.util.*;

public class Test {
    private static List<Student> students = new ArrayList<>();
    public static void main(String[] args) {
        initData();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(" ");
            System.out.println("1. 添加学生信息");
            System.out.println("2. 修改学生信息");
            System.out.println("3. 删除学生信息");
            System.out.println("4. 按总成绩排序输出");
            System.out.println("5. 按课程成绩排序输出");
            System.out.println("6. 查看所有学生成绩");
            System.out.println("0. 退出程序");
            System.out.println("请选择操作：");
            int option = scanner.nextInt();

            switch (option) {
                case 1 -> addStudent();
                case 2 -> modifyStudent();
                case 3 -> deleteStudent();
                case 4 -> sortByTotalGrade();
                case 5 -> sortByCourseGrade();
                case 6 -> printStudents();
                case 0 -> {
                    System.out.println("程序已退出");
                    return;
                }
                default -> System.out.println("无效的选项");
            }
        }
    }

    private static void initData() {
        students.add(new Undergraduate("1001", "张三", new CourseGrade[]{
                new CourseGrade(1, 80),
                new CourseGrade(2, 90),
                new CourseGrade(3, 70)
        }));

        students.add(new Undergraduate("1002", "李四", new CourseGrade[]{
                new CourseGrade(1, 70),
                new CourseGrade(2, 75),
                new CourseGrade(3, 80)
        }));

        students.add(new Undergraduate("1003", "王五", new CourseGrade[]{
                new CourseGrade(1, 85),
                new CourseGrade(2, 70),
                new CourseGrade(3, 90)
        }));

        students.add(new Postgraduate("2001", "赵六", new CourseGrade[]{
                new CourseGrade(1, 85),
                new CourseGrade(2, 70),
                new CourseGrade(3, 90)
        }, 85, "有关决策树增益权系数修改的猜想"));

        students.add(new Postgraduate("2002", "钱七", new CourseGrade[]{
                new CourseGrade(1, 75),
                new CourseGrade(2, 80),
                new CourseGrade(3, 90)
        }, 90, "论中国特色社会主义特色优越性"));
    }
    private static void addStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入学生类型（1. 本科生，2. 研究生）：");
        int type = scanner.nextInt();

        System.out.println("请输入学号：");
        String id = scanner.next();

        System.out.println("请输入姓名：");
        String name = scanner.next();

        CourseGrade[] grades = new CourseGrade[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("请输入" + getCourseName(i + 1) + "成绩：");
            float grade = scanner.nextFloat();

            grades[i] = new CourseGrade(i + 1, grade);
        }

        switch (type) {
            case 1 -> students.add(new Undergraduate(id, name, grades));
            case 2 -> {
                System.out.println("请输入论文分数：");
                float thesisGrade = scanner.nextFloat();

                System.out.println("请输入论文题目：");
                String thesisTitle = scanner.next();
                students.add(new Postgraduate(id, name, grades, thesisGrade, thesisTitle));
            }
            default -> System.out.println("无效的学生类型");
        }
    }

    private static void modifyStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入要修改的学生的学号：");
        String id = scanner.next();

        for (Student student : students) {
            if (student.getId().equals(id)) {
                CourseGrade[] grades = new CourseGrade[3];

                for (int i = 0; i < 3; i++) {
                    System.out.println("请输入" + getCourseName(i + 1) + "成绩（原分数为 " + student.getGrades()[i].getGrade() + "）：");
                    float grade = scanner.nextFloat();

                    grades[i] = new CourseGrade(i + 1, grade);
                }

                if (student instanceof Postgraduate) {
                    System.out.println("请输入论文分数（原分数为 " + ((Postgraduate) student).getPaperGrade() + "）：");
                    float thesisGrade = scanner.nextFloat();

                    System.out.println("请输入论文题目（原题目为 " + ((Postgraduate) student).getPaperTitle() + "）：");
                    String thesisTitle = scanner.next();

                    ((Postgraduate) student).setPaperGrade(thesisGrade);
                    ((Postgraduate) student).setPaperTitle(thesisTitle);
                }

                student.setGrades(grades);
                System.out.println("学生信息修改成功");
                return;
            }
        }

        System.out.println("找不到对应的学生");
    }

    private static void deleteStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入要删除的学生的学号：");
        String id = scanner.next();

        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);

            if (student.getId().equals(id)) {
                students.remove(i);
                System.out.println("学生信息删除成功");
                return;
            }
        }

        System.out.println("找不到对应的学生");
    }

    private static void sortByTotalGrade() {
        Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return Float.compare(o2.sum(), o1.sum());
                    }
                }
        );
        printStudents();
    }

    private static void sortByCourseGrade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请选择课程编号：");
        System.out.println("1. 计算机");
        System.out.println("2. 数学");
        System.out.println("3. 英语");

        int courseOption = scanner.nextInt();

        String courseName = getCourseName(courseOption);

        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                float grade1 = getCourseGrade(o1, courseOption);
                float grade2 = getCourseGrade(o2, courseOption);
                return Float.compare(grade2, grade1);
            }
        });

        System.out.println("按" + courseName + "成绩排序输出：");
        printStudents();
    }

    private static String getCourseName(int courseOption) {
        switch (courseOption) {
            case 1 -> {
                return "计算机";
            }
            case 2 -> {
                return "数学";
            }
            case 3 -> {
                return "英语";
            }
            default -> {
                return "未知课程";
            }
        }
    }

    private static float getCourseGrade(Student student, int courseOption) {
        CourseGrade[] grades = student.getGrades();
        switch (courseOption) {
            case 1 -> {
                return grades[0].getGrade();
            }
            case 2 -> {
                return grades[1].getGrade();
            }
            case 3 -> {
                return grades[2].getGrade();
            }
            default -> {
                return 0;
            }
        }
    }

    private static void printStudents() {
        for (Student student : students) {
            StringBuilder sb = new StringBuilder();
            sb.append(student.getId());
            sb.append(" ");
            sb.append(student.getName());
            sb.append(": ");

            CourseGrade[] grades = student.getGrades();

            for (CourseGrade grade : grades) {
                sb.append(getCourseName(grade.getId()));
                sb.append("(");
                sb.append(grade.getGrade());
                sb.append(") ");
            }

            if (student instanceof Postgraduate) {
                sb.append("论文(");
                sb.append(((Postgraduate) student).getPaperTitle());
                sb.append(": ");
                sb.append(((Postgraduate) student).getPaperGrade());
                sb.append(") ");
            }
            System.out.println(sb.toString());
        }
    }
}

