package Panels;

import GameComponents.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class HTPPanel extends JPanel implements ActionListener {
    private CardLayout cl;
    private JButton goPlay;
    private BufferedImage screen;
    public HTPPanel(CardLayout c){
        cl=c;
        goPlay=new MyButton("Play",false);
        goPlay.addActionListener(this);
        add(goPlay);

        try{
            screen= ImageIO.read(HTPPanel.class.getResource("/GraphicPictures/Screens/HTPPanel.png"));
        }
        catch (Exception E){
            System.out.println("htp exception");
        }
    }

    public void paint(Graphics g){
        g.drawImage(screen, 0,0,getWidth(),getHeight(),null);
        g.setColor(Color.WHITE);
        g.fillRect(getWidth()*1500/1600, getHeight()*850/900, getWidth()*100/1600, getHeight()*50/900);
        g.setFont(new Font("Sans", Font.BOLD, 20));
        FontMetrics fontMetrics=g.getFontMetrics();
        int stringwidth=fontMetrics.stringWidth("Play");
        goPlay.setBounds(getWidth()*1500/1600, getHeight()*850/900, getWidth()*100/1600, getHeight()*50/900);
        g.setColor(Color.BLACK);
        g.drawString("Play", getWidth()*1550/1600-stringwidth/2, (getHeight()*880)/900);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==goPlay){
            cl.show(Constants.PANEL, Constants.PLAYPANELNAME);
        }
    }
}
