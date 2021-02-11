package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility;

public class PingAck {
    private String serviceStatus;
    private String dbStatus;

    public PingAck(String serviceStatus, String dbStatus) {
        this.serviceStatus = serviceStatus;
        this.dbStatus = dbStatus;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public PingAck setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
        return this;
    }

    public String getDbStatus() {
        return dbStatus;
    }

    public PingAck setDbStatus(String dbStatus) {
        this.dbStatus = dbStatus;
        return this;
    }
}
