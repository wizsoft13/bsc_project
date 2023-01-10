package librarian;

import lombok.*;

import java.util.Scanner;

@Setter
@Getter
@ToString
@EqualsAndHashCode

public class AudioItem extends Book implements Item {

    private long byteSize;//holds the size of the audio book.

    private String isbn;

    public AudioItem(String title, String author, int id){
        super(title,id,author);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the book in number: ");
        byteSize = sc.nextInt();
        System.out.println("Enter the ISBN number: ");
        isbn = sc.next();
    }

    @Override
    public String getISBN(){
        boolean isIbn = Item.checkWellFormedISBN(isbn);
        if (!isIbn) {
            throw new IllegalArgumentException("ISBN must consist only of digits and well-formed.");
        }
        return this.isbn;

    }




    @Override
    public boolean insTock(){
        return true;
    }
}
