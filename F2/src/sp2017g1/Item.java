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
    private Timer time;
    private int points;
    private boolean picked;
    
    private static HashMap<ItemEnum, Item> allItems = new HashMap<>();
    
    public Item(ItemEnum itemname) {
        this.itemName = itemname;
    }
    
    public Item(ItemEnum itemname, int points) {
        this.itemName = itemname;
        this.points = points;
        allItems.put(itemName, this);
    }
    
    public Item(ItemEnum itemname, int points, Timer timeBonus) {
        this.itemName = itemname;
        this.points = points;
        this.time = timeBonus;
        allItems.put(itemname, this);
    }

    public Item(ItemEnum itemName, Room room) {
        this.itemName = itemName;
        selectedRoom = room;
        selectedRoom.getRoomItems().put(this.itemName, this);
        selectedRoom.addItem(itemName.toString());
        allItems.put(itemName, this);
    }
    
    public Item(ItemEnum itemName, Room room, int points) {
        this.itemName = itemName;
        selectedRoom = room;
        selectedRoom.getRoomItems().put(this.itemName, this);
        allItems.put(itemName, this);
        this.points = points;
        
        picked = false;
    }
    
    public Item(ItemEnum itemName, Room room, int points, Timer timeBonus) {
        this.itemName = itemName;
        selectedRoom = room;
        selectedRoom.getRoomItems().put(this.itemName,this);
        allItems.put(itemName, this);
        
        this.points = points;
        this.time = timeBonus;
        
        picked = false;
    }

    public void setRoom(Room room) {
        this.selectedRoom = room;
    }
    
//    returnerer navnet af item
    public String getItemName() {
        return itemName.toString();
    }
    
    public static HashMap<ItemEnum, Item> getAllItems() {
        return allItems;
    }
    
    public boolean isUnknown() {
        return (itemName == ItemEnum.unknown);
    }

    public static void enumAllItems() {
        for (ItemEnum itemName : allItems.keySet()) {
            System.out.println(itemName.toString());
        }

    }
    public String getAllItemLocations() {
        return "items";
    }
    
    public String getItemLocation() {
        if(selectedRoom != null){
            return selectedRoom.getRoomName();
        } else {
            return null;
        }
    }
    
    public static Item getItem(ItemEnum itemName){
        return allItems.get(itemName);
    }
    
    public int getPoints() {
        if(picked) {
            return 0;
        } else {
            return points;
        }
    }
    
    public void picked() {
        picked = true;
    }
    
    public boolean isPicked() {
        return picked;
        
    }
}
