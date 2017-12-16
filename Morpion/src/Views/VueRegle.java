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

        boutonPanel.setPreferredSize(new Dimension(window.getWidth(), 50));
        JButton boutonMorpion = new JButton("Règle du jeu");
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
        JLabel titreRegleM = new JLabel("Règle du jeu", SwingConstants.CENTER);
        titreRegleM.setFont(new Font("Comic Sans MS", titreRegleM.getFont().getStyle(), (titreRegleM.getFont().getSize() * 2)));
        regleMorpion.add(titreRegleM, BorderLayout.NORTH);

        JPanel descRegleM = new JPanel(new GridLayout(1,3));
        regleMorpion.add(descRegleM, BorderLayout.CENTER);
        for(int i = 0; i < 3; i++){
            if(i == 1){
                JLabel desc = new JLabel("<html> Le morpion est un jeu de réflexion se pratiquant à deux joueurs au tour par tour et dont le but est de créer le premier un alignement (horizontal, vertical, diagonal) sur une grille. Les joueurs inscrivent tour à tour leurs symboles (O ou X) sur une grille de taille comprise entre 5x5 et 9x9. Le premier qui parvient à aligner cinq de ses symboles gagne la partie. Alternativement, on peut aussi jouer la partie aux points : les joueurs jouent jusqu'à avoir rempli l'ensemble de la grille et celui qui a le plus de point est déclaré gagnant. </html>");
                desc.setVerticalAlignment(1);
                desc.setFont(new Font("Euphemia UCAS", desc.getFont().getStyle(), desc.getFont().getSize()));
                descRegleM.add(desc);
            } else {
                descRegleM.add(new JLabel());
            }
        }

        // ------------------------------------
        // Onglet Règle d'un Tournoi
        JLabel titreRegleT = new JLabel("Règle d'un Tournoi", SwingConstants.CENTER);
        titreRegleT.setFont(new Font(titreRegleT.getFont().getName(), titreRegleT.getFont().getStyle(), (titreRegleT.getFont().getSize() * 2)));
        regleTournoi.add(titreRegleT, BorderLayout.NORTH);

        JPanel descRegleT = new JPanel(new GridLayout(1,3));
        regleTournoi.add(descRegleT, BorderLayout.CENTER);
        for(int i = 0; i < 3; i++){
            if(i == 1){
                JLabel desc = new JLabel("<html> Le morpion est un jeu de réflexion se pratiquant à deux joueurs au tour par tour et dont le but est de créer le premier un alignement (horizontal, vertical, diagonal) sur une grille. Les joueurs inscrivent tour à tour leurs symboles (O ou X) sur une grille de taille comprise entre 5x5 et 9x9. Le premier qui parvient à aligner cinq de ses symboles gagne la partie. Alternativement, on peut aussi jouer la partie aux points : les joueurs jouent jusqu'à avoir rempli l'ensemble de la grille et celui qui a le plus de point est déclaré gagnant. </html>");
                desc.setVerticalAlignment(1);
                desc.setFont(new Font("Euphemia UCAS", desc.getFont().getStyle(), desc.getFont().getSize()));
                desc.setSize(500,300);
                descRegleT.add(desc);
            } else {
                descRegleT.add(new JLabel());
            }
        }
    }

    public void afficher(){
        this.window.setVisible(true);
    }
}

