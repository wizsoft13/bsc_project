package user;

import librarian.*;
import lombok.*;

import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.List;

/*
Represents the administrator of the library.
The librarian is responsible for main library functions.
@author Alain Kwasisi
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Librarian {

    private static String name; //holds the value of name.
    private static String address; //stores the instance variable address.

    private static Librarian instance = null;

    private Connection con = null;
    private Statement stmt = null;
    private FactoryBook fb = new FactoryBook();

    private UserFactory userFactory = new UserFactory();

    /*
    constructor for a librarian instance.
    @param name a string
    @param address a string
     */
    private Librarian(String name, String address){
        this.name = name;
        this.address = address;

    }
    public static Librarian getInstance(){
        Librarian lb;
        if(instance== null){
           synchronized (Librarian.class){
               if(instance==null){
                   instance = new Librarian(name, address);
               }
           }
        }
        return instance;
    }
    /*
    add a book object to the database.
    @param  ab string of Book type
    @return string.
     */

    public String addBook(String ab){

        return fb.bookToAdd(ab);
    }


    /*
    remove a book from the list
    @param rb of string type
    @return string
     */
    public String removeBook(String rb){

        return fb.bookToRem(rb);
    }
    /*
    keeps track of all books borrowed
    @ param bk of type Book
    @return none.

     */
    public List<Book> trackBorrowedBook(String tb){

        String st =fb.bookStatus(tb);
        List<Book> borrowedList = new ArrayList<>();
        if(st.equals("available")){
            borrowedList.add(fb.returnBookType(tb));
        }
        return borrowedList;
    }
    /*
    gets the list of available books
    @return books (list)
     */
    public List<Book> getAvailableBooks(){

        List<Book> availBooks = new ArrayList<>();
        return availBooks = List.copyOf(fb.bookTypeList());
    }
    /*
    get the list of returned books
    @param sb string (type of Book)
    @return retBook (list of returned books)
     */
    public List<Book> retBookList(String sb){

        List<Book> retBook = new ArrayList<>();//to hold all the return books.
        String st = fb.bookStatus(sb); // status value of the book.
        if(st.equals("unavailable")){
            retBook.add(fb.returnBookType(sb));
        }
        return retBook;
    }
    /*
    get the user list
    @return string representation of list of user.
     */
    public  String getUserList() {
        String res = null;

        for(User us : userFactory.getUsers()){
             res += String.format("ID: % d \t NAME: %s \t ADDRESS: %s \t\n", us.getId(), us.getName(), us.getAddress());
        }
        return res;
    }
    /*
    add a new user in the system
    @param aType string(for a type of user).
    @return none.
     */

     public String addMember(String aType) {

         return userFactory.setUserToAdd(aType);
     }
    /*
   remove a  user from the system
   @param rType string (type user).
   @return string.
    */
     public String removeUser(String rType){

         return userFactory.setUserToRem(rType);

     }
     /*
     get the date the book was borrowed
     @param outB string (type of book borrowed)
     return date
      */
     public LocalDate outBookTime(String bk){
         LocalDate date = null;
         Book outB = fb.returnBookType(bk);
         if(trackBorrowedBook(bk).get(trackBorrowedBook(bk).size()-1).equals(outB)){
             date = LocalDate.now();
         }
         return date;
     }
     /*
     get the date the book was returned
     @param inBk type string (type of book to return)
     return date(date of return)
      */
    public LocalDate inBookTime(String inBk){
        LocalDate date = null;
        Book inB = fb.returnBookType(inBk);
        if(retBookList(inBk).get(retBookList(inBk).size()-1).equals(inB)){
            date = LocalDate.now();
        }
        return date;
    }
    /*
    test if a fine is occurred
    need issuing
    @param inOut a String (type of book)
    @return true or false
     */
    public  boolean isFined(String inOut){
         final int MAX_TIME = 14;// maximum time for book retention
        LocalDate startDate = outBookTime(inOut);
        LocalDate endDate = inBookTime(inOut );
        long noOfDays = startDate.until(endDate, ChronoUnit.DAYS);
        if(noOfDays>MAX_TIME){

            return true;
        }
        return false;
    }


}
