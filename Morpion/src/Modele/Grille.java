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
        if (i<0 || i>=getN() || j<0 || j>=getN()){
            return SYMBOLES.NULL;
        } else {
            return cases[i][j].getEtat();
        }
    }

    public ArrayList<Integer> getCasesGagnantes(int nombreGagnant){

        ArrayList<Integer> casesGagnantes = new ArrayList<>();

        for (int i=0; i<getN(); i++){
            for (int j=0; j<getN(); j++){
                if (cases[i][j].getEtat() != SYMBOLES.VIDE){
                    for (DIRECTION DIRECTION : DIRECTION.values()){
                        System.out.println("==========================================");
                        if (verifierAlignement(i,j, DIRECTION,nombreGagnant) != null){
                            casesGagnantes.addAll(verifierAlignement(i,j, DIRECTION, nombreGagnant));
                        }
                    }

                }
            }
        }
        return casesGagnantes;
    }


    public ArrayList<Integer> verifierAlignement(int i, int j, DIRECTION direction, int nombreGagnant){

        int longueurAlignement = 0;
        SYMBOLES symboleCaseDepart = cases[i][j].getEtat();
        //System.out.println("Symbole case départ : "+symboleCaseDepart);
        ArrayList<Integer> casesGagnantes = new ArrayList<>();

        if (direction.equals(DIRECTION.VERTICAL)) {

            System.out.println("V : i=" + i + " et j=" + j);
            while (!getSymbole(i - 1, j).equals(SYMBOLES.NULL) && getSymbole(i - 1, j).equals(symboleCaseDepart) && longueurAlignement < nombreGagnant) {
                casesGagnantes.add(i - 1);
                casesGagnantes.add(j);
                i--;
                longueurAlignement++;
                //System.out.println("V1");
            }
            while (!getSymbole(i + 1, j).equals(SYMBOLES.NULL) && getSymbole(i + 1, j).equals(symboleCaseDepart) && longueurAlignement < nombreGagnant) {
                casesGagnantes.add(i + 1);
                casesGagnantes.add(j);
                i++;
                longueurAlignement++;
                //System.out.println("V2");
            }

        } else if (direction.equals(DIRECTION.HORIZONTAL)) {

            System.out.println("H : i=" + i + " et j=" + j);
            while (!getSymbole(i, j - 1).equals(SYMBOLES.NULL) && getSymbole(i, j - 1).equals(symboleCaseDepart) && longueurAlignement < nombreGagnant) {
                casesGagnantes.add(i);
                casesGagnantes.add(j - 1);
                j--;
                longueurAlignement++;
            }
            while (!getSymbole(i, j + 1).equals(SYMBOLES.NULL) && getSymbole(i, j + 1).equals(symboleCaseDepart) && longueurAlignement < nombreGagnant) {
                casesGagnantes.add(i);
                casesGagnantes.add(j + 1);
                j++;
                longueurAlignement++;
            }

        } else if (direction.equals(DIRECTION.DIAGONALE_DESCANDANTE)) {

            System.out.println("DD : i=" + i + " et j=" + j);
            while (!getSymbole(i - 1, j - 1).equals(SYMBOLES.NULL) && getSymbole(i - 1, j - 1).equals(symboleCaseDepart) && longueurAlignement < nombreGagnant) {
                casesGagnantes.add(i - 1);
                casesGagnantes.add(j - 1);
                i--;
                j--;
                longueurAlignement++;
            }
            while (!getSymbole(i + 1, j + 1).equals(SYMBOLES.NULL) && getSymbole(i + 1, j + 1).equals(symboleCaseDepart) && longueurAlignement < nombreGagnant) {
                casesGagnantes.add(i + 1);
                casesGagnantes.add(j + 1);
                i++;
                j++;
                longueurAlignement++;
            }

        } else {

            System.out.println("DM : i="+i+" et j="+j);
            while(!getSymbole(i-1,j+1).equals(SYMBOLES.NULL) && getSymbole(i-1,j+1).equals(symboleCaseDepart) && longueurAlignement<nombreGagnant) {
                casesGagnantes.add(i-1); casesGagnantes.add(j+1);
                i--; j++; longueurAlignement++;
            }
            while(!getSymbole(i+1,j-1).equals(SYMBOLES.NULL) && getSymbole(i+1,j-1).equals(symboleCaseDepart) && longueurAlignement<nombreGagnant) {
                casesGagnantes.add(i+1); casesGagnantes.add(j-1);
                i++; j--; longueurAlignement++;
            }
        }

        if (longueurAlignement == nombreGagnant) return casesGagnantes; else return null;
    }
}


