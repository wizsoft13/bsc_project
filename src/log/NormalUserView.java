package log;

import librarian.AudioBook;
import librarian.Book;
import librarian.Ebook;
import user.NormalUser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/*
provides a panel of functions
 for a normal use
 @author Alain Kwasisi
 */
public class NormalUserView extends JFrame implements ActionListener {

    //declaring components
    private Container container;
    private JButton borrowButton, retButton, fineButton, availBookButton, readButton;
    private JTextArea outMess;

    private  JLabel title;

    /*
    constructor to initialized components.
     */
    public NormalUserView(){

        setBounds(300, 90, 1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Border border = new LineBorder(Color.black);

        setResizable(true);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Normal User view");
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

        fineButton = new JButton("Pay Fine");
        fineButton  .setFont(new Font("Arial", Font.PLAIN,15));
        fineButton  .setSize(250, 50);
        fineButton.setLocation(80, 360);
        container.add(fineButton );

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
        fineButton.addActionListener(this);
        retButton.addActionListener(this);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter user name, id and address:");
        String name, id, address;
        name = in.nextLine();
        id = in.nextLine();
        address = in.next();

        NormalUser user = new NormalUser(name, id, address);

        if(e.getSource()== availBookButton){

            for(Book b: user.showAvailableBooks()){
                outMess.append(b.toString());
            }

        }
        String type,title, author, isbn;
        int bkId, quantity;
        long bte;
        System.out.println("Enter the book type, title, id, author, quantity or byte number and isbn");

        type = in.nextLine();
        title = in.nextLine();
        bkId = in.nextInt();
        author = in.nextLine();
        quantity = in.nextInt();
        bte = in.nextLong();
        isbn = in.next();

        if(e.getSource()== borrowButton){

            outMess.append("Book title: "+ title+ "\n"+
                    "Book id: "+ bkId+ "\n"+
                    "Quantity: " +quantity+"\n"+
                    "Byte size: "+ bte+"\n"+
                    "Been borrowed on"+ user.borrowBook(type));

        }
        if(e.getSource()== readButton){

            if(type.equals("ebook")) {
                if(user.showAvailableBooks().contains(new Ebook(bkId, title, author, bte,isbn))) {
                    outMess.append(user.readBook(new Ebook(bkId, title, author, bte, isbn)));
                }else {
                    outMess.setText("This book is not available");
                }
            }
            if(type.equals("audio")) {
                if(user.showAvailableBooks().contains(new AudioBook(bkId, title, author, isbn, bte))){
                    outMess.append(user.readBook(new AudioBook(bkId, title, author, isbn, bte)));
                }else {
                    outMess.setText("This book is not available");
                }

            }

        }
        if(e.getSource()== fineButton){

            outMess.setText(user.payFine(type));

        }
        if(e.getSource()== retButton){

            if(type.equals("ebook")|| type.equals("audio")) {
                outMess.append("Book of type: " + type + "\ntitle: " + title + "\n Book Id: " + bkId + "\n Book author: " + author +
                        "\n Number of byte: " + bte + "ISBN: " + isbn + "\n Has been returned on " + user.checkInBook(type));
            }
            if(type.equals("physical")){
                outMess.append("Book of type: " + type + "\ntitle: " + title + "\n Book Id: " + bkId + "\n Book author: " + author +
                        "\n Quantity: " + quantity + "ISBN: " + isbn + "\n Has been returned on " + user.checkInBook(type));
            }

        }

    }

}
