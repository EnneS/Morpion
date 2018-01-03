package Controleur;

import Views.VueGrille;
import Views.VueOptionTournoi;

import java.util.Observable;
import Enum.MESSAGES;

public class ControleurTournoi extends Controleur{

    private ControleurPrincipale controleurPrincipale;
    private VueOptionTournoi vueOptionTournoi;
    private VueGrille vueGrille;

    public ControleurTournoi(ControleurPrincipale controleurPrincipale){
        setControleurPrincipale(controleurPrincipale);

        setVueOptionTournoi(controleurPrincipale.getVueOptionTournoi());
        getVueOptionTournoi().ajouterObservateur(this);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg == MESSAGES.LANCER_PARTIE){
            ouvrirVue(vueGrille);
            fermerVue(vueOptionTournoi);
        }

        if (arg == MESSAGES.QUITTER){
            ouvrirVue(controleurPrincipale.getVueMenu());
            fermerVue(getVueOptionTournoi());
        }



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
}
