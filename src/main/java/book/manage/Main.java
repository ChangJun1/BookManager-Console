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
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            LogManager manager = LogManager.getLogManager();
            manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
            while (true) {
                System.out.println("===================");
                System.out.println("1. 录入学生信息");
                System.out.println("2. 录入书籍信息");
                System.out.println("请输入您想要执行的操作（输入Q/q退出）：");
                String input;
                try {
                    input = scanner.nextLine();
                } catch (Exception e) {
                    return;
                }
                char c = input.charAt(0);
                switch (c) {
                    case '1':
                        addStudent(scanner);
                        break;
                    case '2':
                        addBook(scanner);
                        break;
                    case 'Q':
                    case 'q':
                        return;
                    default:
                        System.out.println("输入信息有误，请重新输入");
                        break;
                }
            }
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
        System.out.println("请输入书籍简介：：");
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
}
