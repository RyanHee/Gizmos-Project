package GameComponents;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UpgradeCard extends Card{

        String upgrade;
        private BufferedImage frontImg;
        private BufferedImage backImg;
        public UpgradeCard(int l, int v, String c, int cc, String t, String u){

            super(l,v,c,cc,t);
            upgrade=u;

            try{
                frontImg = ImageIO.read(UpgradeCard.class.getResource("/GraphicPictures/Card/Level"+super.getLevel()+"/Upgrade/"+upgrade+"_"+super.getColor()+".jpg"));
                backImg = ImageIO.read(UpgradeCard.class.getResource("/GraphicPictures/Card/BackCard/Level"+super.getLevel()+".jpg"));
                super.setFrontImg(frontImg);
                super.setBackImg(backImg);
            }
            catch (Exception E){
                System.out.println("Upgrade Card Exception "+"/GraphicPictures/Card/Level"+super.getLevel()+"/Upgrade/"+upgrade+"_"+super.getColor()+".jpg");
            }

        }


        public String getUpgrade(){
            return upgrade;
        }






}
