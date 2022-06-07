package shared.events;

import shared.response.Response;

public class ChangeTurnEvent extends Event{
    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.changeTurn();
    }
}
