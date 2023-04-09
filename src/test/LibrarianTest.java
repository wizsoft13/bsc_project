package test;

import librarian.AudioBook;
import librarian.Ebook;
import user.Librarian;
import librarian.PhysicalItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import user.NormalUser;
import user.SpecialUser;


public class LibrarianTest {

    Librarian lb = Librarian.getInstance();

    @Test
    void addBookTest(){
        AudioBook audio = new AudioBook(1, "Negra", "Cathy-Glass", "9780008507503", 320);

        String mess = String.format("Book: %s\tBy: %s\t\n Has been successfully added ", "Negra", "Cathy-Glass");
        Assertions.assertTrue(mess.equals(lb.addBook(audio)));

    }
    @Test
    void addBookTest1(){
        String mess = String.format("Book: %s\tBy: %s\t\n Has been successfully added ", "The Earth Transformed", "Peter Frankopan");

        PhysicalItem pBook = new PhysicalItem(5, "The Earth Transformed", "Peter Frankopan", "9780631194651", 10 );

        Assertions.assertTrue(mess.equals(lb.addBook(pBook)));
    }
    @Test
    void addBookTest2(){
        String mess = String.format("Book: %s\tBy: %s\t\n Has been successfully added ", "Ziggyology", "Simon Goddard");

        Ebook eBook = new Ebook(2, "Ziggyology", "Simon Goddard", 512,"9780091948894");

        Assertions.assertTrue(mess.equals(lb.addBook(eBook)));
    }

    @Test

    void remBookTest(){
        AudioBook audio = new AudioBook(1, "Negra", "Cathy-Glass", "978-0-00-850750-3", 320);
        String mess =String.format("Book: %s\tBy: %s\t\n Has been successfully removed ", "Negra", "Cathy-Glass");
        Assertions.assertTrue(mess.equals(lb.removeBook(audio)));

    }
    @Test
    void remBookTest1(){
        String mess = String.format("Book: %s\tBy: %s\t\n Has been successfully removed ", "The Earth Transformed", "Peter Frankopan");

        PhysicalItem pBook = new PhysicalItem(5, "The Earth Transformed", "Peter Frankopan", "9780631194651", 10 );

        Assertions.assertTrue(mess.equals(lb.removeBook(pBook)));
    }
    @Test
    void remBookTest2(){
        String mess = String.format("Book: %s\tBy: %s\t\n Has been successfully removed ", "Ziggyology", "Simon Goddard");

        Ebook eBook = new Ebook(2, "Ziggyology", "Simon Goddard", 512,"9780091948894");

        Assertions.assertTrue(mess.equals(lb.removeBook(eBook)));
    }

    @Test
    void addUserTest(){
        NormalUser nUser = new NormalUser("n3", "John Smith", "250 High Street");
        String mess = String.format("The User: %7s\n With ID: %7s\n Of Address: %7s\nHas been added as new user.","John Smith","n3", "250 High Street");

        Assertions.assertTrue(mess.equals(lb.addMember(nUser)));
    }
    @Test
    void addUserTest1(){
        SpecialUser sUser = new SpecialUser( "Jane Lane", "s1","10 The Drive");
        String mess = String.format("The User: %7s\n With ID: %7s\n Of Address: %7s\nHas been added as new user.","Jane Lane","s1", "10 The Drive");

        Assertions.assertTrue(mess.equals(lb.addMember(sUser)));
    }
    @Test
    void remUserTest(){
        NormalUser nUser = new NormalUser("n3", "John Smith", "250 High Street");
        String retMess = "The user: "+ "John Smith"+" with id number: "+ "n3"+ " has been removed from user database.";
        Assertions.assertTrue(retMess.equals(lb.removeUser(nUser)));

    }
    @Test
    void remUserTest1(){
        SpecialUser sUser = new SpecialUser( "John Smith", "s1", "10 The Drive");
        String retMess = "The user: "+ "John Smith"+" with id number: "+ "s1"+ " has been removed from user database.";
        Assertions.assertTrue(retMess.equals(lb.removeUser(sUser)));

    }
    @Test
    void returnBookTest(){
        //NormalUser aUser = new NormalUser("n6", "Ann Brooks", "17 The Drive");
        Ebook ebook = new Ebook(6, "Slant", "Bear Greg", 1080, " 9780812524826");
        //AudioBook aBook = new AudioBook(1, "Negra","Cathy_Glass", "9780008507503", 320);

        Assertions.assertTrue(lb.retBookList(ebook).contains(ebook));
    }
    @Test
    void returnBookTest1(){

        Ebook ebook = new Ebook(6, "Slant", "Bear Greg", 1080, " 9780812524826");
        int size = 0;
        Assertions.assertEquals(size, lb.retBookList(ebook).size());
    }
    @Test
    void availableBookTest(){
        String exp = "";
        Assertions.assertFalse(exp.equals(lb.getAvailableBooks()));
    }
    @Test
    void availableBookTest1(){
        String exp = String.format("%-6d\t%-50s\t%-40s",13,"Red Moon Rising","Matthew Brzezinki")+ "\n";
        Assertions.assertTrue(lb.getAvailableBooks().contains(exp));
    }

}
