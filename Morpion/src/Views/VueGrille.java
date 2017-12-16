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

            /*window.getContentPane().add(new JButton("Center"),BorderLayout.CENTER);
            window.getContentPane().add(new JButton("North"),BorderLayout.NORTH);
            window.getContentPane().add(new JButton("South"),BorderLayout.SOUTH);
            window.getContentPane().add(new JButton("East"),BorderLayout.EAST);
            window.getContentPane().add(new JButton("West"),BorderLayout.WEST);

            window.getContentPane().setPreferredSize(new Dimension(100,100 )); */

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

            JLabel infoCentre = new JLabel("VS",SwingConstants.CENTER);
            infoCentre.setFont(new Font("Impact", infoCentre.getFont().getStyle(), infoCentre.getFont().getSize()*4));
            hautPanel.add(infoCentre);

            JPanel infoJoueurDroite = new JPanel(new GridLayout(2,1));
            hautPanel.add(infoJoueurDroite);


            //Construction panel grille (centre de panel principal)

            JPanel grillePanel = new JPanel(new BorderLayout());
            centrePanel.add(grillePanel);

            JLabel hautGrilleLabel = new JLabel("C'est à Théophile de joué !", SwingConstants.CENTER);
            grillePanel.add(hautGrilleLabel, BorderLayout.NORTH);

            JPanel centreGrillePanel = new JPanel(new GridLayout(9,9));
            grillePanel.add(centreGrillePanel, BorderLayout.CENTER);

            for (int i = 0; i < 81 ; i++){
                JButton btn = new  JButton("X");
                btn.setPreferredSize(new Dimension(60,60));
                centreGrillePanel.add(btn);
            }

            JPanel basGrillePanel = new JPanel();
            grillePanel.add(basGrillePanel, BorderLayout.SOUTH);

            JButton btnAnnuler = new JButton("Annuler");
            btnAnnuler.setPreferredSize(new Dimension(600,40));
            basGrillePanel.add(btnAnnuler);





            window.setVisible(true);

        }

    public void afficher(){
        this.window.setVisible(true);
    }

}

