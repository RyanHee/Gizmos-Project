package Panels;
import GameComponents.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class PlayPanel extends JPanel implements ActionListener {
    private CardLayout cl;
    private int red;
    private int yellow;
    private int blue;
    private int black;
    private String label;
    private int victorypoint;
    private int totalcards;
    private int lv3totalcards;
    private int[]xlst;
    private int[]ylst;
    private int display;
    private TreeMap<Integer, Card> CardDeck;
    private TreeMap<Integer, Card> AvailableCard;
    private Player p1, p2, p3, p4, currPlayer;
    private BufferedImage firstplayer, otherplayer, screen, level0, lv1, lv2, lv3, Red, Black, Blue, Yellow;
    private BufferedImage placehold;
    private ArrayList<JButton> buttonsForResearchCards;
    private Card researchSelected;
    private ArrayList<Card>level1Cards;
    private ArrayList<Card>level2Cards;
    private ArrayList<Card>level3Cards;
    private Card[] level1Show;
    private Card[] level2Show;
    private Card[] level3Show;
    private EnergyDeck energyDeck;
    private ArrayList<Energy>six;
    private Card converterCardPicked;
    private JButton energyDeckButton, convertB, fileB, pickB, buildB, r1B, r2B, r3B, blueB, redB, yellowB, blackB, test;
    private ArrayList<JButton> energyButtonLst;
    private ArrayList<Card> lst=new ArrayList<>();
    private int inventoryHeight;
    private Player[]playerlst;
    private boolean convert=false, file=false, pick=false, build=false, didAction=false, grab, bof=false, pickOut=false, pickConverter=false, bff=false;
    private boolean l1r=false, l2r=false, l3r=false, pickr=false, pickl1r=false, pickl2r=false, pickl3r=false, r1bClicked=false, r2bClicked=false, r3bClicked=false;
    private Energy input;
    private int curr;
    private int under;
    private JButton[][]DisplayCardButtons;
    private Card[][]DisplayCards;
    private ArrayList<JButton>ConverterButtonList, FCB;
    private String output;
    private int end=0;
    public PlayPanel(CardLayout c){
        cl=c;
        red = 0;
        yellow = 0;
        blue = 0;
        black = 0;
        victorypoint = 0;
        totalcards = 0;
        lv3totalcards = 0;
        curr=0;
        grab=false;
        display=0;
        try {
            firstplayer = ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Player/FirstPlayer.jpg"));
            otherplayer = ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Player/OtherPlayer.jpg"));
            screen = ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Screens/PlayScreen.PNG"));
            level0 = ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Card/Level0/Pick_White.jpg"));
            lv1 = ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Card/BackCard/Level1.jpg"));
            lv2 = ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Card/BackCard/Level2.jpg"));
            lv3 = ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Card/BackCard/Level3.jpg"));
            placehold = ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Card/Level1/Build/Black_Point_Blue.jpg"));
            Red=ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Energy/Red_Energy.jpg"));
            Blue=ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Energy/Blue_Energy.jpg"));
            Black=ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Energy/Black_Energy.jpg"));
            Yellow=ImageIO.read(PlayPanel.class.getResource("/GraphicPictures/Energy/Yellow_Energy.jpg"));
        }
        catch(Exception E){
            System.out.println("Exception Error2");
            return;
        }

        FCB=new ArrayList<>();

        energyDeck=new EnergyDeck();
        six=energyDeck.topSix();

        p1=new Player(true, 1);
        p2=new Player(false, 2);
        p3=new Player(false, 3);
        p4=new Player(false, 4);
        currPlayer=new Player(false, 0);

        buttonsForResearchCards=new ArrayList<>();
        r1B=new MyButton("l1",false);
        r2B=new MyButton("l2",false);
        r3B =new MyButton("l3",false);
        // test=new MyButton("test", true);
        convertB=new MyButton("convert", false);
        pickB=new MyButton("pick", false);
        fileB=new MyButton("file", false);
        buildB=new MyButton("build", false);
        energyDeckButton=new MyButton("Edeck",false);
        redB=new MyButton("",false);
        blackB=new MyButton("",false);
        blueB=new MyButton("", false);
        yellowB=new MyButton("",false);


        energyDeckButton.addActionListener(this);
        convertB.addActionListener(this);
        pickB.addActionListener(this);
        fileB.addActionListener(this);
        buildB.addActionListener(this);
        redB.addActionListener(this);
        blackB.addActionListener(this);
        yellowB.addActionListener(this);
        blueB.addActionListener(this);
        r1B.addActionListener(this);
        r2B.addActionListener(this);
        r3B.addActionListener(this);
        //test.addActionListener(this);

        //add(test);
        add(energyDeckButton);
        add(pickB);
        add(fileB);
        add(buildB);
        add(convertB);
        add(blueB);
        add(blackB);
        add(yellowB);
        add(redB);
        add(r1B);
        add(r2B);
        add(r3B);
        label="Player 1 turn";
        //=new JLabel("Label");
        //add(label);
        //this.addKeyListener(this);


        energyButtonLst=new ArrayList<>();

        for (int i=0;i<6;i++){
            JButton eb = new MyButton(""+(i+1), false);
            eb.addActionListener(this);
            energyButtonLst.add(eb);
            add(eb);

        }

        xlst=new int[4];
        ylst=new int[4];

        playerlst=new Player[4];
        playerlst[0]=p1;
        playerlst[1]=p2;
        playerlst[2]=p3;
        playerlst[3]=p4;

        Constants.p1=p1;
        Constants.p2=p2;
        Constants.p3=p3;
        Constants.p4=p4;

        setUpLevel1();
        setUpLevel2();
        setUpLevel3();
        shuffleAll();
        setUpShow();

        DisplayCards=new Card[3][];
        DisplayCards[0]=level3Show;
        DisplayCards[1]=level2Show;
        DisplayCards[2]=level1Show;

        DisplayCardButtons=new JButton[3][];
        JButton[] arr3=new JButton[2];
        arr3[0]=new MyButton("L31",false);
        arr3[1]=new MyButton("L32",false);
        DisplayCardButtons[0]=arr3;

        JButton[] arr2=new JButton[3];
        arr2[0]=new MyButton("L21",false);
        arr2[1]=new MyButton("L22",false);
        arr2[2]=new MyButton("L23",false);
        DisplayCardButtons[1]=arr2;


        JButton[] arr1=new JButton[4];
        arr1[0]=new MyButton("L11",false);
        arr1[1]=new MyButton("L12",false);
        arr1[2]=new MyButton("L13",false);
        arr1[3]=new MyButton("L14",false);
        DisplayCardButtons[2]=arr1;

        for (JButton[] arr:DisplayCardButtons){
            for (JButton b:arr){
                b.addActionListener(this);
                add(b);
            }
        }

        p1.pick(new Energy("Blue", 0,0));
        p1.pick(new Energy("Blue", 0,0));
        p1.pick(new Energy("Red", 0,0));
        p1.pick(new Energy("Black", 0,0));
        p1.pick(new Energy("Black", 0,0));
        p1.File(new ConverterCard(1, 1, "Red", 1, "Converter", "Blue", "Any"));
        //p1.addCard(new UpgradeCard(2,3,"Black",3,"Upgrade","2_1_2"));
        //p1.addCard(new UpgradeCard(2,3,"Black",3,"Upgrade","2_1_2"));
        p2.addCard(new UpgradeCard(2,3,"Black",3,"Upgrade","2_1_2"));
        p2.addCard(new ConverterCard(1, 1, "Red", 1, "Converter", "Black", "Any"));
        p2.pick(new Energy("Black", 0, 0));
        p2.pick(new Energy("Blue",1,1));
        p2.pick(new Energy("Red",0,0));

        ConverterButtonList=new ArrayList<>();
        //.add(new JButton("0"));

        int i=0;
        int x=60;

        //label.setText("pick input energy");
        //label.repaint();
        //repaint();


    }
    public void paint(Graphics g) {
        super.paint(g);

        int inventoryWidth=getWidth()*500/1600;
        inventoryHeight=getHeight()*50/900;
        int cardWidth=getWidth()*70/1600;
        int cardHeight=getHeight()*70/900;
        under=getHeight()*70/900;


        String redt = "Red: " + red;
        String yellowt = "Yellow: " + yellow;
        String bluet = "Blue: " + blue;
        String blackt = "Black: " + black;
        String energy="ENERGY";
        String Victorypoints = "Victory Points";

        g.setColor(Color.WHITE);

        //screen
        g.drawImage(screen, 0, 0, getWidth(), getHeight(),null);


        g.drawLine(0,getHeight()/2, getWidth(), getHeight()/2);
        g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());




        //Player 1 top left
        int x=0;
        int y=0;
        xlst[0]=x;
        ylst[0]=y;

        //p1.File(new FileCard(1,1,"Blue", 1, "File", "Pick"));
        p1.draw(g, x, y, getWidth(), getHeight());
        g.drawImage(Red, x+getWidth()/127+getWidth()*19/1600, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Blue, x+getWidth()/127+getWidth()*19/1600, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Yellow, x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Black, x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900,null);




        //Player2 top right
        x=getWidth()*159/160-cardWidth-inventoryWidth;
        y=0;
        xlst[1]=x;
        ylst[1]=y;
        //p2.pick(new Energy("Blue", 0, 0));
        p2.draw(g,x,y,getWidth(),getHeight());

        g.drawImage(Red, x+getWidth()/127+getWidth()*19/1600, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Blue, x+getWidth()/127+getWidth()*19/1600, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Yellow, x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Black, x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900,null);


        //test.setBounds(0,0,200,200);

        //Player 3 bottom right
        x=getWidth()*159/160-cardWidth-inventoryWidth;
        y=getHeight()*3/5;
        xlst[2]=x;
        ylst[2]=y;

        //p3.pick(new Energy("Red", 0, 0));
        p3.draw(g,x,y,getWidth(),getHeight());

        g.drawImage(Red, x+getWidth()/127+getWidth()*19/1600, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Blue, x+getWidth()/127+getWidth()*19/1600, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Yellow, x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Black, x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900,null);



        //Player 4 bottom left
        x=0;
        y=getHeight()*3/5;
        xlst[3]=x;
        ylst[3]=y;

        //p4.addCard(new FileCard(3, 4, "Blue", 4, "File", "Draw"));
        p4.draw(g, x, y, getWidth(), getHeight());

        g.drawImage(Red, x+getWidth()/127+getWidth()*19/1600, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Blue, x+getWidth()/127+getWidth()*19/1600, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Yellow, x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900,null);
        g.drawImage(Black, x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900,null);


//------card on board----------------------------------------------------------------------

        //lv3
        int positioninterval = getWidth()*110/1600;
        int xStart=getWidth()*555/1600;
        cardWidth=getWidth()*85/1600;
        cardHeight=getHeight()*85/900;
        int l3Height = getHeight()*230/900;
        int l2Height = getHeight()*330/900;
        int l1Height = getHeight()*430/900;

        for(int i = 1; i < 3; i++) {
            level3Show[i-1].drawFront(g,xStart+positioninterval*i,l3Height,cardWidth,cardHeight);
            DisplayCardButtons[0][i-1].setBounds(xStart+positioninterval*i,l3Height,cardWidth,cardHeight);
        }
        //lv2
        for(int i = 1; i < 4; i++) {
            level2Show[i-1].drawFront(g,xStart+positioninterval*i,l2Height,cardWidth,cardHeight);
            DisplayCardButtons[1][i-1].setBounds(xStart+positioninterval*i,l2Height,cardWidth,cardHeight);
        }
        //lv1
        for(int i = 1; i < 5; i++) {
            level1Show[i-1].drawFront(g,xStart+positioninterval*i,l1Height,cardWidth,cardHeight);
            DisplayCardButtons[2][i-1].setBounds(xStart+positioninterval*i,l1Height,cardWidth,cardHeight);
        }

//-----levelcards------------------------------------------------------------------------------------------------------
        g.drawImage(lv3, xStart, l3Height, cardWidth, cardHeight, null);
        g.drawImage(lv2, xStart, l2Height, cardWidth, cardHeight, null);
        g.drawImage(lv1, xStart, l1Height, cardWidth, cardHeight, null);
        r1B.setBounds(xStart, l1Height, cardWidth, cardHeight);
        r2B.setBounds(xStart, l2Height, cardWidth, cardHeight);
        r3B.setBounds(xStart, l3Height, cardWidth, cardHeight);
//-----Energy stuff----------------------------------------------------------------------------------------------------------------------------
        xStart=getWidth()*1392/1600;
        energyDeck.draw(g,xStart,getHeight()*350/900,getWidth()*208/1600,getHeight()*168/900);
        energyDeckButton.setBounds(xStart,getHeight()*350/900,getWidth()*208/1600,getHeight()*168/900);


        xStart=getWidth()*1400/1600;
        int space=getWidth()*35/1600;
        g.setColor(new Color(168, 0, 230));
        g.fillRoundRect(xStart-7*space-20,getHeight()*435/900,5*space+60,getHeight()*30/900,getWidth()*15/1600,getHeight()*15/900);
        for (int i=0;i<six.size();i++){
            six.get(i).draw(g, xStart-space*(7-i), getHeight()*440/900, getWidth()*20/1600,getHeight()*20/900);
            energyButtonLst.get(i).setBounds(xStart-space*(7-i), getHeight()*440/900, getWidth()*20/1600,getHeight()*20/900);
        }


        if (build&&convert){
            g.setColor(Color.BLUE);
            ArrayList<Card> converters = currPlayer.getConverters();

            //int i=0;
            int xx=getWidth()*60/1600;

            for (int i=0;i<converters.size();i++) {
                //b.addActionListener(this);
                //add(b);
                //b.setBounds(getWidth()*600/1600+i*xx, getHeight()*600/900, getWidth()*50/1600, getHeight()*50/900);
                g.setFont(new Font("Sans", Font.BOLD, 30));
                FontMetrics fontMetrics=g.getFontMetrics();

                int strWidth=fontMetrics.stringWidth(""+(i+1));
                int strHeight=fontMetrics.getAscent();
                g.fillRect(getWidth()*600/1600+i*xx, getHeight()*600/900, getWidth()*50/1600, getHeight()*50/900);
                g.setColor(Color.WHITE);
                g.drawString(""+(i+1), getWidth()*600/1600+i*xx+getWidth()*25/1600-strWidth/2, getHeight()*620/900+strHeight/2);
                //b.setVisible(!pickConverter);

            }
        }

        g.setColor(Color.WHITE);
        //while (end==false){
        //for (int i=0;i<4;i++){
        if (gameEnd()){
            System.out.println("end"+end);
            game(g);
            end=curr;
            if (end==0){
                Constants.p1=p1;
                Constants.p2=p2;
                Constants.p3=p3;
                Constants.p4=p4;
                cl.show(Constants.PANEL, Constants.ENDPANELNAME);
            }
        }
        else{
            game(g);
        }
            //repaint();
        //}



    }

    public void game(Graphics g){

        curr=curr%4;
        currPlayer = playerlst[curr];
        g.setFont(new Font("Sans", Font.BOLD, 30));
        FontMetrics fontMetrics=g.getFontMetrics();
        //setText("Player "+(curr+1)+" turn");
        if (display==0){
            label="Player "+(curr+1)+" turn";
        }

        int strWidth=fontMetrics.stringWidth(label);
        //label.setBounds((getWidth()-strWidth)/2, getHeight()*40/900 ,strWidth, fontMetrics.getHeight());
        //label.paint(g);
        g.drawString(label,(getWidth()-strWidth)/2, getHeight()*40/900);
        if (label.equals("Convert?")){
            strWidth=fontMetrics.stringWidth("if no, pick card to build");
            g.drawString("if no, pick card to build",(getWidth()-strWidth)/2, getHeight()*75/900);
        }
        else if (label.equals("Pick converter card")){
            strWidth=fontMetrics.stringWidth("(1 is top card)");
            g.drawString("(1 is top card)",(getWidth()-strWidth)/2, getHeight()*75/900);
        }

        int x=xlst[curr];
        int y=ylst[curr];
        convertB.setBounds(x+getWidth()*9/160, y+under, getWidth()*9/160, inventoryHeight);
        fileB.setBounds(x+getWidth()*181/1600, y+under, getWidth()*90/1600, inventoryHeight);
        pickB.setBounds(x+getWidth()*272/1600, y+under, getWidth()*83/1600, inventoryHeight);
        buildB.setBounds(x+getWidth()*357/1600, y+under, getWidth()*83/1600, inventoryHeight);
        redB.setBounds(x+getWidth()/127+getWidth()*19/1600, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900);
        yellowB.setBounds(x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*23/900, getWidth()*22/1600, getHeight()*20/900);
        blackB.setBounds(x+getWidth()/127+getWidth()*19/1600+ getWidth()/16, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900);
        blueB.setBounds(x+getWidth()/127+getWidth()*19/1600, y+getHeight()*47/900, getWidth()*22/1600, getHeight()*20/900);

        if (bff){
            g.setColor(Color.GRAY);
            ArrayList<Card> filed = currPlayer.getFiled();

            //int i=0;
            int xx=getWidth()*60/1600;

            for (int i=0;i<filed.size();i++) {
                //b.addActionListener(this);
                //add(b);
                //b.setBounds(getWidth()*600/1600+i*xx, getHeight()*600/900, getWidth()*50/1600, getHeight()*50/900);
                g.setFont(new Font("Sans", Font.BOLD, 20));
                fontMetrics=g.getFontMetrics();

                strWidth=fontMetrics.stringWidth("FC"+(i+1));
                int strHeight=fontMetrics.getAscent();
                g.fillRect(getWidth()*600/1600+i*xx, getHeight()*600/900, getWidth()*50/1600, getHeight()*50/900);
                g.setColor(Color.WHITE);
                g.drawString("FC"+(i+1), getWidth()*600/1600+i*xx+getWidth()*25/1600-strWidth/2, getHeight()*620/900+strHeight/2);

                //b.setVisible(!pickConverter);

            }
        }

        if (l1r||l2r||l3r){
            //System.out.println("l1r");
            drawResearch(g);
        }

        if (didAction){

            curr++;
            repaint();
            didAction=false;
            resetBooleans();
        }

            //repaint();



    }

    private void shuffleAll(){
        Collections.shuffle(level1Cards);
        Collections.shuffle(level2Cards);
        Collections.shuffle(level3Cards);
    }


    private void setUpShow(){
        level1Show=new Card[4];
        level2Show=new Card[3];
        level3Show=new Card[2];

        for (int i=0;i<4;i++){
            level1Show[i]=level1Cards.remove(0);
        }

        for (int i=0;i<3;i++){
            level2Show[i]=level2Cards.remove(0);
        }

        for (int i=0;i<2;i++){
            level3Show[i]=level3Cards.remove(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int a=0;

        if (e.getSource()==pickB){
            if (!file && !build && !convert && !l1r && !l2r && !l3r){
                boolean b = currPlayer.canPick();
                System.out.println(b);
                if (b){
                    pick=true;
                    label="pick an energy";
                }
                else{
                    label="Can't pick, still player "+(curr+1)+" turn";

                }
                display=1;

                repaint();
            }

        }

        if (e.getSource()==fileB) {
            if (bof) {
                if (currPlayer.getCanFile()) {
                    int num = currPlayer.getResearchNum();
                    boolean b = currPlayer.File(researchSelected);
                    if (b) {
                        didAction = true;
                        bof = false;
                        pickr = false;
                        System.out.println("old length" + level1Cards.size());
                        if (r1bClicked) {
                            for (Card c : lst) {
                                if (r1bClicked) {
                                    level1Cards.add(c);
                                }
                            }
                            for (int i = 0; i < num; i++) {
                                level1Cards.remove(0);
                            }
                        }
                        lst = new ArrayList<>();
                        for (JButton button : buttonsForResearchCards) {
                            remove(button);
                        }
                        //buttonsForResearchCards=new ArrayList<>();
                        System.out.println("new length" + level1Cards.size());
                    } else {
                        label = "can't file!";
                        repaint();
                    }
                }
            }
            else if (!pick && !build && !convert && !l1r && !l2r && !l3r) {
                file = true;
                label="pick a card";
                display=1;
                repaint();
                System.out.println("file");
            }



        }

        if (e.getSource()==buildB){
            if (bof){
                boolean b = currPlayer.build(researchSelected);
                if (b){
                    int num=currPlayer.getResearchNum();
                    didAction=true;
                    bof=false;
                    pickr=false;
                    //System.out.println("old length"+level1Cards.size());
                    if (r1bClicked){
                        for (Card c:lst){
                            level1Cards.add(c);
                        }
                        for (int i=0;i<num;i++){
                            level1Cards.remove(0);
                        }
                    }
                    else if (r2bClicked){
                        for (Card c:lst){
                            level2Cards.add(c);
                        }
                        for (int i=0;i<num;i++){
                            level2Cards.remove(0);
                        }
                    }
                    lst=new ArrayList<>();
                    for (JButton button:buttonsForResearchCards){
                        remove(button);
                    }
                    //buttonsForResearchCards=new ArrayList<>();
                    //System.out.println("new length"+level1Cards.size());
                }
                else{

                    label="can't build!";
                    build=false;
                    repaint();
                }
            }

            else if (!pick && !file && !convert && !l1r && !l2r && !l3r){
                build=true;
                System.out.println(a);
                bff=true;
                label="Convert?";
                display=1;
                FCB=new ArrayList<>();
                int xx=getWidth()*60/1600;
                ArrayList<Card>filedCard=currPlayer.getFiled();
                for (int i=0;i<filedCard.size();i++){
                    JButton b = new MyButton("Filed Card "+(i+1), false);
                    FCB.add(b);
                }
                int i=0;
                for (JButton b:FCB){
                    b.addActionListener(this);
                    add(b);
                    b.setBounds(getWidth()*600/1600+i*xx, getHeight()*600/900, getWidth()*50/1600, getHeight()*50/900);
                    i++;
                }


                repaint();

            }

        }

        if (e.getSource()==convertB){
            if (build && !pick && !file && !l1r && !l2r && !l3r){
                convert=true;
                grab=true;
                System.out.println(1);
                int xx=getWidth()*60/1600;
                ArrayList<Card> converters = currPlayer.getConverters();
                for (int i=0;i<converters.size();i++){
                    ConverterButtonList.add(new MyButton(""+(i+1),false));
                }
                int i=0;
                for (JButton b:ConverterButtonList) {
                    b.addActionListener(this);
                    add(b);
                    b.setBounds(getWidth()*600/1600+i*xx, getHeight()*600/900, getWidth()*50/1600, getHeight()*50/900);
                    i++;
                }
                //pickBuild=false;
                label="Pick converter card";
                repaint();
            }
        }

        if (e.getSource()==r1B){
            if (!build && !convert && !pick && !file && !r1bClicked && !l2r && !l3r){
                r1bClicked=true;

                if (currPlayer.getCanResearch()){
                    lst = new ArrayList<>();
                    buttonsForResearchCards=new ArrayList<>();
                    int n = currPlayer.getResearchNum();
                    for (int i=0;i<n;i++){
                        lst.add(level1Cards.get(i));
                        JButton b = new MyButton(""+(i+1)+(curr+1), false);
                        b.addActionListener(this);
                        add(b);
                        buttonsForResearchCards.add(b);
                    }
                    l1r=true;
                    label="Pick a card";
                    display=1;
                    repaint();
                }
                else{
                    label="Player can't research!";
                    display=1;
                    repaint();
                }

            }
            System.out.println("r1B");
        }

        if (e.getSource()==r2B){
            if (!build && !convert && !pick && !file && !l1r && !r2bClicked && !l3r){
                r2bClicked=true;

                if (currPlayer.getCanResearch()){
                    lst = new ArrayList<>();
                    buttonsForResearchCards=new ArrayList<>();
                    int n = currPlayer.getResearchNum();
                    for (int i=0;i<n;i++){
                        lst.add(level2Cards.get(i));
                        JButton b = new MyButton(""+(i+1)+(curr+1), false);
                        b.addActionListener(this);
                        add(b);
                        buttonsForResearchCards.add(b);
                    }
                    l2r=true;
                    label="Pick a card";
                    display=1;
                    repaint();
                }
                else{
                    label="Player can't research!";
                    display=1;
                    repaint();
                }
            }
            System.out.println("r2B");
        }

        if (e.getSource()==r3B){
            if (!build && !convert && !pick && !file && !l1r && !l2r && !r3bClicked){
                r3bClicked=true;

                if (currPlayer.getCanResearch()){
                    lst = new ArrayList<>();
                    buttonsForResearchCards=new ArrayList<>();
                    int n = currPlayer.getResearchNum();
                    for (int i=0;i<n;i++){
                        lst.add(level3Cards.get(i));
                        JButton b = new MyButton(""+(i+1)+(curr+1), false);
                        b.addActionListener(this);
                        add(b);
                        buttonsForResearchCards.add(b);
                    }
                    l3r=true;
                    label="Pick a card";
                    display=1;
                    repaint();
                }
                else{
                    label="Player can't research!";
                    display=1;
                    repaint();
                }
            }
            System.out.println("r3B");
        }

        if (e.getSource()==energyDeckButton){
            System.out.println("EnergyDeckButton");
        }

        if(e.getSource()==redB){
            if (pickConverter&&convert&&grab){
                input=currPlayer.removeEnergy("Red");
                grab=false;
                pickOut=true;
                label="pick output energy";
                repaint();
            }
            else if (pickOut){
                output="Red";
                pickOut=false;
                System.out.println("setRed");
                ConverterCard c = (ConverterCard) converterCardPicked;
                Energy con=c.convert(input, output);
                System.out.println(c.getColor());
                currPlayer.pick(con);
                label="pick card to build now";
                repaint();
            }

            //pickOut=true;
        }

        if (e.getSource()==blueB){
            if (pickConverter&&convert&&grab){
                input=currPlayer.removeEnergy("Blue");
                System.out.println("grab blue");
                grab=false;
                pickOut=true;
                label="pick output energy";
                repaint();
            }
            else if (pickOut){
                output="Blue";
                pickOut=false;
                System.out.println("setBlue");
                ConverterCard c = (ConverterCard) converterCardPicked;
                Energy con=c.convert(input, output);
                System.out.println(c.getColor());
                currPlayer.pick(con);
                label="pick card to build now";
                repaint();
            }

        }

        if (e.getSource()==blackB){
            if (pickConverter&&convert&&grab){
                input=currPlayer.removeEnergy("Black");
                grab=false;
                pickOut=true;
                label="pick output energy";
                repaint();
            }
            else if (pickOut){
                output="Black";
                pickOut=false;
                System.out.println("setBlack");
                ConverterCard c = (ConverterCard) converterCardPicked;
                Energy con=c.convert(input, output);
                System.out.println(c.getColor());
                currPlayer.pick(con);
                label="pick card to build now";
                repaint();
            }
        }

        if (e.getSource()==yellowB){
            if (pickConverter&&convert&&grab){
                input=currPlayer.removeEnergy("Yellow");
                grab=false;
                label="pick output energy";
                pickOut=true;
                repaint();
            }
            else if (pickOut){
                output="Yellow";
                pickOut=false;
                System.out.println("Yellow");
                ConverterCard c = (ConverterCard) converterCardPicked;
                Energy con=c.convert(input, output);
                currPlayer.pick(con);
                System.out.println(c.getColor());
                label="pick card to build now";
                repaint();
            }


        }

        int ii=0;
        for (JButton b:FCB){
            if(e.getSource()==b){
                System.out.println("here "+build);
                if (build){
                    boolean b1 = currPlayer.build(currPlayer.getFiled().get(ii));
                    System.out.println("b1: "+b1);
                    if (b1){
                        didAction=true;
                        currPlayer.filedRemove(ii);
                        repaint();
                    }
                    else{
                        label="Can't build this card!";
                        display=1;
                        repaint();
                    }
                }
            }
            ii++;
        }

        int idx=0;
        ArrayList<Card> converters = currPlayer.getConverters();
        for (JButton b:ConverterButtonList) {
            if (e.getSource() == b) {
                grab = true;

                if (convert) {
                    converterCardPicked = converters.get(idx);
                    label="pick input energy";
                    repaint();
                    pickConverter=true;
                    //convert=false;
                }
            }
            idx++;
        }

        for (int i=0;i<3;i++){
            for (int j=0;j<DisplayCardButtons[i].length;j++){
                if (e.getSource()==DisplayCardButtons[i][j]){
                    if (build){
                        boolean b = currPlayer.build(DisplayCards[i][j]);
                        System.out.println(b);
                        if (!FCB.isEmpty()){
                            for (JButton button:FCB){
                                remove(button);
                            }
                        }
                        FCB=new ArrayList<>();
                        if (b){
                            didAction=true;
                            if (i==0){
                                DisplayCards[i][j]=level3Cards.remove(0);
                            }
                            else if (i==1){
                                DisplayCards[i][j]=level2Cards.remove(0);
                            }
                            else{
                                DisplayCards[i][j]=level1Cards.remove(0);
                            }
                        }
                        else if (!b){
                            display=1;
                            System.out.println("cant build");
                            label="Can't build this card!";
                            build=false;
                            repaint();

                        }

                    }
                    else if (file){
                        if (currPlayer.getCanFile()){
                            boolean b = currPlayer.File(DisplayCards[i][j]);
                            System.out.println(b);
                            if (b){
                                didAction=true;
                                if (i==0){
                                    DisplayCards[i][j]=level3Cards.remove(0);
                                }
                                else if (i==1){
                                    DisplayCards[i][j]=level2Cards.remove(0);
                                }
                                else{
                                    DisplayCards[i][j]=level1Cards.remove(0);
                                }
                            }
                            else {
                                label="Can't file!";
                                file=false;
                                repaint();
                            }
                        }
                        else{
                            label="Can't file!";
                            file=false;
                            repaint();
                        }

                    }
                    //display=0;
                    //DisplayCards[i][j]=

                    //repaint();
                }
            }
        }
        idx=0;
        for (JButton b:buttonsForResearchCards){
            if (e.getSource()==b){
                System.out.println("pickr "+pickr);
                System.out.println("pickl1r"+pickl1r);
                if (pickr&&!pickl1r){

                    researchSelected=lst.remove(idx);
                    label="build or file?";
                    display=1;
                    repaint();
                    bof=true;
                    pickr=false;
                    pickl1r=true;
                }
            }
            idx++;
        }

        for (int i=0;i<energyButtonLst.size();i++){
            if (e.getSource()==energyButtonLst.get(i)){
                System.out.println(i);
                if (pick){
                    currPlayer.pick(six.remove(i));
                    six.add(energyDeck.pickRandom());
                    didAction=true;
                    pick=false;


                }


            }

        }



    }

    public void drawResearch(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(getWidth()*590/1600, getHeight()*600/900, getWidth()*420/1600, getHeight()*200/900);

        int space =getWidth()*12/1600;
        int width = getWidth()*70/1600;
        int height = getHeight()*70/900;
        if (lst.size()>5){
            System.out.println("here");
            for (int i=0;i<5;i++){
                Card c = lst.get(i);

                //buttonsForResearchCards.get(i).addActionListener(this);
                //add(buttonsForResearchCards.get(i));
                //buttonsForResearchCards.get(i).setBounds(getWidth()*592/1600+(i+1)*space+width*i, getHeight()*620/900, width, height);
                c.drawFront(g, getWidth()*592/1600+(i+1)*space+width*i, getHeight()*620/900, width, height);
                buttonsForResearchCards.get(i).setBounds(getWidth()*592/1600+(i+1)*space+width*i, getHeight()*620/900, width, height);
            }
            for (int i=5;i<lst.size();i++){
                Card c = lst.get(i);

                //buttonsForResearchCards.get(i).addActionListener(this);
                //add(buttonsForResearchCards.get(i));
                buttonsForResearchCards.get(i).setBounds(getWidth()*592/1600+(i-4)*space+width*(i-5), getHeight()*710/900, width, height);
                System.out.println("setbounds");
                c.drawFront(g, getWidth()*592/1600+(i-4)*space+width*(i-5), getHeight()*710/900, width, height);
            }
        }
        else{
            int i=0;
            for (Card c:lst){
                System.out.println(buttonsForResearchCards.size());
                //buttonsForResearchCards.get(i).addActionListener(this);
                //add(buttonsForResearchCards.get(i));
                buttonsForResearchCards.get(i).setBounds(getWidth()*592/1600+(i+1)*space+width*i, getHeight()*620/900, width, height);
                c.drawFront(g, getWidth()*592/1600+(i+1)*space+width*i, getHeight()*620/900, width, height);
                System.out.println("setbounds1");
                i++;
            }
        }
        pickr=true;
    }



    public void resetBooleans(){
        convert=false;
        file=false;
        pick=false;
        build=false;
        didAction=false;
        grab=false;
        //pickBuild=false;
        pickOut=false;
        pickConverter=false;
        display=0;
        l1r=false;
        l2r=false;
        l3r=false;
        pickl1r=false;
        r1bClicked=false;
        r2bClicked=false;
        r3bClicked=false;
        bof=false;
        bff=false;
        if (!FCB.isEmpty()){
            for (JButton b:FCB){
                remove (b);
            }
        }
        //keepl1r=false;

        return;
    }

    public boolean gameEnd(){
        if (p1.getCardNum()>=3){
            return true;
        }
        if (p2.getCardNum()>=16){
            return true;
        }
        if (p3.getCardNum()>=16){
            return true;
        }
        if (p4.getCardNum()>=16){
            return true;
        }
        if (p1.getCardNum3()>=4){
            return true;
        }
        if (p2.getCardNum3()>=4){
            return true;
        }
        if (p3.getCardNum3()>=4){
            return true;
        }
        if (p4.getCardNum3()>=4){
            return true;
        }
        return false;
    }



    private void setUpLevel1(){
        level1Cards=new ArrayList<>();

        //level1Build
        level1Cards.add(new BuildCard(1, 1, "Red", 1, "Build", "Black", "Pick"));
        level1Cards.add(new BuildCard(1, 1, "Blue", 1, "Build", "Black", "Point"));
        level1Cards.add(new BuildCard(1, 1, "Black", 1, "Build", "Blue", "Pick"));
        level1Cards.add(new BuildCard(1, 1, "Yellow", 1, "Build", "Blue", "Point"));
        level1Cards.add(new BuildCard(1, 1, "Yellow", 1, "Build", "Red", "Pick"));
        level1Cards.add(new BuildCard(1, 1, "Black", 1, "Build", "Red", "Point"));
        level1Cards.add(new BuildCard(1, 1, "Blue", 1, "Build", "Yellow", "Pick"));
        level1Cards.add(new BuildCard(1, 1, "Red", 1, "Build", "Yellow", "Point"));

        //Level1Converter
        level1Cards.add(new ConverterCard(1, 1, "Red", 1, "Converter", "Black", "Any"));
        level1Cards.add(new ConverterCard(1, 1, "Yellow", 1, "Converter", "Black", "Any"));
        level1Cards.add(new ConverterCard(1, 1, "Red", 1, "Converter", "Blue", "Any"));
        level1Cards.add(new ConverterCard(1, 1, "Yellow", 1, "Converter", "Blue", "Any"));
        level1Cards.add(new ConverterCard(1, 1, "Black", 1, "Converter", "Red", "Any"));
        level1Cards.add(new ConverterCard(1, 1, "Blue", 1, "Converter", "Red", "Any"));
        level1Cards.add(new ConverterCard(1, 1, "Black", 1, "Converter", "Yellow", "Any"));
        level1Cards.add(new ConverterCard(1, 1, "Blue", 1, "Converter", "Yellow", "Any"));

        //Level1File
        level1Cards.add(new FileCard(1, 1, "Black",1, "File", "Pick"));
        level1Cards.add(new FileCard(1, 1, "Blue",1, "File", "Pick"));
        level1Cards.add(new FileCard(1, 1, "Red",1, "File", "Pick"));
        level1Cards.add(new FileCard(1, 1, "Yellow",1, "File", "Pick"));

        //Level1Pick
        level1Cards.add(new PickCard(1, 1, "Blue", 1, "Pick", "Black"));
        level1Cards.add(new PickCard(1, 1, "Yellow", 1, "Pick", "Black"));
        level1Cards.add(new PickCard(1, 1, "Black", 1, "Pick", "Blue"));
        level1Cards.add(new PickCard(1, 1, "Red", 1, "Pick", "Blue"));
        level1Cards.add(new PickCard(1, 1, "Blue", 1, "Pick", "Red"));
        level1Cards.add(new PickCard(1, 1, "Yellow", 1, "Pick", "Red"));
        level1Cards.add(new PickCard(1, 1, "Black", 1, "Pick", "Yellow"));
        level1Cards.add(new PickCard(1, 1, "Red", 1, "Pick", "Yellow"));

        //Level1Upgrade
        level1Cards.add(new UpgradeCard(1, 1, "Black", 1, "Upgrade", "1_0_1"));
        level1Cards.add(new UpgradeCard(1, 1, "Blue", 1, "Upgrade", "1_0_1"));
        level1Cards.add(new UpgradeCard(1, 1, "Red", 1, "Upgrade", "1_0_1"));
        level1Cards.add(new UpgradeCard(1, 1, "Yellow", 1, "Upgrade", "1_0_1"));
        level1Cards.add(new UpgradeCard(1, 1, "Black", 1, "Upgrade", "1_1_0"));
        level1Cards.add(new UpgradeCard(1, 1, "Blue", 1, "Upgrade", "1_1_0"));
        level1Cards.add(new UpgradeCard(1, 1, "Red", 1, "Upgrade", "1_1_0"));
        level1Cards.add(new UpgradeCard(1, 1, "Yellow", 1, "Upgrade", "1_1_0"));



    }

    private void setUpLevel2(){
        level2Cards=new ArrayList<>();

        //Level2Build
        level2Cards.add(new BuildCard(2,2, "Yellow", 2, "Build", "Black_Red", "Pick"));
        level2Cards.add(new BuildCard(2,3, "Blue", 3, "Build", "Black_Red", "Point"));
        level2Cards.add(new BuildCard(2,2, "Red", 2, "Build", "Blue_Black", "Pick"));
        level2Cards.add(new BuildCard(2,2, "Yellow", 2, "Build", "Blue_Black", "Pick"));
        level2Cards.add(new BuildCard(2,2, "Red", 2, "Build", "Blue_Yellow", "Pick"));
        level2Cards.add(new BuildCard(2,3, "Black", 3, "Build", "Blue_Yellow", "Point"));
        level2Cards.add(new BuildCard(2,3, "Black", 3, "Build", "File", "Pick"));
        level2Cards.add(new BuildCard(2,3, "Blue", 3, "Build", "File", "Pick"));
        level2Cards.add(new BuildCard(2,3, "Red", 3, "Build", "File", "Pick"));
        level2Cards.add(new BuildCard(2,3, "Yellow", 3, "Build", "File", "Pick"));
        level2Cards.add(new BuildCard(2,2, "Black", 2, "Build", "Red_Blue", "Pick"));
        level2Cards.add(new BuildCard(2,3, "Yellow", 3, "Build", "Red_Blue", "Pick"));
        level2Cards.add(new BuildCard(2,2, "Blue", 2, "Build", "Yellow_Black", "Pick"));
        level2Cards.add(new BuildCard(2,3, "Red", 3, "Build", "Yellow_Black", "Point"));
        level2Cards.add(new BuildCard(2,2, "Black", 2, "Build", "Yellow_Red", "Pick"));
        level2Cards.add(new BuildCard(2,2, "Blue", 2, "Build", "Yellow_Red", "Pick"));

        //Level2Converter
        level2Cards.add(new ConverterCard(2,2,"Blue",2,"Converter","Black","Any" ));
        level2Cards.add(new ConverterCard(2,3,"Red",3,"Converter","Black","Split" ));
        level2Cards.add(new ConverterCard(2,3,"Yellow",3,"Converter","Black","Split" ));
        level2Cards.add(new ConverterCard(2,2,"Black",2,"Converter","Blue","Any" ));
        level2Cards.add(new ConverterCard(2,3,"Red",3,"Converter","Blue","Split" ));
        level2Cards.add(new ConverterCard(2,3,"Yellow",3,"Converter","Blue","Split" ));
        level2Cards.add(new ConverterCard(2,2,"Yellow",2,"Converter","Red","Any" ));
        level2Cards.add(new ConverterCard(2,3,"Black",3,"Converter","Red","Split" ));
        level2Cards.add(new ConverterCard(2,3,"Blue",3,"Converter","Red","Split" ));
        level2Cards.add(new ConverterCard(2,2,"Red",2,"Converter","Yellow","Any" ));
        level2Cards.add(new ConverterCard(2,3,"Black",2,"Converter","Yellow","Split" ));
        level2Cards.add(new ConverterCard(2,3,"Blue",3,"Converter","Yellow","Split" ));

        //Level2Pick
        level2Cards.add(new PickCard(2,2,"Red",2,"Pick","Blue_Black"));
        level2Cards.add(new PickCard(2,2,"Yellow",2,"Pick","Blue_Red"));
        level2Cards.add(new PickCard(2,2,"Blue",2,"Pick","Yellow_Black"));
        level2Cards.add(new PickCard(2,2,"Black",2,"Pick","Yellow_Red"));

        //Level2Upgrade
        level2Cards.add(new UpgradeCard(2,3,"Black",3,"Upgrade","2_1_2"));
        level2Cards.add(new UpgradeCard(2,3,"Blue",3,"Upgrade","2_1_2"));
        level2Cards.add(new UpgradeCard(2,3,"Red",3,"Upgrade","2_1_2"));
        level2Cards.add(new UpgradeCard(2,3,"Yellow",3,"Upgrade","2_1_2"));




    }


    private void setUpLevel3(){
        level3Cards=new ArrayList<>();

        //Level3Build
        level3Cards.add(new BuildCard(3,5,"Yellow",5,"Build","Black_Red","File"));
        level3Cards.add(new BuildCard(3,6,"Yellow",6,"Build","Blue_Black","Level1"));
        level3Cards.add(new BuildCard(3,7,"Red",7,"Build","Blue_Black","Research"));
        level3Cards.add(new BuildCard(3,5,"Black",5,"Build","Blue_Yellow","File"));
        level3Cards.add(new BuildCard(3,5,"Red",5,"Build","File","Point"));
        level3Cards.add(new BuildCard(3,5,"Yellow",5,"Build","File","Point"));
        level3Cards.add(new BuildCard(3,6,"Black",6,"Build","Level2","Pick"));
        level3Cards.add(new BuildCard(3,6,"Red",6,"Build","Level2","Pick"));
        level3Cards.add(new BuildCard(3,5,"Black",5,"Build","Red_Blue","Point"));
        level3Cards.add(new BuildCard(3,5,"Red",5,"Build","Yellow_Black","Point"));
        level3Cards.add(new BuildCard(3,6,"Blue",6,"Build","Yellow_Red","Level1"));
        level3Cards.add(new BuildCard(3,7,"Blue",7,"Build","Yellow_Red","Research"));

        //Level3Converter
        level3Cards.add(new ConverterCard(3,4,"Red",4,"Converter","Any","Any"));
        level3Cards.add(new ConverterCard(3,4,"Yellow",4,"Converter","Any","Any"));
        level3Cards.add(new ConverterCard(3,5,"Blue",5,"Converter","Black_Red","Split"));
        level3Cards.add(new ConverterCard(3,5,"Black",5,"Converter","Blue_Yellow","Split"));

        //Level3File
        level3Cards.add(new FileCard(3,4,"Blue",4,"File","Draw"));
        level3Cards.add(new FileCard(3,4,"Yellow",4,"File","Draw"));
        level3Cards.add(new FileCard(3,4,"Black",4,"File","Point"));
        level3Cards.add(new FileCard(3,4,"Red",4,"File","Point"));

        //Level3Upgrade
        level3Cards.add(new UpgradeCard(3,5,"Blue",5,"Upgrade","Minus1_File"));
        level3Cards.add(new UpgradeCard(3,5,"Red",5,"Upgrade","Minus1_File"));
        level3Cards.add(new UpgradeCard(3,5,"Blue",5,"Upgrade","Minus1_Level2"));
        level3Cards.add(new UpgradeCard(3,5,"Yellow",5,"Upgrade","Minus1_Level2"));
        level3Cards.add(new UpgradeCard(3,6,"Black",6,"Upgrade","Minus1_Research"));
        level3Cards.add(new UpgradeCard(3,6,"Yellow",6,"Upgrade","Minus1_Research"));
        level3Cards.add(new UpgradeCard(3,7,"Blue",4,"Upgrade","No_File"));
        level3Cards.add(new UpgradeCard(3,7,"Red",4,"Upgrade","No_File"));
        level3Cards.add(new UpgradeCard(3,8,"Black",4,"Upgrade","No_Research"));
        level3Cards.add(new UpgradeCard(3,8,"Yellow",4,"Upgrade","No_Research"));
        level3Cards.add(new UpgradeCard(3,-2,"Any",7,"Upgrade","Plus_VicTokens"));
        level3Cards.add(new UpgradeCard(3,-1,"Any",7,"Upgrade","Plus_Energy"));
        level3Cards.add(new UpgradeCard(3,-2,"Any",7,"Upgrade","Plus_VicTokens"));
        level3Cards.add(new UpgradeCard(3,-1,"Any",7,"Upgrade","Plus_Energy"));
        level3Cards.add(new UpgradeCard(3,4,"Black",4,"Upgrade","Plus4"));
        level3Cards.add(new UpgradeCard(3,4,"Blue",4,"Upgrade","Plus4"));





    }






}
