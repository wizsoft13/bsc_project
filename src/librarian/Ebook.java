package librarian;

import lombok.*;

import java.util.Scanner;

@Getter
@Setter
@ToString
@EqualsAndHashCode

/**
 * Represents ebook object
 * @author Alain Kwasisi
 */
public class Ebook extends Book implements Item {

    private long byteSize;//holds the size of the Ebook.

    /*
    constructor for ebook object
     */
    public Ebook(int id, String title, String author, long byteSize, String isbn){
        super(title,id,author,isbn);

        this.byteSize = byteSize;

    }

    @Override
    public String checkISBN() {
        boolean isIbn = Item.checkWellFormedISBN(super.getIsbn());
        if (!isIbn) {
            throw new IllegalArgumentException("ISBN must consist only of digits and well-formed.");
        }
        return super.getIsbn();
    }

    @Override
    public boolean insTock(){
        return true;
    }
}
