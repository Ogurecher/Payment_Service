package dto;

import entity.enums.CardAuthorizationInfo;

public class UserDetailsDTO {
    private String username;
    private CardAuthorizationInfo cardAuthorizationInfo;

    public UserDetailsDTO(String username, CardAuthorizationInfo cardAuthorizationInfo) {
        this.username = username;
        this.cardAuthorizationInfo = cardAuthorizationInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CardAuthorizationInfo getCardAuthorizationInfo() {
        return cardAuthorizationInfo;
    }

    public void setCardAuthorizationInfo(CardAuthorizationInfo cardAuthorizationInfo) {
        this.cardAuthorizationInfo = cardAuthorizationInfo;
    }
}
