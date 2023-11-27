package GameComponents;

import Panels.PlayPanel;
import com.sun.source.tree.Tree;

import javax.imageio.ImageIO;
import javax.swing.tree.ExpandVetoException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class Player {
    private TreeMap<String, ArrayList<Card>> inventory;
    private ArrayList<UpgradeCard> Upgrade;
    private ArrayList<ConverterCard> Converter;
    private ArrayList<FileCard> File;
    private ArrayList<PickCard> Pick;
    private ArrayList<BuildCard> Build;

    private ArrayList<Card> Storage;
    private int maxEnergy;
    private int maxFile;
    private int researchNum;
    private int cardNum;
    private int cardNum3;
    private int score;
    private int FileBuiltDiscount;
    private int ResearchBuiltDiscount;
    private int Level2BuildDiscount;

    private boolean start;
    private BufferedImage inventoryImg;
    private TreeMap<String, ArrayList<Energy>> energyStorage;
    private TreeMap<Integer, String> map;
    private TreeMap<String, Integer>reversemap;
    private boolean canResearch;
    private boolean canFile;




    public Player(boolean s){
        inventory=new TreeMap<>();
        map=new TreeMap<>();
        reversemap=new TreeMap<>();

        Upgrade=new ArrayList<>();
        Build=new ArrayList<>();
        Converter=new ArrayList<>();
        File=new ArrayList<>();
        Pick=new ArrayList<>();

        inventory.put("1Upgrade", new ArrayList<Card>());
        inventory.put("2Converter", new ArrayList<Card>());
        inventory.put("3File", new ArrayList<Card>());
        inventory.put("4Pick", new ArrayList<Card>());
        inventory.put("5Build", new ArrayList<Card>());
        inventory.put("6Storage", new ArrayList<Card>());
        inventory.get("3File").add(new FileCard(0, 0, "White", 0,"File", "Pick"));

        map.put(1, "Upgrade");
        map.put(2, "Converter");
        map.put(3, "File");
        map.put(4, "Pick");
        map.put(5, "Build");
        map.put(6, "Storage");

        reversemap.put("Upgrade", 1);
        reversemap.put("Converter", 2);
        reversemap.put("File", 3);
        reversemap.put("Pick", 4);
        reversemap.put("Build", 5);
        reversemap.put("Storage", 6);

        //inventory.get("1Upgrade").add(new UpgradeCard(1, 1, "Black", 1,  "Upgrade", "1_0_1"));
        //inventory.get("2Converter").add(new ConverterCard(1, 1, "Red", 1, "Converter", "Black", "Any"));
        //inventory.get("3File").add(new FileCard(1, 1, "Black", 1, "File", "Pick"));
        //inventory.get("4Pick").add(new PickCard(2, 2, "Blue", 2, "Pick", "Yellow_Black"));
        //inventory.get("5Build").add(new BuildCard(3, 5, "Yellow", 5, "Build", "Black_Red", "File"));


        FileBuiltDiscount=0;
        ResearchBuiltDiscount=0;
        Level2BuildDiscount=0;
        maxEnergy=5;
        maxFile=1;
        researchNum=3;
        cardNum=1;
        cardNum3=0;
        score=0;
        start=s;
        energyStorage=new TreeMap<String, ArrayList<Energy>>();
        canResearch=true;
        canFile=true;

        energyStorage.put("Red", new ArrayList<Energy>());
        energyStorage.put("Yellow", new ArrayList<Energy>());
        energyStorage.put("Black", new ArrayList<Energy>());
        energyStorage.put("Blue", new ArrayList<Energy>());



        try{
            if (start){
                inventoryImg = ImageIO.read(Player.class.getResource("/GraphicPictures/Player/FirstPlayer.jpg")) ;
            }
            else{
                inventoryImg = ImageIO.read(Player.class.getResource("/GraphicPictures/Player/OtherPlayer.jpg")) ;
            }
        }
        catch (Exception e){
            System.out.println("Player exception error");
        }
    }

    public Energy removeEnergy(String s){
        if (energyStorage.get(s).size()>0){
            return energyStorage.get(s).remove(0);
        }
        return null;
    }

    public void draw(Graphics g, int x, int y, int width, int height){

        int inventoryWidth=width*500/1600;
        int inventoryHeight=height*50/900;
        int cardWidth=width*70/1600;
        int cardHeight=height*70/900;
        int under=height*70/900;


        String redt = ": "+energyStorage.get("Red").size();
        String yellowt = ": " + energyStorage.get("Yellow").size();
        String bluet = ": " + energyStorage.get("Blue").size();
        String blackt = ": " + energyStorage.get("Black").size();
        String energy="ENERGY";
        String Victorypoints = "Victory Points";

        g.setColor(Color.WHITE);

        //inventory
        g.drawImage(inventoryImg, x,  y+under,inventoryWidth, inventoryHeight,null);

        //file card
        //g.drawImage(placehold, x+inventoryWidth, y+under, cardWidth, cardHeight, null);

        //StartCard


        g.setFont(new Font("Sans", Font.BOLD, 20));

        //Energy
        g.drawString(energy, x+width/127, y+height*22/900);
        g.drawString(redt, x+width/127+width*40/1600, y+height*42/900);
        g.drawString(bluet, x+width/127+width*40/1600, y+height*62/900);
        g.drawString(yellowt, x+width/127 + width/16+width*40/1600, y+height*42/900);
        g.drawString(blackt, x+width/127 + width/16+width*40/1600, y+height*62/900);

        //Victory Points
        g.drawString(Victorypoints, x+width/6, y+height*20/900);
        g.drawString(""+calculateScore(), x+width/6, y+height*50/900);

        getCardNum();
        getCardNum3();
        //CardNum
        g.drawString("Cards: " + cardNum, x+width*430/1600, y+height*20/900);
        g.drawString("Lvl 3 Cards: " + cardNum3, x+width*430/1600, y+height*50/900);

        //Cards
        int[]xpluslst=new int[5];
        int n=width/180;
        int xplus=1;

        xpluslst[0]=0;
        xpluslst[1]=width*20/1600;
        xpluslst[2]=width*45/1600;
        xpluslst[3]=width*62/1600;
        xpluslst[4]=width*78/1600;

        for (int i=0;i<5;i++){
            String s = map.get(i+1);
            s=(i+1)+s;
            int down=0;
            xplus=xpluslst[i];
            for (Card c: inventory.get(s)){
                c.drawFront(g, x+n+xplus+cardWidth*i, y+under+inventoryHeight+down, cardWidth, cardHeight);
                down+=height*17/900;
            }
            //xplus+=3;
        }

        //Filed Cards
        int down=0;
        for (Card c:inventory.get("6Storage")){
            c.drawFront(g, x+inventoryWidth+width*5/1600, y+under+down, cardWidth, cardHeight);
            //down+=cardHeight+height*5/900;
            down+=height*10/900;
        }
        //g.drawImage(placehold, x+inventoryWidth, y+under, cardWidth, cardHeight, null);
    }

    public void filedRemove(int idx){
        if (idx<inventory.get("6Storage").size()){
            inventory.get("6Storage").remove(idx);
        }
    }
    public ArrayList<Card> getFiled() {
        return inventory.get("6Storage");
    }

    public ArrayList<Card> getConverters(){

        return inventory.get("2Converter");
    }
    public void setCanResearch(boolean b){
        canResearch=b;
    }

    public void setCanFile(boolean b){
        canFile=b;
    }
    public int getMaxEnergy(){
        return maxEnergy;
    }

    public int getResearchNum(){
        return researchNum;
    }
    public int getMaxFile(){
        return maxFile;
    }
    public int getCardNum(){
        int result=0;
        for (int i=1;i<6;i++){
            String s=""+i+map.get(i);
            result+=inventory.get(s).size();
        }
        cardNum=result;
        return cardNum;
    }
    public int getCardNum3(){
        int result=0;
        for (int i=1;i<6;i++){
            String s=""+i+map.get(i);
            for (Card c:inventory.get(s)){
                if (c.getLevel()==3){
                    result++;
                }
            }
        }
        cardNum3=result;
        return cardNum3;
    }

    public int getScore(){
        return score;
    }

    public boolean getCanResearch(){
        return canResearch;
    }

    public boolean getCanFile(){
        return canFile;
    }

    public TreeMap<String, ArrayList<Energy>> getEnergyStorage(){
        return energyStorage;
    }

    public void discount(String s, int n){
        if (s.equals("File")){
            FileBuiltDiscount+=n;
        }
        else if (s.equals("Research")){
            ResearchBuiltDiscount+=n;
        }
        else{
            Level2BuildDiscount+=n;
        }
    }

    public void upgradeStats(Card c){

        UpgradeCard uc=(UpgradeCard) c;
        String[] up = uc.getUpgrade().split("_");
        if (c.getLevel()<3){


            int eplus = Integer.parseInt(up[0]);
            int fplus = Integer.parseInt(up[1]);
            int rplus = Integer.parseInt(up[2]);
            maxEnergy+=eplus;
            maxFile+=fplus;
            researchNum+=rplus;
        }
        else{
            if (up[0].equals("Minus1")){
                this.discount(up[1], 1);
            }
            else if (up[0].equals("No")){
                if (up[1].equals("File")){
                    canFile=false;
                }
                else if (up[1].equals("Research")){
                    canResearch=false;
                }
            }

            else if (up[0].equals("Plus4")){
                maxEnergy+=4;
            }
        }

    }

    public boolean canPick(){
        int size=0;
        size+=energyStorage.get("Red").size();
        size+=energyStorage.get("Blue").size();
        size+=energyStorage.get("Black").size();
        size+=energyStorage.get("Yellow").size();
        if (size>=maxEnergy){
            return false;
        }
        return true;
    }

    public boolean pick(Energy e){
        int size=0;
        size+=energyStorage.get("Red").size();
        size+=energyStorage.get("Blue").size();
        size+=energyStorage.get("Black").size();
        size+=energyStorage.get("Yellow").size();
        if (size>=maxEnergy){
            return false;
        }
        energyStorage.get(e.getColor()).add(e);
        return true;
    }

    public boolean File(Card c){
        if (inventory.get("6Storage").size()>=maxFile){
            return false;
        }
        inventory.get("6Storage").add(c);
        return true;
    }

    private int calculateScore(){
        Set<String> keys = inventory.keySet();
        boolean addEnergyNum=false;
        boolean Mult2 = false;
        Iterator<String> iter = keys.iterator();
        int ret=0;
        while (iter.hasNext()){
            String s =iter.next();
            ArrayList<Card> lst =inventory.get(s);
            for (Card c:lst){
                if (c.getValue()==-1){
                    addEnergyNum=true;
                }
                else if (c.getValue()==-2){
                    Mult2=true;
                }
                else{
                    ret+=c.getValue();
                }
            }
        }
        if (addEnergyNum){
            ret+=energyStorage.size();
        }
        if (Mult2){
            ret+=ret;
        }
        return ret;
    }

    public boolean build(Card c){
        int x = c.getCost();
        if (c.getBuiltOrigin().equals("File")){
            x-=FileBuiltDiscount;
        }
        else if (c.getBuiltOrigin().equals("Research")){
            x-=ResearchBuiltDiscount;
        }
        if (c.getLevel()==2){
            x-=Level2BuildDiscount;
        }

        if (c.getColor().equals("Any")){

        }

        else if (energyStorage.get(c.getColor()).size()>=x){

            while (x>0){
                energyStorage.get(c.getColor()).remove(0);
                x--;
            }
            inventory.get(reversemap.get(c.getType())+c.getType()).add(c);
            if (c.getType().equals("Upgrade")){
                upgradeStats(c);
            }
            return true;
        }
        return false;

    }

    public void addCard(Card c){
        inventory.get(reversemap.get(c.getType())+c.getType()).add(c);
        if (c.getType().equals("Upgrade")){
            upgradeStats(c);
        }
    }
}
