package shared.response;

import shared.model.User;

public class SingResponse extends Response{
    private final User user;
    private String authToken;

    public SingResponse(User user , String authToken) {
        this.user = user;
        this.authToken = authToken;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.sing(user , authToken);
    }
}
