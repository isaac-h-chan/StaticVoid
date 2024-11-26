import javax.swing.*;
import java.awt.*;

public class View{
    JPanel panel;
    public View(){
        JFrame frame = new JFrame();
        frame.setSize(750, 750);
        ImageIcon[] aPicks = new ImageIcon[6];
        //frame.add(new JLabel(reBoard));
        pitMaker(aPicks);
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the background image
                ImageIcon backgroundIcon = new ImageIcon("visualAssets\\Mancala_Main_Board.png");
                Image backgroundImg = backgroundIcon.getImage();
                g.drawImage(backgroundImg, 0, 0, 700, 600, this);

    
                for(int i = 0; i < 6; i++){
                    Image overlayImg = aPicks[i].getImage();
                    g.drawImage(overlayImg, 115 + (79 * i), 350, 80, 85, this); // Position and size of overlay image
                    
                }
            }
        };
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void pitMaker(ImageIcon[] i){
        for(int j = 0; j < 6; j++){
            i[j] = new ImageIcon("visualAssets\\Mancala Board Pits\\Mancala Board Pits\\0.png");
        }
        

    }
    public ImageIcon pitSize(ImageIcon i){
        Image re = i.getImage();
        Image done = re.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        return new ImageIcon(done);

    }
    public static void main(String[] args) {
        View v = new View();
    }
}