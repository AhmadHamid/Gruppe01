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

    private String/*ItemEnum*/ itemName;
    private Room selectedRoom;
    private Game game;
    private static HashMap<String/*Enum*/, Item> allItems = new HashMap<>();
    
    public Item(String name) {
        itemName = name;
    }

    public Item(String/*ItemEnum*/ name, Room room) {
        itemName = name;
        selectedRoom = room;
        selectedRoom.getRoomItems().put(itemName, this);
        /*selectedRoom.getRoomItems().put(ItemEnum.test, this);*/
        allItems.put(itemName, this);
        /*allItems.put(ItemEnum.test, this);*/
    }

//    returnerer navnet af item
    public String getItemName() {
        /*return itemName;*/
        return itemName.toString();
    }

    public static void enumAllItems() {
        for (String/*Enum*/ itemName : allItems.keySet()) {
            System.out.println(itemName);
        }
//        Enumeration e = Collections.enumeration(allItems.keySet());
//        while (e.hasMoreElements()) {
//            System.out.println(e.nextElement());   
//        }

    }
}
