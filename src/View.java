import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */

/**
 * A view controls the GUI of the mancala game showing the user what
 * has happened in the game and allows them to see a mancala game
 */

public class View{
    private boardDesignStrategy design;
    private JPanel mainPanel;
    private CardLayout card;
    private titlePanel title;
    boardPanel panel;
    int[] playerA = new int[6];
    int[] playerB = new int[6];
    private JButton startButton;
    private JButton undoButton;
    private JButton confirmButton;
    private pitButton[] aPicks;
    private pitButton[] bPicks;
    private int bScore = 0;
    private int aScore = 0;
	private Model m;
    /**
     * Constructs a View object and creates GUI view
     * @param m - the model it is listening from for updates
     */
    public View(Model m){
		this.m = m;
        JFrame frame = new JFrame();
        JPanel gamePanel = new JPanel(new BorderLayout());
        card = new CardLayout();
        mainPanel = new JPanel(card);
        startButton = new JButton("Start");
        title = new titlePanel(startButton, card);
        frame.setSize(750, 750);
        aPicks = new pitButton[6];
        bPicks = new pitButton[6];
        //frame.add(new JLabel(reBoard));
        pitMaker();
        
        panel = new boardPanel(aPicks, bPicks, playerA, playerB, aScore, bScore);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Center the buttons with some spacing
        gamePanel.add(panel, BorderLayout.CENTER);
        gamePanel.add(buttonPanel, BorderLayout.SOUTH);
        confirmButton = new JButton("Confirm");
        undoButton = new JButton("Undo");
        buttonPanel.add(undoButton);
        buttonPanel.add(confirmButton);
        mainPanel.add(title, "titleScreen");
        mainPanel.add(gamePanel, "game");
        
        // frame.add(buttonPanel, BorderLayout.SOUTH);
        // frame.add(panel);
        frame.add(mainPanel);

		// attach changelistener to Model
		m.attach_view_listener((event) -> {
			updateScores(this.m.getAScore(), this.m.getBScore());
			updatePits(this.m.getBoard());
			updateUndo(this.m.getNumUndos());
			updateTurn(this.m.getisATurn());
            setupGame();
            winCondition(this.m.check_win());
		});

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * This will create the pitButtons used in the game and put them in the pitButton arrays
     */
    public void pitMaker(){
        for(int j = 0; j < 12; j++){
            if(j < 6){
            aPicks[j] = new pitButton(new ImageIcon("visualAssets/Mancala Board Pits/Mancala Board Pits/0.png"));
            aPicks[j].setNum(j);
            }
            else{
                bPicks[j - 6] = new pitButton(new ImageIcon("visualAssets/Mancala Board Pits/Mancala Board Pits/0.png"));
                bPicks[j - 6].setNum(j);

            }
        }
        

    }
    /**
     * This will set the boardDesignStrategy that our view will be using for this game
     * updates the boardPanel with this board design
     * @param b - the boardDesignStrategy used this game
     */
    public void setDesign(boardDesignStrategy b){
        design = b;
        panel.setBoard(b);
        //updatePits(this.m.getBoard());
    }
    /**
     * This will update the boardPanel to display the current players undos left
     * @param u - undos that a player has
     */
    public void updateUndo(int u){
        panel.setUndo(u);
    }
    /**
     * This updates the pictures of the marbles on each pitButton to curent count
     */
    public void updatePics(){
        for(int j = 0; j < 6; j++){
            aPicks[j].setPitPic(new ImageIcon(design.getPitDesignPath(playerA[j])));
            bPicks[j].setPitPic(new ImageIcon(design.getPitDesignPath(playerB[j])));
        }

    }
    /**
     * This is to switch the title screen to the mancala board 
     */
    public void setupGame(){
        card.show(mainPanel, "game");
    }
    /**
     * This will return the starting pit size user has selected from titlePanel
     * @return the current selected size of starting pits
     */
    public int getOptions(){
        return title.getOptions();
    }
   /**
    * This will get the user selected design from the titlePanel
    * @return selected design for board
    */
    public String getDesign(){
        return title.getDesign();
    }
    /**
     * This will get the undo button from View
     * @return the undo button
     */
    public JButton getUndo(){
        return undoButton;

    }
    /**
     * This will get the confirm button that the View uses
     * @return the confrim button
     */
    public JButton getConfirm(){
        return confirmButton;
    }
    /**
     * This will update the boardPanel to update and show which player's turn it is
     * @param b - true if player A turn, false if player B's turn
     */
    public void updateTurn(boolean b){
        panel.setATurn(b);
    }
    /**
     * This will update the View variables of each player's scores and call method to set boardPanel score variables
     * @param a - player A's score
     * @param b - player B's score
     */
    public void updateScores(int a, int b){
        aScore = a;
        bScore = b;
        System.out.println(aScore + " " + bScore);
        panel.setAScore(aScore);
        panel.setBScore(bScore);
        panel.repaint();
    }
    /**
     * This will update View's of the count between each player's pits
     * @param up - count of marbles in each pit
     */
    public void updatePits(int[] up){
        for(int i = 0; i < up.length; i++){
            if(i < 6){
                playerA[i] = up[i];
            }
            else{
                playerB[i - 6] = up[i];
            }
        }
        updatePics();
        panel.repaint();

    }
    /**
     * The will return the array of pitButtons holding A's buttons
     * @return - Array of player A's pitButtons
     */
    public pitButton[] getAButtons(){
        return aPicks;
    }
    /**
     * The will return the array of pitButtons holding B's buttons
     * @return - Array of player B's pitButtons
     */
    public pitButton[] getBButtons(){
        return bPicks;
    }
    /**
     * This will retunr the start button for the title screen
     * @return - the start button
     */
    public JButton getStartButton(){
        return startButton;
    }
    /**
     * This will send the current check of win status to boardPanel
     * @param check - int of win status
     */
    public void winCondition(int check){
        panel.setWin(check);
    }
   
}
