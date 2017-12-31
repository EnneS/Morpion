package Controleur;

import Views.VueGrille;
import Views.VueOptionTournoi;
import Enum.Messages;
import java.util.Observable;

public class ControleurTournoi extends Controleur{

    private ControleurPrincipale controleurPrincipale;
    private VueOptionTournoi vueOptionTournoi;
    private VueGrille vueGrille;

    public ControleurTournoi(ControleurPrincipale controleurPrincipale, VueOptionTournoi vueOptionTournoi, VueGrille vueGrille){
        setControleurPrincipale(controleurPrincipale);
        setVueOptionTournoi(vueOptionTournoi);
        vueOptionTournoi.ajouterObservateur(this);
        setVueGrille(vueGrille);
        getVueGrille().ajouterObservateur(this);
    }

    public void finalize(){}

    @Override
    public void update(Observable o, Object arg) {

        if (arg == Messages.LANCER_PARTIE){
            ouvrirVue(vueGrille);
            fermerVue(vueOptionTournoi);
        }

        if (arg == Messages.QUITTER){
            ouvrirVue(controleurPrincipale.getVueMenu());
            fermerVue(getVueOptionTournoi());
            this.finalize();
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
