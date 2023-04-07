package test;

import log.ValidateCredentials;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginCredentialTest {

    ValidateCredentials validate = new ValidateCredentials();

    @Test
    void validPasswordTest(){

        String exp = "BreachTextPro1";
        Assertions.assertTrue(validate.validatePassword(exp));
    }
    @Test
    void validPasswordTest1(){

        String res = "betting13";
        Assertions.assertTrue(validate.validatePassword(res));
    }
    @Test
    void notValidPasswordTest(){
        String rest = "gold";
        Assertions.assertFalse(validate.validatePassword(rest));
    }
    @Test
    void notValidPasswordTest1(){
        String rest = "gold@$&@";
        Assertions.assertFalse(validate.validatePassword(rest));
    }
    @Test
    void validUsernameTest(){

        String exp = "softBearer";
        Assertions.assertTrue(validate.validatePassword(exp));
    }
    @Test
    void validUsernameTest1(){

        String exp = "CodeBreaker2";
        Assertions.assertTrue(validate.validatePassword(exp));
    }
    @Test
    void notValidUsernameTest(){

        String exp = "Cosy";
        Assertions.assertFalse(validate.validatePassword(exp));
    }
    @Test
    void notValidUsernameTest1(){

        String exp = "C@softy&ing";
        Assertions.assertFalse(validate.validatePassword(exp));
    }


}
