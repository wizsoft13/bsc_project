package librarian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
creates object book
according to their types.

@author Alain Kwasisi
 */
public class FactoryBook {
    //instance variable of type book.

    private PreparedStatement pstmt = null;
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    /*
    constructor for factoryBook object
     */
    public FactoryBook(){}

    /*
    sets the right book type
    to be inserted in the
    right table in the database.
    @param rBook
    @return bBook.
     */
    public Book returnBookType(Book rBook){

        Book bBook =null;

        if(rBook instanceof  PhysicalItem) {
            bBook = rBook;
        }
        if(rBook instanceof Ebook){
            bBook = rBook;
        }
        if(rBook instanceof AudioBook){
            bBook = rBook;
        }
        return bBook;
    }
    /*
    set a book to add
    in the database
    @param adBook Book type
    @return mess a string type
     */
    public String bookToAdd(Book adBook){

        String mess= null;
        Ebook sBook;
        PhysicalItem tBook;
        AudioBook rBook;
        try {


            initMysql();

            if(adBook instanceof PhysicalItem) {
                tBook = (PhysicalItem) adBook;
                if(tBook.getIsbn().equals(tBook.checkISBN())) {
                    String sql = "INSERT INTO pbook values(?, ?, ?, ?, ?, 'available')";
                    pstmt = con.prepareStatement(sql);

                    pstmt.setInt(1, tBook.getItemId());
                    pstmt.setString(2, tBook.getTitle());
                    pstmt.setString(3, tBook.getAuthor());
                    pstmt.setString(4, tBook.getIsbn());
                    pstmt.setInt(5, tBook.getQuantity());

                    pstmt.executeUpdate();

                    mess = String.format("Book: %s\tBy: %s\t\n Has been successfully added ", tBook.getTitle(), tBook.getAuthor());
                }

            }
            if(adBook instanceof Ebook) {
                sBook = (Ebook) adBook;

                if(sBook.getIsbn().equals(sBook.checkISBN())) {
                    String sql1 = "INSERT INTO ebooktab values(?, ?, ?, ?, ?,'available')";
                    pstmt = con.prepareStatement(sql1);

                    pstmt.setInt(1, sBook.getItemId());
                    pstmt.setString(2, sBook.getTitle());
                    pstmt.setString(3, sBook.getAuthor());
                    pstmt.setString(4, sBook.getIsbn());
                    pstmt.setLong(5, sBook.getByteSize());

                    pstmt.executeUpdate();

                    mess = String.format("Book: %s\tBy: %s\t\n Has been successfully added ", sBook.getTitle(), sBook.getAuthor());
                }

            }
            if(adBook instanceof AudioBook) {
                rBook = (AudioBook) adBook;

                if(rBook.getIsbn().equals(rBook.checkISBN())) {
                    String sql2 = "INSERT INTO aubook values(?, ?, ?, ?, ?,'available')";

                    pstmt = con.prepareStatement(sql2);
                    pstmt.setInt(1, rBook.getItemId());
                    pstmt.setString(2, rBook.getTitle());
                    pstmt.setString(3, rBook.getAuthor());
                    pstmt.setString(4, rBook.getIsbn());
                    pstmt.setLong(5, rBook.getByteSize());

                    pstmt.executeUpdate();

                    mess = String.format("Book: %s\tBy: %s\t\n Has been successfully added ", rBook.getTitle(), rBook.getAuthor());
                }
            }

        } catch (Exception err) {

            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }
        return mess;
    }
    /*
    sets the relevant book type
    to be deleted from the relevant table
    in the database.
    @param dBook string
    return string
     */

