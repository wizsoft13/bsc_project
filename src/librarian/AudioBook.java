package librarian;

import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode

/**
 * This class represents audio book object
 * @author Alain Kwasisi
 */

public class AudioBook extends Book implements Item {

    private long byteSize;//holds the size of the audio book.


    /*
    constructor for audio book object.
     */
    public AudioBook( int id, String title, String author,  String isbn, long byteSize){
        super(title,id,author, isbn);

        this.byteSize = byteSize;

    }
    @Override
    public String checkISBN(){
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
