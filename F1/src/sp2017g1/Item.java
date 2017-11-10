/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

import java.util.*;
import originalFiles.*;

/**
 *
 * @author Student
 */
public class Item {

    private ItemEnum itemName;
    private Room selectedRoom;
    private Game game;
    private static HashMap<ItemEnum, Item> allItems = new HashMap<>();
    
    public Item(ItemEnum itemname) {
        this.itemName = itemname;
    }

    public Item(ItemEnum itemName, Room room) {
        this.itemName = itemName;
        selectedRoom = room;
        selectedRoom.getRoomItems().put(this.itemName, this);
        allItems.put(itemName, this);
        /*allItems.put(ItemEnum.test, this);*/
    }

//    returnerer navnet af item
    public String getItemName() {
        return itemName.toString();
    }
    
    public boolean isUnknown() {
        return (itemName == ItemEnum.unknown);
    }

    public static void enumAllItems() {
        for (ItemEnum itemName : allItems.keySet()) {
            System.out.println(itemName.toString());
        }

    }
}
