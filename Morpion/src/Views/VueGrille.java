package Views;

import Enum.MESSAGES;
import Enum.SYMBOLES;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class VueGrille extends Vue {

        private final JFrame window;

        //Fonts
        private Font regular = new Font("Euphemia UCAS",0,14);
        private Font regular2 = new Font("Euphemia UCAS", 0, 20);
        private Font bold = new Font("Euphemia UCAS", 1, 40);
        private Font semiBold2 = new Font("Euphemia UCAS",1,14);
        private Font italic = new Font("Euphemia UCAS", 2,15);
        private Font impact = new Font("Impact",1,60);

        public VueGrille(int tailleGrille, ArrayList<String> pseudos){

            //caracteristiques fenetre
            window = new JFrame();
            window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            window.setSize(1280,720);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
            window.setTitle("Jeu du Morpion");

            window.setLayout(new BorderLayout());
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            window.add(mainPanel);


            //construction panel principal
            JPanel hautPanel = new JPanel(new GridLayout(1,3));
            mainPanel.add(hautPanel, BorderLayout.NORTH);

            JPanel droitPanel = new JPanel();
            mainPanel.add(droitPanel, BorderLayout.EAST);

            JPanel gauchePanel = new JPanel();
            mainPanel.add(gauchePanel, BorderLayout.WEST);

            JPanel centrePanel = new JPanel();
            mainPanel.add(centrePanel, BorderLayout.CENTER);

            //construction panel information (haut de panel principal)
            JPanel infoJoueurGauche = new JPanel(new GridLayout(2,1));
            hautPanel.add(infoJoueurGauche);

            //Font lol = new Font("Euphemia UCAS", Font.BOLD, lol.getSize()*3);
            JLabel symboleJoueurGauche = new JLabel("X",SwingConstants.CENTER);
            symboleJoueurGauche.setFont(bold);
            infoJoueurGauche.add(symboleJoueurGauche);

            JLabel nomJoueurGauche = new JLabel(pseudos.get(0),SwingConstants.CENTER);
            nomJoueurGauche.setFont(regular2);
            infoJoueurGauche.add(nomJoueurGauche);

            JLabel infoCentre = new JLabel("VS",SwingConstants.CENTER);
            infoCentre.setFont(impact);
            hautPanel.add(infoCentre);

            JPanel infoJoueurDroite = new JPanel(new GridLayout(2,1));
            hautPanel.add(infoJoueurDroite);

            JLabel symboleJoueurDroite = new JLabel("O",SwingConstants.CENTER);
            symboleJoueurDroite.setFont(bold);
            infoJoueurDroite.add(symboleJoueurDroite);

            JLabel nomJoueurDroite = new JLabel(pseudos.get(1),SwingConstants.CENTER);
            nomJoueurDroite.setFont(regular2);
            infoJoueurDroite.add(nomJoueurDroite);

            //Construction panel grille (centre de panel principal)

            JPanel grillePanel = new JPanel(new BorderLayout());
            centrePanel.add(grillePanel);

            JPanel HautGrillePanel = new JPanel();
            grillePanel.add(HautGrillePanel, BorderLayout.NORTH);
            //HautGrillePanel.setBackground(Color.WHITE);

            JLabel hautGrilleLabel = new JLabel("C'est à Théophile de jouer !", SwingConstants.CENTER);
            hautGrilleLabel.setFont(italic);
            HautGrillePanel.add(hautGrilleLabel);

            JPanel centreGrillePanel = new JPanel(new GridLayout(tailleGrille,tailleGrille));
            grillePanel.add(centreGrillePanel, BorderLayout.CENTER);

            for (int i = 0; i < tailleGrille*tailleGrille ; i++){
                JButton btn = new  JButton(SYMBOLES.NULL.toString());
                btn.setFont(new Font("Euphemia UCAS", btn.getFont().getStyle(), btn.getFont().getSize()*5));
                btn.setPreferredSize(new Dimension(500/tailleGrille,500/tailleGrille));
                centreGrillePanel.add(btn);
            }

            JPanel basGrillePanel = new JPanel();
            grillePanel.add(basGrillePanel, BorderLayout.SOUTH);

            //Bouton quitter
            JButton btnQuitter = new JButton("Quitter la partie");
            btnQuitter.setFont(regular);
            btnQuitter.setPreferredSize(new Dimension(200,35));
            basGrillePanel.add(btnQuitter, BorderLayout.SOUTH);

            btnQuitter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(MESSAGES.QUITTER_PARTIE);
                    clearChanged();
                }
            });

            //Bouton annuler
            JButton btnAnnuler = new JButton("Revenir en arrière");
            btnAnnuler.setFont(regular);
            btnAnnuler.setPreferredSize(new Dimension(200,35));
            basGrillePanel.add(btnAnnuler);

            //Bouton information
            JButton btnInformation = new JButton("Information");
            btnInformation.setFont(regular);
            btnInformation.setPreferredSize(new Dimension(200,35));
            basGrillePanel.add(btnInformation, BorderLayout.SOUTH);

        }

    public void setVisible(Boolean b) {
        window.setVisible(b);
    }

    public void finalize(){};


}

