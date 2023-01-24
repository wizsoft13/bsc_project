package log;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
this class validate user's username and password.
 */

public class ValidateCredentials {

     /*
    return a valid username or null (if username not valid)
    @param none.
     */

    public String getUserName(){
        String username = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter your username: ");
        String userN = sc.next();
        if(validateUsername(userN)){
            username = userN;
        }

        return username;
    }
    /*
     return a valid password or null (if password not valid)
     @param none.
      */
    public String getPassword(){
        String password = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter your password: ");
        String pass = sc.next();
        if(validatePassword(pass)){
            password = pass;
        }

        return password;
    }
    public boolean stringOfChar(String str) {
        str = str.toLowerCase();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (!(ch >= 'a' && ch <= 'z')) {
                return false;
            }
        }

        return true;
    }
    public boolean validateUsername(String user){

        if(user == null){
            System.out.println("username must be at least 5 characters long");
            return false;
        }
        else if(user.length() < 5){
            System.out.println("username must be at least 5 characters long");
            return false;
        }
        else if(! stringOfChar(user)){
            System.out.println("username must contains only characters");
            return false;
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
        String regex  = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        Pattern p = Pattern.compile(regex);
        if(pass == null){
            return false;
        }
        if(stringOfChar(pass)){
            return false;
        }
        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(pass);

        return m.matches();

    }
}
