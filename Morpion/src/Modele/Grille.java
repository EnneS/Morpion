/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import Enum.SYMBOLES;
import Enum.DIRECTION;

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

    public SYMBOLES getSymbole(int i, int j){
        if (i<0 || i>getN() || j<0 || j>getN()){
            return SYMBOLES.NULL;
        } else {
            return cases[i][j].getEtat();
        }
    }

    public SYMBOLES gagnant(int nombreGagnant){

        Boolean gagnantTrouve = false;
        int i = 0;
        int j = 0;

        while (i < getN() && !gagnantTrouve){

            j=0;
            while(j < getN() && !gagnantTrouve){

                if (cases[i][j].getEtat() != SYMBOLES.VIDE){
                    for (DIRECTION DIRECTION : DIRECTION.values()){
                        if (verifierAlignement(i,j, DIRECTION,nombreGagnant) == true){
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


    public Boolean verifierAlignement(int i, int j, DIRECTION DIRECTION, int nombreGagnant){

        int longeurAlignement = 1;
        SYMBOLES SYMBOLESCaseDepart = cases[i][j].getEtat();
        ArrayList<Case> casesGagnantes = new ArrayList<>();

        switch (DIRECTION)
        {
            case VERTICAL:
                while(!getSymbole(i-1,j).equals(SYMBOLES.NULL) && getSymbole(i-1,j).equals(SYMBOLESCaseDepart) && longeurAlignement<nombreGagnant) {
                    i--; longeurAlignement++;
                }
                while(!getSymbole(i+1,j).equals(SYMBOLES.NULL) && getSymbole(i+1,j).equals(SYMBOLESCaseDepart) && longeurAlignement<nombreGagnant) {
                    i++; longeurAlignement++;
                }

            case HORIZONTAL:
                while(!getSymbole(i,j-1).equals(SYMBOLES.NULL) && getSymbole(i,j-1).equals(SYMBOLESCaseDepart) && longeurAlignement<nombreGagnant) {
                    j--; longeurAlignement++;
                }
                while(!getSymbole(i,j+1).equals(SYMBOLES.NULL) && getSymbole(i,j+1).equals(SYMBOLESCaseDepart) && longeurAlignement<nombreGagnant) {
                    j++; longeurAlignement++;
                }

            case DIAGONALE_DESCANDANTE:
                while(!getSymbole(i-1,j-1).equals(SYMBOLES.NULL) && getSymbole(i-1,j-1).equals(SYMBOLESCaseDepart) && longeurAlignement<nombreGagnant) {
                    i--; j--; longeurAlignement++;
                }
                while(!getSymbole(i+1,j+1).equals(SYMBOLES.NULL) && getSymbole(i+1,j+1).equals(SYMBOLESCaseDepart) && longeurAlignement<nombreGagnant) {
                    i++; j++; longeurAlignement++;
                }

            case DIAGONALE_MONTANTE:
                while(!getSymbole(i-1,j+1).equals(SYMBOLES.NULL) && getSymbole(i-1,j+1).equals(SYMBOLESCaseDepart) && longeurAlignement<nombreGagnant) {
                    i--; j++; longeurAlignement++;
                }
                while(!getSymbole(i+1,j-1).equals(SYMBOLES.NULL) && getSymbole(i+1,j-1).equals(SYMBOLESCaseDepart) && longeurAlignement<nombreGagnant) {
                    i++; j--; longeurAlignement++;
                }
        }
        return longeurAlignement == nombreGagnant;
    }
}


