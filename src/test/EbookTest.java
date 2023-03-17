package test;

import librarian.Ebook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * For testing Ebook class
 * @author  Alain Kwasisi
 */

public class EbookTest {
    Ebook ebook = new Ebook(1, "Paul Temple", " Francis Durbridge",1000,"9780008507503" );

    @Test

    void checkStockTest(){

        Assertions.assertTrue(ebook.insTock());
    }
    @Test

    void authorTest(){

        String name = " Francis Durbridge";
        Assertions.assertTrue(name.equals(ebook.getAuthor()));

    }
    @Test

    void validIsbnTest(){

        String isbn = "9780008507503" ;
        Assertions.assertTrue(isbn.equals(ebook.checkISBN()));

    }
    @Test
    void invalidIsbnTest(){
        Ebook newBook = new Ebook(1, "Paul Temple", " Francis Durbridge",1000,"97800085075" );
        Assertions.assertThrows(IllegalArgumentException.class, newBook::checkISBN);
    }

    void titleTest(){

        String title = "Paul Temple";
        Assertions.assertTrue(title.equals( ebook.getTitle()));
    }
    @Test

    void byteTest(){
        long bt = 1000;
        Assertions.assertEquals(bt, ebook.getByteSize());
    }
}