    public String bookToRem(Book dBook){
        String res = null;
        Ebook sBook = null;
        PhysicalItem tBook=null;
        AudioBook rBook = null;
        try {

            initMysql();

            if(dBook instanceof PhysicalItem) {
                tBook = (PhysicalItem) dBook;

                String sql1 = "DELETE FROM pbook WHERE PTITLE = ?";

                pstmt = con.prepareStatement(sql1);

                pstmt.setString(1, tBook.getTitle());

                pstmt.executeUpdate();

                res = String.format("Book: %s\tBy: %s\t\n Has been successfully removed ", tBook.getTitle(), tBook.getAuthor());
            }
            if(dBook instanceof Ebook){
                sBook = (Ebook) dBook;

                String sql2 = "DELETE FROM ebooktab WHERE ETITLE = ?";

                pstmt = con.prepareStatement(sql2);

                pstmt.setString(1, sBook.getTitle());

                pstmt.executeUpdate();

                res = String.format("Book: %s\tBy: %s\t\n Has been successfully removed ", sBook.getTitle(), sBook.getAuthor());
            }
            if(dBook instanceof AudioBook){
                rBook = (AudioBook) dBook;

                String sql3 = "DELETE FROM aubook WHERE TITLE = ?";

                pstmt = con.prepareStatement(sql3);

                pstmt.setString(1, rBook.getTitle());

                pstmt.executeUpdate();

                res = String.format("Book: %s\tBy: %s\t\n Has been successfully removed ", rBook.getTitle(), rBook.getAuthor());
            }

        } catch (Exception err) {
            //e.printStackTrace();
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }
        return res;
    }
    public String bookTypeList() {

        String bookList ="";

        try {

            initMysql();

            String sql = "SELECT PNUM, PTITLE, PAUTHOR, PISBN, PQTY  FROM pbook WHERE PSTATUS = 'available'";
            rs = stmt.executeQuery(sql);

            while(rs.next()){

                String title = rs.getString(2);
                int id = rs.getInt(1);
                String aut = rs.getString(3);
                String isbn = rs.getString(4);
                int qty = rs.getInt(5);

               bookList  += String.format("%-6d\t%-50s\t%-40s",id, title, aut) +"\n";

            }

            String sql1 = "SELECT EBNUM, ETITLE, EAUTHOR, EISBN, EBYTESIZE  FROM ebooktab WHERE ESTATUS = 'available' ";
            rs = stmt.executeQuery(sql1);

            while(rs.next()){

                String title = rs.getString(2);
                int id = rs.getInt(1);
                String aut = rs.getString(3);
                String isbn = rs.getString(4);
                int bts = rs.getInt(5);
                bookList += String.format("%-6d\t%-50s\t%-40s",id,title,aut)+ "\n";

            }

            String sql2 = "SELECT ANUM, TITLE, AUTHOR, ISBN, BYTESIZE  FROM aubook WHERE STATUS = 'available' ";
            rs = stmt.executeQuery(sql2);

            while(rs.next()){

                String title = rs.getString(2);
                int id = rs.getInt(1);
                String aut = rs.getString(3);
                String isbn = rs.getString(5);
                long bts = rs.getLong(4);
                bookList  += String.format("%-6d\t%-50s\t%-40s",id, title, aut) +"\n";

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
        return bookList;
    }
    /*
    changing book status
    @return none
     */
    public String bookStatus(Book sBook){

        String status = null;
        String sql;
        String sql1;
        Book tBook;

        try {

           initMysql();

            if(sBook instanceof PhysicalItem){
                tBook = sBook;

                sql = "SELECT PSTATUS from pbook where PNUM = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, tBook.getItemId());
                ResultSet rs = pstmt.executeQuery();

                if(rs.next()){
                    status = rs.getString("PSTATUS");
                }

            }
            if(sBook instanceof Ebook){
                tBook = (Ebook) returnBookType(sBook);

                sql = "SELECT ESTATUS from ebooktab where EBNUM = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, tBook.getItemId());
                ResultSet rs = pstmt.executeQuery();

                if(rs.next()){
                    status = rs.getString("ESTATUS");
                }

            }
            if(sBook instanceof AudioBook){
                tBook = (AudioBook) sBook;

                sql = "SELECT STATUS from aubook where ANUM = ?";
                pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, tBook.getItemId());
                ResultSet rs = pstmt.executeQuery();

                if(rs.next()){
                    status = rs.getString("STATUS");
                }

            }

        }  catch (Exception err) {

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
        return status;
    }
    /*
    update the status of the book
     */
    public void upBookStatus(Book stBook){

        String sql;
        Book tBook;

        try {

            initMysql();

            if(stBook instanceof PhysicalItem){
                tBook = stBook;
                if(bookStatus(tBook).equals("available")){
                    sql = "UPDATE pbook SET PSTATUS = 'unavailable' WHERE PNUM=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, tBook.getItemId());
                    pstmt.executeUpdate();
                }else{
                    sql = "UPDATE pbook SET PSTATUS = 'available' WHERE PNUM=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, tBook.getItemId());
                    pstmt.executeUpdate();
                }

            }
            if(stBook instanceof Ebook){
                tBook = (Ebook) stBook;

                if(bookStatus(tBook).equals("available")){
                    sql = "UPDATE ebooktab SET ESTATUS = 'unavailable' WHERE EBNUM=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, tBook.getItemId());
                    pstmt.executeUpdate();
                }else{
                    sql = "UPDATE ebooktab SET ESTATUS = 'available' WHERE EBNUM=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, tBook.getItemId());
                    pstmt.executeUpdate();
                }

            }
            if(stBook instanceof AudioBook){

                tBook = (AudioBook) stBook;

                if(bookStatus(tBook).equals("available")){
                    sql = "UPDATE aubook SET STATUS = 'unavailable' WHERE ANUM=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, tBook.getItemId());
                    pstmt.executeUpdate();
                }else{
                    sql = "UPDATE aubook SET STATUS = 'available' WHERE ANUM=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setInt(1, tBook.getItemId());
                    pstmt.executeUpdate();
                }

            }


        }  catch (Exception err) {

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
    }

    /*
    return a list of books which are unavailable
     */
    public List<Book>unavailList(){
        List<Book>list = new ArrayList<>();
        ResultSet rs = null;
        try{
            initMysql();
            String title, auth, isbn;
            int id, qty;
            long bte;
            String query = "SELECT * FROM pbook WHERE PSTATUS = 'unavailable'";

            rs = stmt.executeQuery(query);
            while(rs.next()){
                id = rs.getInt("PNUM");
                title = rs.getString("PTITLE");
                auth = rs.getString("PAUTHOR");
                isbn = rs.getString("PISBN");
                qty = rs.getInt("PQTY");
                list.add(new PhysicalItem(id, title, auth, isbn, qty));
            }
            String sql = "SELECT * FROM aubook WHERE STATUS = 'unavailable'";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                id = rs.getInt("ANUM");
                title = rs.getString("TITLE");
                auth = rs.getString("AUTHOR");
                isbn = rs.getString("ISBN");
                bte = rs.getInt("BYTESIZE");
                list.add(new AudioBook(id, title, auth, isbn, bte));
            }
            String sql1 = "SELECT * FROM ebooktab WHERE ESTATUS = 'unavailable'";
            rs = stmt.executeQuery(sql1);
            while(rs.next()){
                id = rs.getInt("EBNUM");
                title = rs.getString("ETITLE");
                auth = rs.getString("EAUTHOR");
                isbn = rs.getString("EISBN");
                bte = rs.getInt("EBYTESIZE");
                list.add(new Ebook(id, title, auth, bte, isbn));
            }
        }catch(Exception err){
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        } finally {
            try {
                if (stmt != null ) {
                    stmt.close();
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return list;
    }
    /*
    return a book title or
    book author if
    book status available.
     */
    public String bookByTitleOrAuthor(String det){
        String result = "";

        try{
            initMysql();
            String title, author;
            String query = "SELECT TiTLE, AUTHOR FROM aubook";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                title = rs.getString("TITLE");
                author = rs.getString("AUTHOR");
                if(det.equals(title) && ! det.equals(author)){
                    result += " the book title: "+ title+ " is available in audio form"+ "\n";
                }
                if(!det.equals(title) && det.equals(author)){
                    result += " the book authored by: "+ author+ " is available in audio form"+ "\n";
                }
            }
            String query1 = "SELECT PTiTLE, PAUTHOR FROM pbook";
            rs = stmt.executeQuery(query1);
            while(rs.next()) {
                title = rs.getString("PTITLE");
                author = rs.getString("PAUTHOR");
                if (det.equals(title) && !det.equals(author)) {
                    result += " the book title: " + title + " is available in paper form" + "\n";
                }
                if (!det.equals(title) && det.equals(author)) {
                    result += " the book authored by: " + author + " is available in paper form" + "\n";
                }
            }
            String query2 = "SELECT ETiTLE, EAUTHOR FROM ebooktab";
            rs = stmt.executeQuery(query2);
            while(rs.next()) {
                title = rs.getString("ETITLE");
                author = rs.getString("EAUTHOR");
                if (det.equals(title) && !det.equals(author)) {
                    result += " the book title: " + title + " is available in eBook form" + "\n";
                }
                if (!det.equals(title) && det.equals(author)) {
                    result += " the book authored by: " + author + " is available in eBook form" + "\n";
                }
            }

        }catch(Exception err){
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        } finally {
            try {
                if (stmt != null ) {
                    stmt.close();
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return result;
    }

    /*
    prepare the database connection
     */
    protected void initMysql() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        String jdbcURL = "jdbc:mysql://localhost:3306/ library_db";
        String pass = "Boutrosdatabase13";
        String user = "root";

        con = DriverManager.getConnection(jdbcURL, user, pass);

        stmt = con.createStatement();

    }
}
