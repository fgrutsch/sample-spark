package at.devfly.samples.spark.rest.response;

public abstract class ResponseEnvelope {

    private String status;

    public ResponseEnvelope(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
