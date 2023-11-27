package Panels;

import GameComponents.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class EndPanel extends JPanel {
    private BufferedImage screen, replay;
    private CardLayout cl;
    private Energy e;
    private int fstplace, fstplacepts, sndplace,trdplace,fthplace,sndplacepts,trdplacepts,fthplacepts;
    public EndPanel(CardLayout c){
        cl=c;

        try {
            screen = ImageIO.read(EndPanel.class.getResource("/GraphicPictures/Screens/EndScreen.png"));
            replay = ImageIO.read(EndPanel.class.getResource("/GraphicPictures/Screens/replay.png"));
        }
        catch(Exception E){
            System.out.println("Exception Error3");
            return;
        }
    }

    public void paint(Graphics g) {
        //screen
        g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);

        //replay button
        //g.drawImage(replay, 590, 600, 600, 130, null);

        //winner label
        g.setFont(new Font("Sans", Font.BOLD, 100));
        g.setColor(Color.white);
        g.drawString("WINNER: PLAYER " + fstplace + "!", 500, 200);

        //winner's points
        g.setFont(new Font("Sans", Font.BOLD, 60));
        g.setColor(Color.white);
        g.drawString(fstplacepts + " pts" , 850, 270);

        //place labels
        g.setFont(new Font("Sans", Font.BOLD, 20));
        g.drawString("2nd: player " + sndplace +  "- " + sndplacepts + "pts", 850, 400);
        g.drawString("3nd: player " + trdplace +  "- " + trdplacepts + "pts", 850, 460);
        g.drawString("4nd: player " + fthplace +  "- " + fthplacepts + "pts", 850, 520);

    }
}
