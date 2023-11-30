package Panels;

import GameComponents.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

public class EndPanel extends JPanel implements ActionListener {
    private BufferedImage screen, replay;
    private CardLayout cl;

    private Player[]playerLst;
    private JButton backButton;
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

        backButton=new MyButton("back", false);
        backButton.addActionListener(this);
        add(backButton);
    }



    public void paint(Graphics g) {
        //screen
        g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);

        Arrays.sort(playerLst);


        //winner label
        g.setFont(new Font("Sans", Font.BOLD, 60));
        g.setColor(Color.WHITE);
        String str = "WINNER: PLAYER " + (playerLst[3].getTurn()) +" with " + playerLst[3].getScore() + " POINTS!";
        FontMetrics fontMetrics=g.getFontMetrics();
        int stringwidth=fontMetrics.stringWidth(str);
        g.drawString(str, getWidth()/2-stringwidth/2, getHeight()*200/900);


        //place labels
        g.setFont(new Font("Sans", Font.BOLD, 40));
        fontMetrics=g.getFontMetrics();
        str="2nd place: player " + (playerLst[2].getTurn()) +  " with " + playerLst[2].getScore() + " points";
        stringwidth=fontMetrics.stringWidth(str);
        g.drawString(str, getWidth()/2-stringwidth/2, getHeight()*400/900);

        str="3nd place: player " + (playerLst[1].getTurn()) +  " with " + playerLst[1].getScore() + " points";
        stringwidth=fontMetrics.stringWidth(str);
        g.drawString(str, getWidth()/2-stringwidth/2, getHeight()*460/900);

        str="4th place: player " + (playerLst[0].getTurn()) +  " with " + playerLst[0].getScore() + " points";
        stringwidth=fontMetrics.stringWidth(str);
        g.drawString(str, getWidth()/2-stringwidth/2, getHeight()*520/900);

        g.setFont(new Font("Sans", Font.BOLD, 20));
        fontMetrics=g.getFontMetrics();
        stringwidth=fontMetrics.stringWidth("Play Screen");
        int stringheight=fontMetrics.getAscent();
        g.fillRect(getWidth()*1450/1600, getHeight()*850/900, getWidth()*150/1600, getHeight()*50/900);
        g.setColor(Color.BLACK);
        g.drawString("Play Screen", getWidth()*1525/1600-stringwidth/2, (getHeight()*880)/900);
        backButton.setBounds(getWidth()*1450/1600, getHeight()*850/900, getWidth()*150/1600, getHeight()*50/900);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backButton){
            Constants.showEnd=true;
            System.out.println(1);
            cl.show(Constants.PANEL, Constants.PLAYPANELNAME);
        }
    }
}
