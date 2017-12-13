package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenu {

    private final JFrame window;
    private final JButton btnPartieRapide;
    /*private final JButton btnTournoi;
    private final JButton btnFermer;
    private final JButton btnRegles; */

    public VueMenu() {

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1280,720);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);

        /* public void paintComponent(Graphics graphics){
            graphics.drawString("Jeu de morpion",10,20);
        } */

        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);

        JButton btnPartieRapide = new JButton("Partie rapide");
        mainPanel.add(btnPartieRapide, BorderLayout.SOUTH);

    }


    public void afficher(){
        this.window.setVisible(true);
    }




}
