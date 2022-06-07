package shared.events;

import shared.response.Response;

public class FindOpponentEvent extends Event{
    public FindOpponentEvent(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.opponentFound();
    }
}
