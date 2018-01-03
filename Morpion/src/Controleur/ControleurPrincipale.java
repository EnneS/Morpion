package Controleur;

import Views.*;
import java.util.Observable;
import Enum.MESSAGES;

public class ControleurPrincipale extends Controleur {

    private VueMenu vueMenu = new VueMenu();
    private VueRegle vueRegle;

    private ControleurPartieRapide controleurPartieRapide;
    private ControleurTournoi controleurTournoi;

    private Boolean controleurPartieRapideExiste = false;
    private Boolean controleurTournoiExiste = false;

    private Boolean vueRegleExiste = false;

    public ControleurPrincipale() {
        getVueMenu().ajouterObservateur(this);
        ouvrirVue(getVueMenu());

    }

    @Override
    public void update(Observable o, Object arg) {

        //redirection vers controleurs secondaires

        if (arg == MESSAGES.PARTIERAPIDE) {

            if (!controleurPartieRapideExiste){
                controleurPartieRapide = new ControleurPartieRapide(this);
                controleurPartieRapideExiste = true;
            } else {
                ouvrirVue(getControleurPartieRapide().getVueOptionPartieRapide());
            }
        }

        if (arg == MESSAGES.TOURNOI) {

            if (!controleurTournoiExiste){
                controleurTournoi = new ControleurTournoi(this);
                controleurTournoiExiste = true;
            } else {
                ouvrirVue(getControleurTournoi().getVueOptionTournoi());
            }
        }

        //traitement des autres boutons du menu

        if (arg == MESSAGES.REGLES) {
            if (!vueRegleExiste){
                vueRegle = new VueRegle();
                getVueRegle().ajouterObservateur(this);
                vueRegleExiste = true;
            }
            ouvrirVue(getVueRegle());
        }

        if (arg == MESSAGES.FERMER_REGLES) {
            fermerVue(getVueRegle());
        }

        if (arg == MESSAGES.QUITTER) {
            fermerVue(getVueMenu());
        }

    }

    public VueMenu getVueMenu() {
        return vueMenu;
    }

    public void setVueMenu(VueMenu vueMenu) {
        this.vueMenu = vueMenu;
    }

    public VueRegle getVueRegle() {
        return vueRegle;
    }

    public void setVueRegle(VueRegle vueRegle) {
        this.vueRegle = vueRegle;
    }

    public ControleurPartieRapide getControleurPartieRapide() {
        return controleurPartieRapide;
    }

    public void setControleurPartieRapide(ControleurPartieRapide controleurPartieRapide) {
        this.controleurPartieRapide = controleurPartieRapide;
    }

    public ControleurTournoi getControleurTournoi() {
        return controleurTournoi;
    }

    public void setControleurTournoi(ControleurTournoi controleurTournoi) {
        this.controleurTournoi = controleurTournoi;
    }
}


