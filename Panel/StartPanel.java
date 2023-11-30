package Panels;
import GameComponents.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class StartPanel extends JPanel implements ActionListener {
    private BufferedImage screen, play;
    private JButton b, b1;
    private CardLayout cl;

    public StartPanel(CardLayout c) {
        cl=c;
        setLayout(null);
        try {
            screen = ImageIO.read(StartPanel.class.getResource("/GraphicPictures/Screens/StartScreen.PNG"));
            //play = ImageIO.read(new File("play.png"));
        } catch (Exception E) {
            System.out.println("Exception Error1");
            return;
        }
        b = new MyButton("start",false);
        b1=new MyButton("HTP",false);
        b.addActionListener(this);
        add(b);
        b1.addActionListener(this);
        add(b1);
        //b.setVisible(false);
        //b.setBounds(Constants.WIDTH/2-200, Constants.HEIGHT/2+50, 400, 200);





    }

    public void paint(Graphics g) {

        //screen
        g.drawImage(screen, 0, 0, getWidth(), getHeight(), null);
        b.setBounds(getWidth()/2-getWidth()*178/1600, getHeight()/2+getHeight()*80/900, getWidth()*356/1600, getHeight()*161/900);
        b1.setBounds(getWidth()/2-getWidth()*178/1600, getHeight()/2+getHeight()*280/900,getWidth()*356/1600, getHeight()*138/900);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b){
            System.out.println("Clicked");
            cl.show(Constants.PANEL, Constants.PLAYPANELNAME);
        }
        if (e.getSource()==b1){
            System.out.println("How to play");
            cl.show(Constants.PANEL,Constants.HTPPANELNAME);
        }
    }
}
