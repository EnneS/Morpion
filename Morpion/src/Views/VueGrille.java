package Views;

import Enum.MESSAGES;
import Enum.SYMBOLES;
import Enum.MESSAGE_COCHE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.synth.SynthTextAreaUI;

public class VueGrille extends Vue {

        private final JFrame window;
        private JButton cases[][];
        private JLabel hautGrilleLabel;
        private ArrayList<String> pseudos = new ArrayList<>();
        private JButton btnAnnuler;
        private JLabel nomJoueurGauche;
        private JLabel nomJoueurDroite;

        public VueGrille(int tailleGrille, String joueur1, String joueur2){
            // Stockage des pseudos
            pseudos.add(joueur1); pseudos.add(joueur2);

            //caracteristiques fenetre
            window = new JFrame();
            window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            window.setSize(1050,750);
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
            Border border = BorderFactory.createLineBorder(Color.BLACK,2);

            JPanel infoJoueurGauche = new JPanel(new GridLayout(2,1));
            hautPanel.add(infoJoueurGauche);

            JLabel symboleJoueurGauche = new JLabel("X",SwingConstants.CENTER);
            symboleJoueurGauche.setFont(bold);
            infoJoueurGauche.add(symboleJoueurGauche);

            nomJoueurGauche = new JLabel(pseudos.get(0),SwingConstants.CENTER);
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

            nomJoueurDroite = new JLabel(pseudos.get(1),SwingConstants.CENTER);
            nomJoueurDroite.setFont(regular2);
            infoJoueurDroite.add(nomJoueurDroite);

            hautPanel.setBorder(border);

            //Construction panel grille (centre de panel principal)
            JPanel grillePanel = new JPanel(new BorderLayout());
            centrePanel.add(grillePanel);

            JPanel HautGrillePanel = new JPanel();
            grillePanel.add(HautGrillePanel, BorderLayout.NORTH);

            hautGrilleLabel = new JLabel("C'est à " + pseudos.get(0) + " de jouer !", SwingConstants.CENTER);
            hautGrilleLabel.setFont(italic);
            HautGrillePanel.add(hautGrilleLabel);

            JPanel centreGrillePanel = new JPanel(new GridLayout(tailleGrille,tailleGrille));
            grillePanel.add(centreGrillePanel, BorderLayout.CENTER);

            // ========================================
            // Création de la grille
            cases = new JButton[tailleGrille][tailleGrille]; // Tableau de bouton servant à pouvoir les retrouver puis mettre à jour

            for (int i = 0; i < tailleGrille*tailleGrille ; i++){
                // Création du bouton (Symbole VIDE)
                JButton btn = new  JButton(SYMBOLES.VIDE.toString());

                // On ajoute le bouton au tableau de bouton
                cases[i/tailleGrille][i%tailleGrille] = btn;

                // Personnalisation du bouton
                btn.setFont(new Font("Euphemia UCAS", btn.getFont().getStyle(), btn.getFont().getSize()*3));
                btn.setPreferredSize(new Dimension(500/tailleGrille,500/tailleGrille));
                btn.setBackground(Color.WHITE);
                centreGrillePanel.add(btn);

                // On créé le message correspondant au bouton avec i%tailleGrille le n° de la colonne et i/tailleGrille le n° de la ligne
                MESSAGE_COCHE m = new MESSAGE_COCHE(i%tailleGrille,i/tailleGrille);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();
                        notifyObservers(m);
                        clearChanged();
                    }
                });
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

            //===============
            //Bouton annuler
            btnAnnuler = new JButton("Revenir en arrière");
            btnAnnuler.setFont(regular);
            btnAnnuler.setPreferredSize(new Dimension(200,35));
            basGrillePanel.add(btnAnnuler);
            btnAnnuler.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(MESSAGES.ANNULER_COUP);
                    clearChanged();
                }
            });
            // Désactivé de base
            btnAnnuler.setEnabled(false);

            //=================
            //Bouton information
            JButton btnInformation = new JButton("Information");
            btnInformation.setFont(regular);
            btnInformation.setPreferredSize(new Dimension(200,35));
            basGrillePanel.add(btnInformation, BorderLayout.SOUTH);
            btnInformation.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(MESSAGES.INFORMATION);
                    clearChanged();
                }
            });

        }

    public void setVisible(Boolean b) {
        window.setVisible(b);
    }

    public void updateVue(int j, int i, SYMBOLES symbole, int joueurActif, boolean onCoche){
            cases[j][i].setText(symbole.toString());
            hautGrilleLabel.setText("C'est à " + pseudos.get((joueurActif+1)%pseudos.size()) + " de jouer !");

            // S'il s'agit d'un cochage de case on désactive le bouton sinon on le ractive.
            if(onCoche) cases[j][i].setEnabled(false); else cases[j][i].setEnabled(true);
    }

    public JButton getBtnAnnuler(){
            return btnAnnuler;
    }

    public void highlightGagnant(ArrayList<Integer> casesGagnantes, String joueurGagnant){
            // Mise en surbrillance des cases gagnantes
            int i = 0;
            while (i < casesGagnantes.size()) {
                cases[casesGagnantes.get(i)][casesGagnantes.get(i + 1)].setBackground(Color.RED);
                i += 2;
            }

            // Affichage du nom du gagnant
            hautGrilleLabel.setText(joueurGagnant + " gagne la partie !");
    }

    public void matchFini(boolean matchNul){
            // Désactivation de tout les boutons
            for (int i = 0; i < cases.length; i++) {
                for (int j = 0; j < cases.length; j++) {
                    cases[i][j].setEnabled(false);
                }
            }

            if(matchNul) {
                // On indique que le match est nul !
                hautGrilleLabel.setText("Personne ne gagne, match nul !");
            }

            // Le bouton annuler devient un bouton rejouer !
            getBtnAnnuler().setText("Rejouer");
            getBtnAnnuler().removeActionListener(getBtnAnnuler().getActionListeners()[0]); // Suppression de l'ancien actionListener
            getBtnAnnuler().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(MESSAGES.REJOUER);
                    clearChanged();
                }
            });
        }

    public void nextPartie(String joueur1, String joueur2){
        // Remplacement des pseudos par les nouveaux
        pseudos.clear();
        pseudos.add(joueur1); pseudos.add(joueur2);

        // Update visuel des pseudos
        nomJoueurGauche.setText(joueur1);
        nomJoueurDroite.setText(joueur2);
        hautGrilleLabel.setText("C'est à " + pseudos.get(0) + " de jouer :");

        // Remise à 0 de la grille
        resetGrille();

        getBtnAnnuler().setEnabled(false);
    }

    public  void resetPartie(){
            // Remise à 0 de la grille
         resetGrille();

         // Le bouton rejouer redevient "Revenir en arrière"
         getBtnAnnuler().setText("Revenir en arrière");
         getBtnAnnuler().removeActionListener(getBtnAnnuler().getActionListeners()[0]); // Suppression de l'ancien actionListener
         btnAnnuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MESSAGES.ANNULER_COUP);
                clearChanged();
            }
         });

         // Il est désactivé de base
         getBtnAnnuler().setEnabled(false);

         // On affiche qui doit jouer
         hautGrilleLabel.setText("C'est à " + pseudos.get(0) + " de jouer :");
    }

    public void resetGrille(){
        // Update visuel des cases : elles deviennent actives, vides, et blanches de nouveau
        for(int i = 0; i < cases.length; i++){
            for (int j = 0; j < cases.length; j++){
                cases[i][j].setEnabled(true);
                cases[i][j].setText(SYMBOLES.VIDE.toString());
                cases[i][j].setBackground(Color.WHITE);
            }
        }
    }

    public JLabel getHautGrilleLabel(){
            return hautGrilleLabel;
    }

    public void finalize(){};

}

