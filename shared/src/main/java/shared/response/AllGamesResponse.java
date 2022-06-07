package shared.response;

import shared.model.GamePlayer;

import java.util.ArrayList;

public class AllGamesResponse extends Response {
    ArrayList<GamePlayer> games ;

    public AllGamesResponse(ArrayList<GamePlayer> games) {
        this.games = games;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.allGames(this.games);
    }
}
