package shared.events;

import shared.model.game.Game;
import shared.response.Response;

public interface EventVisitor {

    public Response register (String username , String password);

    public Response login (String username , String password);

    public Response findOpponentRequest(String authToken);

    public Response opponentFound();

    public Response getGame();

    public Response arrangeBoard();

    public Response readyBoard();

    public Response clickBoard(int x , int y);

    public Response changeTurn();

    public Response scoreBoard();

    public Response allGames();

    public Response getGame(String player1 , String player2);


}
