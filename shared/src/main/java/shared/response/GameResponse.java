package shared.response;

import shared.model.game.Game;

public class GameResponse extends Response {
    Game game ;
    String s;

    public GameResponse(Game game) {
        this.game = game;
    }
    public GameResponse(Game game , String s){
        this.game  = game ;
        this.s = s;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        if (this.s == null){
            responseVisitor.getGame(this.game);
        }else{
            responseVisitor.getGame(this.game , this.s);
        }
    }
}
