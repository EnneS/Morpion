package Views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Enum.MESSAGES;
import Modele.Joueur;

public class VueInformationTournoi extends Vue {

    private final JFrame window;
    private ArrayList<JLabel> JLabels = new ArrayList<>();
    private int indexJLabels = 0;

    public VueInformationTournoi(int nbJoueur){

        //paramètres fenêtre
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1280,720);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Informations tournoi");
        window.setResizable(false);

        //construction panel principal
        int nbRound, nbRows;
        switch (nbJoueur){
            case 4: nbRound = 2; break;
            case 8: nbRound = 3; break;
            case 16: nbRound = 4; break;
            default: nbRound = 5;
        }
        nbRows = nbRound+2;

        JPanel mainPanel = new JPanel(new GridLayout(nbRows,1));
        window.add(mainPanel);


        //construction panel titre
        JPanel titrePanel = new JPanel(new GridLayout(3,1));
        mainPanel.add(titrePanel);

        for (int i = 0; i<1 ; i++){
            JPanel panel = new JPanel();
            titrePanel.add(panel);
        }

        JLabel titreLabel = new JLabel("Informations tournoi");
        titreLabel.setFont(bold);
        titreLabel.setHorizontalAlignment(0);
        titrePanel.add(titreLabel);

        for (int i = 0; i<1 ; i++) {
            JPanel panel = new JPanel();
            titrePanel.add(panel);
        }


        //construction récap tournoi avec les gagants dans les différents rounds

        Border border = BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK);

        for (int i = nbRound; i>0;i--){


            int nbCols;

            switch (i){
                case 5: nbRows = 4; nbCols = 4; break;
                case 4: nbRows = 2; nbCols = 4; break;
                case 3: nbRows = 1; nbCols = 4; break;
                case 2: nbRows = 1; nbCols = 2; break;
                default: nbRows = 1; nbCols = 1;
            }

            //construction des différents pannels selon le round
            JPanel panel = new JPanel(new GridLayout(nbRows,nbCols));

            int niveauTournoi = (int) Math.pow(2,i-1);

            if (i==1){
                TitledBorder recapBorder = BorderFactory.createTitledBorder(border, "Finale",2,0,semiBold2);
                panel.setBorder(recapBorder);
            } else {
                TitledBorder recapBorder = BorderFactory.createTitledBorder(border, "1/"+niveauTournoi+"e de final",2,0,semiBold2);
                panel.setBorder(recapBorder);
            }

            mainPanel.add(panel);

            //remplissage des pannels

            for (int nbCase=0; nbCase<nbRows*nbCols;nbCase++){
                JLabel jLabel = new JLabel();
                jLabel.setText("-");
                jLabel.setFont(regular);
                JLabels.add(jLabel);
                panel.add(jLabel);
                jLabel.setHorizontalAlignment(0);
            }

        }

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

    public void finalize() {}

    @Override
    public void setVisible(Boolean b) {
        window.setVisible(b);
    }

    public void updateVue(ArrayList<Joueur> joueursJouant, ArrayList<Joueur> joueurGagnant ){
        // On actualise la gagnant du match joué
        JLabels.get(indexJLabels).setText(joueursJouant.get(0).getNom()+" VS " +joueursJouant.get(1).getNom()+" : "+joueurGagnant.get((joueurGagnant.size()-1)).getNom());

        // On passe au prochain match
        indexJLabels++;
    }

    public void updateVue(ArrayList<Joueur> joueursEnLice){
        int i = 0 ;
        while(i < joueursEnLice.size() - 1){ // -1 car le dernier match est affiché grâce à l'avant dernier joueur
            JLabels.get((i/2)+indexJLabels).setText(joueursEnLice.get(i).getNom() + " VS " + joueursEnLice.get(i+1).getNom());
            i += 2; // On augmente de 2 car une partie est constituée de 2 joueurs..
        }
    }
}
