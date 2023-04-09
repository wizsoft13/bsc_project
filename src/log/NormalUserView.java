package log;

import librarian.*;
import user.NormalUser;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


/**
provides a panel of functions
 for a normal use
 @author Alain Kwasisi
 */
public class NormalUserView extends JFrame implements ActionListener {

    //declaring components
    private Container container;
    private JButton borrowButton, retButton, fineButton, availBookButton, readButton, searchButton;
    private JTextArea outMess;

    private JTextField searchBar;

    private JPanel panel;
    private JLabel title;
    private  JScrollPane jScrollPane;

    /*
    constructor to initialized components.
     */
    public NormalUserView() {

        setBounds(300, 90, 1200, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Border border = new LineBorder(Color.black);

        setResizable(true);

        container = getContentPane();
        container.setLayout(null);

        title = new JLabel("Normal User view");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(500, 30);
        title.setLocation(300, 30);
        container.add(title);

        JTextField text = new JTextField("Enter the book's title or the book's author in the search box", 20);
        text.setBounds(150,130, 550, 20);
        text.setFont(new Font("Arial", Font.PLAIN, 20));
        text.setBorder(BorderFactory.createEmptyBorder());
        container.add(text);
        searchBar = new JTextField(30);
        Dimension searchSize = new Dimension(200, 40);
        searchBar.setPreferredSize(searchSize);
        searchBar.setFont(new Font("Arial", Font.PLAIN, 30));
        searchBar.setForeground(Color.GRAY);

        searchButton = new JButton("Search");
        JPanel searchField = new JPanel();
        searchField.setBounds(50, 80, 1000, 80);
        searchField.add(searchBar);
        searchField.add(searchButton);
        container.add(searchField);


        ImageIcon icon = createImageIcon("/icons/b_borr.jpg");
        availBookButton = new JButton("View Available Books", icon);
        availBookButton.setFont(new Font("Arial", Font.PLAIN, 15));
        availBookButton.setSize(250, 75);
        availBookButton.setLocation(80, 180);
        container.add(availBookButton);

        ImageIcon img1 = createImageIcon("/icons/borr_bk.jpg");
        borrowButton = new JButton("Borrow Books", img1);
        borrowButton.setFont(new Font("Arial", Font.PLAIN, 15));
        borrowButton.setSize(250, 75);
        borrowButton.setLocation(400, 180);
        container.add(borrowButton);

        ImageIcon img2 = createImageIcon("/icons/book_open.jpg");
        readButton = new JButton("Read Books", img2);
        readButton.setFont(new Font("Arial", Font.PLAIN, 15));
        readButton.setSize(250, 75);
        readButton.setLocation(700, 180);
        container.add(readButton);

        ImageIcon img3 = createImageIcon("/icons/penalty.jpg");
        fineButton = new JButton("Pay Fine", img3);
        fineButton.setFont(new Font("Arial", Font.PLAIN, 15));
        fineButton.setSize(250, 75);
        fineButton.setLocation(80, 360);
        container.add(fineButton);

        ImageIcon img4 = createImageIcon("/icons/b_return.jpg");
        retButton = new JButton("Return Borrowed Books", img4);
        retButton.setFont(new Font("Arial", Font.PLAIN, 15));
        retButton.setSize(275, 75);
        retButton.setLocation(400, 360);
        container.add(retButton);

        outMess = new JTextArea();
        outMess.setForeground(Color.blue);
        outMess.setFont(new Font("Arial", Font.ITALIC,30));
        jScrollPane = new JScrollPane(outMess);
        jScrollPane.setBounds(80,500,1000,300);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        container.add(jScrollPane);

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

        if (e.getSource() == availBookButton) {

            user.showAvailableBooks();
        }
        if(e.getSource()== searchButton){

            String result = searchBar.getText();
            outMess.setText(new FactoryBook().bookByTitleOrAuthor(result));
        }

        if (e.getSource() == borrowButton) {


            String type,title, author, isbn;
            int bkId, quantity;
            long bte;
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter the book type, title, author, and isbn: ");

            type = sc.nextLine();
            title = sc.nextLine();
            author = sc.nextLine();
            isbn = sc.next();

            System.out.println("Enter the book id, quantity, byte number: ");

            bkId = sc.nextInt();
            quantity = sc.nextInt();
            bte = sc.nextLong();

            if (type.equals("physical")) {
                outMess.append("Book title: " + title + "\n" +
                        "Book author: "+ author + "\n" +
                        "Book id: " + bkId + "\n" +
                        "Quantity: " + quantity + "\n" +
                        "Has been borrowed on" + user.borrowBook(new PhysicalItem(bkId, title, author, isbn, quantity)));
            } else if (type.equals("ebook")) {
                outMess.append("Book title: " + title + "\n" +
                        "Book author: "+ author + "\n" +
                        "Book id: " + bkId + "\n" +
                        "Byte size: " + bte + "\n" +
                        "has been borrowed on" + user.borrowBook(new Ebook(bkId, title, author, bte, isbn)));
            } else if (type.equals("audio")) {
                outMess.append("Book title: " + title + "\n" +
                        "Book author: "+ author + "\n" +
                        "Book id: " + bkId + "\n" +
                        "Byte size: " + bte + "\n" +
                        "Has been borrowed on" + user.borrowBook(new AudioBook(bkId, title, author, isbn, bte)));
            } else {
                outMess.setText("This book is not available");

            }
        }
        if (e.getSource() == readButton) {

            Scanner sc = new Scanner(System.in);
            String type,title, author, isbn;
            int bkId;
            long bte;
            System.out.println("Enter the book type, title, author, and isbn: ");
            type = sc.nextLine();
            title = sc.nextLine();
            author = sc.nextLine();
            isbn = sc.next();

            System.out.println("Enter the book id, and the number of byte: ");
            bkId = sc.nextInt();
            bte = sc.nextLong();
            if(type.equals("ebook")) {

                outMess.setText(user.readBook(new Ebook(bkId, title, author, bte, isbn), type));
            }
            if(type.equals("audio")){
                outMess.setText(user.readBook(new AudioBook(bkId, title, author,isbn,bte), type));
            }

        }
        if (e.getSource() == fineButton) {

            String type,title, author, isbn;
            int bkId, quantity;
            long bte;
            System.out.println("Enter the book type, title, author, id, quantity, byte number and isbn: ");

            type = in.nextLine();
            title = in.nextLine();
            author = in.nextLine();
            bkId = in.nextInt();
            quantity = in.nextInt();
            bte = in.nextLong();
            isbn = in.next();

            if (type.equals("physical")) {
                outMess.append(user.payFine(new PhysicalItem(bkId, title, author, isbn, quantity)));

            } else if (type.equals("ebook")) {
                outMess.append(user.payFine(new Ebook(bkId, title, author, bte, isbn)));

            } else if (type.equals("audio")) {
                outMess.append(user.payFine(new AudioBook(bkId, title, author, isbn, bte)));
            }
            outMess.setText("This book type does not exist ");

        }
        if (e.getSource() == retButton) {

            String type,title, author, isbn;
            int bkId, quantity;
            long bte;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the book type, title, author, and isbn: ");

            type = sc.nextLine();
            title = sc.nextLine();
            author = sc.nextLine();
            isbn = sc.next();

            System.out.println("Enter the book id, quantity, and byte number: ");
            bkId = sc.nextInt();
            bte = sc.nextLong();
            quantity = sc.nextInt();

            if (type.equals("ebook")) {
                outMess.append("Book of type: " + type + "\ntitle: " + title + "\n Book Id: " + bkId + "\n Book author: " + author +
                            "\n Number of byte: " + bte + "ISBN: " + isbn + "\n Has been returned on " + user.checkInBook(new Ebook(bkId, title, author, bte, isbn)));

            } else if (type.equals("audio")) {
                outMess.append("Book of type: " + type + "\ntitle: " + title + "\n Book Id: " + bkId + "\n Book author: " + author +
                            "\n Number of byte: " + bte + "ISBN: " + isbn + "\n Has been returned on " + user.checkInBook(new AudioBook(bkId, title, author, isbn, bte)));
            }

            else if (type.equals("physical")) {
                   outMess.append("Book of type: " + type + "\ntitle: " + title + "\n Book Id: " + bkId + "\n Book author: " + author +
                            "\n Quantity: " + quantity + "ISBN: " + isbn + "\n Has been returned on " + user.checkInBook(new PhysicalItem(bkId, title, author, isbn, quantity)));
            }
            outMess.setText("This book type does not exist ");
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
