package shared.events;

import shared.response.Response;

public class LoginEvent extends Event {
    String username ;
    String password ;

    public LoginEvent(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.login(this.username , this.password);
    }
}
