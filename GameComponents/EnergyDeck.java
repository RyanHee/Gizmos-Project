package GameComponents;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicFormattedTextFieldUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
public class EnergyDeck {
    private ArrayList<Energy> deck;
    private BufferedImage img;

    public EnergyDeck(){
        deck=new ArrayList<>();
        for (int i=0;i<13;i++){
            deck.add(new Energy("Blue", 0, 0));
            deck.add(new Energy("Red", 0, 0));
            deck.add(new Energy("Yellow", 0, 0));
            deck.add(new Energy("Black", 0, 0));
        }
        Collections.shuffle(deck);

        try{
            img = ImageIO.read(EnergyDeck.class.getResource("/GraphicPictures/Energy/EnergyDispenser.jpg"));
        }
        catch (Exception E){
            System.out.println("Energy deck exception");
        }
    }



    public Energy pickRandom(){
        int n=deck.size();
        double idx = Math.random()*n;
        int index = (int)idx;
        Energy e = deck.remove(index);
        return e;
    }

    public void addEnergy(Energy e){
        deck.add(e);
    }
    public ArrayList<Energy> topSix(){
        Energy[]lst = new Energy[6];

        for (int i=0;i<6;i++){
            lst[i]=pickRandom();
        }
        ArrayList<Energy>list=new ArrayList<>();
        Collections.addAll(list,lst);
        return list;
    }
    public void draw(Graphics g, int x, int y, int width, int height){
        g.drawImage(img,x,y,width,height,null);
    }

    public String toString(){
        return deck.toString();
    }
}
