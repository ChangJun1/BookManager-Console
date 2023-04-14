package book.manage.entity;

import lombok.Data;

/**
 * @author ach
 */
@Data
public class Book {
    int bid;
    final String title;
    final String description;
    final double price;
}
