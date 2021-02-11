package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility;

public class Product {

    private String product_id;
    private Integer quantity;
    private double price;

    public Product(String product_id, Integer quantity, double price) {
        this.product_id = product_id;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public Product setProduct_id(String product_id) {
        this.product_id = product_id;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Product setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }
}
