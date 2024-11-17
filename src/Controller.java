/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan
 * @version 1.0 11/15/2024
 */

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // Attach listeners for pits
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 6; col++) {
                final int pitIndex = (row == 0) ? 11 - col : col; // Map row/col to model pit index
                view.getPitButton(row, col).addActionListener(e -> handlePitSelection(pitIndex));
            }
        }

        // Attach listeners for undo and pass turn
        view.getUndoButton().addActionListener(e -> handleUndo());
        view.getPassTurnButton().addActionListener(e -> handlePassTurn());

        updateView();
    }

    private void handlePitSelection(int pitIndex) {
        model.make_move(pitIndex);
        updateView();
        if (model.check_win() != 0) {
            endGame();
        }
    }

    private void handleUndo() {
        model.undo_move();
        updateView();
    }

    private void handlePassTurn() {
        model.pass_turn();
        updateView();
    }

    private void updateView() {
        view.updateBoard(model.getBoard());
        view.updateScores(model.getAScore(), model.getBScore());
        view.getStatusLabel().setText(model.getisATurn() ? "Player A's Turn" : "Player B's Turn");
    }

    private void endGame() {
        int result = model.check_win();
        if (result > 0) {
            view.getStatusLabel().setText("Player A Wins!");
        } else if (result < 0) {
            view.getStatusLabel().setText("Player B Wins!");
        } else {
            view.getStatusLabel().setText("It's a Tie!");
        }
    }
}