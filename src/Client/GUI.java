package Client; /**
 * Created by Stan on 27-12-2016.
 */
import javax.swing.*;

public class GUI extends JFrame {

    public static void main(String[] args){
        new GUI().setVisible(true);
    }

    private GUI() {
        super("Connect Four");
        setSize(600,600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new SpringLayout());
    }
}
