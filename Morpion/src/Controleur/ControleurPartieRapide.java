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

    // Infos sur la partie
    private int joueurActif = 0;
    private int dernierCoup[] = new int[2];

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
            setVueGrille(new VueGrille(tailleGrille, pseudos.get(0), pseudos.get(1)));
            getVueGrille().ajouterObservateur(this);

            // Lancement de la partie
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
            resetPartie();
        }

        if (arg == MESSAGES.ANNULER_COUP){
            // On annule le dernier coup sur la vue
            vueGrille.updateVue(dernierCoup[0], dernierCoup[1], SYMBOLES.VIDE, joueurActif%joueurs.size(), false);

            // Et sur le modèle
            grille.getCases()[dernierCoup[0]][dernierCoup[1]].setEtat(SYMBOLES.VIDE);

            // On redonne la main au joueur précédent
            joueurActif--;

            // On ne peut plus annuler de coup désormais
            vueGrille.getBtnAnnuler().setEnabled(false);
        }

        // ===================
        // COCHAGE D'UNE CASE

        // On vérifie si le message reçu est bien de type MESSAGE_COCHE (cela signifie que la joueur a coché une case)
        if(arg instanceof MESSAGE_COCHE) {
            MESSAGE_COCHE m = (MESSAGE_COCHE)arg;

            // Update Vue Grille
            vueGrille.updateVue(m.getJ(), m.getI(), joueurs.get(joueurActif%joueurs.size()).getSymbole(), joueurActif%joueurs.size(), true);

            // Update modèle grille
            grille.getCases()[m.getJ()][m.getI()].setEtat(joueurs.get(joueurActif%joueurs.size()).getSymbole());

            // Récupération du coup
            dernierCoup[0] = m.getJ();
            dernierCoup[1] = m.getI();

            // On passe au joueur suivant
            joueurActif++;

            // Il est désormais possible d'annuler le coup
            vueGrille.getBtnAnnuler().setEnabled(true);

            // Si le coup est gagnant alors on le met en évidence
            vueGrille.highlightGagnant(getGrille().getCasesGagnantes(m.getJ(), m.getI(), alignementGagnant));



            // ==================================
            // DEBUG CONSOLE ETAT GRILLE MODELE
            for(int i = 0; i < grille.getN(); i++){
                for(int j = 0; j < grille.getN(); j++){
                    if(grille.getCases()[i][j].getEtat() == SYMBOLES.VIDE){
                        //System.out.print("-");
                    } else {
                        //System.out.print(grille.getCases()[i][j].getEtat().toString());
                    }
                }
                //System.out.println();
            }
            //System.out.println("================");
        }
    }

    public void lancerPartie(){
        // Initialisation des modèles
        grille = new Grille(tailleGrille);
        joueurs.add(new Joueur(pseudos.get(0), SYMBOLES.CROIX));
        joueurs.add(new Joueur(pseudos.get(1), SYMBOLES.ROND));

        // Affichage de la vue
        ouvrirVue(vueGrille);
        fermerVue(vueOptionPartieRapide);
    }

    public void resetPartie(){
        joueurs.clear();
        pseudos.clear();
        joueurActif = 0;
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
