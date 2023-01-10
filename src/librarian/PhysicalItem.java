package librarian;


import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@EqualsAndHashCode
public class PhysicalBook extends Items implements Book {

    private int quantity;

    public PhysicalBook(String title, String isbn,int id, Author author, int quantity){
        super(title, id,isbn,author);
        this.quantity = quantity;
    }
    @Override
    public boolean insTock(){

        return this.quantity > 0;

    }
    @Override
    public String getISBN(){
        boolean isIbn = Book.checkWellFormedISBN(getISBN());
        if (!isIbn) {
            throw new IllegalArgumentException("ISBN must consist only of digits and well-formed.");
        }
        return this.getISBN();

    }
    public int compareBooks(Book xBook, Book yBook){
        return 0;
    }


}
