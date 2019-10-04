package service;

import entity.OrderItem;

import java.util.Set;


public class ItemService {
    public void releaseItems(Set<OrderItem> items) {
        System.out.printf("Item Service: released items\n");
    }

    public void changeItemAmount(Set<OrderItem> items) {
        System.out.printf("Item Service: removed items\n");
    }
}
