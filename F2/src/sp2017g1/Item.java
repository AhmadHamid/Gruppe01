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
    
    /**
     * Constructor for item
     * @param itemname the name of the item
     */
    public Item(ItemEnum itemname) {
        this.itemName = itemname;
    }
    
    /**
     * constructor for item class
     * @param itemname the name of the item
     * @param points the amount of points given on pick up
     */
    public Item(ItemEnum itemname, int points) {
        this.itemName = itemname;
        this.points = points;
        allItems.put(itemName, this);
    }
    
    /**
     * constructor for item class
     * @param itemname the name of the item
     * @param points amount of points given on pick up
     * @param timeBonus amount of time given on pick up
     */
    public Item(ItemEnum itemname, int points, Timer timeBonus) {
        this.itemName = itemname;
        this.points = points;
        this.time = timeBonus;
        allItems.put(itemname, this);
    }

    /**
     * constructor for item class
     * @param itemName the name of the item
     * @param room location of the item
     */
    public Item(ItemEnum itemName, Room room) {
        this.itemName = itemName;
        selectedRoom = room;
        selectedRoom.getRoomItems().put(this.itemName, this);
        selectedRoom.addItem(itemName.toString());
        allItems.put(itemName, this);
    }
    
    /**
     * 
     * @param itemName the name of the item
     * @param room location of the item
     * @param points amount of points the item gives on pick up
     */
    public Item(ItemEnum itemName, Room room, int points) {
        this.itemName = itemName;
        selectedRoom = room;
        selectedRoom.getRoomItems().put(this.itemName, this);
        allItems.put(itemName, this);
        this.points = points;
        
        picked = false;
    }
    
    /**
     * 
     * @param itemName the name of the item
     * @param room location of the item
     * @param points amount of points the item gives on pick up
     * @param timeBonus amount of time the item gives on pick up
     */
    public Item(ItemEnum itemName, Room room, int points, Timer timeBonus) {
        this.itemName = itemName;
        selectedRoom = room;
        selectedRoom.getRoomItems().put(this.itemName,this);
        allItems.put(itemName, this);
        
        this.points = points;
        this.time = timeBonus;
        
        picked = false;
    }

    /**
     * changes the location of an item
     * @param room new location for the item 
     */
    public void setRoom(Room room) {
        this.selectedRoom = room;
    }
    
    /**
     * 
     * @return name of the item 
     */
    public String getItemName() {
        return itemName.toString();
    }
    
    /**
     * 
     * @return HashMap of all items
     */
    public static HashMap<ItemEnum, Item> getAllItems() {
        return allItems;
    }
    
    /**
     * 
     * @return true if items name is unknown, otherwise false
     */
    public boolean isUnknown() {
        return (itemName == ItemEnum.unknown);
    }

    /**
     * 
     */
    public static void enumAllItems() {
        for (ItemEnum itemName : allItems.keySet()) {
            System.out.println(itemName.toString());
        }

    }
    
//    /**
//     * 
//     * @return string 
//     */
//    public String getAllItemLocations() {
//        return "items";
//    }
  
    /**
     * 
     * @return location of the item if it has a location, otherwise null
     */
    public String getItemLocation() {
        if(selectedRoom != null){
            return selectedRoom.getRoomName();
        } else {
            return null;
        }
    }
    
    /**
     * 
     * @param itemName name of an item
     * @return item object
     */
    public static Item getItem(ItemEnum itemName){
        return allItems.get(itemName);
    }
    
    /**
     * 
     * @return points that the item gives 
     */
    public int getPoints() {
        if(picked) {
            return 0;
        } else {
            return points;
        }
    }
    
    /**
     * 
     */
    public void picked() {
        picked = true;
    }
    
    /**
     * 
     * @return true if the item has been picked up, otherwise false
     */
    public boolean isPicked() {
        return picked;
        
    }
}
