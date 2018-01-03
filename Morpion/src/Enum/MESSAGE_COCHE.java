package Enum;

public class MESSAGE_COCHE {

    private MESSAGES messageCoche = MESSAGES.COCHER;
    private int i;
    private int j;

    public MESSAGE_COCHE(int i, int j){
        this.i = i;
        this.j = j;
    }

    public int getI(){
        return i;
    }

    public int getJ(){
        return j;
    }
}
