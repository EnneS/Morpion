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
public class Joueur {

    private String nom;
    private SYMBOLES symbole;

    public Joueur(String nom, SYMBOLES symboles){
        setNom(nom);
        setSYMBOLES(symboles);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSYMBOLES(SYMBOLES symbole) {
        this.symbole = symbole;
    }

    public SYMBOLES getSymbole() {
        return symbole;
    }


}
