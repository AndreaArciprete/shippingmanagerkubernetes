package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility;

import java.io.Serializable;

public class OrderPaidRequest implements Serializable {
    private String orderId;
    private String userId;
    private double amountPaid;

    public OrderPaidRequest(String orderId, String userId, double amountPaid) {
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderPaidRequest setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public OrderPaidRequest setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public OrderPaidRequest setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }
}
