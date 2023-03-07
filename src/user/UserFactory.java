package user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
creates the required type of user

@author Alain Kwasisi
 */
public class UserFactory {

    private User user; //instance object of type User.

    private Connection con = null;
    private Statement stmt = null;

    private PreparedStatement pstmt = null;

    /*
    default constructor for a UserFactory object.
     */
    public UserFactory(){

    }

    /*
    returns the appropriate user to add
    @param tUser type string
    @return user
     */
    public User retUserType(String tUser){
        user = null;

        Scanner myDet = new Scanner(System.in);

        System.out.println("Enter name, id and address: ");

        //user's name
        String name = myDet.nextLine();
        //user's id
        String id = myDet.nextLine();
        //user's address
        String address = myDet.next();


        switch (tUser){
            case "NormalUser":
                user = new NormalUser(id, name,address);

            case "SpecialUser":
                user = new SpecialUser(name,id, address);
            default:
                System.out.println("Unknown type of user");
        }
        return user;
    }
    /*
    return a list of users
     */
    public List<User> getUsers() {
        List<User>userList = new ArrayList<>();
        try {

            String sql;
            initMysql();
           
            if(user instanceof SpecialUser){
                SpecialUser su = (SpecialUser) user;
                sql = "SELECT * from specuser ";
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {

                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String address = rs.getString(3);
                    userList.add(new SpecialUser(name, id, address));
                }

            } else if (user instanceof NormalUser) {

                NormalUser nu = (NormalUser) user;
                sql = "SELECT * from reguser ";
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {

                    String id = rs.getString(1);
                    String name = rs.getString(2);
                    String address = rs.getString(3);
                    userList.add(new NormalUser(id, name, address));
                }
            }
            else {
                System.out.println("There is not such type of user!!");
            }


        } catch (Exception err) {
            //e.printStackTrace();
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }
        return userList;
    }

    /*
    set a user to add
     */
    public String setUserToAdd(String cl){
        String clType = null;
        String sql;
        try {

            if (cl.equals("NormalUser")) {
                NormalUser nu = (NormalUser) retUserType(cl);

                initMysql();
                sql = "INSERT INTO reguser values(?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, nu.getId());
                pstmt.setString(2, nu.getName());
                pstmt.setString(3, nu.getAddress());
                pstmt.executeUpdate();

                clType = String.format("ID: %d\nName: %s\nAddress: %s\n Has been added as new user.  ",nu.getId(),nu.getName(),nu.getAddress());

            }
            if (user.equals("SpecialUser")) {
                SpecialUser su = (SpecialUser) retUserType(cl);

                initMysql();
                sql = "INSERT INTO specuser values(?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, su.getId());
                pstmt.setString(2, su.getName());
                pstmt.setString(3, su.getAddress());
                pstmt.executeUpdate();

                clType = String.format("ID: %d\nName: %s\nAddress: %s\n Has been added as new user.  ",su.getId(),su.getName(),su.getAddress());

            }
        }  catch (Exception err) {
        //e.printStackTrace();
        System.err.println("Error loading JDBC driver");
        err.printStackTrace(System.err);
        System.exit(0);
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }
        return clType;
    }
    public String setUserToRem(String remType){
        return null;
    }

    /*
    setting up database connection
     */
    protected void initMysql() throws ClassNotFoundException, SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/ library_db";
        String pass = "Boutrosdatabase13";
        String user = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");

        con = DriverManager.getConnection(jdbcURL, user, pass);

        stmt = con.createStatement();

    }
}
