package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenu {

    private final JFrame window;
    JButton btnPartieRapide;
    /*private final JButton btnTournoi;
    private final JButton btnFermer;
    private final JButton btnRegles; */

    public VueMenu() {

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1280,720);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Jeu du Morpion");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        JPanel hautPanel = new JPanel();
        mainPanel.add(hautPanel, BorderLayout.NORTH);
     
        JLabel titreVue = new JLabel("Menu");
        titreVue.setFont(new Font(titreVue.getFont().getName(), titreVue.getFont().getStyle(), (int) (titreVue.getFont().getSize() * 2)));
        hautPanel.add(titreVue);
        
        JButton btnPartieRapide = new JButton("Partie rapide");
        mainPanel.add(btnPartieRapide, BorderLayout.SOUTH);

    }


    public void afficher(){
        this.window.setVisible(true);
    }



}
