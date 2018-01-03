package Controleur;
import Views.*;
import Enum.MESSAGES;
import java.util.Observable;

public class ControleurPartieRapide extends Controleur {

    private ControleurPrincipale controleurPrincipale;
    private VueOptionPartieRapide vueOptionPartieRapide;
    private VueGrille vueGrille;

    //options de jeu
    private int tailleGrille;
    private int alignementGagnant;


    public ControleurPartieRapide(ControleurPrincipale controleurPrincipale) {
        setControleurPrincipale(controleurPrincipale);

        setVueOptionPartieRapide(controleurPrincipale.getVueOptionPartieRapide());
        getVueOptionPartieRapide().ajouterObservateur(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg == MESSAGES.LANCER_PARTIE){

            //récupérations options
            tailleGrille = ((VueOptionPartieRapide) o).getTailleGrilleSelectionne();
            alignementGagnant = ((VueOptionPartieRapide) o ).getLongeurAlignementSelectionnee();

            setVueGrille(new VueGrille(tailleGrille));
            getVueGrille().ajouterObservateur(this);

            ouvrirVue(vueGrille);
            fermerVue(vueOptionPartieRapide);

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
}
