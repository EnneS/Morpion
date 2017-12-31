package Controleur;

import Enum.Messages;
import Views.*;
import java.util.Observable;

public class ControleurPrincipale extends Controleur {

    private VueMenu vueMenu = new VueMenu();
    private VueOptionPartieRapide vueOptionPartieRapide = new VueOptionPartieRapide();
    private VueOptionTournoi vueOptionTournoi = new VueOptionTournoi();
    private VueGrille vueGrille = new VueGrille();
    private VueRegle vueRegle = new VueRegle();
    private VueInformation vueInformation = new VueInformation();

    public ControleurPrincipale() {

        getVueMenu().ajouterObservateur(this);
        getVueRegle().ajouterObservateur(this);
        ouvrirVue(getVueMenu());

    }


    @Override
    public void update(Observable o, Object arg) {

        //redirection vers controleurs secondaires

        if (arg == Messages.PARTIERAPIDE) {
            ouvrirVue(getVueOptionPartieRapide());
            fermerVue(getVueMenu());
            ControleurPartieRapide controleurPartieRapide = new ControleurPartieRapide(this, getVueOptionPartieRapide(), getVueGrille());

        }

        if (arg == Messages.TOURNOI) {
            ouvrirVue(getVueOptionTournoi());
            fermerVue(getVueMenu());
            ControleurTournoi controleurTournoi = new ControleurTournoi(this, getVueOptionTournoi(), getVueGrille());

        }

        //traitement des autres boutons du menu

        if (arg == Messages.REGLES) {
            ouvrirVue(getVueRegle());
        }

        if (arg == Messages.FERMER_REGLES) {
            fermerVue(getVueRegle());
        }

        if (arg == Messages.QUITTER) {
            fermerVue(getVueMenu());
        }


    }

    public VueMenu getVueMenu() {
        return vueMenu;
    }

    public void setVueMenu(VueMenu vueMenu) {
        this.vueMenu = vueMenu;
    }

    public VueOptionPartieRapide getVueOptionPartieRapide() {
        return vueOptionPartieRapide;
    }

    public void setVueOptionPartieRapide(VueOptionPartieRapide vueOptionPartieRapide) {
        this.vueOptionPartieRapide = vueOptionPartieRapide;
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

    public VueRegle getVueRegle() {
        return vueRegle;
    }

    public void setVueRegle(VueRegle vueRegle) {
        this.vueRegle = vueRegle;
    }

    public VueInformation getVueInformation() {
        return vueInformation;
    }

    public void setVueInformation(VueInformation vueInformation) {
        this.vueInformation = vueInformation;
    }
}


