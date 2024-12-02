public class Mancala {
    public static void main(String[] args) {
        View v = new View();
        Model m = new Model(v);
        Controller c = new Controller(v, m);
        m.setup_game();
    }
    
}
