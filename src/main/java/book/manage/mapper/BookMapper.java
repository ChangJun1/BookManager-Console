package book.manage.mapper;

import book.manage.entity.Book;
import book.manage.entity.Borrow;
import book.manage.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author ach
 */
public interface BookMapper {
    @Insert("insert into student(name, sex, grade) values(#{name}, #{sex}, #{grade}) ")
    int addStudent(Student student);

    @Select("select * from student where sid =#{sid}")
    Student findStudentBySid(int sid);

    @Select("select * from student")
    List<Student> findAllStudents();


    @Insert("insert into book(title, description, price) values (#{title}, #{description}, #{price})")
    int addBook(Book book);

    @Select("select * from book where bid =#{bid}")
    Book findBookByBid(int bid);

    @Select("select * from book")
    List<Book> findAllBooks();

    @Insert("insert into borrow(sid, bid) values (#{sid}, #{bid})")
    int addBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Results({
            @Result(column = "id", property = "id", id = true),
            @Result(column = "sid", property = "student", one = @One(select = "findStudentBySid")),
            @Result(column = "bid", property = "book", one = @One(select = "findBookByBid"))
    })
    @Select("select * from borrow")
    List<Borrow> findAllBorrow();
}
