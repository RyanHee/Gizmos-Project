package GameComponents;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ConverterCard extends Card{
    private ArrayList<String>inpt;
    private String outpt;
    private ArrayList<String>usedColor;
    private boolean canConvert;
    private BufferedImage frontImg, backImg;

    public ConverterCard(int l, int v, String c, int cc, String t, String i, String o){
        super(l,v,c,cc,t);
        inpt = new ArrayList<>();
        usedColor=new ArrayList<>();
        String[]lst = i.split("_");
        for (String s:lst){
            inpt.add(s);
        }
        outpt=o;
        canConvert=true;

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

    public String getOutpt(){
        return outpt;
    }

    public void reset(){
        canConvert=true;
        usedColor=new ArrayList<>();
    }
    private boolean canConvert(Energy e, String color){
        if (!canConvert){
            return false;

        }
        if (inpt.contains(e.getColor())){
            if (outpt.contains(color)||outpt.contains("Any")){
                return true;
            }
        }
        return false;
    }
    public Energy convert11(Energy e, String color){
            if (canConvert(e,color)){
                canConvert=false;
                return new Energy(color,e.getX(),e.getY());
            }


        return null;
    }

    public Energy convert12(Energy e, String color){
        if (usedColor.contains(e.getColor())&&usedColor.size()==2){
            return null;
        }
        if (inpt.contains(e.getColor())){
            usedColor.add(e.getColor());
            return new Energy(color,e.getX(),e.getY());
        }
        return null;
    }
    public ArrayList<Energy> convert22(Energy e){
        if (inpt.contains(e.getColor())){
            ArrayList<Energy> ret=new ArrayList<>();
            ret.add(new Energy(e.getColor(),0,0));
            ret.add(new Energy(e.getColor(),0,0));
            canConvert=false;
            return ret;
        }
        return new ArrayList<>();
    }


    public Energy convert13(Energy e, String s){
        if (!canConvert){
            return null;
        }
        canConvert=false;
        return new Energy(s,0,0);
    }

    public ArrayList<Energy> convert23(Energy e){
        if (usedColor.contains(e.getColor())){
            return new ArrayList<>();
        }
        if (inpt.contains(e.getColor())){
            usedColor.add(e.getColor());
            ArrayList<Energy> ret=new ArrayList<>();
            ret.add(new Energy(e.getColor(),0,0));
            ret.add(new Energy(e.getColor(),0,0));
            return ret;
        }
        return new ArrayList<>();
    }



}
