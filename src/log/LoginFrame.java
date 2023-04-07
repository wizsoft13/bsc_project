package log;

import user.UserFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


/**
this class creates a log in frame
 */

public class LoginFrame extends JFrame implements ActionListener {

    private Connection con = null;

    private PreparedStatement pstmt = null;

    private static LoginFrame loginFrame=null;
    private JPanel panel;
    private Container container;
    private JLabel userLab, passwordLab, idLab, title ;
    private JTextField userText, idText, message;
    private JPasswordField passwordText;
    private JButton submit, register;
    public LoginFrame() {

        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Login");
        title.setFont(new Font("Arial", Font.PLAIN,30));
        title.setSize(500, 30);
        title.setLocation(300, 30);
        container.add(title);

        userLab = new JLabel("Username");
        userLab.setFont(new Font("Arial", Font.PLAIN,20));
        userLab.setSize(100, 40);
        userLab.setLocation(30, 100);
        container.add(userLab);

        userText = new JTextField();
        userText.setFont(new Font("Arial", Font.PLAIN,15));
        userText.setSize(190, 40);
        userText.setLocation(200, 100);
        container.add(userText);

        idLab = new JLabel("ID Number");
        idLab.setFont(new Font("Arial", Font.PLAIN,20));
        idLab.setSize(100, 40);
        idLab.setLocation(30, 150);
        container.add(idLab);

        idText = new JTextField();
        idText.setFont(new Font("Arial", Font.PLAIN,15));
        idText.setSize(190, 40);
        idText.setLocation(200, 150);
        container.add(idText);

        passwordLab = new JLabel("Password");
        passwordLab.setFont(new Font("Arial", Font.PLAIN,20));
        passwordLab.setSize(100, 40);
        passwordLab.setLocation(30, 200);
        container.add(passwordLab);

        passwordText = new JPasswordField();
        passwordText.setFont(new Font("Arial", Font.PLAIN,15));
        passwordText.setSize(190, 40);
        passwordText.setLocation(200, 200);
        container.add(passwordText);


        //setting up buttons
        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN,15));
        submit.setSize(100, 30);
        submit.setLocation(150, 300);
        container.add(submit);

        register = new JButton("Sign Up");
        register.setFont(new Font("Arial", Font.PLAIN,15));
        register.setSize(100, 30);
        register.setLocation(280, 300);
        container.add(register);

        //listen to button click
        submit.addActionListener(this);
        register.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        UserFactory userFac = new UserFactory();

        if(e.getSource()== submit) {

            String id = idText.getText();
            String userName = userText.getText();
            String password = passwordText.getText();


            if(userFac.retCredentials(id, userName, password)){

                if(id.indexOf('l')==0){
                    new LibrarianView();
                    setVisible(true);

                    this.setVisible(false);
                }
                if(id.indexOf('n')==0){

                    new NormalUserView();
                    setVisible(true);
                    this.setVisible(false);
                }
                if(id.indexOf('s')==0){
                    new SpecialUserView();
                    setVisible(true);
                    this.setVisible(false);
                }
            }else{
                message= new JTextField();
                message.setSize(500, 40);
                message.setLocation(100, 600 );
                message.setText("Invalid id number and/or username and/or password!!");
                userText.setText("");
                idText.setText("");
                passwordText.setText("");

            }

        }
        if(e.getSource() == register){
            SignUpFrame sf = new SignUpFrame();
            sf.setVisible(true);
            this.setVisible(false);
        }

    }

}
