package Views;

import javax.swing.*;
import java.awt.*;

public class VueOptionTournoi {

    private final JFrame window;

    public VueOptionTournoi(){

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1280,720);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Jeu du Morpion");

        //Construction



    }

    public void afficher(){
        this.window.setVisible(true);
    }
}
