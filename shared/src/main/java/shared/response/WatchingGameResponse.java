package shared.response;

import shared.model.game.Game;

public class WatchingGameResponse extends Response{
    private Game game;

    public WatchingGameResponse(Game game) {
        this.game = game;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.watchGame(this.game);
    }
}
