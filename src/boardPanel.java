import javax.swing.*;
import java.awt.*;
public class boardPanel extends JPanel{
    private pitButton[] aPicks;
    private pitButton[] bPicks;
    private int[] playerA;
    private int[] playerB;
    private int aScore;
    private int bScore;
    private int undo;
    private boolean aTurn;


    public boardPanel(pitButton[] aPicks, pitButton[] bPicks, int[] a, int[] b, int asc, int bsc) {
        this.aPicks = aPicks;
        this.bPicks = bPicks;
        playerA = a;
        playerB = b;
        aScore = asc;
        bScore = bsc;
        setupButtons();
    }

    protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the background image
                ImageIcon backgroundIcon = new ImageIcon("visualAssets/Mancala_Main_Board.png");
                Image backgroundImg = backgroundIcon.getImage();
                g.drawImage(backgroundImg, 0, 0, 700, 600, this);
                for(int i = 0; i < 6; i++){
                    Image overlayImg = aPicks[i].getPic().getImage();
                    g.drawImage(overlayImg, 115 + (79 * i), 353, 80, 85, this); // Position and size of overlay image
                    Image overlayImgB = bPicks[i].getPic().getImage();
                    g.drawImage(overlayImgB, 510 - (79 * i), 170, 80, 85, this); // Position and size of overlay image
                    g.setColor(Color.BLACK); // Set the color for the text
                    g.setFont(new Font("Arial", Font.BOLD, 14)); // Set font for the numbers
                    g.drawString(String.valueOf(playerA[i]), 115 + (79 * i) + 35, 450); 
                    g.drawString(String.valueOf(playerB[i]), 510 - (79 * i) + 35, 270);
                }
                g.setFont(new Font("Arial",Font.BOLD, 27));
                g.drawString(String.valueOf(bScore), 70, 305);
                g.drawString(String.valueOf(aScore), 620, 305);
                g.drawString("Undos left: " + String.valueOf(3 - undo), 50, 500);
                if(aTurn){
                    g.drawString("Player A's Turn", 300, 500);
                }
                else{
                    g.drawString("Player B's Turn", 300, 500);
                }
            }
    // public void setA(ImageIcon[] a){
    //     aPicks = a;
    // }
    // public void setB(ImageIcon[] b){
    //     bPicks = b;
    // }
    public void setAScore(int a){
        aScore = a;
        repaint();
    }
    public void setBScore(int b){
        bScore = b;
        repaint();
    }
    private void setupButtons() {
        this.setLayout(null); // Use absolute positioning
        for (int i = 0; i < 6; i++) {
            aPicks[i].setBounds(115 + (79 * i), 353, 75, 75); // Updated size and alignment
            aPicks[i].setContentAreaFilled(false); 
            aPicks[i].setBorderPainted(false);
            this.add(aPicks[i]);
    
            // Configure player B's buttons
            bPicks[i].setBounds(510 - (79 * i), 180, 75, 75); // Adjusted dimensions
            bPicks[i].setContentAreaFilled(false);
            bPicks[i].setBorderPainted(false);
            this.add(bPicks[i]);
        }
    
        this.revalidate(); // Ensure layout is updated
        this.repaint(); // Repaint the container
        System.out.println("setup buttons");
    }
    public void setUndo(int u){
        undo = u;
        repaint();
    }
    public void setATurn(boolean b){
        aTurn = b;
        repaint();

    }
    
}
