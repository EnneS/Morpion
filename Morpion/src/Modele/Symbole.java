/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author souliern
 */
public enum Symbole {
    ROND("O"),
    CROIX("X"),
    VIDE(""),
    NULL("");
    
    private String name;
    
    Symbole(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
