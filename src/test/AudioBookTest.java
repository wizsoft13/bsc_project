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

    void inValidIsbnTest(){

        AudioBook auBook = new AudioBook(2, "The War Of The World", "Jeff Wayne", "978316-14841-00", 1020 );
        Assertions.assertThrows(IllegalArgumentException.class, auBook::checkISBN);
    }
   @Test

    void authorTest(){
       String aut = "Jeff Wayne";
       Assertions.assertTrue(aut.equals(audio.getAuthor()));
   }
   @Test
   void authorTest1(){
        AudioBook auB = new AudioBook(1, "Negra", "Cathy-Glass", "9780008507503", 320);
        String aut = "Cathy-Glass";
        Assertions.assertTrue(aut.equals(auB.getAuthor()));
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
