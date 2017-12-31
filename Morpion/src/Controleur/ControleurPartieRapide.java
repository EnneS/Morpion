package Controleur;
import Enum.Messages;
import Views.VueGrille;
import Views.VueOptionPartieRapide;

import java.util.Observable;

public class ControleurPartieRapide extends Controleur {

    private ControleurPrincipale controleurPrincipale;
    private VueOptionPartieRapide vueOptionPartieRapide;
    private VueGrille vueGrille;


    public ControleurPartieRapide(ControleurPrincipale controleurPrincipale, VueOptionPartieRapide vueOptionPartieRapide, VueGrille vueGrille) {
        setControleurPrincipale(controleurPrincipale);
        setVueOptionPartieRapide(vueOptionPartieRapide);
        getVueOptionPartieRapide().ajouterObservateur(this);
        setVueGrille(vueGrille);
        getVueGrille().ajouterObservateur(this);
    }

    //destructeur
    public void finalize(){}

    @Override
    public void update(Observable o, Object arg) {

        if (arg == Messages.LANCER_PARTIE){
            ouvrirVue(vueGrille);
            fermerVue(vueOptionPartieRapide);
        }

        if (arg == Messages.QUITTER){
            ouvrirVue(controleurPrincipale.getVueMenu());
            fermerVue(getVueOptionPartieRapide());
            this.finalize();
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
