package service;

import dto.ItemDTO;

import java.util.List;


public class ItemService {
    public void releaseItems(List<ItemDTO> items) {
        System.out.print("Item Service: released items\n");
    }

    public void changeItemAmount(List<ItemDTO> items) {
        System.out.print("Item Service: removed items\n");
    }
}
