package librarian;

import lombok.Data;
import  lombok.AllArgsConstructor;
import user.User;

import java.util.List;

/*
The librarian responsible for main library functions
@author Alain Kwasisi
 */
@Data
@AllArgsConstructor
public class Librarian {

    private String name; //holds the value of name.
    private String address; //stores the instance variable address.
    private List<Book>books;
    /*
    add a book object to the list.
    @param newBook of type Book
    @return none.
     */
    public void addBook(Book newBook){

    }
    /*
    remove a book from the list
    @param aBook of book type
    @return new book list
     */
    public List<Book> removeBook(Book aBook){
        return null;
    }
    /*
    add a new user in the system
    @param newUser type user.
    @return none.
     */
     public void addMember(User newUser){

     }
    /*
   remove a  user from the system
   @param aUser type user.
   @return none.
    */
     public void removeUser(User aUser){

     }

}
