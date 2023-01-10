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
public abstract class Book {

    private String title;
    private int itemId;
    protected String author;

    private String isbn;




    public Book(String title, int itemId, String author, String isbn){

        this.title = title;
        this.itemId = itemId;
        this.author = author;
        this.isbn = isbn;

    }
    abstract boolean insTock();

    public void titleAlphaOrder(List<Book> books){


    }
    private int compare(Book x, Book y){
        return x.title.compareTo(y.title);
    }
    public void authorOrder(List<Book> list){

    }

}
