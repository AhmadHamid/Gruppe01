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

    private String itemName;
    private Room selectedRoom;
    private boolean collectability;
    private Game game;
    private static HashMap<String, Item> allItems = new HashMap<>();

    public Item(String name, Room room) {
        itemName = name;
        selectedRoom = room;
        selectedRoom.getRoomItems().put(itemName, this);
        allItems.put(itemName, this);
    }

//    Denne constructor skal slettes, når NPC implementeres.
    public Item(String name, Room room, boolean collectability) {
        this(name, room);
        this.collectability = collectability;
        selectedRoom.getRoomItems().put(itemName, this);
        allItems.put(itemName, Item.this);
    }

//    returnerer navnet af item
    public String getItemName() {
        return itemName;
    }

    public boolean isNotCollectable() {
        return collectability;
    }

    public static void enumAllItems() {
//        for (String itemName : allItems.keySet()) {
//            System.out.println(allItems);
//        }
        Enumeration e = Collections.enumeration(allItems.keySet());
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());   
        }
    }
}
