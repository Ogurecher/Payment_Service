package entity;

import entity.enums.Status;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Order implements Serializable {

    private long id;
    private Status status;
    private String username;
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public Order() {}

    public Order(String username) {
        this.username = username;
        this.status = Status.COLLECTING;
        this.orderItems = new HashSet<OrderItem>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
