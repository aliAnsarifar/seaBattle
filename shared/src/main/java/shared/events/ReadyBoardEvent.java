package shared.events;

import shared.response.Response;

public class ReadyBoardEvent extends Event{
    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.readyBoard();
    }
}
