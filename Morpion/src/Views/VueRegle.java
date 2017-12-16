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
    CardLayout cl = new CardLayout();
    JPanel onglet = new JPanel();
    //Liste des onglets avec un nom associé
    String[] listeOnglets = {"RegleMorpion", "RegleTournoi"};
    public VueRegle() {

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1280,720);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Règles");

        JPanel regleMorpion = new JPanel(new BorderLayout());
        JPanel regleTournoi = new JPanel(new BorderLayout());
        JPanel boutonPanel = new JPanel();

        boutonPanel.setPreferredSize(new Dimension(window.getWidth(), 40));
        JButton boutonMorpion = new JButton("Règle d'une partie de morpion");
        boutonMorpion.setPreferredSize(new Dimension(300, 35));
        boutonMorpion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                cl.show(onglet, listeOnglets[0]); // 0 = Onglet Règle d'une partie de morpion
            }
        });


        JButton boutonTournoi = new JButton("Règle d'un tournoi");
        boutonTournoi.setPreferredSize(new Dimension(300, 35));
        boutonTournoi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                cl.show(onglet, listeOnglets[1]); // 1 = Onglet Règle d'un tournoi
            }
        });

        boutonPanel.add(boutonMorpion);
        boutonPanel.add(boutonTournoi);

        onglet.setLayout(cl);
        onglet.add(regleMorpion, listeOnglets[0]);
        onglet.add(regleTournoi, listeOnglets[1]);

        window.add(boutonPanel, BorderLayout.NORTH);
        window.add(onglet, BorderLayout.CENTER);

        JButton boutonRetour = new JButton("Retour");
        boutonRetour.setPreferredSize(new Dimension(window.getWidth(),50));
        window.getContentPane().add(boutonRetour, BorderLayout.SOUTH);

        // ------------------------------------
        // Onglet Règle d'une partie de Morpion
        JLabel titreRegleM = new JLabel("Règle d'une partie de Morpion", SwingConstants.CENTER);
        titreRegleM.setFont(new Font("Comic Sans MS", titreRegleM.getFont().getStyle(), (titreRegleM.getFont().getSize() * 2)));
        regleMorpion.add(titreRegleM, BorderLayout.NORTH);

        // ------------------------------------
        // Onglet Règle d'un Tournoi
        JLabel titreRegleT = new JLabel("Règle d'un Tournoi", SwingConstants.CENTER);
        titreRegleT.setFont(new Font(titreRegleT.getFont().getName(), titreRegleT.getFont().getStyle(), (titreRegleT.getFont().getSize() * 2)));
        regleTournoi.add(titreRegleT, BorderLayout.NORTH);

    }

    public void afficher(){
        this.window.setVisible(true);
    }
}

