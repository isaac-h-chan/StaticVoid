import javax.swing.ImageIcon;
/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */
/**
 * This class is for the Brown Mancala board that implements boardDesignStrategy and will change the
 * style of the mancala board 
 */
public class brownDesign implements boardDesignStrategy{

    @Override
    /**
     * This will return the ImageIcon of the Brown mancala board design
     * @return - ImageIcon of Brown board design
     */
    public ImageIcon getBoardDesign() {
        // TODO Auto-generated method stub
        return new ImageIcon("visualAssets/Mancala_Main_Board.png");
    }

    @Override
    /**
     * This will get the pit picture asset of a specific marble instance
     * @return - String of relative location of pit picture
     */
    public String getPitDesignPath(int i) {
        // TODO Auto-generated method stub
        return "visualAssets/Mancala Board Pits/Mancala Board Pits/" + i + ".png";
    }
    
}
