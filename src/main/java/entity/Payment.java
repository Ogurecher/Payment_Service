package entity;

import entity.enums.CardAuthorizationInfo;

public class Payment {
    private long id;
    private long orderId;
    private CardAuthorizationInfo cardAuthorizationInfo;
    private String username;

    public Payment(long orderId, CardAuthorizationInfo cardAuthorizationInfo, String username) {
        this.orderId = orderId;
        this.cardAuthorizationInfo = cardAuthorizationInfo;
        this.username = username;
    }

    public String toString() {
        return new String("{orderId: " + this.orderId + ", cardAuthorizationInfo: " + this.cardAuthorizationInfo + ", username: " + this.username + "}");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public CardAuthorizationInfo getCardAuthorizationInfo() {
        return cardAuthorizationInfo;
    }

    public void setCardAuthorizationInfo(CardAuthorizationInfo cardAuthorizationInfo) {
        this.cardAuthorizationInfo = cardAuthorizationInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
