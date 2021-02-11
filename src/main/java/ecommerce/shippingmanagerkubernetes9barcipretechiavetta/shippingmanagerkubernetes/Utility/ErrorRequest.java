package ecommerce.shippingmanagerkubernetes9barcipretechiavetta.shippingmanagerkubernetes.Utility;

import java.io.Serializable;

public class ErrorRequest implements Serializable {
    private String timestamp;
    private String sourceIp;
    private String service;
    private String request;
    private String error;

    public ErrorRequest(String timestamp, String sourceIp, String service, String request, String error) {
        this.timestamp = timestamp;
        this.sourceIp = sourceIp;
        this.service = service;
        this.request = request;
        this.error = error;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ErrorRequest setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public ErrorRequest setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
        return this;
    }

    public String getService() {
        return service;
    }

    public ErrorRequest setService(String service) {
        this.service = service;
        return this;
    }

    public String getRequest() {
        return request;
    }

    public ErrorRequest setRequest(String request) {
        this.request = request;
        return this;
    }

    public String getError() {
        return error;
    }

    public ErrorRequest setError(String error) {
        this.error = error;
        return this;
    }
}
