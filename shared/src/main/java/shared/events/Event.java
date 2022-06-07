package shared.events;


import shared.response.Response;

public abstract class Event {
    protected String authToken;
    public abstract Response visit(EventVisitor eventVisitor);
}
