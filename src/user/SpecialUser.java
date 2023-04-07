package user;

import librarian.*;
import log.SimpleAudio;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
/**
 * represents a special user i.e.,
 * user with low reading level
 * @author Alain Kwasisi
 */
public class SpecialUser extends User{

    /*
    constructor for SpecialUser class
    @params name, id and address of type string
     */
    public  SpecialUser( String name, String id, String address){
        super(id, name, address);
    }
    @Override

    public  String payFine(Book bToFine){

        return "There is no fine to be paid.";
    }
    /*
   reading a book
   @param aBook type Book
   @param type a string type
    */
    public String readBook(Book aBook, String type){

        SimpleAudio simpleAudio = new SimpleAudio();
        String res = null;
        FactoryBook factoryBook = new FactoryBook();

        if(type.equals("ebook")){

            String stat = factoryBook.bookStatus(aBook);
            if(stat.equals("available")) {
                res = "Reading now the book: " + aBook.getTitle() + "\t authored by: " + aBook.getAuthor();
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
    public LocalDate borrowBook(Book outB){

        if(new FactoryBook().bookStatus(outB).equals("available")){
            String res = "Book: "+ outB.getTitle()+ " has been reserved";
            new FactoryBook().upBookStatus(outB);
        }

        return LocalDate.now();
    }
    /*
    return a borrowed book
    @param inB Book type
    @return localDate (due date)
     */
    public LocalDate checkInBook(Book inB){
        if(new FactoryBook().bookStatus(inB).equals("unavailable")){
            String ret = "Book: "+ inB.getTitle()+" has now being returned.";
            new FactoryBook().upBookStatus(inB);
        }

        return  LocalDate.now();
    }
    /*
    returns the number of point collected
    every time readBook method is called
    @params pBook type Book and type string
    @return int point.
     */
    public int collectReadingPoint(Book pBook, String type){
        int point = 0;
        boolean hasRead = false;
        if(hasRead){
            readBook(pBook, type);
            point++;
        }

        return point;
    }
    public String redeemPoints(Book reBook, String type){
        final int MIN_POINT = 5;
        String outRes = "";
        if(collectReadingPoint(reBook,type)>= MIN_POINT){
            outRes = "Well done!!\n You have collected enough points, now you can claim your prize";
        }
        return outRes;
    }

}
