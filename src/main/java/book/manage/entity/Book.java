package book.manage.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ach
 */
@Data
@NoArgsConstructor
public class Book {
    int bid;
    String title;
    String description;
    double price;

    public Book(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
