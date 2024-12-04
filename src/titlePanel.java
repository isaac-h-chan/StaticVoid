import javax.swing.*;
import java.awt.*;
/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */
/**
 * This class a JPanel that will act as our title screen of our mancala program
 */
public class titlePanel extends JPanel{
    private JButton start;
    private JComboBox<Integer> options;
    private JComboBox<String> design;
    /**
     * This is the Constructor for titlePanel
     * @param s - This is the Start button
     * @param c - this is the Card Layout used to switch screens of game
     */
    public titlePanel(JButton s, CardLayout c){
        start = s;
        
        setLayout(new BorderLayout());

    
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(CENTER_ALIGNMENT);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 200)));
        JLabel titleLabel = new JLabel("Mancala Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT); // Center alignment for BoxLayout
        centerPanel.add(titleLabel);

        // Add spacing between the label and combo box
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        JLabel sizeLabel = new JLabel("Select the starting pit size", SwingConstants.CENTER);
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sizeLabel.setAlignmentX(CENTER_ALIGNMENT); // Center alignment for BoxLayout
        centerPanel.add(sizeLabel);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        options = new JComboBox<>();
        options.addItem(3);
        options.addItem(4);
        options.setPreferredSize(new Dimension(150, 30));
        options.setMaximumSize(options.getPreferredSize());
        options.setAlignmentX(CENTER_ALIGNMENT); 
        centerPanel.add(options);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel boardLabel = new JLabel("Select the board color", SwingConstants.CENTER);
        boardLabel.setFont(new Font("Arial", Font.BOLD, 16));
        boardLabel.setAlignmentX(CENTER_ALIGNMENT); // Center alignment for BoxLayout
        centerPanel.add(boardLabel);

        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        design = new JComboBox<>();
        design.addItem("Brown");
        design.addItem("Beige");
        design.setPreferredSize(new Dimension(150, 30));
        design.setMaximumSize(options.getPreferredSize());
        design.setAlignmentX(CENTER_ALIGNMENT); 
        centerPanel.add(design);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        start.setAlignmentX(CENTER_ALIGNMENT);
        centerPanel.add(start);
        add(centerPanel, BorderLayout.CENTER);
       

    }
    /**
     * Will return the int in the options JComboBox that represents the number of marbles in starting pit
     * @return - number of marbles in starting pit
     */
    public int getOptions(){
        return (int) options.getSelectedItem();
    }
    /**
     * This will return the string in the design JComboBox
     * @return - string in design JComboBox
     */
    public String getDesign(){
        return (String) design.getSelectedItem();
    }
    
    
}
