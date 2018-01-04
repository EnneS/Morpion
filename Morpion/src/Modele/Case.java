/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import Enum.SYMBOLES;

/**
 *
 * @author souliern
 */
public class Case extends ArrayList<Case> {

    private SYMBOLES etat = SYMBOLES.VIDE;
    
    public Case(){
    }

    public SYMBOLES getEtat() {
        return etat;
    }

    public void setEtat(SYMBOLES etat) {
        this.etat = etat;
    }
}
