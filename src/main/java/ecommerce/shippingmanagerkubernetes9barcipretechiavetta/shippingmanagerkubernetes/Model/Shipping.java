package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility.Product;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="shippings")
public class Shipping {


    @Id
    private ObjectId id;

    private String orderId;
    private String userId;
    private String shippingAddress;
    private List<Product> products;
    private Integer DDT;
    private String shippingState;


    @JsonCreator
    public Shipping(String orderId, String userId, String shippingAddress, List<Product> products, String shippingState) {
        this.orderId = orderId;
        this.userId = userId;
        this.shippingAddress = shippingAddress;
        this.products = products;
        this.DDT = null;
        this.shippingState = shippingState;
    }

    @JsonGetter
    public String getShippingId_String() {
        return id.toHexString();
    }

    public ObjectId getShippingId() {
        return id;
    }

    public Shipping setShippingId(ObjectId id) {
        this.id = id;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public Shipping setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Shipping setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Shipping setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Shipping setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public Integer getDDT() {
        return DDT;
    }

    public Shipping setDDT(Integer DDT) {
        this.DDT = DDT;
        return this;
    }

    public String getShippingState() {
        return shippingState;
    }

    public Shipping setShippingState(String shippingState) {
        this.shippingState = shippingState;
        return this;
    }
}
