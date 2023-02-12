package librarian;

import java.sql.*;

/*
creates object book
according to their types.

@author Alain Kwasisi
 */
public class FactoryBook {
    //instance variable of type book.
    private Book book;

    private Connection con = null;
    private Statement stmt = null;
    /*
    constructor for factoryBook object
     */
    public FactoryBook(Book tBook){

        this.book = tBook;
    }
    /*
    sets the right book type
    to be inserted in the
    right table in the database.
    @param none
    @return none.
     */
    public void returnBookType(){

        try {

            initMysql();
            PreparedStatement pstmt;

            if(book instanceof PhysicalItem){
                PhysicalItem tBook = (PhysicalItem) book;


                String sql1 = "INSERT INTO pbooktab values(?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(sql1);

                pstmt.setInt(1, tBook.getItemId());
                pstmt.setString(2, tBook.getTitle());
                pstmt.setString(3, tBook.getAuthor());
                pstmt.setString(4, tBook.getIsbn());
                pstmt.setInt(1, tBook.getQuantity());

                pstmt.executeUpdate();

            }
            if(book instanceof Ebook){
                Ebook tBook = (Ebook) book;


                String sql1 = "INSERT INTO ebooktab values(?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(sql1);

                pstmt.setInt(1, tBook.getItemId());
                pstmt.setString(2, tBook.getTitle());
                pstmt.setString(3, tBook.getAuthor());
                pstmt.setString(4, tBook.getIsbn());
                pstmt.setInt(1, tBook.getByteSize());

                pstmt.executeUpdate();

            }
            if(book instanceof AudioBook){
                AudioBook tBook = (AudioBook) book;


                String sql1 = "INSERT INTO aubooktab values(?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(sql1);

                pstmt.setInt(1, tBook.getItemId());
                pstmt.setString(2, tBook.getTitle());
                pstmt.setString(3, tBook.getAuthor());
                pstmt.setString(4, tBook.getIsbn());
                pstmt.setInt(1, tBook.getByteSize());

                pstmt.executeUpdate();

            }else{
                throw new RuntimeException();
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
    }
    /*
    sets the relevant book type
    to be deleted from the relevant table
    in the database.
    @param none
    return none.
     */

    public void removeBookType(){


        try {

            initMysql();
            PreparedStatement pstmt;

            if(book instanceof PhysicalItem){
                PhysicalItem tBook = (PhysicalItem) book;


                String sql1 = "DELETE FROM pbooktab WHERE title = ?";

                pstmt = con.prepareStatement(sql1);

                pstmt.setString(1, tBook.getTitle());

                pstmt.executeUpdate();

            }
            if(book instanceof AudioBook){
                AudioBook tBook = (AudioBook) book;


                String sql1 = "DELETE FROM aubooktab WHERE title = ?";

                pstmt = con.prepareStatement(sql1);

                pstmt.setString(1, tBook.getTitle());

                pstmt.executeUpdate();

            }
            if(book instanceof Ebook){
                Ebook tBook = (Ebook) book;


                String sql1 = "DELETE FROM ebooktab WHERE title = ?";

                pstmt = con.prepareStatement(sql1);

                pstmt.setString(1, tBook.getTitle());

                pstmt.executeUpdate();

            }
            else{
                throw new RuntimeException();
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
    }

    protected void initMysql() throws ClassNotFoundException, SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/ library_db";
        String pass = "Boutrosdatabase13";
        String user = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");

        con = DriverManager.getConnection(jdbcURL, user, pass);

        stmt = con.createStatement();
        PreparedStatement pstmt = null;
    }
}
