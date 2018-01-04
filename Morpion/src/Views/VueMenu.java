package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Enum.MESSAGES;

public class VueMenu extends Vue {

    private final JFrame window;

    public VueMenu() {
        window = new JFrame();
        window.setSize(1280,720);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Jeu du Morpion");
        window.setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);

        JPanel hautPanel = new JPanel();
        mainPanel.add(hautPanel, BorderLayout.NORTH);

        JPanel centrePanel = new JPanel(new GridLayout(3,3));
        mainPanel.add(centrePanel, BorderLayout.CENTER);

        for(int i = 0; i<9; i++){
            if(i == 1 || i == 4 || i == 6 || i == 8){
                // GridLayout utilisé plus tard pour les boutons Quitter et Règles
                GridLayout gridQuitterRegles = new GridLayout(4, 3);
                gridQuitterRegles.setHgap(-200);
                if(i == 1) {
                    JPanel panelTitre = new JPanel(new GridLayout(4, 1));
                    centrePanel.add(panelTitre);
                    for (int j = 0; j < 4; j++) {
                        if (j == 3) {
                            JLabel titreVue = new JLabel("Jeu du Morpion", SwingConstants.CENTER);
                            titreVue.setFont(new Font("Euphemia UCAS", Font.BOLD, titreVue.getFont().getSize()*3));
                            panelTitre.add(titreVue);
                        } else {
                            panelTitre.add(new JLabel());
                        }
                    }
                } else if (i == 4){
                    GridLayout gridBoutons = new GridLayout(3, 2);
                    gridBoutons.setHgap(35);
                    JPanel panelBoutons = new JPanel(gridBoutons);
                    centrePanel.add(panelBoutons);
                    for (int j = 0; j < 6; j++){
                        if (j ==2 || j == 3) {
                            if (j ==2) {
                                JButton btnPartieRapide = new JButton("Partie Rapide");
                                btnPartieRapide.setFont(regular);
                                panelBoutons.add(btnPartieRapide);

                                btnPartieRapide.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        setChanged();
                                        notifyObservers(MESSAGES.PARTIERAPIDE);
                                        clearChanged();
                                    }
                                });

                            } else {
                                JButton btnTournoi = new JButton("Tournoi");
                                btnTournoi.setFont(regular);
                                panelBoutons.add(btnTournoi);

                                btnTournoi.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        setChanged();
                                        notifyObservers(MESSAGES.TOURNOI);
                                        clearChanged();
                                    }
                                });
                            }
                        } else {
                            panelBoutons.add(new JLabel());
                        }
                    }
                } else if (i == 6){
                    JPanel panelQuitter = new JPanel(gridQuitterRegles);
                    centrePanel.add(panelQuitter);
                    for (int j = 0; j < 12; j++){
                        if(j == 7){
                            JButton btnQuitter = new JButton("Quitter");
                            btnQuitter.setFont(regular);
                            panelQuitter.add(btnQuitter);

                            btnQuitter.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    setChanged();
                                    notifyObservers(MESSAGES.QUITTER);
                                    clearChanged();
                                }
                            });
                        } else {
                            panelQuitter.add(new JLabel());
                        }
                    }
                } else if (i == 8) {
                    JPanel panelQuitter = new JPanel(gridQuitterRegles);
                    centrePanel.add(panelQuitter);
                    for (int j = 0; j < 12; j++) {
                        if (j == 7) {
                            JButton btnRegle = new JButton("Règles");
                            btnRegle.setFont(regular);
                            panelQuitter.add(btnRegle);

                            btnRegle.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    setChanged();
                                    notifyObservers(MESSAGES.REGLES);
                                    clearChanged();
                                }
                            });
                        } else {
                            panelQuitter.add(new JLabel());
                        }
                    }
                }
            } else {
                centrePanel.add(new JLabel());
            }
        }

    }

    public void setVisible(Boolean b){
        this.window.setVisible(b);
    }
}
