package test;

import librarian.AudioBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * for testing AudioBook class
 * @author Alain Kwasisi
 */
public class AudioBookTest {

    AudioBook audio = new AudioBook(2, "The War Of The World", "Jeff Wayne", "9783161484100", 1020 );


    @Test

    void validIsbnTest(){

       String isbn = "9783161484100";
        Assertions.assertTrue(isbn.equals(audio.checkISBN()));
   }
   @Test

    void authorTest(){
       String aut = "Jeff Wayne";
       Assertions.assertTrue(aut.equals(audio.getAuthor()));
   }
   @Test

    void inStockTest(){
        Assertions.assertTrue(audio.insTock());
   }
   @Test

    void titleTest(){
        String name= "The War Of The World";
        Assertions.assertTrue(name.equals(audio.getTitle()));
   }
   @Test

    void byteTest(){
        long bt = 1020;
        Assertions.assertEquals(bt, audio.getByteSize());
   }
}
