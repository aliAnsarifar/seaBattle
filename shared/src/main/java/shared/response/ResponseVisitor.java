package shared.response;


import shared.model.GamePlayer;
import shared.model.Side;
import shared.model.User;
import shared.model.game.Game;

import java.util.ArrayList;

public interface ResponseVisitor {
    public void showError(String error);

    public void sing(User user , String authToken);

    public void opponentFound(Side side);

    public void getGame(Game game);

    public void getGame (Game game , String s);

    public void endGame(int finished);

    public void scoreBoard(ArrayList<User> users);

    public void allGames(ArrayList<GamePlayer> games);

    public void watchGame(Game game);
}
