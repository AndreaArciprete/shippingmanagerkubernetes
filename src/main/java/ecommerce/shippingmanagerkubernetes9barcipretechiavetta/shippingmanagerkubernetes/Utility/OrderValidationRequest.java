package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderValidationRequest implements Serializable {
    private LocalDateTime timestamp;
    private Integer status;
    private String orderId;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public OrderValidationRequest setTimestamp(LocalDateTime timestamp) {
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
