package shared.response;

import shared.model.User;

import java.util.ArrayList;

public class ScoreBoardResponse  extends Response{
    ArrayList<User> users ;

    public ScoreBoardResponse(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.scoreBoard(this.users);
    }
}
