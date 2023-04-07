package log;


import user.UserFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

/**
 * For new users to register their username
 * and password
 * @author Alain Kwasisi
 */

public class SignUpFrame extends JFrame implements ActionListener {


    private Container container;
    private JLabel userLab, passLab, message,  title, conPassLab, userId;

    private JTextArea inputMess;
    private JTextField userText, userIdText;
    private JPasswordField passText, conPassText;
    private JButton submit, reset;

    //constructor, to initialize components
    //with default values.
    public SignUpFrame(){

        setBounds(300, 90, 1200, 600);
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
        userText.setSize(190, 40);
        userText.setLocation(200, 100);
        container.add(userText);

        passLab = new JLabel("Password");
        passLab.setFont(new Font("Arial", Font.PLAIN,20));
        passLab.setSize(100, 20);
        passLab.setLocation(30, 150);
        container.add(passLab);

        passText = new JPasswordField();
        passText.setFont(new Font("Arial", Font.PLAIN,15));
        passText.setSize(190, 40);
        passText.setLocation(200, 150);
        container.add(passText);

        conPassLab = new JLabel("Confirm Password");
        conPassLab.setFont(new Font("Arial", Font.PLAIN,20));
        conPassLab.setSize(185, 20);
        conPassLab.setLocation(30, 200);
        container.add(conPassLab);

        conPassText = new JPasswordField();
        conPassText.setFont(new Font("Arial", Font.PLAIN,15));
        conPassText.setSize(190, 40);
        conPassText.setLocation(200, 200);
        container.add(conPassText);


        userId = new JLabel("User ID");
        userId.setFont(new Font("Arial", Font.PLAIN,20));
        userId.setSize(75, 20);
        userId.setLocation(30, 250);
        container.add(userId);

        userIdText = new JTextField();
        userIdText.setFont(new Font("Arial", Font.PLAIN,15));
        userIdText.setSize(190, 40);
        userIdText.setLocation(200, 250);
        container.add(userIdText);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN,15));
        submit.setSize(100, 30);
        submit.setLocation(150, 350);
        container.add(submit);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN,15));
        reset.setSize(100, 30);
        reset.setLocation(270, 350);
        container.add(reset);

        //listen to button click
        submit.addActionListener(this);
        reset.addActionListener(this);

        message= new JLabel("");
        message.setFont(new Font("Arial", Font.PLAIN, 20));
        message.setSize(900, 40);
        message.setLocation(50, 500);
        container.add(message);

        inputMess = new JTextArea();
        inputMess.setFont(new Font("Arial", Font.PLAIN, 25));
        inputMess.setSize(450, 150);
        inputMess.setLocation(550, 100);
        inputMess.setLineWrap(true);
        container.add(inputMess);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){

        String userName;
        String pssWord;
        String confPass;
        String myId;
        ValidateCredentials valid = new ValidateCredentials();

        if(e.getSource()==submit){

            userName = userText.getText();
            pssWord = passText.getText();
            confPass = conPassText.getText();
            myId = userIdText.getText();

            if(!valid.validatePassword(pssWord) && valid.validateUsername(userName)){
                passText.setText("");
                conPassText.setText("");
                passText.setBackground(Color.red);
                conPassText.setBackground(Color.red);
                message.setText("The password entered is not valid\n Press reset to try again");
            }
            if (!valid.validateUsername(userName)&& valid.validatePassword(pssWord)) {
                userText.setText("");
                userText.setBackground(Color.red);
                message.setText("The username entered is not valid\n Press reset to try again ");
            }
            if (!valid.validateUsername(userName)&& !valid.validatePassword(pssWord)){
                userText.setText("");
                userText.setBackground(Color.red);
                passText.setText("");
                passText.setBackground(Color.red);
                conPassText.setText("");
                conPassText.setBackground(Color.red);
                message.setText("The username and password entered are not valid \n Press reset to try again");
            }
            if(pssWord.equals(confPass)){
                if(valid.validateUsername(userName) && valid.validatePassword(pssWord)) {

                    UserFactory userFactory = new UserFactory();

                    String result = userFactory.appendCredDet(myId, userName, pssWord);

                    inputMess.setText("Success!! \n Username and Password successfully saved" + "\n" + result);
                    inputMess.setForeground(Color.blue);
                    userText.setText("");
                    passText.setText("");
                    conPassText.setText("");
                    userIdText.setText("");

                }
            }else{
                passText.setText("");
                conPassText.setText("");
                passText.setBackground(Color.red);
                conPassText.setBackground(Color.red);
                message.setText("Password do not match!!\n Click the reset button to try again.");
                message.setForeground(Color.red);

            }

        }
        if(e.getSource()==reset){
            userText.setText("");
            userText.setBackground(Color.white);
            passText.setText("");
            passText.setBackground(Color.white);
            conPassText.setText("");
            conPassText.setBackground(Color.white);
            userIdText.setText("");
            userIdText.setBackground(Color.white);
            inputMess.setText("");
            inputMess.setBackground(Color.white);
        }

    }

}





