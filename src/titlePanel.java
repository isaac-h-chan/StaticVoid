import javax.swing.*;
import java.awt.*;
public class titlePanel extends JPanel{
    private JButton start;
    private JComboBox<Integer> options;
    private JComboBox<String> design;
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
    public int getOptions(){
        return (int) options.getSelectedItem();
    }
    public String getDesign(){
        return (String) design.getSelectedItem();
    }
    
    
}
