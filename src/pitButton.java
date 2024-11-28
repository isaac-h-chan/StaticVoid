import java.awt.*;

import javax.swing.*;

public class pitButton extends JButton{
    private ImageIcon pit;
    private int pitNum;
    public pitButton(ImageIcon p){
        pit = p;
        
    }

    public void setPitPic(ImageIcon p){
        pit = p;
        // setIcon(pitSize(pit));
    }
    // public ImageIcon pitSize(ImageIcon i){
    //     Image re = i.getImage();
    //     Image done = re.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    //     return new ImageIcon(done);

    // }
    public void setNum(int n){
        pitNum = n;
    }
    public int getNum(){
        return pitNum;
    }
    public ImageIcon getPic(){
        return pit;
    }

    
}
