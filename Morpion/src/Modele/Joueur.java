/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;

/**
 *
 * @author souliern
 */
public class Joueur {
    private String nom;
    //private int score = 0;
    private Symbole symbole;
    ArrayList <Case>casesCochées = new ArrayList<>();
    
    public Joueur(String nom, Symbole symbole){
        setNom(nom);
        setSymbole(symbole);
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
     * @return the symbole
     */
    public Symbole getSymbole() {
        return symbole;
    }

    /**
     * @param symbole the symbole to set
     */
    public void setSymbole(Symbole symbole) {
        this.symbole = symbole;
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
