// Main method

/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan
 * @version 1.0 11/15/2024
 */

public class MancalaTest {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        model.setup_game();
    }
}