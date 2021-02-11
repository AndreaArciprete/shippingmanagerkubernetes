package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility;

import java.io.Serializable;
import java.util.List;

public class OrderCompletedRequest implements Serializable {
    private String orderId;
    private List<Product> products;
    private double total;
    private String shippingAddress;
    private String billingAddress;
    private String userId;

    public String getOrderId() {
        return orderId;
    }

    public OrderCompletedRequest setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public OrderCompletedRequest setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public double getTotal() {
        return total;
    }

    public OrderCompletedRequest setTotal(double total) {
        this.total = total;
        return this;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public OrderCompletedRequest setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public OrderCompletedRequest setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public OrderCompletedRequest setUserId(String userId) {
        this.userId = userId;
        return this;
    }
}
