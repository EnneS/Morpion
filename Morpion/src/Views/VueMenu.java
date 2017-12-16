package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueMenu {

    private final JFrame window;
    JButton btnPartieRapide;
    /*private final JButton btnTournoi;
    private final JButton btnFermer;
    private final JButton btnRegles; */

    public VueMenu() {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1280,720);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);

        /* public void paintComponent(Graphics graphics){
            graphics.drawString("Jeu de morpion",10,20);
        } */
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);

        JPanel hautPanel = new JPanel();
        mainPanel.add(hautPanel, BorderLayout.NORTH);


        JPanel centrePanel = new JPanel(new GridLayout(3,3));
        mainPanel.add(centrePanel, BorderLayout.CENTER);

        for(int i = 0; i<9; i++){
            if(i == 1 || i == 4 || i == 6 || i == 8){
                if(i == 1) {
                    JPanel panelTitre = new JPanel(new GridLayout(4, 1));
                    centrePanel.add(panelTitre);
                    for (int j = 0; j < 4; j++) {
                        if (j == 3) {
                            JLabel titreVue = new JLabel("Jeu du Morpion", SwingConstants.CENTER);
                            titreVue.setFont(new Font(titreVue.getFont().getName(), titreVue.getFont().getStyle(), (int) (titreVue.getFont().getSize() * 3)));
                            panelTitre.add(titreVue);

                        } else {
                            panelTitre.add(new JLabel(""));
                        }
                    }
                } else if (i == 4){
                    GridLayout gridBoutons = new GridLayout(3, 2);
                    gridBoutons.setHgap(50);
                    JPanel panelBoutons = new JPanel(gridBoutons);
                    centrePanel.add(panelBoutons);
                    for (int j = 0; j < 6; j++){
                        if (j ==2 || j == 3) {
                            if (j ==2) {
                                panelBoutons.add(new JButton("Partie Rapide"));
                            } else {
                                panelBoutons.add(new JButton("Tournoi"));
                            }
                        } else {
                            panelBoutons.add(new JLabel(""));
                        }
                    }
                } else if (i == 6){
                    GridLayout gridQuitter = new GridLayout(4, 3);
                    gridQuitter.setHgap(-200);
                    JPanel panelQuitter = new JPanel(gridQuitter);
                    centrePanel.add(panelQuitter);
                    for (int j = 0; j < 12; j++){
                        if(j == 7){
                            panelQuitter.add(new JButton("Quitter"));
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


    public void afficher(){
        this.window.setVisible(true);
    }



}
