package GameComponents;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class BuildCard extends Card{

    private ArrayList<String> require;
    private String effect;
    private BufferedImage frontImg, backImg;

    public BuildCard(int l, int v, String c, int cc, String t, String r, String e){
        super(l,v,c,cc,t);
        String[] lst = r.split("_");
        require=new ArrayList<>();
        require.addAll(Arrays.asList(lst));
        effect=e;

        try{
            frontImg = ImageIO.read(BuildCard.class.getResource("/GraphicPictures/Card/Level"+super.getLevel()+"/Build/"+r+"_"+effect+"_"+super.getColor()+".jpg"));

            backImg = ImageIO.read(BuildCard.class.getResource("/GraphicPictures/Card/BackCard/Level"+super.getLevel()+".jpg"));
            super.setFrontImg(frontImg);
            super.setBackImg(backImg);
        }
        catch (Exception E){
            System.out.println("Build Card Exception "+"/GraphicPictures/Card/Level"+super.getLevel()+"/Build/"+r+"_"+effect+"_"+super.getColor()+".jpg");
        }

    }

    public String effect(Card c){
        if (require.get(0).equals("File")){
            if (c.getBuiltOrigin().equals("File")){
                return effect;
            }
        }
        else if (require.get(0).equals("Level2")){
            if (c.getLevel()==2){
                return effect;
            }
        }
        else {
            if (require.contains(c.getColor())){
                return effect;
            }
        }
        return "none";
    }

    public void drawFront(Graphics g, int x, int y, int width, int height){

        super.drawFront(g, x, y, width, height);
    }

    public void drawBack(Graphics g, int x, int y, int width, int height){
        super.drawBack(g, x, y, width, height);
    }
}
