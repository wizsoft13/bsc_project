package log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
this class validate user's username and password.
 */

public class ValidateCredentials {

    /*
     return a true (if the string matches regex) or false
     @param exp type string.
     return true or false.
      */
    public boolean hasRegEx(String exp){

        String regex  = "^[a-zA-Z0-9]+$";
        Pattern p = Pattern.compile(regex);
        // Pattern class contains matcher() method
        // to find matching between given password or username
        // and regular expression.
        Matcher m = p.matcher(exp);

        return m.matches();
    }

    /*
   return a valid username or null (if username not valid)
   @param user type string.
   @return true (if valid) or false.
    */
    public boolean validateUsername(String user){

        if(user.trim().equals("")){
            System.out.println("username must contains characters ");
            return false;
        }else {

            if (user.trim().length() < 5) {
                System.out.println("username must be at least 5 characters long");
                return false;

            }

            if (!hasRegEx(user.trim())) {
                System.out.println("username must contains characters and/or digits");
                return false;
            }
            if (user.trim().matches("[0-9]+")) {

                System.out.println("username must contains characters as well as digits");
                return false;
            }
        }
        return true;
    }
    /*
    takes a string password
    and return true if it is valid
    false otherwise.
    @param pass type String
    @return true or false
     */
    public boolean validatePassword(String pass){

        if(pass.equals("")){
            System.out.println("Password cannot be empty");
            return false;
        }
        if (!hasRegEx(pass.trim()) ){

            System.out.println("Password must contains characters and/or digits");
            return false;
        }
        if (pass.trim().matches("[0-9]+") ){

            System.out.println("Password must contains characters as well as digits");
            return false;
        }
        if(pass.trim().length() < 5){
            System.out.println("password must be at least 5 characters long");
            return false;
        }
        return true;
    }

}
