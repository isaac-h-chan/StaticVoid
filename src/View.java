import java.awt.*;
import javax.swing.*;

public class View{
    public View(){
        JFrame frame = new JFrame();
        frame.setSize(750, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JLabel(new ImageIcon("visualAssets\\board.png")));





        frame.setVisible(true);

    }
    public static void main(String[] args) {
        View v = new View();
    }
}