package Controleur;

import Views.VueGrille;
import Views.VueOptionTournoi;

import java.util.ArrayList;
import java.util.Observable;
import Enum.MESSAGES;

public class ControleurTournoi extends Controleur{

    private Controleur.ControleurPrincipale controleurPrincipale;
    private VueOptionTournoi vueOptionTournoi;
    private VueGrille vueGrille;

    //options de jeu
    private int tailleGrille;
    private int alignementGagnant;
    private ArrayList<String> pseudos;
    private int nombreJoueur;

    public ControleurTournoi(Controleur.ControleurPrincipale controleurPrincipale){
        setControleurPrincipale(controleurPrincipale);

        setVueOptionTournoi(new VueOptionTournoi());
        getVueOptionTournoi().ajouterObservateur(this);

        ouvrirVue(getVueOptionTournoi());
        fermerVue(controleurPrincipale.getVueMenu());
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg == MESSAGES.LANCER_PARTIE){

            //récupérations options
            tailleGrille = ((VueOptionTournoi) o).getTailleGrilleSelectionne();
            alignementGagnant = ((VueOptionTournoi) o).getLongeurAlignementSelectionnee();
            pseudos = ((VueOptionTournoi) o).getPseudos();
            nombreJoueur = ((VueOptionTournoi) o).getNombreJoueur();

            setVueGrille(new VueGrille(tailleGrille, pseudos));
            getVueGrille().ajouterObservateur(this);

            ouvrirVue(vueGrille);
            fermerVue(vueOptionTournoi);

            lancerTournoi();
        }

        if (arg == MESSAGES.QUITTER){
            ouvrirVue(controleurPrincipale.getVueMenu());
            fermerVue(getVueOptionTournoi());
        }

        if (arg == MESSAGES.QUITTER_PARTIE){
            ouvrirVue(controleurPrincipale.getVueMenu());
            fermerVue(getVueGrille());
            getVueGrille().finalize();
        }

    }


    public void lancerTournoi(){
        // déroulement tournoi à implémenter
    }

    public Controleur.ControleurPrincipale getControleurPrincipale() {
        return controleurPrincipale;
    }

    public void setControleurPrincipale(Controleur.ControleurPrincipale controleurPrincipale) {
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
}
