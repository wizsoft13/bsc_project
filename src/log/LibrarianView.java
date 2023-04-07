package log;



import librarian.AudioBook;
import librarian.Book;
import librarian.Ebook;
import librarian.PhysicalItem;
import user.Librarian;
import user.NormalUser;
import user.SpecialUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
provide GUI user interface for
for librarian
 @author Alain Kwasisi
 */
public class LibrarianView extends JFrame implements ActionListener {

    private Container container;
    private JButton remBookButton,borrowButton, availButton, userListButton, addBookButton, addUserButton, fineButton, remUserButton;

    private JTextArea outMess;
    private  JLabel title;

    private JScrollPane jScrollPane;

    /*
    constructor for LibrarianView objects.
     */
    public LibrarianView(){

        setBounds(300, 90, 1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);


        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Librarian view");
        title.setFont(new Font("Arial", Font.PLAIN,30));
        title.setSize(500, 30);
        title.setLocation(300, 30);
        container.add(title);

        ImageIcon icon = createImageIcon("/icons/b_borr.jpg");
        availButton = new JButton("Issue Books", icon);
        availButton.setFont(new Font("Arial", Font.PLAIN,15));
        availButton.setSize(250, 75);
        availButton.setLocation(75, 90);
        container.add(availButton);

        ImageIcon img = createImageIcon("/icons/borr_bk.jpg");
        borrowButton = new JButton("View Borrowed Books", img);
        borrowButton.setFont(new Font("Arial", Font.PLAIN,15));
        borrowButton.setSize(280, 75);
        borrowButton.setLocation(400, 90);
        container.add(borrowButton);

        ImageIcon img1= createImageIcon("/icons/del_book.png");
        remBookButton = new JButton("Remove Books", img1);
        remBookButton.setFont(new Font("Arial", Font.PLAIN,15));
        remBookButton.setSize(250, 75);
        remBookButton.setLocation(755, 90);
        container.add(remBookButton);

        ImageIcon img2= createImageIcon("/icons/add_book.png");
        addBookButton = new JButton("Add Books", img2);
        addBookButton.setFont(new Font("Arial", Font.PLAIN,15));
        addBookButton.setSize(250, 75);
        addBookButton.setLocation(400, 210);
        container.add(addBookButton);

        ImageIcon img3 = createImageIcon("/icons/penalty.jpg");
        fineButton = new JButton("Issue Fine", img3);
        fineButton.setFont(new Font("Arial", Font.PLAIN,15));
        fineButton.setSize(250, 75);
        fineButton.setLocation(735, 210);
        container.add(fineButton);

        ImageIcon img4= createImageIcon("/icons/user_list.jpg");
        userListButton = new JButton("View Users", img4);
        userListButton.setFont(new Font("Arial", Font.PLAIN,15));
        userListButton.setSize(250, 75);
        userListButton.setLocation(75, 320);
        container.add(userListButton);

        ImageIcon img5= createImageIcon("/icons/user.png");
        addUserButton = new JButton("Add Users", img5);
        addUserButton.setFont(new Font("Arial", Font.PLAIN,15));
        addUserButton.setSize(250, 75);
        addUserButton.setLocation(400, 320);
        container.add(addUserButton);

        ImageIcon img6= createImageIcon("/icons/del_user.jpg");
        remUserButton = new JButton("Remove Users", img6);
        remUserButton.setFont(new Font("Arial", Font.PLAIN,15));
        remUserButton.setSize(250, 75);
        remUserButton.setLocation(735, 320);
        container.add(remUserButton);

        availButton.addActionListener(this);
        borrowButton.addActionListener(this);
        remBookButton.addActionListener(this);
        addBookButton.addActionListener(this);
        fineButton.addActionListener(this);
        userListButton.addActionListener(this);
        addUserButton.addActionListener(this);
        remUserButton.addActionListener(this);

        outMess = new JTextArea();
        outMess.setForeground(Color.blue);
        outMess.setFont(new Font("Arial", Font.ITALIC,30));
        jScrollPane = new JScrollPane(outMess);
        jScrollPane.setBounds(80,500,1000,300);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        container.add(jScrollPane);


        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){


        Librarian lb = Librarian.getInstance();
        Scanner in = new Scanner(System.in);

        try {

            if (e.getSource() == availButton) {
                outMess.setText("List of Books Available \nID \tTitle\tAuthor\tISBN\tQuantity or Bytes Number\n");
                outMess.append( lb.getAvailableBooks());
            }

            if (e.getSource() == borrowButton) {

                for(Book book: lb.trackBorrowedBook()){
                    outMess.append("Book title: "+ book.getTitle()+"\t Book author: "+ book.getAuthor()+ "\n");
                }

            }
            if (e.getSource() == remBookButton) {
                System.out.println("Enter book type, title, author, id, quantity(if applies), byte size(if applies) and isbn:  ");

                String type = in.nextLine();
                String title = in.nextLine();
                String author = in.nextLine();
                int id = in.nextInt();
                int qty = in.nextInt();
                long btSize = in.nextLong();
                String isbn = in.next();
               if(type.equals("physical")) {
                   outMess.append(lb.removeBook(new PhysicalItem(id, title, author, isbn, qty)));
               }else if (type.equals("ebook")) {
                   outMess.append(lb.removeBook(new Ebook(id, title, author, btSize,isbn)));
               }else if(type.equals("audio")){
                   outMess.append(lb.removeBook(new AudioBook(id, title, author,isbn, btSize)));
               }else{
                   outMess.setText("This book type does not exist!!");
               }
            }
            if (e.getSource() == addBookButton) {

                System.out.println("Enter book type, title, author, id, quantity(if applies), byte size(if applies) and isbn:  ");

                String type = in.nextLine();
                String title = in.nextLine();
                String author = in.nextLine();
                int id = in.nextInt();
                int qty = in.nextInt();
                long btSize = in.nextLong();
                String isbn = in.next();

                if(type.equals("physical")) {
                    outMess.append(lb.addBook(new PhysicalItem(id, title, author, isbn, qty)));
                }else if (type.equals("ebook")) {
                    outMess.append(lb.addBook(new Ebook(id, title, author, btSize,isbn)));
                }else if(type.equals("audio")){
                    outMess.append(lb.addBook(new AudioBook(id, title, author,isbn, btSize)));
                }else{
                    outMess.setText("This book type does not exist!!");
                }

            }
            if (e.getSource() == fineButton) {

                System.out.println("Enter book type, title, author, id, quantity(if applies), byte size(if applies) and isbn:  ");

                String type = in.nextLine();
                String title = in.nextLine();
                String author = in.nextLine();
                int id = in.nextInt();
                int qty = in.nextInt();
                long btSize = in.nextLong();
                String isbn = in.next();

                if(type.equals("physical")) {
                    if(lb.isFined(new PhysicalItem(id, title, author, isbn, qty))){
                        outMess.setText("A fine has been issued in relation to this book.");
                    }
                    outMess.setText("No fine issued in relation to this book.");
                }else if (type.equals("ebook")) {
                    if(lb.isFined(new Ebook(id, title, author, btSize,isbn))){
                        outMess.setText("A fine has been issued in relation to this book.");
                    }
                    outMess.setText("No fine issued in relation to this book.");
                }else if(type.equals("audio")){
                    if(lb.isFined(new AudioBook(id, title, author,isbn, btSize))){
                        outMess.setText("A fine has been issued in relation to this book.");
                    }
                    outMess.setText("No fine issued in relation to this book.");
                }else{
                    outMess.setText("This book type does not exist!!");
                }
            }


            if (e.getSource() == userListButton) {

                outMess.setText("List of register members \nID NUMBER\tNAME\tADDRESS\n");
                outMess.append( lb.getUserList());
            }
            if (e.getSource() == addUserButton) {

                System.out.println("Enter your user type (normal or special), user id, name and address: ");
                String uType = in.nextLine();
                String uId = in.nextLine();
                String name = in.nextLine();
                String address= in.nextLine();

                if(uType.equals("normal")){
                    outMess.append(lb.addMember(new NormalUser(uId, name, address)));
                } else if (uType.equals("special")) {
                    outMess.append(lb.addMember(new SpecialUser(name, uId, address)));
                }else{
                    outMess.setText("No such user type");
                }
            }
            if (e.getSource() == remUserButton) {

                System.out.println("Enter your user type (normal or special), user id, name and address: ");
                String uType = in.nextLine();
                String uId = in.nextLine();
                String name = in.nextLine();
                String address= in.next();

                if(uType.equals("normal")){
                    outMess.append(lb.removeUser(new NormalUser(uId, name, address)));
                } else if (uType.equals("special")) {
                    outMess.append(lb.removeUser(new SpecialUser(name, uId, address)));
                }else{
                    outMess.setText("No such user type");
                }

            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }
    /* Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path){
        java.net.URL imgURL = getClass().getResource(path);
        if(imgURL !=null){
            return new ImageIcon(imgURL);
        }else{
            System.out.println("Couldn't find file: "+ path);
            return null;
        }
    }

}
