package at.devfly.samples.spark.rest.response;

public class ErrorResponse extends ResponseEnvelope {

    private String message;
    private String developerMessage;

    private ErrorResponse() {
        super("error");
    }

    public ErrorResponse(String message, String developerMessage) {
        this();
        this.message = message;
        this.developerMessage = developerMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
