package com.test;

import book.manage.entity.Book;
import book.manage.entity.Student;
import book.manage.sql.SqlUtil;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void test01() {
        SqlUtil.doSqlwork(bookMapper -> bookMapper.addStudent(new Student("小花", "女", 2020)));
    }

    @Test
    public void test02() {
        SqlUtil.doSqlwork(bookMapper -> bookMapper.addBook(new Book("金庸全集", "武侠小说", 299.9)));
    }

    @Test
    public void test03() {
        SqlUtil.doSqlwork(bookMapper -> bookMapper.findAllStudents().forEach(System.out::println));
    }

    @Test
    public void test04() {
        SqlUtil.doSqlwork(bookMapper -> bookMapper.findAllBooks().forEach(System.out::println));
    }

    @Test
    public void test05() {
        SqlUtil.doSqlwork(bookMapper -> bookMapper.findAllBorrow().forEach(System.out::println));
    }
}
