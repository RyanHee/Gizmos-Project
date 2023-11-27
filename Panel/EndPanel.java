package Panels;

import GameComponents.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class EndPanel extends JPanel {
    private BufferedImage screen, replay;
    private CardLayout cl;
    private Energy e;
    private Player[]playerLst;
    private int[]playerScore;
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
        playerLst=new Player[4];
        playerLst[0]=Constants.p1;
        playerLst[1]=Constants.p2;
        playerLst[2]=Constants.p3;
        playerLst[3]=Constants.p4;
        playerScore=new int[4];
        playerScore[0]=playerLst[0].getScore();
        playerScore[1]=playerLst[1].getScore();
        playerScore[2]=playerLst[2].getScore();
        playerScore[3]=playerLst[3].getScore();
    }



    public void paint(Graphics g) {
        //screen
        g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);

        Arrays.sort(playerLst);


        //winner label
        g.setFont(new Font("Sans", Font.BOLD, 100));
        g.setColor(Color.white);
        g.drawString("WINNER: PLAYER " + playerLst[3].getTurn()+1 + "!", 500, 200);

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
