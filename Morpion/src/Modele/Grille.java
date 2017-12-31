/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Enum.Direction;

import java.util.ArrayList;

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

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Case[][] getCases() {
        return cases;
    }

    public Symbole getSymbole(int i, int j){
        if (i<0 || i>getN() || j<0 || j>getN()){
            return Symbole.NULL;
        } else {
            return cases[i][j].getEtat();
        }
    }

    public Symbole gagnant(int nombreGagnant){

        Boolean gagnantTrouve = false;
        int i = 0;
        int j = 0;

        while (i < getN() && !gagnantTrouve){

            j=0;
            while(j < getN() && !gagnantTrouve){

                if (cases[i][j].getEtat() != Symbole.VIDE){
                    for (Direction direction : Direction.values()){
                        if (verifierAlignement(i,j,direction,nombreGagnant) == true){
                            gagnantTrouve = true;
                        }
                    }
                }
                j++;
            }
            i++;
        }
        return getSymbole(i-1,j-1);
    }


    public Boolean verifierAlignement(int i, int j, Direction direction ,int nombreGagnant){

        int longeurAlignement = 1;
        Symbole symboleCaseDepart = cases[i][j].getEtat();
        ArrayList<Case> casesGagnantes = new ArrayList<>();

        switch (direction)
        {
            case VERTICAL:
                while(!getSymbole(i-1,j).equals(Symbole.NULL) && getSymbole(i-1,j).equals(symboleCaseDepart) && longeurAlignement<nombreGagnant) {
                    i--; longeurAlignement++;
                }
                while(!getSymbole(i+1,j).equals(Symbole.NULL) && getSymbole(i+1,j).equals(symboleCaseDepart) && longeurAlignement<nombreGagnant) {
                    i++; longeurAlignement++;
                }

            case HORIZONTAL:
                while(!getSymbole(i,j-1).equals(Symbole.NULL) && getSymbole(i,j-1).equals(symboleCaseDepart) && longeurAlignement<nombreGagnant) {
                    j--; longeurAlignement++;
                }
                while(!getSymbole(i,j+1).equals(Symbole.NULL) && getSymbole(i,j+1).equals(symboleCaseDepart) && longeurAlignement<nombreGagnant) {
                    j++; longeurAlignement++;
                }

            case DIAGONALE_DESCANDANTE:
                while(!getSymbole(i-1,j-1).equals(Symbole.NULL) && getSymbole(i-1,j-1).equals(symboleCaseDepart) && longeurAlignement<nombreGagnant) {
                    i--; j--; longeurAlignement++;
                }
                while(!getSymbole(i+1,j+1).equals(Symbole.NULL) && getSymbole(i+1,j+1).equals(symboleCaseDepart) && longeurAlignement<nombreGagnant) {
                    i++; j++; longeurAlignement++;
                }

            case DIAGONALE_MONTANTE:
                while(!getSymbole(i-1,j+1).equals(Symbole.NULL) && getSymbole(i-1,j+1).equals(symboleCaseDepart) && longeurAlignement<nombreGagnant) {
                    i--; j++; longeurAlignement++;
                }
                while(!getSymbole(i+1,j-1).equals(Symbole.NULL) && getSymbole(i+1,j-1).equals(symboleCaseDepart) && longeurAlignement<nombreGagnant) {
                    i++; j--; longeurAlignement++;
                }
        }
        return longeurAlignement == nombreGagnant;
    }
}


