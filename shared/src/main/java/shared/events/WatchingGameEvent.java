package shared.events;

import shared.response.Response;

public class WatchingGameEvent extends Event{
    String player1 ;
    String player2;

    public WatchingGameEvent(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getGame(player1 , player2);
    }
}
