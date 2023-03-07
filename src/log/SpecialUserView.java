package log;

import user.SpecialUser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
provide user's panel.

@author Alain Kwasisi
 */

public class SpecialUserView extends JFrame implements ActionListener {

    private Container container;
    private JButton borrowButton, retButton, pointButton, availBookButton, readButton;
    private JTextArea outMess;

    private  JLabel title;

    /*
    constructor to initialized components.
     */
    public SpecialUserView(){

        setBounds(300, 90, 1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Border border = new LineBorder(Color.black);

        setResizable(true);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Special User view");
        title.setFont(new Font("Arial", Font.PLAIN,30));
        title.setSize(500, 30);
        title.setLocation(300, 30);
        container.add(title);

        availBookButton = new JButton("View Available Books");
        availBookButton.setFont(new Font("Arial", Font.PLAIN,15));
        availBookButton.setSize(250, 50);
        availBookButton.setLocation(80, 180);
        container.add(availBookButton);

        borrowButton = new JButton("Borrow Books");
        borrowButton  .setFont(new Font("Arial", Font.PLAIN,15));
        borrowButton  .setSize(250, 50);
        borrowButton.setLocation(400, 180);
        container.add(borrowButton );

        readButton = new JButton("Read Books");
        readButton  .setFont(new Font("Arial", Font.PLAIN,15));
        readButton  .setSize(250, 50);
        readButton.setLocation(700, 180);
        container.add(readButton );

        pointButton = new JButton("Collect AND Redeem Points");
        pointButton  .setFont(new Font("Arial", Font.PLAIN,15));
        pointButton  .setSize(250, 50);
        pointButton.setLocation(80, 360);
        container.add(pointButton );

        retButton = new JButton("Return Borrowed Books");
        retButton  .setFont(new Font("Arial", Font.PLAIN,15));
        retButton  .setSize(250, 50);
        retButton.setLocation(400, 360);
        container.add(retButton );

        outMess = new JTextArea();
        outMess.setFont(new Font("Arial", Font.PLAIN,15));
        outMess.setSize(1000, 300);
        outMess.setLocation(80, 500);
        outMess.setBorder(border);
        container.add(outMess );

        availBookButton.addActionListener(this);
        borrowButton.addActionListener(this);
        readButton.addActionListener(this);
        pointButton.addActionListener(this);
        retButton.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()== availBookButton){

        }
        if(e.getSource()== borrowButton){

        }
        if(e.getSource()== readButton){


        }
        if(e.getSource()== pointButton){

        }
        if(e.getSource()== retButton){

        }


    }

    public static void main(String[] args){

        SpecialUserView spv = new SpecialUserView();
    }
}
