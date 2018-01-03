package Controleur;

import Views.*;
import java.util.Observable;
import Enum.MESSAGES;

public class ControleurPrincipale extends Controleur {

    private VueMenu vueMenu = new VueMenu();
    private VueRegle vueRegle;

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
                ControleurPartieRapide controleurPartieRapide = new ControleurPartieRapide(this);
                controleurPartieRapideExiste = true;
            }
        }

        if (arg == MESSAGES.TOURNOI) {

            if (!controleurTournoiExiste){
                ControleurTournoi controleurTournoi = new ControleurTournoi(this);
                controleurTournoiExiste = true;
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

}


