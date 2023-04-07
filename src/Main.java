import log.LoginFrame;

import java.awt.*;

/**
 * main class
 * to launch the library application
 * @author Alain Kwasisi
 */
public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new LoginFrame();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
}