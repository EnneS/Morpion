package Views;

import java.awt.*;
import javax.swing.*;

public class VueGrille {

        private final JFrame window;

        public VueGrille(){

            window = new JFrame();
            window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            window.setSize(1280,720);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
            window.setTitle("Jeu du Morpion");

            window.setLayout(new BorderLayout());
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            window.add(mainPanel);


            //Construction panel principal

            JPanel hautPanel = new JPanel(new GridLayout(1,3));
            mainPanel.add(hautPanel, BorderLayout.NORTH);

            JPanel droitPanel = new JPanel();
            mainPanel.add(droitPanel, BorderLayout.EAST);

            JPanel gauchePanel = new JPanel();
            mainPanel.add(gauchePanel, BorderLayout.WEST);

            JPanel centrePanel = new JPanel();
            mainPanel.add(centrePanel, BorderLayout.CENTER);

            //Construction panel information (haut de panel principal)

            JPanel infoJoueurGauche = new JPanel(new GridLayout(2,1));
            hautPanel.add(infoJoueurGauche);

            JLabel symboleJoueurGauche = new JLabel("X",SwingConstants.CENTER);
            symboleJoueurGauche.setFont(new Font("Impact", Font.BOLD, symboleJoueurGauche.getFont().getSize()*3));
            infoJoueurGauche.add(symboleJoueurGauche);

            JLabel nomJoueurGauche = new JLabel("Nathan",SwingConstants.CENTER);
            nomJoueurGauche.setFont(new Font("Euphemia UCAS", nomJoueurGauche.getFont().getStyle(), nomJoueurGauche.getFont().getSize()*2));
            infoJoueurGauche.add(nomJoueurGauche);

            JLabel infoCentre = new JLabel("VS",SwingConstants.CENTER);
            infoCentre.setFont(new Font("Impact", infoCentre.getFont().getStyle(), infoCentre.getFont().getSize()*5));
            hautPanel.add(infoCentre);

            JPanel infoJoueurDroite = new JPanel(new GridLayout(2,1));
            hautPanel.add(infoJoueurDroite);

            JLabel symboleJoueurDroite = new JLabel("O",SwingConstants.CENTER);
            symboleJoueurDroite.setFont(new Font("Impact", Font.BOLD, symboleJoueurDroite.getFont().getSize()*3));
            infoJoueurDroite.add(symboleJoueurDroite);

            JLabel nomJoueurDroite = new JLabel("Théophile",SwingConstants.CENTER);
            nomJoueurDroite.setFont(new Font("Euphemia UCAS", nomJoueurDroite.getFont().getStyle(), nomJoueurDroite.getFont().getSize()*2));
            infoJoueurDroite.add(nomJoueurDroite);

            //Construction panel grille (centre de panel principal)

            JPanel grillePanel = new JPanel(new BorderLayout());
            centrePanel.add(grillePanel);

            JPanel HautGrillePanel = new JPanel();
            grillePanel.add(HautGrillePanel, BorderLayout.NORTH);
            HautGrillePanel.setBackground(Color.WHITE);

            JLabel hautGrilleLabel = new JLabel("C'est à Théophile de jouer !", SwingConstants.CENTER);
            hautGrilleLabel.setFont(new Font("Euphemia UCAS", Font.ITALIC, hautGrilleLabel.getFont().getSize()));
            HautGrillePanel.add(hautGrilleLabel);

            JPanel centreGrillePanel = new JPanel(new GridLayout(9,9));
            grillePanel.add(centreGrillePanel, BorderLayout.CENTER);
            centreGrillePanel.setBackground(Color.WHITE);

            for (int i = 0; i < 81 ; i++){
                JButton btn = new  JButton("");
                btn.setFont(new Font("Euphemia UCAS", btn.getFont().getStyle(), btn.getFont().getSize()*2));
                btn.setPreferredSize(new Dimension(55,55));
                centreGrillePanel.add(btn);
            }

            JPanel basGrillePanel = new JPanel();
            grillePanel.add(basGrillePanel, BorderLayout.SOUTH);
            basGrillePanel.setBackground(Color.WHITE);

            //Bouton quitter

            JButton btnQuitter = new JButton("Quitter la partie");
            btnQuitter.setFont(new Font("Euphemia UCAS", btnQuitter.getFont().getStyle(), btnQuitter.getFont().getSize()));
            btnQuitter.setPreferredSize(new Dimension(200,35));
            basGrillePanel.add(btnQuitter, BorderLayout.SOUTH);

            //Bouton annuler

            JButton btnAnnuler = new JButton("Revenir en arrière");
            btnAnnuler.setFont(new Font("Euphemia UCAS", btnAnnuler.getFont().getStyle(), btnAnnuler.getFont().getSize()));
            btnAnnuler.setPreferredSize(new Dimension(200,35));
            basGrillePanel.add(btnAnnuler);

            window.setVisible(true);

            //Bouton information

            JButton btnInformation = new JButton("Information");
            btnInformation.setFont(new Font("Euphemia UCAS", btnInformation.getFont().getStyle(), btnInformation.getFont().getSize()));
            btnInformation.setPreferredSize(new Dimension(200,35));
            basGrillePanel.add(btnInformation, BorderLayout.SOUTH);


            

        }

    public void afficher(){
        this.window.setVisible(true);
    }

}

