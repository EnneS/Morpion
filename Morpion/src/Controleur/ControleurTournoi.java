package Controleur;

import Enum.SYMBOLES;
import Enum.MESSAGE_COCHE;
import Modele.Grille;
import Modele.Joueur;
import Views.VueGrille;
import Views.VueInformationPartieRapide;
import Views.VueInformationTournoi;
import Views.VueOptionTournoi;

import java.util.ArrayList;
import java.util.Observable;
import Enum.MESSAGES;

public class ControleurTournoi extends Controleur{

    private ControleurPrincipale controleurPrincipale;
    private VueOptionTournoi vueOptionTournoi;
    private VueGrille vueGrille;
    private VueInformationTournoi vueInformationTournoi;

    //options de jeu
    private int tailleGrille;
    private int alignementGagnant;
    private ArrayList<String> pseudos;
    private int nombreJoueur;

    // Infos sur la partie
    private int joueurActif = 0;
    private int dernierCoup[] = new int[2];
    private ArrayList<Integer> casesGagnantes = new ArrayList<>();

    // Infos sur le tournoi
    private int indexJoueurEnLice = 0;

    // Modèles
    private Grille grille;
    private ArrayList<Joueur> joueursJouant = new ArrayList<>();
    private ArrayList<Joueur> joueursEnLice = new ArrayList<>();
    private ArrayList<Joueur> joueursNext = new ArrayList<>();

    public ControleurTournoi(ControleurPrincipale controleurPrincipale){
        setControleurPrincipale(controleurPrincipale);

        setVueOptionTournoi(new VueOptionTournoi());
        getVueOptionTournoi().ajouterObservateur(this);

        ouvrirVue(getVueOptionTournoi());
        fermerVue(controleurPrincipale.getVueMenu());
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg == MESSAGES.LANCER_PARTIE){
            // Récupération des paramètres du tournoi
            tailleGrille = ((VueOptionTournoi) o).getTailleGrilleSelectionne();
            alignementGagnant = ((VueOptionTournoi) o).getLongeurAlignementSelectionnee();
            pseudos = ((VueOptionTournoi) o).getPseudos();
            nombreJoueur = ((VueOptionTournoi) o).getNombreJoueur();

            //on créer la vueInformation
            setVueInformationTournoi(new VueInformationTournoi(nombreJoueur));
            getVueInformationTournoi().ajouterObservateur(this);

            // Lancement du tournoi
            lancerTournoi();
        }

        if (arg == MESSAGES.QUITTER){
            ouvrirVue(controleurPrincipale.getVueMenu());
            fermerVue(getVueOptionTournoi());
        }

        if (arg == MESSAGES.QUITTER_PARTIE){
            // Fermeture / Ouverture des vues correspondantes
            ouvrirVue(controleurPrincipale.getVueMenu());
            fermerVue(getVueGrille());
            // Destruction de la vue Grille
            getVueGrille().finalize();
            // Remise à 0 des paramètres du tournois
            resetTournoi();
            //Destruction de la vue Information
            getVueInformationTournoi().finalize();
        }

        if (arg == MESSAGES.REJOUER){
            resetPartie();
        }

        if (arg == MESSAGES.ANNULER_COUP){
            // On annule le dernier coup sur la vue
            vueGrille.updateVue(dernierCoup[0], dernierCoup[1], SYMBOLES.VIDE, joueurActif% joueursJouant.size(), false);

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
            vueGrille.updateVue(m.getJ(), m.getI(), joueursEnLice.get(joueurActif%joueursJouant.size()).getSymbole(), joueurActif% joueursJouant.size(), true);

            // Update modèle grille
            grille.getCases()[m.getJ()][m.getI()].setEtat(joueursEnLice.get(joueurActif% joueursJouant.size()).getSymbole());

            // Récupération du coup pour pouvoir l'annuler
            dernierCoup[0] = m.getJ();
            dernierCoup[1] = m.getI();

            // On passe la main au joueur suivant
            joueurActif++;

            // Il est désormais possible d'annuler le coup
            vueGrille.getBtnAnnuler().setEnabled(true);

            // Si le coup est gagnant alors on le met en évidence
            casesGagnantes = getGrille().getCasesGagnantes(m.getJ(), m.getI(), alignementGagnant);
            if (!casesGagnantes.isEmpty()){
                vueGrille.highlightGagnant(casesGagnantes, joueursJouant.get((joueurActif-1)%joueursJouant.size()).getNom());
                nextPartie();
            } else if (getGrille().grillePleine()){ // Si le match est rempli et qu'il n'y a pas de gagnant
                vueGrille.matchFini(true);
            }
        }

        if (arg == MESSAGES.INFORMATION) {
            ouvrirVue(getVueInformationTournoi());
        }

