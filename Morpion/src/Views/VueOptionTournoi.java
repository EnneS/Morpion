package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Enum.MESSAGES;

public class VueOptionTournoi extends Vue {

    private final JFrame window;
    private final JComboBox combo = new JComboBox();

    public VueOptionTournoi(){

        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1280,720);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setResizable(false);
        window.setTitle("Jeu du Morpion");

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
        JLabel titreLabel = new JLabel("Option du tournoi");
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

        JPanel choixJoueurPanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(choixJoueurPanel);

        JLabel texteChoixJoueurLabel = new JLabel("                           Veuillez entrez le nombre de joueur");
        texteChoixJoueurLabel.setFont(new Font("Euphemia UCAS", texteChoixJoueurLabel.getFont().getStyle(), texteChoixJoueurLabel.getFont().getSize()));
        choixJoueurPanel.add(texteChoixJoueurLabel);

        JPanel selectionChoixJoueurPanel = new JPanel(new GridLayout(1,7));
        JPanel panel5 = new JPanel();
        selectionChoixJoueurPanel.add(panel5);
        JButton btn4 = new JButton("4");
        btn4.setFont(new Font("Euphemia UCAS", btn4.getFont().getStyle(), btn4.getFont().getSize()));
        selectionChoixJoueurPanel.add(btn4);
        JButton btn8 = new JButton("8");
        btn8.setFont(new Font("Euphemia UCAS", btn8.getFont().getStyle(), btn8.getFont().getSize()));
        selectionChoixJoueurPanel.add(btn8);
        JButton btn16 = new JButton("16");
        btn16.setFont(new Font("Euphemia UCAS", btn16.getFont().getStyle(), btn16.getFont().getSize()));
        selectionChoixJoueurPanel.add(btn16);
        JButton btn32 = new JButton("32");
        btn32.setFont(new Font("Euphemia UCAS", btn32.getFont().getStyle(), btn32.getFont().getSize()));
        selectionChoixJoueurPanel.add(btn32);
        JPanel panel6 = new JPanel();
        selectionChoixJoueurPanel.add(panel6);
        JPanel panel7 = new JPanel();
        selectionChoixJoueurPanel.add(panel7);
        choixJoueurPanel.add(selectionChoixJoueurPanel);

        JPanel panel4 = new JPanel();
        choixJoueurPanel.add(panel4);

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
                notifyObservers(MESSAGES.QUITTER);
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

    @Override
    public void setVisible(Boolean b) {
        window.setVisible(b);
    }

}
