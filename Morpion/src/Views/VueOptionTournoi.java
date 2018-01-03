package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Enum.*;

public class VueOptionTournoi extends Vue {

    private final JFrame window;
    private final JComboBox taillesGrille = new JComboBox(TAILLE_GRILLE.values());
    private final JComboBox longeurAlignementPossible = new JComboBox();
    private Boolean etat = true;
    private JButton memoire;
    private Boolean btn3x3Selectionne = true;
    private Boolean btn4Selectionne = true;
    private Boolean btn6x6Selectionne = false;
    private Boolean btn9X9Selectionne = false;
    private Boolean btn8Selectionne = false;
    private Boolean btn16Selectionne = false;
    private Boolean btn32Selectionne = false;
    private JButton btn3, btn4, btn6, btn8, btn9, btn16, btn32;

    //Fonts
    private Font regular = new Font("Euphemia UCAS",0,14);
    private Font bold = new Font("Euphemia UCAS", 1, 26);
    private Font semiBold = new Font("Euphemia UCAS", 1, 20);
    private Font semiBold2 = new Font("Euphemia UCAS",1,14);

    public VueOptionTournoi(){

        //paramètres fenêtre
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1280,720);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setResizable(false);
        window.setTitle("Jeu du Morpion");

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

        JLabel titreLabel = new JLabel("Option du tournoi");
        titreLabel.setFont(bold);
        titreLabel.setHorizontalAlignment(0);
        titrePanel.add(titreLabel);

        for (int i = 0; i<1 ; i++) {
            JPanel panel = new JPanel();
            titrePanel.add(panel);
        }

        //construction panel choixGrille (utilisation gridbaglayout pour les marges)
        JPanel choixGrillePanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(choixGrillePanel);

        JPanel gb = new JPanel(new GridBagLayout());
        choixGrillePanel.add(gb);

        JLabel texteChoixGrilleLabel = new JLabel("Veuillez séléctionner la taille de votre grille");
        texteChoixGrilleLabel.setFont(regular);

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(0,0,0,666);
        gb.add(texteChoixGrilleLabel,gc);

        JPanel selectionChoixGrillePanel = new JPanel(new GridLayout(1,7));
        JPanel panel1 = new JPanel();
        selectionChoixGrillePanel.add(panel1);

