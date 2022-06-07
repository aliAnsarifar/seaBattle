package shared.events;

import shared.response.Response;

public class FindOpponentRequestEvent extends Event{
    public FindOpponentRequestEvent(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.findOpponentRequest(this.authToken);
    }
}
