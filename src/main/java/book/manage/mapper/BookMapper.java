package book.manage.mapper;

import book.manage.entity.Book;
import book.manage.entity.Student;
import org.apache.ibatis.annotations.Insert;

/**
 * @author ach
 */
public interface BookMapper {
    @Insert("insert into student(name, sex, grade) values(#{name}, #{sex}, #{grade}) ")
    int addStudent(Student student);

    @Insert("insert into book(title, description, price) values (#{title}, #{description}, #{price})")
    int addBook(Book book);
}
