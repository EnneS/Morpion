package Controleur;

import Views.Vue;
import java.util.Observer;

public abstract class Controleur implements Observer {

    public void ouvrirVue(Vue vue){
        vue.setVisible(true);
    }

    public void fermerVue(Vue vue){
        vue.setVisible(false);
    }
}
