package shared.events;

import shared.response.Response;

public class ClickBoardEvent extends Event{
    int x;
    int y;

    public ClickBoardEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Response visit(EventVisitor eventVisitor) {
        return eventVisitor.clickBoard(this.x , this.y);
    }
}
