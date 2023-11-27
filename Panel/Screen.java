package Panels;

import GameComponents.Energy;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {

    private CardLayout cl;
    public Screen(String s){
        super(s);
        cl=new CardLayout();
        JPanel mainPanel = new JPanel();
        Constants.PANEL=mainPanel;
        mainPanel.setLayout(cl);

        StartPanel startPanel = new StartPanel(cl);
        PlayPanel playPanel = new PlayPanel(cl);
        EndPanel endPanel = new EndPanel(cl);
        ConvertPanel convertPanel=new ConvertPanel(cl);

        mainPanel.add(startPanel, Constants.STARTPANELNAME);
        mainPanel.add(playPanel, Constants.PLAYPANELNAME);
        mainPanel.add(endPanel, Constants.ENDPANELNAME);
        mainPanel.add(convertPanel, "convert");

        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);

        cl.show(mainPanel, Constants.STARTPANELNAME);
    }
}
