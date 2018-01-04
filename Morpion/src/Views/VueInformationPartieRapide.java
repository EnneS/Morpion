package Views;

import Enum.MESSAGES;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VueInformationPartieRapide extends Vue {

    private final JFrame window;
    private JLabel infoJoueur1;
    private JLabel infoJoueur2;
    private JLabel infoGrille;
    private JLabel infoAlignement;

    //Fonts
    private Font regular = new Font("Euphemia UCAS",0,14);
    private Font bold = new Font("Euphemia UCAS", 1, 26);
    private Font semiBold = new Font("Euphemia UCAS", 1, 20);
    private Font semiBold2 = new Font("Euphemia UCAS",1,14);
    private Font italic = new Font("Euphemia UCAS", 2,13);

    public VueInformationPartieRapide(){

        //paramètres fenêtre
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(600,600);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Information partie rapide");
        window.setResizable(true);
        window.setVisible(true);

        //construction panel principal
        JPanel mainPanel = new JPanel(new GridLayout(5,1));
        window.add(mainPanel);


        //construction panel titre
        JPanel titrePanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(titrePanel);

        for (int i = 0; i<1 ; i++){
            JPanel panel = new JPanel();
            titrePanel.add(panel);
        }

        JLabel titreLabel = new JLabel("Informations partie 1V1");
        titreLabel.setFont(bold);
        titreLabel.setHorizontalAlignment(0);
        titrePanel.add(titreLabel);

        for (int i = 0; i<1 ; i++) {
            JPanel panel = new JPanel();
            titrePanel.add(panel);
        }

        //construction panel récap
        Border border = BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK);

        JPanel recapPanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(recapPanel);
        TitledBorder recapBorder = BorderFactory.createTitledBorder(border, "Récapitulatif partie",2,0,semiBold2);
        recapPanel.setBorder(recapBorder);


        infoJoueur1 = new JLabel("Joueur 1 n'a pas encore gagné de partie");
        infoJoueur1.setFont(regular);
        recapPanel.add(infoJoueur1);

        infoJoueur2 = new JLabel("Joueur 2 n'a pas encore gagné de partie");
        infoJoueur2.setFont(regular);
        recapPanel.add(infoJoueur2);

        JPanel panel = new JPanel();
        recapPanel.add(panel);


        //construction panel settings
        JPanel settingPanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(settingPanel);
        TitledBorder settingBorder = BorderFactory.createTitledBorder(border, "Paramètres de jeu",2,0,semiBold2);
        settingPanel.setBorder(settingBorder);

        infoGrille = new JLabel("Taille grille : NxN cases");
        infoGrille.setFont(regular);
        settingPanel.add(infoGrille);

        infoAlignement = new JLabel("Nombre de symbole à aligner pour gagner  : N symboles");
        infoAlignement.setFont(regular);
        settingPanel.add(infoAlignement);

        JLabel infoJeu = new JLabel("Mode de jeu  : 1V1");
        infoJeu.setFont(regular);
        settingPanel.add(infoJeu);

        //remplissage panel principale
        JPanel panel2 = new JPanel();
        mainPanel.add(panel2);


        //construction panel sortie
        JPanel sortiePanel = new JPanel(new GridLayout(3,3));

        for (int i = 0; i<4; i++){
            JPanel panel3 = new JPanel();
            sortiePanel.add(panel3);
        }

        JButton btnFermer = new JButton("Retour au jeu");
        btnFermer.setFont(regular);
        sortiePanel.add(btnFermer);

        btnFermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MESSAGES.QUITTER_INFORMATION);
                clearChanged();
            }
        });


        for (int i = 0; i<4; i++){
            JPanel panel4 = new JPanel();
            sortiePanel.add(panel4);
        }

        mainPanel.add(sortiePanel);

    }

    public void finalize(){}

    @Override
    public void setVisible(Boolean b) {
        window.setVisible(b);
    }

    public void updateVue(ArrayList pseudos, int partiesGagneesJ1, int partiesGagneesJ2, int tailleGrille, int longeurGagnante){

        //si les joueurs ont au moins gagnés une partie
        if (partiesGagneesJ1+partiesGagneesJ2 != 0){
            infoJoueur1.setText(pseudos.get(0)+" a gagné : "+partiesGagneesJ1+" partie(s)");
            infoJoueur2.setText(pseudos.get(1)+" a gagné : "+partiesGagneesJ2+" Èpartie(s)");
        }
        infoGrille.setText("Taille de la grille : "+tailleGrille);
        infoAlignement.setText("Nombre de symbole à aligner pour gagner : "+longeurGagnante+" symboles");
    }

    public void updateVue(ArrayList pseudos, int partiesGagneesJ1, int partiesGagneesJ2){
        infoJoueur1.setText(pseudos.get(0)+" a gagné : "+partiesGagneesJ1+" partie(s)");
        infoJoueur2.setText(pseudos.get(1)+" a gagné : "+partiesGagneesJ2+" partie(s)");
    }
}
