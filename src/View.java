import javax.swing.*;
import java.awt.*;

/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan
 * @version 1.0 11/15/2024
 */

public class View extends JFrame {

    private JButton[][] pits;  // Buttons for the pits
    private JLabel mancalaA, mancalaB, statusLabel;
    private JButton undoButton, passTurnButton;

    public View() {
        setTitle("Mancala Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Mancala board
        JPanel boardPanel = new JPanel(new GridLayout(2, 6));
        pits = new JButton[2][6];
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 6; col++) {
                pits[row][col] = new JButton("4"); // Default number of stones
                pits[row][col].setFont(new Font("Arial", Font.BOLD, 20));
                boardPanel.add(pits[row][col]);
            }
        }

        mancalaA = new JLabel("A: 0", SwingConstants.CENTER);
        mancalaB = new JLabel("B: 0", SwingConstants.CENTER);

        add(mancalaA, BorderLayout.WEST);
        add(boardPanel, BorderLayout.CENTER);
        add(mancalaB, BorderLayout.EAST);

        // Controls
        JPanel controlPanel = new JPanel(new FlowLayout());
        undoButton = new JButton("Undo");
        passTurnButton = new JButton("Pass Turn");
        statusLabel = new JLabel("Player A's Turn");

        controlPanel.add(undoButton);
        controlPanel.add(passTurnButton);
        controlPanel.add(statusLabel);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    // Getter for undoButton
    public JButton getUndoButton() {
        return this.undoButton;
    }

    // Getter for passTurnButton
    public JButton getPassTurnButton() {
        return this.passTurnButton;
    }

    // Getter for statusLabel
    public JLabel getStatusLabel() {
        return this.statusLabel;
    }

    // Update Mancala display
    public void updateScores(int aScore, int bScore) {
        mancalaA.setText("A: " + aScore);
        mancalaB.setText("B: " + bScore);
    }

    // Update board with the current state
    public void updateBoard(int[] board) {
        for (int i = 0; i < 6; i++) {
            pits[0][i].setText(String.valueOf(board[11 - i])); // Top row (Player B)
            pits[1][i].setText(String.valueOf(board[i]));      // Bottom row (Player A)
        }
    }

    // Getter for pit buttons
    public JButton getPitButton(int row, int col) {
        return pits[row][col];
    }
}