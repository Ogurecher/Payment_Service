package dto;

public class OrderDTO {
    private long id;
    private String username;

    public OrderDTO(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public String toString() {
        return "{id: " + id + ", username: " + username + "}";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}