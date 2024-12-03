import javax.swing.ImageIcon;

public class beigeDesign implements boardDesignStrategy{

    @Override
    public ImageIcon getBoardDesign() {
        // TODO Auto-generated method stub
        return new ImageIcon("visualAssets/Mancala_Main_Board_Style2.png");
    }

    @Override
    public String getPitDesignPath(int index) {
        // TODO Auto-generated method stub
        return "visualAssets/Pits Style2/"+ index + ".png";
    }
    
}
