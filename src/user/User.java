package user;

import librarian.Book;
import lombok.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode

/**
 * Represents a generic user type
 * @author Alain Kwasisi
 */
public abstract class User {

    //instance variables
    private String name;
     private String address;
     private String id;

     /*
     constructor
      */
     public User(String id,String name, String address){
         this.name = name;
         this.id = id;
         this.address = address;
     }

     abstract String payFine( Book fBook);
}
