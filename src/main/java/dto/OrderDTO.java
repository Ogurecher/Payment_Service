package dto;

import entity.Order;
import entity.enums.Status;

public class OrderDTO {
    private long id;
    private Status status;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.status = order.getStatus();
    }

    public OrderDTO(long id) {
        this.id = id;
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
}
