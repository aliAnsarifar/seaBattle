package shared.events;

import shared.response.Response;

public class ScoreBoardEvent extends Event{
    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.scoreBoard();
    }
}
