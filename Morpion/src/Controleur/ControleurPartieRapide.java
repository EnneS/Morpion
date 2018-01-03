package Controleur;
import Views.*;
import Enum.MESSAGES;

import java.util.ArrayList;
import java.util.Observable;

public class ControleurPartieRapide extends Controleur {

    private Controleur.ControleurPrincipale controleurPrincipale;
    private VueOptionPartieRapide vueOptionPartieRapide;
    private VueGrille vueGrille;

    //options de jeu
    private int tailleGrille;
    private int alignementGagnant;
    private ArrayList<String> pseudos;


    public ControleurPartieRapide(Controleur.ControleurPrincipale controleurPrincipale) {
        setControleurPrincipale(controleurPrincipale);

        setVueOptionPartieRapide(controleurPrincipale.getVueOptionPartieRapide());
        getVueOptionPartieRapide().ajouterObservateur(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg == MESSAGES.LANCER_PARTIE){

            //récupérations options
            tailleGrille = ((VueOptionPartieRapide) o).getTailleGrilleSelectionne();
            alignementGagnant = ((VueOptionPartieRapide) o).getLongeurAlignementSelectionnee();
            pseudos = ((VueOptionPartieRapide) o).getPseudos();

            setVueGrille(new VueGrille(tailleGrille, pseudos));
            getVueGrille().ajouterObservateur(this);

            ouvrirVue(vueGrille);
            fermerVue(vueOptionPartieRapide);

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

    }

    public void lancerPartie(){
        // déroulement partie à implémenter
    }

    public VueOptionPartieRapide getVueOptionPartieRapide() {
        return vueOptionPartieRapide;
    }

    public void setVueOptionPartieRapide(VueOptionPartieRapide vueOptionPartieRapide) {
        this.vueOptionPartieRapide = vueOptionPartieRapide;
    }

    public Controleur.ControleurPrincipale getControleurPrincipale() {
        return controleurPrincipale;
    }

    public void setControleurPrincipale(Controleur.ControleurPrincipale controleurPrincipale) {
        this.controleurPrincipale = controleurPrincipale;
    }

    public VueGrille getVueGrille() {
        return vueGrille;
    }

    public void setVueGrille(VueGrille vueGrille) {
        this.vueGrille = vueGrille;
    }
}
