package Controleur;
import Enum.SYMBOLES;
import Views.*;
import Enum.MESSAGES;
import Enum.MESSAGE_COCHE;
import Modele.*;

import java.util.ArrayList;
import java.util.Observable;

public class ControleurPartieRapide extends Controleur {

    private ControleurPrincipale controleurPrincipale;
    private VueOptionPartieRapide vueOptionPartieRapide;
    private VueGrille vueGrille;

    //options de jeu
    private int tailleGrille;
    private int alignementGagnant;
    private ArrayList<String> pseudos;

    // Modèles
    private Grille grille;
    private ArrayList<Joueur>joueurs = new ArrayList<>();

    // Joueur Actif
    private int joueurActif = 0;

    public ControleurPartieRapide(ControleurPrincipale controleurPrincipale) {
        setControleurPrincipale(controleurPrincipale);

        setVueOptionPartieRapide(new VueOptionPartieRapide());
        getVueOptionPartieRapide().ajouterObservateur(this);

        ouvrirVue(getVueOptionPartieRapide());
        fermerVue(controleurPrincipale.getVueMenu());
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg == MESSAGES.LANCER_PARTIE){

            //récupérations options
            tailleGrille = ((VueOptionPartieRapide) o).getTailleGrilleSelectionne();
            alignementGagnant = ((VueOptionPartieRapide) o).getLongeurAlignementSelectionnee();
            pseudos = ((VueOptionPartieRapide) o).getPseudos();

            // Création de la vue Grille
            setVueGrille(new VueGrille(tailleGrille, pseudos));
            getVueGrille().ajouterObservateur(this);

            lancerPartie();
        }

        if (arg == MESSAGES.QUITTER){
            ouvrirVue(controleurPrincipale.getVueMenu());
            fermerVue(getVueOptionPartieRapide());
        }

        if (arg == MESSAGES.QUITTER_PARTIE){
            ouvrirVue(controleurPrincipale.getVueMenu());
            fermerVue(getVueGrille());
            getVueGrille().finalize();
        }

        // ===================
        // COCHE

        // On vérifie si le message reçu est bien de type MESSAGE_COCHE
        if(arg instanceof MESSAGE_COCHE) {
            MESSAGE_COCHE m = (MESSAGE_COCHE)arg;
            System.out.println(m.getJ() + " | " + m.getI());

            grille.getCases()[m.getJ()][m.getI()].setEtat(joueurs.get(joueurActif%joueurs.size()).getSymbole());

            vueGrille.updateVue(m.getJ(), m.getI(), joueurs.get(joueurActif%joueurs.size()).getSymbole(), joueurActif%joueurs.size());

            joueurActif++;

            // =====
            // DEBUG CONSOLE ETAT GRILLE
            for(int i = 0; i < grille.getN(); i++){
                for(int j = 0; j < grille.getN(); j++){
                    if(grille.getCases()[i][j].getEtat() == SYMBOLES.VIDE){
                        System.out.print("-");
                    } else {
                        System.out.print(grille.getCases()[i][j].getEtat().toString());
                    }
                }
                System.out.println();
            }

        }
    }

    public void lancerPartie(){
        // Initialisation des modèles
        grille = new Grille(tailleGrille);
        joueurs.add(new Joueur(pseudos.get(0), SYMBOLES.CROIX));
        joueurs.add(new Joueur(pseudos.get(1), SYMBOLES.ROND));
        ouvrirVue(vueGrille);
        fermerVue(vueOptionPartieRapide);
    }

    public VueOptionPartieRapide getVueOptionPartieRapide() {
        return vueOptionPartieRapide;
    }

    public void setVueOptionPartieRapide(VueOptionPartieRapide vueOptionPartieRapide) {
        this.vueOptionPartieRapide = vueOptionPartieRapide;
    }

    public ControleurPrincipale getControleurPrincipale() {
        return controleurPrincipale;
    }

    public void setControleurPrincipale(ControleurPrincipale controleurPrincipale) {
        this.controleurPrincipale = controleurPrincipale;
    }

    public VueGrille getVueGrille() {
        return vueGrille;
    }

    public void setVueGrille(VueGrille vueGrille) {
        this.vueGrille = vueGrille;
    }

    public Grille getGrille(){
        return grille;
    }
}
