package at.devfly.samples.rest.response;

public class SuccessResponse extends ResponseEnvelope {

    private Object data;

    public SuccessResponse(Object data) {
        super("success");
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
