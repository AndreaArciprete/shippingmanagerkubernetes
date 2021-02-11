package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility;

public class LoggingRequest extends OrderPaidRequest{
    private String timestamp;

    public LoggingRequest(String orderId, String userId, double amountPaid, String timestamp) {
        super(orderId, userId, amountPaid);
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public LoggingRequest setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
