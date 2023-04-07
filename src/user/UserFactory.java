package user;

import java.sql.*;


/**
creates the required type of user

@author Alain Kwasisi
 */
public class UserFactory {

    private Connection con = null;
    private Statement stmt = null;

    private PreparedStatement pstmt = null;

    ResultSet rs = null;

    /*
    default constructor for a UserFactory object.
     */
    public UserFactory(){

    }

    /*
    returns a string message
    confirming whether details
    have been entered.
    @param id, username and pass type string
    @return stRes String
     */
    public String appendCredDet(String id, String username, String pass){
       String stRes= "";
       try{
           initMysql();
           String sql = "INSERT INTO credentials values(?,?,?)";
           pstmt = con.prepareStatement(sql);
           pstmt.setString(1, id);
           pstmt.setString(2, username);
           pstmt.setString(3, pass);
           pstmt.executeUpdate();
           stRes = "Details have been entered into credentials table";

       }catch(Exception err){
           System.err.println("Error loading JDBC driver");
           err.printStackTrace(System.err);
           System.exit(0);
       } finally {
           try {
               if (stmt != null || pstmt != null) {
                   stmt.close();
                   pstmt.close();
               }
           } catch (SQLException sqlEx) {
               sqlEx.printStackTrace();
           }

       }
       return stRes;
    }

    /*
    retrieve user credentials
     */
    public boolean retCredentials(String id, String username, String pass){

        try{
            initMysql();

            String myUs = null;
            String myPass = null;
            String query = "SELECT userName, password FROM credentials WHERE userId =? ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                myUs = rs.getString("userName");
                myPass = rs.getString("password");
            }
            if(username.equals(myUs) && pass.equals(myPass)){
               return true;
            }


        }catch(Exception err){
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        } finally {
            try {
                if (stmt != null || pstmt != null) {
                    stmt.close();
                    pstmt.close();

                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }
       return false;
    }
    /*
    return a list of users
     */
    public String getUsers() {
        String res ="";
        try {

            initMysql();

            String sql = "SELECT * from specuser ";

            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();

            while (rs.next()) {

                String id = rs.getString("USERSID");
                String name = rs.getString("NAME");
                String address = rs.getString("ADDRESS");

                res +=String.format(id+"\t"+ name+"\t"+ address) +"\n";

            }

            sql = "SELECT * from reguser ";

             rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String id = rs.getString("USERID");
                String name = rs.getString("NAME");
                String address = rs.getString("ADDRESS");
                res +=String.format(id+"\t"+ name+"\t"+ address) +"\n";

            }



        } catch (Exception err) {

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
        return res;
    }

    /*
    set a user to add
     */
    public String setUserToAdd(User adUser){
        String clType = null;

        try {

            if (adUser instanceof NormalUser) {

                NormalUser nu = (NormalUser) adUser;

                initMysql();
                String sql = "INSERT INTO reguser values(?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, nu.getId());
                pstmt.setString(2, nu.getName());
                pstmt.setString(3, nu.getAddress());
                pstmt.executeUpdate();

                clType ="The User: " + nu.getName()+"\n With ID: "+ nu.getId()+ "\n Of Address: "+ nu.getAddress()+ "\n Has been added as a new register member!!";

            }
            if (adUser instanceof SpecialUser) {

                SpecialUser su = (SpecialUser) adUser;

                initMysql();
                String sql1 = "INSERT INTO specuser values(?,?,?)";
                pstmt = con.prepareStatement(sql1);
                pstmt.setString(1, su.getId());
                pstmt.setString(2, su.getName());
                pstmt.setString(3, su.getAddress());
                pstmt.executeUpdate();

                clType ="The User: " + su.getName()+"\n With ID: "+ su.getId()+ "\n Of Address: "+ su.getAddress()+"\n Has been added as a new register member!!";

            }
        }catch (Exception err) {

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
    public String setUserToRem(User remUs){
        String retMess = null;
        try {
            if (remUs instanceof NormalUser) {
                NormalUser normal = (NormalUser) remUs;

                initMysql();
                String sql1 = "DELETE FROM reguser WHERE USERID=?";
                pstmt = con.prepareStatement(sql1);
                pstmt.setString(1, normal.getId());
                pstmt.executeUpdate();
                String sql2 = "DELETE FROM credentials WHERE userId=? ";
                pstmt = con.prepareStatement(sql2);
                pstmt.setString(1, normal.getId());
                pstmt.executeUpdate();

                retMess = "The user: "+ normal.getName()+" with id number: "+ normal.getId()+ " has been removed from user database.";

            }else if (remUs instanceof SpecialUser) {
                SpecialUser special = (SpecialUser) remUs;

                initMysql();
                String sql1 = "DELETE FROM specuser WHERE USERSID=?";
                pstmt = con.prepareStatement(sql1);
                pstmt.setString(1, special.getId());
                pstmt.executeUpdate();
                String sql2 = "DELETE FROM credentials WHERE userId=? ";
                pstmt = con.prepareStatement(sql2);
                pstmt.setString(1, special.getId());
                pstmt.executeUpdate();

                retMess = "The user: "+ special.getName()+" with id number: "+ special.getId()+ " has been removed from user database.";

            }
        }catch (Exception err) {

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
        return retMess ;
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
