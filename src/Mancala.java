/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */
/**
 * This class will run the whole Mancala game 
 */
public class Mancala {
 
    public static void main(String[] args) {
        Model m = new Model();
        View v = new View(m);
        Controller c = new Controller(v, m);
    }
    
}
