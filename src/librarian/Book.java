package librarian;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Scanner;

@Setter
@Getter
@EqualsAndHashCode
@ToString
/**
 * Represents a generic class for books object
 * @author Alain Kwasisi
 */
public abstract class Book {

    private String title;
    private int itemId;
    private String author;

    private String isbn;

    /*
    constructor for Book object.
     */
    public Book(String title, int itemId, String author, String isbn){

        this.title = title;
        this.itemId = itemId;
        this.author = author;
        this.isbn = isbn;

    }
    abstract boolean insTock();

}