        if (arg == MESSAGES.QUITTER_INFORMATION) {
            fermerVue(getVueInformationTournoi());
        }
    }

    public void nextPartie(){
        // Qualification du joueur gagnant (soit joueurActif-1 parmis ceux qui jouaient !)
        joueursNext.add(joueursJouant.get((joueurActif - 1) % joueursJouant.size()));

        // Affichage du gagnant dans la vue information
        vueInformationTournoi.updateVue(joueursJouant,joueursNext);

        // =================
        // RESET DE LA PARTIE

        // Remise à 0 de la grille (modèle)
        for(int i = 0; i < getGrille().getN(); i++){
            for (int j = 0; j < getGrille().getN(); j++){
                getGrille().getCases()[i][j].setEtat(SYMBOLES.VIDE);
            }
        }

        joueurActif = 0; // Le joueur actif est le premier de ceux qui jouent
        indexJoueurEnLice += 2; // Les joueurs en lices qui vont jouer sont les 2 prochains

        // Si ces joueurs existent (Le premier des derniers joueurs à jouer est l'avant dernier de ceux en lices)
        if (indexJoueurEnLice < joueursEnLice.size()-1) {
            // Remplacement des joueurs qui vont jouer par les prochains en lice
            joueursJouant.clear();
            joueursJouant.add(joueursEnLice.get(indexJoueurEnLice)); joueursJouant.add(joueursEnLice.get(indexJoueurEnLice +1));

            // Update de la vue en fonction des joueurs qui jouent
            vueGrille.nextPartie(joueursJouant.get(0).getNom(), joueursJouant.get(1).getNom());
        } else {
            // Sinon on passe au round suivant ! (joueurs en lice épuisés)
            nextRound();
        }
    }

    public void nextRound(){
        // S'il y a plus d'un qualifié
        if(joueursNext.size() > 1) {
            // ================
            // RESET DU ROUND
            indexJoueurEnLice = 0; // On remet l'index des joueurs en lice à 0
            joueursEnLice.clear(); // On remplace les joueurs en lice par ceux qualifiés
            joueursEnLice.addAll(joueursNext);
            joueursNext.clear(); // On remet à 0 les joueurs qualifiés

            // Réassignation des symboles des joueurs
            SYMBOLES symbole;
            for (int i = 0; i < joueursEnLice.size(); i++){
                if(i%2 == 0) { symbole = SYMBOLES.CROIX; } else { symbole = SYMBOLES.ROND; }
                joueursEnLice.get(i).setSYMBOLES(symbole);
            }

            // Affichage de tout les joueurs en lice pour le round actuel
            vueInformationTournoi.updateVue(joueursEnLice);
            // ================
            // DEBUT DE LA PREMIERE PARTIE DU ROUND
            joueursJouant.clear(); // On remplace les joueurs en train de jouer par les suivants
            joueursJouant.add(joueursEnLice.get(indexJoueurEnLice)); joueursJouant.add(joueursEnLice.get(indexJoueurEnLice +1));
            vueGrille.nextPartie(joueursJouant.get(0).getNom(), joueursJouant.get(1).getNom()); // Update de la vue en fonction des joueurs en train de jouer

        } else { // Sinon, nous avons un gagnant
            vueGrille.getHautGrilleLabel().setText(joueursNext.get(0).getNom() + " a gagné le tournoi !");
        }
    }

    public void lancerTournoi(){
        grille = new Grille(tailleGrille);

        SYMBOLES symbole;
        for (int i = 0; i < nombreJoueur; i++){
            if (i%2 == 0) { symbole = SYMBOLES.CROIX; } else { symbole = SYMBOLES.ROND; }
            Joueur joueur = new Joueur(pseudos.get(i), symbole);
            joueursEnLice.add(joueur);
        }

        // Affichage de tout les joueurs en lice pour le round actuel
        vueInformationTournoi.updateVue(joueursEnLice);

        // Les joueurs jouant sont les 2 premiers de ceux en lice
        joueursJouant.add(joueursEnLice.get(indexJoueurEnLice)); joueursJouant.add(joueursEnLice.get(indexJoueurEnLice +1));

        // On initialise la vue grille
        setVueGrille(new VueGrille(tailleGrille, joueursJouant.get(indexJoueurEnLice).getNom(), joueursJouant.get(indexJoueurEnLice +1).getNom()));
        getVueGrille().ajouterObservateur(this);

        // On l'affiche
        ouvrirVue(vueGrille);
        fermerVue(vueOptionTournoi);

        // IDEM : Question pour le professeur : lorsque je lance une partie, la quitte puis enfin relance une partie, la vue Menu du controleur principal s'ouvre...
        // POURQUOI ?
        // Cette ligne est donc uniquement là pour régler ce bug. Ca fait pas beau.
        fermerVue(controleurPrincipale.getVueMenu());
    }

    public void resetTournoi(){
        joueursJouant.clear();
        joueursEnLice.clear();
        joueursNext.clear();
        joueurActif = 0;
        indexJoueurEnLice = 0;
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
    public ControleurPrincipale getControleurPrincipale() {
        return controleurPrincipale;
    }

    public void setControleurPrincipale(ControleurPrincipale controleurPrincipale) {
        this.controleurPrincipale = controleurPrincipale;
    }

    public VueOptionTournoi getVueOptionTournoi() {
        return vueOptionTournoi;
    }

    public void setVueOptionTournoi(VueOptionTournoi vueOptionTournoi) {
        this.vueOptionTournoi = vueOptionTournoi;
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

    public VueInformationTournoi getVueInformationTournoi() {
        return vueInformationTournoi;
    }

    public void setVueInformationTournoi(VueInformationTournoi vueInformationTournoi) {
        this.vueInformationTournoi = vueInformationTournoi;
    }
}
