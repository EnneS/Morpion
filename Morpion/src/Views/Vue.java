package Views;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public abstract class Vue extends Observable{

    //Fonts
    protected Font regular = new Font("Euphemia UCAS",0,14);
    protected Font bold = new Font("Euphemia UCAS", 1, 26);
    protected Font semiBold = new Font("Euphemia UCAS", 1, 20);
    protected Font semiBold2 = new Font("Euphemia UCAS",1,14);
    protected Font italic = new Font("Euphemia UCAS", 2,13);
    protected Font impact = new Font("Impact",1,60);
    protected Font regular2 = new Font("Euphemia UCAS", 0, 20);

    public Vue(){
    }
    public void ajouterObservateur(Observer ecoutant){ this.addObserver(ecoutant);}
    public abstract void setVisible(Boolean b);

}
