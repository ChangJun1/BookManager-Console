package book.manage;

import book.manage.entity.Book;
import book.manage.entity.Student;
import book.manage.sql.SqlUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

/**
 * @author ach
 */
@Log
public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
            while (true) {
                System.out.println("===================");
                System.out.println("1. 录入学生信息");
                System.out.println("2. 录入书籍信息");
                System.out.println("3. 添加借阅信息");
                System.out.println("4. 查询学生信息");
                System.out.println("5. 查询书籍信息");
                System.out.println("6. 查询借阅信息");
                System.out.println("请输入您想要执行的操作（输入Q/q退出）：");
                System.out.println("===================");
                String input;
                try {
                    input = scanner.nextLine();
                } catch (Exception e) {
                    return;
                }
                char c = input.charAt(0);
                switch (c) {
                    case '1' -> addStudent(scanner);
                    case '2' -> addBook(scanner);
                    case '3' -> addBorrow(scanner);
                    case '4' -> showStudent();
                    case '5' -> showBook();
                    case '6' -> showBorrow();
                    case 'Q', 'q' -> {
                        return;
                    }
                    default -> System.out.println("输入信息有误，请重新输入");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.println("请输入学生姓名：");
        String name = scanner.nextLine();
        System.out.println("请输入学生性别（男/女)：");
        String sex = scanner.nextLine();
        System.out.println("请输入学生年级：");
        String grade = scanner.nextLine();
        int g = Integer.parseInt(grade);
        Student student = new Student(name, sex, g);
        SqlUtil.doSqlwork(mapper -> {
            int i = mapper.addStudent(student);
            if (i > 0) {
                System.out.println("学生信息录入成功！");
                log.info("学生信息录入成功：" + student);
            } else {
                System.out.println("学生信息录入失败，请重试！");
                log.info("学生信息录入失败");
            }
        });
    }

    private static void addBook(Scanner scanner) {
        System.out.println("请输入书籍标题：");
        String title = scanner.nextLine();
        System.out.println("请输入书籍简介：");
        String description = scanner.nextLine();
        System.out.println("请输入书籍价格：");
        String price = scanner.nextLine();
        double p = Double.parseDouble(price);
        Book book = new Book(title, description, p);
        SqlUtil.doSqlwork(mapper -> {
            int i = mapper.addBook(book);
            if (i > 0) {
                System.out.println("书籍信息录入成功！");
                log.info("书籍信息录入成功" + book);
            } else {
                System.out.println("书籍信息录入失败，请重试！");
                log.info("书籍信息录入失败");
            }
        });
    }

    private static void addBorrow(Scanner scanner) {
        System.out.println("请输入借阅学生ID：");
        String sidStr = scanner.nextLine();
        int sid = Integer.parseInt(sidStr);
        System.out.println("请输入借阅书籍ID：");
        String bidStr = scanner.nextLine();
        int bid = Integer.parseInt(bidStr);
        SqlUtil.doSqlwork(mapper -> {
            try {
                mapper.findStudentBySid(sid).getSid();
            } catch (NullPointerException e) {
                System.out.print("不存在该名学生！请重新输入！");
                log.info("不存在该名学生！");
                return;
            }

            try {
                mapper.findBookByBid(bid).getBid();
            } catch (NullPointerException e) {
                System.out.println("不存在此书籍！请重新输入！");
                log.info("不存在此书籍！");
                return;
            }

            try {
                mapper.addBorrow(sid, bid);
                System.out.println("借阅信息录入成功！");
                log.info("借阅信息录入成功");
            } catch (Exception e) {
                System.out.println("借阅信息录入失败，请重试！");
                System.out.println("此书籍或已被该名学生借阅，不可重复借阅！");
                log.info("借阅信息录入失败，请重试！");
                log.info("此书籍已被该名学生借阅，不可重复借阅！");
            }
        });
    }

    private static void showStudent() {
        SqlUtil.doSqlwork(mapper -> mapper.findAllStudents().forEach(student ->
                System.out.println("学号：" + student.getSid() + "\t姓名：" + student.getName() + "\t性别：" + student.getSex() + "\t年级：" + student.getGrade())));
    }

    private static void showBook() {
        SqlUtil.doSqlwork(mapper -> mapper.findAllBooks().forEach((book ->
                System.out.println("书籍编号：" + book.getBid() + "\t书籍标题：《" + book.getTitle() + "》\t简介：" + book.getDescription() + "\t价格：￥" + book.getPrice()))));
    }

    private static void showBorrow() {
        SqlUtil.doSqlwork((mapper -> mapper.findAllBorrow().forEach(borrow ->
                System.out.println(borrow.getStudent().getName() + "->" + borrow.getBook().getTitle())
        )));
    }
}
