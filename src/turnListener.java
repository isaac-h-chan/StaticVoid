/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */
/**
 * This interface will be used by controller to know when a turn has been passed to another player
 */
public interface turnListener {
    /**
     * Will used to call a method whenever the listerner is notified a turn has changed
     */
    public void turnChanged();
    /**
     * used to pause all pit buttons on the screen
     */
    public void pauseButton();
}
