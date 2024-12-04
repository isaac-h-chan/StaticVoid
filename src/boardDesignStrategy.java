import javax.swing.*;
/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */
/**
 * This is the interface of our board design strategy
 */
public interface boardDesignStrategy {
    /**
     * This will be used to get the ImageIcon of the board design
     * @return - ImageIcon of board
     */
    public ImageIcon getBoardDesign();
    /**
     * Used to get the string of path to pit design with the specific marble count
     * @param index - number of marbles need in image
     * @return - String of relative path to pit image
     */
    public String getPitDesignPath(int index);
    
}
