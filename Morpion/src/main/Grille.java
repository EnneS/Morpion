/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


/**
 *
 * @author souliern
 */
public class Grille {
    private int n; // Grille de n*n cases
    private Case cases[][];
    
    public Grille(int n){
        setN(n);
        // Création des cases constituant la grille
        cases = new Case[getN()][getN()];
        for (int y = 0; y < n; y++){
            for(int x = 0; x < n; x++){
                Case carreau = new Case(x,y);
                cases[x][y]=carreau;
            }
        }
    }

    /**
     * @return the n
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
    public Case[][] getCases() {
        return cases;
    }
}
