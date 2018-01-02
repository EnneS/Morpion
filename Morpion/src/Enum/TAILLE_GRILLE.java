package Enum;

public enum TAILLE_GRILLE {
    //TROIS("3 X 3"),
    QUATRE("4 X 4"),
    CINQ("5 X 5"),
    //SIX("6 X 6"),
    SEPT("7 X 7"),
    HUIT("8 X 8");
    //NEUF("9 X 9");

    private String name;

    TAILLE_GRILLE(String name){
        this.name = name;
    }

    @Override
    public String toString(){ return name;}

}
