package book.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ach
 */
@Data
@NoArgsConstructor
public class Borrow {
    int id;
    Student student;
    Book book;
}
