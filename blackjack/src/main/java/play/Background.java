package play;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel{

    String bg = "Background.jpg";

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        //背景
        Image bgImg = new ImageIcon("images/setting/" + bg).getImage();
        g.drawImage(bgImg ,0 , 0 ,800 ,500 ,this);

    }

    public void paintCard(String CImg ,int num ,int who){
        Graphics g = this.getGraphics();
        Image Img = new ImageIcon("images/poker/" + CImg).getImage();
        g.drawImage(Img,240+num * 80,who * 150 ,50 ,80 ,this);
    }
}
