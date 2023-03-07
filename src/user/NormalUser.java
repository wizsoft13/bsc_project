package user;

import librarian.Book;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode

/*
a class that represents a normal user of the library
@author Alain Kwasisi
 */

public class NormalUser extends User{

    /*
    constructor of NormalUser class
    @params id, name, address (string type)
     */
    public  NormalUser(String id, String name, String address){
        super(id, name, address);
    }
    @Override
    public String payFine(String fine){
        String pf = null;
        Librarian lib = Librarian.getInstance();
        if(lib.isFined(fine)){
            pf = "Fine is now being paid";
        }
        return pf;
    }

    /*
    reading a book
    @param aBook Book type
    return string
     */
    public String readBook(Book aBook){

        return String.format("Reading now \t title: %s\t by: %s\t", aBook.getTitle(),aBook.getAuthor());
    }

    /*
    view available books
    @param none
    @return list (list of available books)
     */
    public List<Book> showAvailableBooks(){
        Librarian lib = Librarian.getInstance();

        return lib.getAvailableBooks() ;
    }

    /*
    borrow a book
    @param outB string type (for type of book)
    @return localDate (borrowed date)
     */
    public LocalDate borrowBook( String outB){

        Librarian lib = Librarian.getInstance();

        lib.trackBorrowedBook(outB);

        return LocalDate.now();
    }
    /*
    return a borrowed book
    @param inB string type (for type of book)
    @return localDate (due date)
     */
    public LocalDate checkInBook(String inB){
        Librarian lib = Librarian.getInstance();

        lib.retBookList(inB);

        return  LocalDate.now();

    }
}
