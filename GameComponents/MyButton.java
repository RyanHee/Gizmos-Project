package GameComponents;

import javax.swing.*;

public class MyButton extends JButton {
    private boolean visible;

    public MyButton(String s, boolean b){
        super(s);
        if (b==false){
            setBorderPainted(b);
            setContentAreaFilled(b);
            setOpaque(b);
        }

    }

    public void changeVisible(boolean b){
        visible = b;
        setBorderPainted(b);
        setContentAreaFilled(b);
        setOpaque(b);
    }
}
