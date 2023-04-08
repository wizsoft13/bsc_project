package test;

import librarian.AudioBook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import user.SpecialUser;

import java.time.LocalDate;

public class SpecialUserTest {

    SpecialUser spUser = new SpecialUser("Jane Lane", "s1", "10 The Drive");
    AudioBook auBook = new AudioBook(11, "Evolution", "Brian Charlesworth", "9780192802514", 1560);

    @Test
    void zeroPointTest(){
        int exp = 0;
        Assertions.assertEquals(exp, spUser.collectReadingPoint());
    }
    @Test
    void readBookTest(){
        String exp = "Reading now the book: ";
        Assertions.assertTrue(exp.equals(spUser.readBook(auBook, "audio")));
    }
    @Test
    void invalidBorrowTest(){
        LocalDate bDate = LocalDate.of(2023, 04, 01);
        Assertions.assertFalse(bDate.equals(spUser.borrowBook(auBook)));
    }
    @Test
    void borrowTest(){
        LocalDate bDate = LocalDate.of(2023, 04, 8);
        Assertions.assertTrue(bDate.equals(spUser.borrowBook(auBook)));
    }
    @Test
    void fineTest(){
        String exp = "There is no fine to be paid.";
        Assertions.assertTrue(exp.equals(spUser.payFine(auBook)));
    }
    @Test
    void returnBookTest(){
        LocalDate bDate = LocalDate.of(2023, 04, 8);
        Assertions.assertTrue(bDate.equals(spUser.checkInBook(auBook)));
    }
    @Test
    void invalidReturnBookTest(){
        LocalDate bDate = LocalDate.of(2023, 04, 3);
        Assertions.assertFalse(bDate.equals(spUser.checkInBook(auBook)));
    }
    @Test
    void redeemPointTest(){
        String exp = "NoT enough point collected";
        Assertions.assertTrue(exp.equals(spUser.redeemPoints()));
    }
}

