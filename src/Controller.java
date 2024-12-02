import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.Timer;
class Controller implements turnListener{
    private Model m;
    private View v; 
    public Controller(View view, Model real){
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
            m.setup_game();
        });
        updateButtons();
    
    }
    
    public void updateButtons(){
        System.out.println("update Buttons Called");
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
    public void turnChanged() {
        // TODO Auto-generated method stub
       this.updateButtons();
       System.out.println("Listener is called");
    }

    @Override
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