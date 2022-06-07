package shared.model;

public class GamePlayer {
    String player1;
    String player2;

    public GamePlayer(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}
