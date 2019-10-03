package entity;

import entity.enums.CardAuthorizationInfo;

public class Payment {
    private long id;
    private long orderId;
    private CardAuthorizationInfo cardAuthorizationInfo;
    private String username;

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
