package shared.response;

public class ShowError extends Response{

    private final String error;

    public ShowError(String error) {
        this.error = error;
    }

    @Override
    public void visit(ResponseVisitor responseVisitor) {
        responseVisitor.showError(error);
    }
}
