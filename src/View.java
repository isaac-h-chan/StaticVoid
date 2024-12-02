import javax.imageio.plugins.tiff.TIFFDirectory;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View{
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

    public void pitMaker(){
        for(int j = 0; j < 12; j++){
            if(j < 6){
            aPicks[j] = new pitButton(new ImageIcon("visualAssets/Mancala Board Pits/Mancala Board Pits/" + 0 +".png"));
            aPicks[j].setNum(j);
            }
            else{
                bPicks[j - 6] = new pitButton(new ImageIcon("visualAssets/Mancala Board Pits/Mancala Board Pits/" + 0 +".png"));
                bPicks[j - 6].setNum(j);

            }
        }
        

    }
    public void updateUndo(int u){
        panel.setUndo(u);
    }
    public void updatePics(){
        for(int j = 0; j < 6; j++){
            aPicks[j].setPitPic(new ImageIcon("visualAssets/Mancala Board Pits/Mancala Board Pits/" + playerA[j] +".png"));
            bPicks[j].setPitPic(new ImageIcon("visualAssets/Mancala Board Pits/Mancala Board Pits/" + playerB[j] +".png"));
        }

    }
    public void setupGame(){
        card.show(mainPanel, "game");
    }
    public int getOptions(){
        return title.getOptions();
    }
    private JPanel createTitleScreen() {
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Mancala Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            System.out.println("game start");
            card.show(mainPanel, "game");
        });
        titlePanel.add(startButton, BorderLayout.SOUTH);
        
        return titlePanel;
    }
    public JButton getUndo(){
        return undoButton;

    }
    public JButton getConfirm(){
        return confirmButton;
    }
    public void updateTurn(boolean b){
        panel.setATurn(b);
    }
    
    public void updateScores(int a, int b){
        aScore = a;
        bScore = b;
        System.out.println(aScore + " " + bScore);
        panel.setAScore(aScore);
        panel.setBScore(bScore);
        panel.repaint();
    }
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
    public pitButton[] getAButtons(){
        return aPicks;
    }
    public pitButton[] getBButtons(){
        return bPicks;
    }
    public JButton getStartButton(){
        return startButton;
    }

    public void winCondition(int check){
        panel.setWin(check);
    }
   
}
