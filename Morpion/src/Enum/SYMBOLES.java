/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enum;

/**
 *
 * @author souliern
 */
public enum SYMBOLES {
    ROND("O"),
    CROIX("X"),
    VIDE(""),
    NULL("");
    
    private String name;
    
    SYMBOLES(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
