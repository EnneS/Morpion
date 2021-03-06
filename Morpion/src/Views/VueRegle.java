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
import Enum.MESSAGES;

/**
 *
 * @author souliern
 */
public class VueRegle extends Vue{

    private final JFrame window;
    CardLayout cl = new CardLayout();
    JPanel onglet = new JPanel();

    //Liste des onglets avec un nom associé
    String[] listeOnglets = {"RegleMorpion", "RegleTournoi"};


    public VueRegle() {

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1000,500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Règles du Morpion");

        // ----------------------------------
        // Panel des onglets disponibles (2 boutons)
        JPanel boutonPanel = new JPanel();
        boutonPanel.setPreferredSize(new Dimension(window.getWidth(), 50));

        JButton boutonMorpion = new JButton("Règle du jeu");
        boutonMorpion.setFont(new Font("Euphemia UCAS", boutonMorpion.getFont().getStyle(), boutonMorpion.getFont().getSize()));
        boutonMorpion.setPreferredSize(new Dimension(300, 35));
        boutonMorpion.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                cl.show(onglet, listeOnglets[0]); // 0 = Onglet Règle d'une partie de morpion
            }
        });

        JButton boutonTournoi = new JButton("Règle d'un tournoi");
        boutonTournoi.setFont(new Font("Euphemia UCAS", boutonTournoi.getFont().getStyle(), boutonTournoi.getFont().getSize()));
        boutonTournoi.setPreferredSize(new Dimension(300, 35));
        boutonTournoi.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                cl.show(onglet, listeOnglets[1]); // 1 = Onglet Règle d'un tournoi
            }
        });

        boutonPanel.add(boutonMorpion);
        boutonPanel.add(boutonTournoi);

        // -----------------------------------
        // Initialisation des onglets
        JPanel regleMorpion = new JPanel(new BorderLayout());
        JPanel regleTournoi = new JPanel(new BorderLayout());

        onglet.setLayout(cl);
        onglet.add(regleMorpion, listeOnglets[0]);
        onglet.add(regleTournoi, listeOnglets[1]);

        window.add(boutonPanel, BorderLayout.NORTH);
        window.add(onglet, BorderLayout.CENTER);


        // ------------------------------------
        // Onglet Règle d'une partie de Morpion
        JLabel titreRegleM = new JLabel("Règle du jeu", SwingConstants.CENTER);
        titreRegleM.setFont(new Font("Euphemia UCAS", titreRegleM.getFont().getStyle(), titreRegleM.getFont().getSize()*2));
        regleMorpion.add(titreRegleM, BorderLayout.NORTH);

        JPanel descRegleM = new JPanel(new GridLayout(1,3));
        regleMorpion.add(descRegleM, BorderLayout.CENTER);
        for(int i = 0; i < 3; i++){
            if(i == 1){
                JLabel desc = new JLabel("<html> Le morpion est un jeu de réflexion se pratiquant à deux joueurs au tour par tour et dont le but est de créer le premier un alignement (horizontal, vertical, diagonal) d'un nombre donné de symbole sur une grille. Les joueurs inscrivent tour à tour leurs symboles (O ou X) sur une grille de taille NxN cases. Le premier qui parvient à aligner le nombre de symbole suffisant gagne la partie. De plus, le bouton information situé sous la grillle permet de rappeler les paramètres de jeu séléctionnés et, si plusieurs parties ont été éfféctués, permet de connaître le nombre de partie gagné par chacun des joueurs</html>");
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
        titreRegleT.setFont(new Font("Euphemia UCAS", titreRegleT.getFont().getStyle(), titreRegleT.getFont().getSize()*2));
        regleTournoi.add(titreRegleT, BorderLayout.NORTH);

        JPanel descRegleT = new JPanel(new GridLayout(1,3));
        regleTournoi.add(descRegleT, BorderLayout.CENTER);
        for(int i = 0; i < 3; i++){
            if(i == 1){
                JLabel desc = new JLabel("<html>Le tournoi permet d'organiser une compétition de morpion pour 2*n joueurs. La gestion des matchs est automatisée : chaque joueur est associé à un numéro et joue quand son tour est venu. De plus, le bouton information situé sous la grille permet de prendre connaissance de l'avancement du tournoi, et si besoin, de rappeler qui a gagné contre qui.</html>");
                desc.setVerticalAlignment(1);
                desc.setFont(new Font("Euphemia UCAS", desc.getFont().getStyle(), desc.getFont().getSize()));
                descRegleT.add(desc);
            } else {
                descRegleT.add(new JLabel());
            }
        }

        // -------------------------------------
        // Bouton Retour
        JPanel panelBtnRetour = new JPanel(new GridLayout(1,3));
        window.add(panelBtnRetour, BorderLayout.SOUTH);

        for (int i = 0; i < 3; i++){
            if( i == 1){
                JButton boutonRetour = new JButton("Retour");
                boutonRetour.setFont(new Font("Euphemia UCAS", boutonRetour.getFont().getStyle(), boutonRetour.getFont().getSize()));
                panelBtnRetour.add(boutonRetour);
                boutonRetour.setPreferredSize(new Dimension(0 /*La longueur ne change pas quoiqu'il arrive. Swing, tu es incroyable.*/,40));

                boutonRetour.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();
                        notifyObservers(MESSAGES.FERMER_REGLES);
                        clearChanged();
                    }
                });

            } else {
                panelBtnRetour.add(new JLabel());
            }
        }

    }

    public void setVisible(Boolean b) {
        window.setVisible(b);
    }
}

