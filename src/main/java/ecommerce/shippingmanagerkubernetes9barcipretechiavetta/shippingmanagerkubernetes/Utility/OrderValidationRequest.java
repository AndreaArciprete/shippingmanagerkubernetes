package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility;

import java.io.Serializable;

public class OrderValidationRequest implements Serializable {
    private String timestamp;
    private Integer status;
    private String orderId;

    public String getTimestamp() {
        return timestamp;
    }

    public OrderValidationRequest setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public OrderValidationRequest setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderValidationRequest setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }
}
