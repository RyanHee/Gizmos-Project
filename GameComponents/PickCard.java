package GameComponents;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class PickCard extends Card{

    private ArrayList<String> requireColor;
    private BufferedImage frontImg, backImg;
    public PickCard(int l, int v, String c, int cc, String t, String r){
        super(l,v,c,cc,t);
        String[] lst = r.split("_");
        requireColor=new ArrayList<>();
        requireColor.addAll(Arrays.asList(lst));

        try{
            frontImg = ImageIO.read(PickCard.class.getResource("/GraphicPictures/Card/Level"+super.getLevel()+"/Pick/"+r+"_"+super.getColor()+".jpg"));
            backImg = ImageIO.read(PickCard.class.getResource("/GraphicPictures/Card/BackCard/Level"+super.getLevel()+".jpg"));
            super.setFrontImg(frontImg);
            super.setBackImg(backImg);
        }
        catch (Exception E){
            System.out.println("Pick Card Exception");
        }
    }

    public boolean Effect(Energy e){
        if (requireColor.contains(e.getColor())){
            return true;
        }
        return false;
    }

}
