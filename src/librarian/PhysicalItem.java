package librarian;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@EqualsAndHashCode

/**
 * Represent a physical book object
 * @author Alain Kwasisi
 */
public class PhysicalItem extends Book implements Item {

    private  int quantity;//instance variable

    /*
    constructor for Physical item object
     */
    public PhysicalItem( int id, String title, String author, String isbn, int quantity){
        super(title,id,author, isbn);

        this.quantity = quantity;

    }

    @Override
    public boolean insTock(){

        return this.getQuantity()> 0;

    }
    @Override
    public String checkISBN(){
        boolean isIbn = Item.checkWellFormedISBN(super.getIsbn());
        if (!isIbn) {
            throw new IllegalArgumentException("ISBN must consist only of digits and well-formed.");
        }
        return super.getIsbn();

    }

}
