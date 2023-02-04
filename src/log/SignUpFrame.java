package log;

import user.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SignUpFrame extends JFrame implements ActionListener {


   // private Connection con = null;
   // private Statement stmt = null;
    private JPanel panel;
    private Container container;
    private JLabel userLab, passLab, message,  title, conPassLab;

    private JTextArea inputMess;
    private JTextField userText;
    private JPasswordField passText, conPassText;
    private JButton submit, reset;

    //constructor, to initialize components
    //with default values.
    public SignUpFrame(){

        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Create username and password");
        title.setFont(new Font("Arial", Font.PLAIN,30));
        title.setSize(500, 30);
        title.setLocation(300, 30);
        container.add(title);

        userLab = new JLabel("Username");
        userLab.setFont(new Font("Arial", Font.PLAIN,20));
        userLab.setSize(100, 20);
        userLab.setLocation(30, 100);
        container.add(userLab);

        userText = new JTextField();
        userText.setFont(new Font("Arial", Font.PLAIN,15));
        userText.setSize(190, 20);
        userText.setLocation(200, 100);
        container.add(userText);

        passLab = new JLabel("Password");
        passLab.setFont(new Font("Arial", Font.PLAIN,20));
        passLab.setSize(100, 20);
        passLab.setLocation(30, 150);
        container.add(passLab);

        passText = new JPasswordField();
        passText.setFont(new Font("Arial", Font.PLAIN,15));
        passText.setSize(190, 20);
        passText.setLocation(200, 150);
        container.add(passText);

        conPassLab = new JLabel("Confirm Password");
        conPassLab.setFont(new Font("Arial", Font.PLAIN,20));
        conPassLab.setSize(185, 20);
        conPassLab.setLocation(30, 200);
        container.add(conPassLab);

        conPassText = new JPasswordField();
        conPassText.setFont(new Font("Arial", Font.PLAIN,15));
        conPassText.setSize(190, 20);
        conPassText.setLocation(200, 200);
        container.add(conPassText);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN,15));
        submit.setSize(100, 20);
        submit.setLocation(150, 450);
        container.add(submit);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN,15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        container.add(reset);

        message= new JLabel("");
        message.setFont(new Font("Arial", Font.PLAIN, 20));
        message.setSize(500, 25);
        message.setLocation(100, 500);
        container.add(message);

        inputMess = new JTextArea();
        inputMess.setFont(new Font("Arial", Font.PLAIN, 15));
        inputMess.setSize(200, 150);
        inputMess.setLocation(580, 100);
        inputMess.setLineWrap(true);
        container.add(inputMess);

        setVisible(true);


        /*title = new JLabel("Registration Form in Windows Form:");
        title.setForeground(Color.blue);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        userLab = new JLabel("Username: ");
        passLab = new JLabel("Password: ");
        conPassLab = new JLabel("Confirm Password");*/
    }
    /*public void createUserNameAndPassword() throws SQLException {
        String name;
        int idNum;
        setVisible(true);
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {


            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String jdbcURL = "jdbc:mysql://localhost:3306/ library_db";
            String pass = "Boutrosdatabase13";
            String user = "root";

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(jdbcURL, user, pass);

            stmt = con.createStatement();

            String sql = "SELECT";

            stmt.executeUpdate(sql);

            System.out.println("insert complete");

        } catch (Exception err) {
            //e.printStackTrace();
            System.err.println("Error loading JDBC driver");
            err.printStackTrace(System.err);
            System.exit(0);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }*/
    public void actionPerformed(ActionEvent e){

    }
    public static void main(String[]args){
        SignUpFrame sf = new SignUpFrame();

    }

}





