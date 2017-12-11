package originalFiles;

import java.util.*;
import sp2017g1.*;
//import sp2017g1f2.*;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    private String description;
    private String name;
    private HashMap<String, Room> exits;
    private HashMap<ItemEnum, Item> roomItems;
    private ArrayList<String> roomItemsArray;
    private HashMap<String, Door> doorways;
    private static HashMap<String, Room> rooms = new HashMap<String, Room>();
    
    
    public Room(String description, String name) 
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<String, Room>();
        this.roomItems = new HashMap<ItemEnum, Item>();
        this.roomItemsArray = new ArrayList<String>();
        doorways = new HashMap<String, Door>();
        rooms.put(name, this);
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    public void setExit(String direction, Door doorway) 
    {
        this.doorways.put(direction, doorway);
    }
    
//    Return HashMap with items in room
    public HashMap<ItemEnum, Item> getRoomItems() {
        return roomItems;
    }

    public void addItem(String item) {
        //roomItems.put(ItemEnum.valueOf(item.getItemName()), item);
        roomItemsArray.add(item);
    }
    
    public void itemClear() {
        roomItems.clear();
    }
    
//    Return list of items in room - HER ER POBLEMER. VISER KUN DET FØRSTE. 
    public void getRoomItemsList() {
      for (ItemEnum item : roomItems.keySet()) {
            System.out.printf("%s ", roomItems.get(item).getItemName());
        }
    }
    
    public void setRoomItems(HashMap<ItemEnum, Item> roomItems) {
        this.roomItems = roomItems;
    }
    
    public String getRoomName()
    {
        return name;
    }
    
    public static Room getRoom(String name){
        return rooms.get(name);
    }
    
    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
      
        return "You are now " + description + ".\n" + getExitString();
    }

	private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        Set<String> keys1 = doorways.keySet();
        for(String exit : keys1) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public Door getExitDoor(String direction){
        return doorways.get(direction);
    }
    
    public ArrayList<String> getRoomItemsArray() {
        return roomItemsArray;
    }
}

