package shared.events;

import shared.response.Response;

public class AllGamesEvent extends Event{
    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.allGames();
    }
}
