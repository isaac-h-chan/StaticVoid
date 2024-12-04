import java.awt.*;

import javax.swing.*;
/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */
/**
 * This class is to create the pitButtons to make pit clickable and work in the game
 */
public class pitButton extends JButton{
    private ImageIcon pit;
    private int pitNum;
    /**
     * Constructor of pitButton
     * @param p - Image Icon for this pitButton
     */
    public pitButton(ImageIcon p){
        pit = p;
        
    }
    /**
     * Will set the new picture of the pitButton
     * @param p - ImageIcon to set for pitButton
     */
    public void setPitPic(ImageIcon p){
        pit = p;
        // setIcon(pitSize(pit));
    }
    // public ImageIcon pitSize(ImageIcon i){
    //     Image re = i.getImage();
    //     Image done = re.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    //     return new ImageIcon(done);

    // }
    /**
     * This will set the number that represents which pit it is representing
     * @param n - int of number of pit
     */
    public void setNum(int n){
        pitNum = n;
    }
    /**
     * Returns the number that the pit represents
     * @return - int that the pit represents
     */
    public int getNum(){
        return pitNum;
    }
    /**
     * Returns the current ImageIcon the pitButton has associated with it
     * @return - current ImageIcon of pitButton
     */
    public ImageIcon getPic(){
        return pit;
    }

    
}
