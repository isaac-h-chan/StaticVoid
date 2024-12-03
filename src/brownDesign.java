import javax.swing.ImageIcon;

public class brownDesign implements boardDesignStrategy{

    @Override
    public ImageIcon getBoardDesign() {
        // TODO Auto-generated method stub
        return new ImageIcon("visualAssets/Mancala_Main_Board.png");
    }

    @Override
    public String getPitDesignPath(int i) {
        // TODO Auto-generated method stub
        return "visualAssets/Mancala Board Pits/Mancala Board Pits/" + i + ".png";
    }
    
}
