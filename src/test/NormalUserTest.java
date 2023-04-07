package test;

import librarian.Ebook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import user.NormalUser;

import java.time.LocalDate;

public class NormalUserTest {

    NormalUser nUser = new NormalUser("n1","Nick Pen", "5 Bricklane Avenue");
    Ebook ebook = new Ebook(6, "Slant", "Bear Greg", 1080, " 9780812524826");
    @Test
    void readTest(){

        String exp = "Reading now the book: " +"Slant" + " authored by: " + "Bear Greg";
        Assertions.assertTrue(exp.equals(nUser.readBook(ebook, "ebook")));
    }
    @Test

    void borrowTest(){
        LocalDate it = LocalDate.of(2023,04,03);
        Assertions.assertTrue(it.equals(nUser.borrowBook(ebook)));

    }
}
