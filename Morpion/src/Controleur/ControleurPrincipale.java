package Controleur;

import Views.*;
import java.util.Observable;
import Enum.MESSAGES;

public class ControleurPrincipale extends Controleur {

    private VueMenu vueMenu = new VueMenu();
    private VueOptionPartieRapide vueOptionPartieRapide = new VueOptionPartieRapide();
    private VueOptionTournoi vueOptionTournoi = new VueOptionTournoi();
    private VueRegle vueRegle = new VueRegle();
    private VueInformation vueInformation = new VueInformation();

    private Boolean controleurPartieRapideExiste = false;
    private Boolean controleurTournoiExiste = false;

    public ControleurPrincipale() {
        getVueMenu().ajouterObservateur(this);
        getVueRegle().ajouterObservateur(this);
        ouvrirVue(getVueMenu());

    }

    @Override
    public void update(Observable o, Object arg) {

        //redirection vers controleurs secondaires

        if (arg == MESSAGES.PARTIERAPIDE) {
            ouvrirVue(getVueOptionPartieRapide());
            fermerVue(getVueMenu());
            if (!controleurPartieRapideExiste){
                ControleurPartieRapide controleurPartieRapide = new ControleurPartieRapide(this);
                controleurPartieRapideExiste = true;
            }
        }

        if (arg == MESSAGES.TOURNOI) {
            ouvrirVue(getVueOptionTournoi());
            fermerVue(getVueMenu());
            if (!controleurTournoiExiste){
                ControleurTournoi controleurTournoi = new ControleurTournoi(this);
                controleurTournoiExiste = true;
            }
        }

        //traitement des autres boutons du menu

        if (arg == MESSAGES.REGLES) {
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


