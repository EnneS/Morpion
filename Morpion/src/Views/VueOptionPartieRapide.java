package Views;

import Enum.Messages;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueOptionPartieRapide extends Vue{

    private final JFrame window;
    private final JComboBox combo = new JComboBox();

    public VueOptionPartieRapide(){

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1280,720);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Jeu du Morpion");
        window.setResizable(false);

        //Construction panel principal

        JPanel mainPanel = new JPanel(new GridLayout(5,1));
        window.add(mainPanel);

        //Construction panel titre

        JPanel titrePanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(titrePanel);
        for (int i = 0; i<1 ; i++){
            JPanel panel = new JPanel();
            titrePanel.add(panel);
        }
        JLabel titreLabel = new JLabel("Option de la partie");
        titreLabel.setFont(new Font("Euphemia UCAS", Font.BOLD, titreLabel.getFont().getSize()*2));
        titreLabel.setHorizontalAlignment(0);
        titrePanel.add(titreLabel);

        for (int i = 0; i<1 ; i++) {
            JPanel panel = new JPanel();
            titrePanel.add(panel);
        }

        //Construction panel choixGrille

        JPanel choixGrillePanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(choixGrillePanel);

        JLabel texteChoixGrilleLabel = new JLabel("                           Veuillez séléctionner la taille de votre grille");
        texteChoixGrilleLabel.setFont(new Font("Euphemia UCAS", texteChoixGrilleLabel.getFont().getStyle(), texteChoixGrilleLabel.getFont().getSize()));
        choixGrillePanel.add(texteChoixGrilleLabel);

        JPanel selectionChoixGrillePanel = new JPanel(new GridLayout(1,7));
        JPanel panel1 = new JPanel();
        selectionChoixGrillePanel.add(panel1);
        JButton btn5 = new JButton("5 X 5");
        btn5.setFont(new Font("Euphemia UCAS", btn5.getFont().getStyle(), btn5.getFont().getSize()));
        selectionChoixGrillePanel.add(btn5);
        JButton btn7 = new JButton("7 X 7");
        btn7.setFont(new Font("Euphemia UCAS", btn7.getFont().getStyle(), btn7.getFont().getSize()));
        selectionChoixGrillePanel.add(btn7);
        JButton btn9 = new JButton("9 X 9");
        btn9.setFont(new Font("Euphemia UCAS", btn9.getFont().getStyle(), btn9.getFont().getSize()));
        selectionChoixGrillePanel.add(btn9);
        JPanel panel2 = new JPanel();
        selectionChoixGrillePanel.add(panel2);
        selectionChoixGrillePanel.add(combo);
        JPanel panel3 = new JPanel();
        selectionChoixGrillePanel.add(panel3);
        choixGrillePanel.add(selectionChoixGrillePanel);

        JPanel panel = new JPanel();
        choixGrillePanel.add(panel);


        //Construction nombreJoueur

        JPanel joueurInfoPanel = new JPanel(new GridLayout(3,6));
        mainPanel.add(joueurInfoPanel);

        JLabel texteNomJoueurLabel = new JLabel("                           Veuillez entrez les noms des joueurs");
        texteNomJoueurLabel.setFont(new Font("Euphemia UCAS", texteNomJoueurLabel.getFont().getStyle(), texteNomJoueurLabel.getFont().getSize()));
        joueurInfoPanel.add(texteNomJoueurLabel);

        JLabel texteSymboleJoueurLabel = new JLabel("Symbole associé");
        texteSymboleJoueurLabel.setFont(new Font("Euphemia UCAS", texteSymboleJoueurLabel.getFont().getStyle(), texteSymboleJoueurLabel.getFont().getSize()));
        joueurInfoPanel.add(texteSymboleJoueurLabel);

        JPanel inputPanel1 = new JPanel(new GridLayout(1,3));
        JPanel label4 = new JPanel();
        inputPanel1.add(label4);
        JTextField nomJoueur1 = new JTextField();
        nomJoueur1.setFont(new Font("Euphemia UCAS", nomJoueur1.getFont().getStyle(), nomJoueur1.getFont().getSize()));
        nomJoueur1.setText("Nom du joueur n°1");
        inputPanel1.add(nomJoueur1);
        JPanel label5 = new JPanel();
        inputPanel1.add(label5);
        joueurInfoPanel.add(inputPanel1);

        JLabel symboleX = new JLabel("X");
        symboleX.setFont(new Font("Euphemia UCAS", Font.BOLD, symboleX.getFont().getSize()*2));
        joueurInfoPanel.add(symboleX);

        JPanel inputPanel2 = new JPanel(new GridLayout(1,3));
        JPanel label6 = new JPanel();
        inputPanel2.add(label6);
        JTextField nomJoueur2 = new JTextField();
        nomJoueur2.setFont(new Font("Euphemia UCAS", nomJoueur2.getFont().getStyle(), nomJoueur2.getFont().getSize()));
        nomJoueur2.setText("Nom du joueur n°2");
        inputPanel2.add(nomJoueur2);
        JPanel label7 = new JPanel();
        inputPanel2.add(label7);
        joueurInfoPanel.add(inputPanel2);

        JLabel symboleO = new JLabel("O");
        symboleO.setFont(new Font("Euphemia UCAS", Font.BOLD, symboleO.getFont().getSize()*2));
        joueurInfoPanel.add(symboleO);

        //Remplissage panel principale

        JPanel panel8 = new JPanel();
        mainPanel.add(panel8);


        //Construction panel sortie

        JPanel sortiePanel = new JPanel(new GridLayout(3,7));

        for (int i = 0; i<8; i++){
            JPanel panel9 = new JPanel();
            sortiePanel.add(panel9);
        }

        JButton btnFermer = new JButton("Revenir au menu");
        btnFermer.setFont(new Font("Euphemia UCAS", btnFermer.getFont().getStyle(), btnFermer.getFont().getSize()));
        sortiePanel.add(btnFermer);

        btnFermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(Messages.QUITTER);
                clearChanged();
            }
        });

        for (int i = 0; i<3; i++){
            JPanel panel10 = new JPanel();
            sortiePanel.add(panel10);
        }

        JButton btnLancerPartie = new JButton("Lancer la partie");
        btnLancerPartie.setFont(new Font("Euphemia UCAS", btnLancerPartie.getFont().getStyle(), btnLancerPartie.getFont().getSize()));
        sortiePanel.add(btnLancerPartie);

        btnLancerPartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setChanged();
                notifyObservers(Messages.LANCER_PARTIE);
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
}
