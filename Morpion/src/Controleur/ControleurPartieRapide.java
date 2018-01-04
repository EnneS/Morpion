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
    private VueInformationPartieRapide vueInformationPartieRapide;
    private VueGrille vueGrille;

    private Boolean vueInformationExiste = false;

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
    private ArrayList<Integer> casesGagnantes = new ArrayList<>();
    private int partieGagneeJ1 = 0;
    private int partieGagneeJ2 = 0;

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
            ouvrirVue(getControleurPrincipal().getVueMenu());
            fermerVue(getVueOptionPartieRapide());
        }

        if (arg == MESSAGES.REJOUER){
            resetPartie();
        }

        if (arg == MESSAGES.QUITTER_PARTIE){
            ouvrirVue(getControleurPrincipal().getVueMenu());
            fermerVue(getVueGrille());
            getVueGrille().finalize();

            if(vueInformationExiste) {
                fermerVue(getVueInformationPartieRapide());
                getVueInformationPartieRapide().finalize();
            }
            resetInfos();
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
            MESSAGE_COCHE m = (MESSAGE_COCHE) arg;

            // Update Vue Grille
            System.out.println(joueurActif);
            vueGrille.updateVue(m.getJ(), m.getI(), joueurs.get(joueurActif % joueurs.size()).getSymbole(), joueurActif % joueurs.size(), true);

            // Update modèle grille
            grille.getCases()[m.getJ()][m.getI()].setEtat(joueurs.get(joueurActif % joueurs.size()).getSymbole());

            // Récupération du coup
            dernierCoup[0] = m.getJ();
            dernierCoup[1] = m.getI();

            // On passe au joueur suivant
            joueurActif++;

            // Il est désormais possible d'annuler le coup
            vueGrille.getBtnAnnuler().setEnabled(true);

            // Si le coup est gagnant alors on le met en évidence
            casesGagnantes = getGrille().getCasesGagnantes(m.getJ(), m.getI(), alignementGagnant);
            if (!casesGagnantes.isEmpty()) {
                vueGrille.highlightGagnant(casesGagnantes, joueurs.get((joueurActif - 1) % joueurs.size()).getNom());
                vueGrille.matchFini(false);

                // On récupère le nombre de victoire de chaque joueur
                if (((joueurActif-1)%joueurs.size()) == 0){
                    partieGagneeJ1++;
                } else {
                    partieGagneeJ2++;
                }

                // Et on actualise la vue information
                getVueInformationPartieRapide().updateVue(partieGagneeJ1,partieGagneeJ2);
            } else if (getGrille().grillePleine()) { // Si le match est rempli et qu'il n'y a pas de gagnant
                vueGrille.matchFini(true);
            }
        }

        if (arg == MESSAGES.INFORMATION) {
            if (!vueInformationExiste) {
                setVueInformationPartieRapide(new VueInformationPartieRapide(joueurs.get(0).getNom(), joueurs.get(1).getNom()));
                getVueInformationPartieRapide().ajouterObservateur(this);
                vueInformationExiste = true;
            }

            getVueInformationPartieRapide().updateVue(joueurs.get(0).getNom(), joueurs.get(1).getNom(),partieGagneeJ1,partieGagneeJ2,tailleGrille,alignementGagnant);
            ouvrirVue(vueInformationPartieRapide);
        }

        if (arg == MESSAGES.QUITTER_INFORMATION) {
            fermerVue(vueInformationPartieRapide);
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

        // Question pour le professeur : lorsque je lance une partie, la quitte puis enfin relance une partie, la vue Menu du controleur principal s'ouvre...
        // POURQUOI ?
        // Cette ligne est donc uniquement là pour régler ce bug. Ca fait pas beau.
        fermerVue(controleurPrincipale.getVueMenu());
    }

    public void resetPartie(){
        // Remise à 0 des infos de la partie
        joueurActif = 0;

        // Remise à 0 de la grille
        for(int i = 0; i < getGrille().getN(); i++){
            for (int j = 0; j < getGrille().getN(); j++){
                getGrille().getCases()[i][j].setEtat(SYMBOLES.VIDE);
            }
        }

        // Update de la vue pour recommencer une partie
        vueGrille.resetPartie();
    }

    public void resetInfos(){
        joueurs.clear();
        pseudos.clear();
        joueurActif = 0;
        partieGagneeJ1 = 0;
        partieGagneeJ2 = 0;
        vueInformationExiste = false;
    }

    public VueOptionPartieRapide getVueOptionPartieRapide() {
        return vueOptionPartieRapide;
    }

    public void setVueOptionPartieRapide(VueOptionPartieRapide vueOptionPartieRapide) {
        this.vueOptionPartieRapide = vueOptionPartieRapide;
    }

    public ControleurPrincipale getControleurPrincipal() {
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

    public VueInformationPartieRapide getVueInformationPartieRapide() {
        return vueInformationPartieRapide;
    }

    public void setVueInformationPartieRapide(VueInformationPartieRapide vueInformationPartieRapide) {
        this.vueInformationPartieRapide = vueInformationPartieRapide;
    }
}
