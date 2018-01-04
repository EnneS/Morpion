/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.ArrayList;
import Enum.SYMBOLES;

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
        if (i<0 || i>=getN() || j<0 || j>=getN()){
            return null;
        } else {
            return cases[i][j].getEtat();
        }
    }

    public ArrayList<Integer> getCasesGagnantes(int j, int i, int nombreGagnant){

        ArrayList<Integer> casesGagnantes = new ArrayList<>();

        casesGagnantes.addAll(verifierAlignement(j, i, nombreGagnant, 1, 0)); // Horizontal
        if (casesGagnantes.isEmpty()) {
            casesGagnantes.addAll(verifierAlignement(j, i, nombreGagnant, 0, 1)); // Vertical
            if(casesGagnantes.isEmpty()) {
                casesGagnantes.addAll(verifierAlignement(j, i, nombreGagnant, 1, 1)); // Diagonale droite
                if(casesGagnantes.isEmpty()) {
                    casesGagnantes.addAll(verifierAlignement(j, i, nombreGagnant, 1, -1)); // Diagonale droite
                }
            }
        }
        return casesGagnantes;
    }


    public ArrayList<Integer> verifierAlignement(int j, int i, int nombreGagnant, int directionX, int directionY) {
        int longueurAlignement = 1;
        SYMBOLES symboleCaseDepart = cases[j][i].getEtat();
        ArrayList<Integer> casesGagnantes = new ArrayList<>();

        casesGagnantes.add(j); casesGagnantes.add(i);

        int nextX = i + directionX;
        int nextY = j + directionY;
        while (symboleCaseDepart == getSymbole(nextY, nextX)) {
            casesGagnantes.add(nextY);
            casesGagnantes.add(nextX);
            longueurAlignement++;

            nextX = nextX + directionX;
            nextY = nextY + directionY;
        }

        nextX = i - directionX;
        nextY = j - directionY;
        while (symboleCaseDepart == getSymbole(nextY, nextX)) {
            casesGagnantes.add(nextY);
            casesGagnantes.add(nextX);
            longueurAlignement++;

            nextX = nextX - directionX;
            nextY = nextY - directionY;
        }

        if (longueurAlignement >= nombreGagnant) return casesGagnantes; else return new ArrayList<>();
    }

    public boolean grillePleine(){
        int caseCochées = 0;

        for (int i = 0; i < getN(); i++){
            for(int j = 0; j < getN(); j++){
                if (getCases()[i][j].getEtat() != SYMBOLES.VIDE){
                    caseCochées++;
                }
            }
        }

        if(caseCochées == getN()*getN()) return true; else return false;
    }
}