        btn3 = new JButton("3 X 3");
        btn3.setFont(regular);
        btn3.isDefaultButton();
        selectionChoixGrillePanel.add(btn3);

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                boutonSelectionne(btn3);
                btn3x3Selectionne = true;
                //maj alignement possible
                setLongeurAlignementPossibles();
                clearChanged();
            }
        });

        btn6 = new JButton("6 X 6");
        btn6.setFont(regular);
        selectionChoixGrillePanel.add(btn6);

        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                boutonSelectionne(btn6);
                btn6x6Selectionne = true;
                //maj alignement possible
                setLongeurAlignementPossibles();
                clearChanged();
            }
        });

        btn9 = new JButton("9 X 9");
        btn9.setFont(regular);
        selectionChoixGrillePanel.add(btn9);

        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                boutonSelectionne(btn9);
                btn9X9Selectionne = true;
                //maj alignement possible
                setLongeurAlignementPossibles();
                clearChanged();
            }
        });

        JButton btnPerso = new JButton("Personnalisé");
        btnPerso.setFont(regular);
        selectionChoixGrillePanel.add(btnPerso);

        btnPerso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(etat){
                    //enregistre le dernier choix effecuté
                    memoire = btn3;
                    if (btn6x6Selectionne) {
                        memoire = btn6;
                    } else if (btn9X9Selectionne) {
                        memoire = btn9;
                    }
                    //affichage des tailles personnalisés uniquement
                    getTaillesGrillePossible().setVisible(true);
                    btn3.setEnabled(false); btn6.setEnabled(false); btn9.setEnabled(false);
                    etat = false;
                    //maj choix bouton
                    boutonSelectionne(btnPerso);
                } else {
                    //affichage des tailles rapides uniquement
                    getTaillesGrillePossible().setVisible(false);
                    btn3.setEnabled(true); btn6.setEnabled(true); btn9.setEnabled(true);
                    etat = true;
                    //restaure le dernier choix rapide
                    btnPerso.setFont(regular);
                    boutonSelectionne(memoire);
                }
            }
        });

        selectionChoixGrillePanel.add(getTaillesGrillePossible());
        getTaillesGrillePossible().setVisible(false);

        JPanel panel2 = new JPanel();
        selectionChoixGrillePanel.add(panel2);

        choixGrillePanel.add(selectionChoixGrillePanel);

        JPanel panel3 = new JPanel();
        choixGrillePanel.add(panel3);


        //construction panel nombreJoueur
        JPanel choixJoueurPanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(choixJoueurPanel);

        JLabel texteChoixJoueurLabel = new JLabel("                           Veuillez entrez le nombre de joueur");
        texteChoixJoueurLabel.setFont(new Font("Euphemia UCAS", texteChoixJoueurLabel.getFont().getStyle(), texteChoixJoueurLabel.getFont().getSize()));
        choixJoueurPanel.add(texteChoixJoueurLabel);

        JPanel selectionChoixJoueurPanel = new JPanel(new GridLayout(1,7));

        JPanel panel5 = new JPanel();
        selectionChoixJoueurPanel.add(panel5);

        btn4 = new JButton("4");
        btn4.setFont(regular);
        btn4.isDefaultButton();
        selectionChoixJoueurPanel.add(btn4);

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                //boutonSelectionne(btn4);
                //btn4Selectionne = true;
                clearChanged();
            }
        });

        btn8 = new JButton("8");
        btn8.setFont(regular);
        btn8.isDefaultButton();
        selectionChoixJoueurPanel.add(btn8);

        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                //boutonSelectionne(btn8);
                //btn8Selectionne = true;
                clearChanged();
            }
        });

        btn16 = new JButton("16");
        btn16.setFont(regular);
        selectionChoixJoueurPanel.add(btn16);

        btn16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                //boutonSelectionne(btn16);
                //btn16Selectionne = true;
                clearChanged();
            }
        });

        btn32 = new JButton("32");
        btn32.setFont(regular);
        selectionChoixJoueurPanel.add(btn32);

        btn32.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                //boutonSelectionne(btn32);
                //btn32Selectionne = true;
                clearChanged();
            }
        });


        JPanel panel6 = new JPanel();
        selectionChoixJoueurPanel.add(panel6);
        JPanel panel7 = new JPanel();
        selectionChoixJoueurPanel.add(panel7);
        choixJoueurPanel.add(selectionChoixJoueurPanel);

        JPanel panel4 = new JPanel();
        choixJoueurPanel.add(panel4);


        //construction panel choix alignement
        JPanel choixAlignementPanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(choixAlignementPanel);

        JPanel gb3 = new JPanel(new GridBagLayout());
        choixAlignementPanel.add(gb3);

        JLabel texteAlignementLabel = new JLabel("Veuillez séléctionner le nombre de symbole à aligner pour gagner");
        texteAlignementLabel.setFont(regular);

        GridBagConstraints gc3 = new GridBagConstraints();
        gc3.insets = new Insets(0,0,0,522);
        gb3.add(texteAlignementLabel,gc3);

        JPanel choixAlignementGrille = new JPanel(new GridLayout(1,7));
        choixAlignementPanel.add(choixAlignementGrille);

        JPanel panel70 = new JPanel();
        choixAlignementGrille.add(panel70);

        choixAlignementGrille.add(getLongeurAlignementPossible());
        setLongeurAlignementPossibles();

        getTaillesGrillePossible().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLongeurAlignementPossibles();
            }
        });

        for (int i = 0; i<5; i++){
            JPanel panel8 = new JPanel();
            choixAlignementGrille.add(panel8);
        }


        //construction panel sortie
        JPanel sortiePanel = new JPanel(new GridLayout(3,7));

        for (int i = 0; i<8; i++){
            JPanel panel9 = new JPanel();
            sortiePanel.add(panel9);
        }

        JButton btnFermer = new JButton("Revenir au menu");
        btnFermer.setFont(regular);
        sortiePanel.add(btnFermer);

        btnFermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MESSAGES.QUITTER);
                System.out.println(getTailleGrilleSelectionne());
                clearChanged();
            }
        });

        for (int i = 0; i<3; i++){
            JPanel panel10 = new JPanel();
            sortiePanel.add(panel10);
        }

        JButton btnLancerPartie = new JButton("Lancer la partie");
        btnLancerPartie.setFont(regular);
        sortiePanel.add(btnLancerPartie);

        btnLancerPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(MESSAGES.LANCER_PARTIE);
                clearChanged();
            }
        });

        for (int i = 0; i<8; i++){
            JPanel panel11 = new JPanel();
            sortiePanel.add(panel11);
        }

        mainPanel.add(sortiePanel);
    }


    public void setVisible(Boolean b) {
        window.setVisible(b);
    }

    public void boutonSelectionne(JButton btn) {
        //permet de maj et d'enregistrer le dernier bouton séléctionné
        btn.setFont(semiBold2);
        if (btn3 != btn) { btn3.setFont(regular); btn3x3Selectionne = false;}
        if (btn6 != btn) { btn6.setFont(regular); btn6x6Selectionne = false;}
        if (btn9 != btn) { btn9.setFont(regular); btn9X9Selectionne = false;}

    }

    public JComboBox getTaillesGrillePossible() {
        return taillesGrille;
    }

    public int getTailleGrilleSelectionne(){
        int taille = 0;
        if (etat){
            if (btn3x3Selectionne){
                taille = 3;
            } else if (btn6x6Selectionne){
                taille = 6;
            } else {
                taille = 9;
            }
        } else {
            if (getTaillesGrillePossible().getSelectedItem() == TAILLE_GRILLE.QUATRE){
                taille = 4;
            } else if (getTaillesGrillePossible().getSelectedItem() == TAILLE_GRILLE.CINQ){
                taille = 5;
            } else if (getTaillesGrillePossible().getSelectedItem() == TAILLE_GRILLE.SEPT){
                taille = 7;
            } else if (getTaillesGrillePossible().getSelectedItem() == TAILLE_GRILLE.HUIT){
                taille = 8;
            }
        }
        return taille;

    }

    public JComboBox getLongeurAlignementPossible() {
        return longeurAlignementPossible;
    }

    public int getLongeurAlignementSelectionnee(){
        return longeurAlignementPossible.getSelectedIndex()+3;
    }

    public void setLongeurAlignementPossibles() {
        longeurAlignementPossible.setPreferredSize(new Dimension(100,50));
        longeurAlignementPossible.removeAllItems();
        int tailleGrille = getTailleGrilleSelectionne();
        for (int i = 3; i <= tailleGrille;i++) {
            longeurAlignementPossible.addItem(i);
        }
        longeurAlignementPossible.setFont(regular);
    }

    public ArrayList getPseudos(){
        ArrayList<String> pseudos = new ArrayList<>();
        for (int i=1; i<=getNombreJoueur();i++){
            pseudos.add("Joueur "+i);
        }
        return pseudos;
    }

    public int getNombreJoueur() {
        return 0;
    }
}
