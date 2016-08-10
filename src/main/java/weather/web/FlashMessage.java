package weather.web;

public class FlashMessage {
    private String message;
    private String status;

    public static enum Status {
        SUCCESS,
        INFO,
        FAILURE
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
