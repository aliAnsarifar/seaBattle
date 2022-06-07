package shared.events;

import shared.response.Response;

public class RegistrationEvent extends Event{

    private String username;
    private String password;

    public RegistrationEvent(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.register(this.username , this.password);
    }
}
