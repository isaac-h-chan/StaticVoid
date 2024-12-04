import java.awt.*;
import java.awt.event.ActionListener;


/**
 * CS 151 Group Assignment Fall 2024 code
 * @author Isaac Chan, Vincent Do, Kunal Pradhan 
 * @version 1.0 11/15/2024
 */
/**
 * Controller for MVC pattern implements the action of all the buttons on the game
 */
class Controller implements turnListener{
	private boolean isSetup;
    private Model m;
    private View v; 
    /**
     * This will create a Controller object that initializes all the buttons in View
     * @param view - A View object
     * @param real - A Model object
     */
    public Controller(View view, Model real){
		this.isSetup = false;
        v = view;
        m = real;
        m.setListener(this);
        v.getUndo().addActionListener(e -> {
            m.undo_move();
            System.out.println("Undo pressed");
        });
        v.getConfirm().addActionListener(e -> {
            m.pass_turn();
            System.out.println("Confirmed pressed");
        });
        v.getStartButton().addActionListener(e -> {
            int set = v.getOptions();
            m.setStartingPit(set);
            if(v.getDesign().equals("Brown")){
                v.setDesign(new brownDesign());
            }
            else{
                v.setDesign(new beigeDesign());
            }
            m.setup_game();
			this.isSetup = true;
        });
        updateButtons();
    
    }
    /**
     * This will update the current pitButtons action depending on who's turn it is 
     */
    public void updateButtons(){
        System.out.println("update Buttons Called");

		// check if a win has occured, if yes then remove all pit buttons to not take input
		// and then return early
		if (isSetup && m.check_win() != 1000) {
			pitButton[] tempA = v.getAButtons();
			pitButton[] tempB = v.getBButtons();
			for (int i = 0; i < 6; i++) {
				for(ActionListener a : tempA[i].getActionListeners()){
					tempA[i].removeActionListener(a);
				}
				for(ActionListener b : tempB[i].getActionListeners()){
					tempA[i].removeActionListener(b);
				}
			} 
            System.out.println(m.getAScore() + " " + m.getBScore());
			return;
		}

        pitButton[] tempA = v.getAButtons();
        
        for(int i = 0; i < 6; i ++){
            int t = tempA[i].getNum();
            for(ActionListener a : tempA[i].getActionListeners()){
                tempA[i].removeActionListener(a);
            }
            if(m.getisATurn() ){
            tempA[i].addActionListener(e -> {
                System.out.println("A Button pressed: " + t);
                m.make_move(t);
            });
            System.out.println(m.getCanMove());
        }
        }
        pitButton[] tempB = v.getBButtons();

        for(int i = 0; i < 6; i ++){
            int t = tempB[i].getNum();
            for(ActionListener a : tempB[i].getActionListeners()){
                tempB[i].removeActionListener(a);
            }
            if(! m.getisATurn()){
            tempB[i].addActionListener(e -> {
                System.out.println("B Button pressed: " + t);
                m.make_move(t);
               
            });
            System.out.println(m.getCanMove());
        }
        }
    }


    @Override
    /**
     * This is the turnLister that is implemented to call updateButtons() when a turn is changed
     */
    public void turnChanged() {
        // TODO Auto-generated method stub
       this.updateButtons();
       System.out.println("Listener is called");
    }

    @Override
    /**
     * This is used to remove the functionality of all pitButtons in cases 
     * such as in between a move choosing whether to confirm or undo
     */
    public void pauseButton() {
        System.out.println("pause Buttons Called");
        pitButton[] tempA = v.getAButtons();
        for(int i = 0; i < tempA.length; i ++){
            for(ActionListener a : tempA[i].getActionListeners()){
                tempA[i].removeActionListener(a);
            }
        }
        pitButton[] tempB = v.getBButtons();
        for(int i = 0; i < 6; i ++){
            for(ActionListener a : tempB[i].getActionListeners()){
                tempB[i].removeActionListener(a);
            }
        }

    }

}
