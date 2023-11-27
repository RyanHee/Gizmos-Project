package GameComponents;

import Panels.PlayPanel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class FileCard extends Card{
    private String effect;
    private BufferedImage frontImg, backImg;
    public FileCard(int l, int v, String c, int cc, String t, String e){
        super(l,v,c,cc, t);
        effect=e;
        //System.out.println("GraphicPictures/Card/Level"+super.getLevel()+"/"+effect+"_"+super.getColor()+".jpg");
        try{
            if (super.getLevel()==0){
                frontImg=ImageIO.read(FileCard.class.getResource("/GraphicPictures/Card/Level"+super.getLevel()+"/"+effect+"_"+super.getColor()+".jpg"));

            }
            else{
                frontImg = ImageIO.read(FileCard.class.getResource("/GraphicPictures/Card/Level"+super.getLevel()+"/File/"+effect+"_"+super.getColor()+".jpg"));
                backImg = ImageIO.read(FileCard.class.getResource("/GraphicPictures/Card/BackCard/Level"+super.getLevel()+".jpg"));
            }


        }
        catch (Exception E){
            System.out.println("File Card Exception");
        }
        super.setFrontImg(frontImg);
        super.setBackImg(backImg);
    }

    public String getEffect(){
        return effect;
    }


}
