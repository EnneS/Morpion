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
    //private int score = 0;
    private SYMBOLES symbole;
    ArrayList <Case>casesCochées = new ArrayList<>();
    
    public Joueur(String nom, SYMBOLES symboles){
        setNom(nom);
        setSYMBOLES(symboles);
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the score
     */
   /* public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
   /* public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the SYMBOLES
     */

    /**
     * @param symbole the SYMBOLES to set
     */
    public void setSYMBOLES(SYMBOLES symbole) {
        this.symbole = symbole;
    }

    public SYMBOLES getSymbole() {
        return symbole;
    }

    /**
     * @return the casesCochées
     */
    public ArrayList<Case> getCasesCochées() {
        return casesCochées;
    }

    /**
     * @param casesCochées the casesCochées to set
     */
    public void setCasesCochées(ArrayList<Case> casesCochées) {
        this.casesCochées = casesCochées;
    }

}
