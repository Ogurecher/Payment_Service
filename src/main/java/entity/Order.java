package entity;

import entity.enums.Status;

public class Order {
    private long id;
    private String username;
    private Status status;

    public Order(long id, String username) {
        this.id = id;
        this.username = username;
        this.status = Status.COLLECTING;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
