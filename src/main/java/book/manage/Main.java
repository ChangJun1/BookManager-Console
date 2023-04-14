package book.manage;

import book.manage.entity.Book;
import book.manage.entity.Student;
import book.manage.mapper.BookMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author ach
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            BookMapper mapper = session.getMapper(BookMapper.class);
            System.out.println(mapper.addStudent(new Student("小蓝", "男", 95)));
            System.out.println(mapper.addBook(new Book("Thinking in Java", "written by Buce", 50.00)));
        }
    }
}
