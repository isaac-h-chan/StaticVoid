import java.awt.*;
class Controller{
    private Model m;
    private View v; 
    public Controller(View view, Model real){
        v = view;
        m = real;

        v.getUndo().addActionListener(e -> {
            m.undo_move();
            System.out.println("Undo pressed");
        });
        pitButton[] tempA = v.getAButtons();
        for(int i = 0; i < 6; i ++){
            int t = tempA[i].getNum();
            tempA[i].addActionListener(e -> {
                System.out.println("A Button pressed: " + t);
            });
        }
        pitButton[] tempB = v.getBButtons();
        for(int i = 0; i < 6; i ++){
            int t = tempB[i].getNum();
            tempB[i].addActionListener(e -> {
                System.out.println("B Button pressed: " + t);
            });
        }
    }

}