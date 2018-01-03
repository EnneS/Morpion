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
public class Case extends ArrayList<Case> {
    private int x;
    private int y;
    private SYMBOLES etat = SYMBOLES.VIDE;
    
    public Case(int x, int y){
        setX(x);
        setY(y);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the etat
     */
    public SYMBOLES getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(SYMBOLES etat) {
        this.etat = etat;
    }
}