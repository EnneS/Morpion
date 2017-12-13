/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author souliern
 */
public class Grille {
    private int n; // Grille de n*n cases
    private ArrayList<Case>cases = new ArrayList<>();
    
    public Grille(int n){
        setN(n);
        // Création des cases constituant la grille
        for (int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                Case carreau = new Case(x,y);
                cases.add(carreau);
            }
        }
    }

    /**
     * @return the nbLignes
     */
    public int getN() {
        return n;
    }

    /**
     * @param nbLignes the nbLignes to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * @return the cases
     */
    public ArrayList<Case> getCases() {
        return cases;
    }
}
