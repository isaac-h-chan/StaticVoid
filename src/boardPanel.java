import javax.swing.*;
import java.awt.*;
/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */
/**
 * This class will implement the board for Mancala with GUI for pit marble count, 
 * player score count, which turn it is, and undos left it implements JPanel
 */
public class boardPanel extends JPanel{
    private pitButton[] aPicks;
    private pitButton[] bPicks;
    private int[] playerA;
    private int[] playerB;
    private int aScore;
    private int bScore;
    private int undo;
    private boolean aTurn;
    private String win;
    private boardDesignStrategy board;
    /**
     * This will create a board panel object for the board of mancala and pits in place
     * @param aPicks - array of Player A's pitButtons
     * @param bPicks - array of Player B's pitButtons
     * @param a - array of count of marbles in Player A's pits
     * @param b - array of count of marbles in Player B's pits
     * @param asc - int of Player A's score
     * @param bsc - int of Player B's score
     */
    public boardPanel(pitButton[] aPicks, pitButton[] bPicks, int[] a, int[] b, int asc, int bsc) {
        this.aPicks = aPicks;
        this.bPicks = bPicks;
        playerA = a;
        playerB = b;
        aScore = asc;
        bScore = bsc;
        setupButtons();
    }
   /**
    * This will paint the panel for the macala board and game
    * @param g - Graphics needed to paint this boardPanel
    */
    protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load the background image
                ImageIcon backgroundIcon = new ImageIcon("visualAssets/Mancala_Main_Board.png");
                if(board != null){
                    backgroundIcon = board.getBoardDesign();

                }
                
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
                if(win != null){
                    g.drawString(win, 275, 600);
                }
            }
    /**
     * This will set the boardDesignStrategy this boardPanel will be using
     * @param b - a boardDesignStrategy for either brown or beige
     */
    public void setBoard(boardDesignStrategy b){
        board = b;
    }
    /**
     * This will set the current value of recorded A's Score
     * @param a - A's Score
     */
    public void setAScore(int a){
        aScore = a;
        repaint();
    }
    /**
     * This will set the current value of recorded B's Score
     * @param b - B's Score
     */
    public void setBScore(int b){
        bScore = b;
        repaint();
    }
    /**
     * This is used to setup pitButtons in the correct location
     */
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
    /**
     * This will set the recorded amount of undo's a player has
     * @param u - undos left for player
     */
    public void setUndo(int u){
        undo = u;
        repaint();
    }
    /**
     * This will set the boolean of whether or not it is Player A's turn
     * @param b - true if A's turn False if B's turn
     */
    public void setATurn(boolean b){
        aTurn = b;
        repaint();

    }
    /**
     * Sets the win string to something if n is less than 1000
     * @param n - an Integer holding a value for win condition
     */
    public void setWin(int n){
        if(n > 0 && n < 1000){
            win = "Player A won";
        }
        else if(n < 0 ){
            win = "Player B won";
        }
        
        
        repaint();
    }
    
}
