package shared.events;

import shared.response.Response;

public class ArrangeEvent extends Event{

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.arrangeBoard();

    }
}
