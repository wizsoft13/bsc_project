package test;

import librarian.PhysicalItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * For testing PhysicalItem class
 * @author Alain Kwasisi
 */

public class PhysicalItemTest {

    PhysicalItem pBook = new PhysicalItem(5, "The Earth Transformed", "Peter Frankopan", "9780525659167", 10 );

    @Test

    void validIsbnTest(){

        String isbn = "9780525659167";
        Assertions.assertTrue(isbn.equals(pBook.checkISBN()));
    }
    @Test

    void authorTest(){
        String aut = "Peter Frankopan";
        Assertions.assertTrue(aut.equals(pBook.getAuthor()));
    }
    @Test

    void inStockTest(){
        Assertions.assertTrue(pBook.insTock());
    }
    @Test

    void titleTest(){
        String name= "The Earth Transformed";
        Assertions.assertTrue(name.equals(pBook.getTitle()));
    }
    @Test

    void qtTest(){
        int qty= 10;
        Assertions.assertEquals(qty, pBook.getQuantity());
    }
    @Test
    void qtTest2(){
        int qty = 8;
        Assertions.assertEquals(qty, pBook.getQuantity()-2);
    }
    @Test
    void invalidIsbnTest(){

        PhysicalItem pBook = new PhysicalItem(5, "The Earth Transformed", "Peter Frankopan", "9780-525659-16-7", 10 );

        Assertions.assertThrows(IllegalArgumentException.class, pBook::checkISBN);

    }

}
