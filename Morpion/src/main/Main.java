/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Views.VueGrille;
import Views.VueMenu;
import Views.VueOptionTournoi;
import Views.VueRegle;

/**
 *
 * @author souliern
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //VueGrille vueGrille = new VueGrille();
        //VueRegle vueRegle = new VueRegle();
        VueOptionTournoi vueOptionTournoi = new VueOptionTournoi();
        //vueRegle.afficher();
        //vueGrille.afficher();
        vueOptionTournoi.afficher();

        
    }
    
}
