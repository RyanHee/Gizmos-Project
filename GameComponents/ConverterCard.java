package GameComponents;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ConverterCard extends Card{
    private ArrayList<String>inpt;
    private String outpt;
    private BufferedImage frontImg, backImg;

    public ConverterCard(int l, int v, String c, int cc, String t, String i, String o){
        super(l,v,c,cc,t);
        inpt = new ArrayList<>();
        String[]lst = i.split("_");
        for (String s:lst){
            inpt.add(s);
        }
        outpt=o;


        try{
            frontImg = ImageIO.read(ConverterCard.class.getResource("/GraphicPictures/Card/Level"+super.getLevel()+"/Converter/"+i+"_"+o+"_"+super.getColor()+".jpg"));
            backImg = ImageIO.read(ConverterCard.class.getResource("/GraphicPictures/Card/BackCard/Level"+super.getLevel()+".jpg"));
            super.setFrontImg(frontImg);
            super.setBackImg(backImg);
        }
        catch (Exception E){
            System.out.println("Converter Card Exception");
        }


    }

    public ArrayList<String> getInpt(){
        return inpt;
    }

    private boolean canConvert(Energy e, String color){
        if (inpt.contains(e.getColor())){
            if (outpt.contains(color)||outpt.contains("Any")){
                return true;
            }
        }
        return false;
    }
    public Energy convert(Energy e, String color){
        if (canConvert(e,color)){
            return new Energy(color,e.getX(),e.getY());
        }
        return null;
    }


}
