public class MancalaTest {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);

        model.setup_game(); // Set up the initial game state
    }
}