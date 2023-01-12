package librarian;

import lombok.*;
import user.User;

import java.util.*;

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
    private static List<Book>books; //store all the books in the system

    private static List<Integer>borrowedList; //store all

    private static List<Book>returnedList;
    private static Set<User>userList;

    private static Librarian instance = null;

    /*
    constructor for a librarian instance.
    @param name a string
    @param address a string
     */
    private Librarian(String name, String address){
        this.name = name;
        this.address = address;
        books = new ArrayList<>();
        userList = new HashSet<>();
        borrowedList = new ArrayList<>();
        returnedList = new ArrayList<>();
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
    add a book object to the list.
    add all  return books in the book list.
    @param newBook of type Book
    @return none.
     */

    public void addBook(Book newBook){
        if( returnedList != null){
            books.addAll(returnedList);
        }
        if (!books.contains(newBook)) {
            books.add(newBook);
        }

    }
    /*
    remove a book from the list
    @param aBook of book type
    @return new book list
     */
    public void removeBook(Book aBook){

       if(!books.contains(aBook)){
           throw new RuntimeException("book does not exist!");
       }
       books.remove(aBook);


    }
    /*
    keeps track of all books borrowed
    @ param bk of type Book
    @return none.

     */
    public void trackBorrowedBook(Book bk){
        boolean isBorrowed = false;
        if(isBorrowed){
            removeBook(bk);
            borrowedList.add(bk.getItemId());
        }
    }
    public List<Book> getAvailableBooks(){
        return books;
    }
    public List<Integer> getBorrowedList(){
        return borrowedList;
    }
    /*
    add a new user in the system
    @param newUser type user.
    @return none.
     */
     public Set<User> addMember(User newUser){

         if(!userList.contains(newUser)){
             userList.add(newUser);
         }
         return userList;
     }
    /*
   remove a  user from the system
   @param aUser type user.
   @return none.
    */
     public void removeUser(User aUser){
         for(User us: userList){
             if (us.equals(aUser)) {

                 userList.remove(us);
             }
         }

     }


}
