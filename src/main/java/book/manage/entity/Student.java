package book.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ach
 */
@Data
@NoArgsConstructor
public class Student {
    int sid;
    String name;
    String sex;
    int grade;

    public Student(String name, String sex, int grade) {
        this.name = name;
        this.sex = sex;
        this.grade = grade;
    }
}
