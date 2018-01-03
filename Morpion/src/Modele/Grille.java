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

    public ArrayList<Integer> getCasesGagnantes(int i, int j, int nombreGagnant){

        ArrayList<Integer> casesGagnantes = new ArrayList<>();

        casesGagnantes.addAll(verifierAlignement(i, j, nombreGagnant, 1, 0)); // Horizontal
        if (casesGagnantes.isEmpty()) {
            casesGagnantes.addAll(verifierAlignement(i, j, nombreGagnant, 0, 1)); // Vertical
            if(casesGagnantes.isEmpty()) {
                casesGagnantes.addAll(verifierAlignement(i, j, nombreGagnant, 1, 1)); // Diagonale droite
                if(casesGagnantes.isEmpty()) {
                    casesGagnantes.addAll(verifierAlignement(i, j, nombreGagnant, -1, 1)); // Diagonale gauche
                }
            }
        }
        return casesGagnantes;
    }


    public ArrayList<Integer> verifierAlignement(int i, int j, int nombreGagnant, int directionX, int directionY) {

        int longueurAlignement = 1;
        SYMBOLES symboleCaseDepart = cases[i][j].getEtat();
        ArrayList<Integer> casesGagnantes = new ArrayList<>();

        System.out.println("Case actuelle : " + i + " | " + j);
        System.out.println("direction X : " + directionX + " direction Y : " + directionY);

        int nextX = i + directionX;
        int nextY = j + directionY;
        SYMBOLES nextSymbole = getSymbole(nextY, nextX);

        while (symboleCaseDepart == nextSymbole) {
            casesGagnantes.add(j + directionX);
            casesGagnantes.add(i + directionY);
            longueurAlignement++;

            nextX = nextX + directionX;
            nextY = nextY + directionY;
            nextSymbole = getSymbole(nextY, nextX);
        }

        while (symboleCaseDepart == nextSymbole) {
            casesGagnantes.add(j + directionX);
            casesGagnantes.add(i + directionY);
            longueurAlignement++;


            nextX = nextX - directionX;
            nextY = nextY - directionY;
            nextSymbole = getSymbole(nextY, nextX);
        }

        System.out.print("Cases trouvées :" + i + " | " + j + " | ");
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


