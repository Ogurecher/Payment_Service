package service;

import dto.ItemDTO;

import java.util.List;


public class ItemService {
    public void releaseItems(List<ItemDTO> items) {
        System.out.printf("Item Service: released items\n");
    }

    public void changeItemAmount(List<ItemDTO> items) {
        System.out.printf("Item Service: removed items\n");
    }
}
