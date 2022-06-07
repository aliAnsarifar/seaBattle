package shared.events;

import shared.response.Response;

public class GetGameEvent extends Event{
    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.getGame();
    }
}
