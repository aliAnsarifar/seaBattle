package shared.response;

public class EndGameResponse extends Response{
    private  int finished;

    public EndGameResponse(int finished) {
        this.finished = finished;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.endGame(this.finished);
    }
}
