package GameComponents;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Card {

    private int level;
    private int value;
    private int cost;
    private String color;
    private int x;
    private int y;
    private String builtOrigin;
    private String type;

    private BufferedImage frontImg, backImg;


    public Card(int l, int v, String c, int cc,  String t){
        level=l;
        value=v;
        color=c;
        cost=cc;
        type=t;
        builtOrigin="none";


    }

    public int getValue(){
        return value;
    }

    public int getLevel(){
        return level;
    }

    public String getColor(){
        return color;
    }

    public int getCost(){
        return cost;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getType(){
        return type;
    }

    public String getBuiltOrigin(){
        return builtOrigin;
    }
    public void setX(int xx){
        x=xx;
    }

    public void setY(int yy){
        y=yy;
    }

    public void setOrig(String s){
        builtOrigin=s;
    }

    public boolean canBuild(Player p){

        return false;
    }

    public void setFrontImg(BufferedImage b){
        frontImg=b;
    }

    public void setBackImg(BufferedImage b){
        backImg=b;
    }
    public void drawFront(Graphics g, int x, int y, int width, int height){
        g.drawImage(frontImg, x, y, width, height, null);
    }

    public void drawBack(Graphics g, int x, int y, int width, int height){
        g.drawImage(backImg, x, y, width, height, null);
    }

}
