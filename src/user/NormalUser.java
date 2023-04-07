package user;

import librarian.Book;
import librarian.FactoryBook;
import log.SimpleAudio;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@EqualsAndHashCode

/**
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
    public String payFine(Book bToFine){
        String pf = "Fine still unpaid";
        Librarian lib = Librarian.getInstance();
        if(lib.isFined(bToFine)){
            pf = "Fine is now being paid";
        }
        return pf;
    }

    /*
    reading a book
    @param aBook Book type
    return string
     */
    public String readBook(Book aBook, String type){

        SimpleAudio simpleAudio = new SimpleAudio();
        String res = null;
        FactoryBook factoryBook = new FactoryBook();

        if(type.equals("ebook")){
            String stat = factoryBook.bookStatus(aBook);
            if(stat.equals("available")) {
                res = "Reading now the book: " + aBook.getTitle() + " authored by: " + aBook.getAuthor();
            }
        }
        if(type.equals("audio")) {
            String stat = factoryBook.bookStatus(aBook);
            if (stat.equals("available")) {
                res = "Reading now the book: ";
                simpleAudio.textToSpeech("Reading now the book: " + aBook.getTitle() + " authored by: " + aBook.getAuthor());

            }
        }
        return res;
    }

    /*
    view available books
    @param none
    @return list (list of available books)
     */
    public String showAvailableBooks(){
        Librarian lib = Librarian.getInstance();

        return lib.getAvailableBooks() ;
    }

    /*
    borrow a book
    @param outB Book type
    @return localDate (borrowed date)
     */
    public LocalDate borrowBook( Book outB){

        if(new FactoryBook().bookStatus(outB).equals("available")){
            new FactoryBook().upBookStatus(outB);
            return LocalDate.now();
        }
       return null;
    }
    /*
    return a borrowed book
    @param inB Book type
    @return localDate (return date)
     */
    public LocalDate checkInBook(Book inB){
        Librarian lb = Librarian.getInstance();
        lb.retBookList(inB);
        return  LocalDate.now();

    }
}
