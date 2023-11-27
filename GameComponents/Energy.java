package GameComponents;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Energy {
    private String color;
    private int x;
    private int y;
    private BufferedImage img;

    public Energy(String c, int xx, int yy){
        color=c;
        x=xx;
        y=yy;
        try{
            img= ImageIO.read(Energy.class.getResource("/GraphicPictures/Energy/"+color+"_Energy.jpg"));
        }
        catch (Exception E){
            System.out.println("Energy exception");
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String getColor(){
        return color;
    }
    public void draw(Graphics g, int x, int y, int width, int height){
        g.drawImage(img, x, y, width, height, null);
    }
}
