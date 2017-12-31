package Views;

import java.util.Observable;
import java.util.Observer;

public abstract class Vue extends Observable{

    public Vue(){
    }
    public void ajouterObservateur(Observer ecoutant){ this.addObserver(ecoutant);}
    public abstract void setVisible(Boolean b);
}
