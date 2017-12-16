/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author souliern
 */
public class VueRegle {
    
    private final JFrame window;
    
    public VueRegle() {

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
        
        JPanel hautPanel = new JPanel();
        mainPanel.add(hautPanel, BorderLayout.NORTH);
     
        JLabel titreVue = new JLabel("Règles de jeu");
        titreVue.setFont(new Font(titreVue.getFont().getName(), titreVue.getFont().getStyle(), (int) (titreVue.getFont().getSize() * 2)));
        hautPanel.add(titreVue);
        
        JPanel centrePanel = new JPanel(new GridLayout(3,3));
        mainPanel.add(centrePanel, BorderLayout.CENTER);
        
        for(int i = 0; i<9; i++){
            if (i == 4){
                JPanel panelBouton = new JPanel(new GridLayout(4,1));
                centrePanel.add(panelBouton);
                for(int j = 0; j<4; j++){
                    if (j == 0){
                        JButton btnRapide = new JButton("Partie rapide");
                        panelBouton.add(btnRapide);
                    } else {
                        JLabel label = new JLabel() ;
                        panelBouton.add(label);
                    }
                }
            } else {
                JLabel label = new JLabel() ;
                centrePanel.add(label);
            }   
        }

    }
    
    public void afficher(){
        this.window.setVisible(true);
    }
}

