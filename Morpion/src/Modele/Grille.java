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

        System.out.println("Case actuelle : " + j + " | " + i);
        System.out.println("direction X : " + directionX + " direction Y : " + directionY);

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

        System.out.print("Cases trouvées : | ");
        int a = 0;
        while(a < casesGagnantes.size()){
            System.out.print(casesGagnantes.get(a) + " | " + casesGagnantes.get(a+1) + " | ");
            a = a+2;
        }

        System.out.println();
        System.out.println("=====================");

        if (longueurAlignement >= nombreGagnant) return casesGagnantes;
        else return new ArrayList<>();

    }


        /*
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
        System.out.println("LONGUEUR ALIGN " + longueurAlignement);
        if (longueurAlignement == nombreGagnant) return casesGagnantes; else return null;
        */

}


