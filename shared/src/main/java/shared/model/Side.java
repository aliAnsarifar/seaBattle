package shared.model;

public enum Side {
    PLAYER_ONE ,
    PLAYER_TWO;


    @Override
    public String toString() {
        if (this == Side.PLAYER_TWO){
            return "PLAYER_TWO";
        }
        else return "PLAYER_ONE";
    }
}
