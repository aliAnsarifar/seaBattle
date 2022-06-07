package shared.response;

import shared.model.Side;

public class OpponentFoundResponse extends Response {
    private Side side;

    public OpponentFoundResponse(Side side) {
        this.side = side;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.opponentFound(this.side);
    }
}
