package business;

import java.util.*;
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
    
    /**
     *
     * @param description
     * @param name
     */
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

    /**
     *
     * @param direction
     * @param neighbor
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
    /**
     *
     * @param direction
     * @param doorway
     */
    public void setExit(String direction, Door doorway) 
    {
        this.doorways.put(direction, doorway);
    }
    
//    Return HashMap with items in room
    public HashMap<ItemEnum, Item> getRoomItems() {
        return roomItems;
    }

    /**
     *
     * @param item
     */
    public void addItem(String item) {
        roomItemsArray.add(item);
    }
    
    /**
     *
     */
    public void itemClear() {
        roomItems.clear();
    }

    /**
     *
     * @param roomItems
     */
    public void setRoomItems(HashMap<ItemEnum, Item> roomItems) {
        this.roomItems = roomItems;
    }
    
    /**
     *
     * @return
     */
    public String getRoomName()
    {
        return name;
    }
    
    /**
     *
     * @param name
     * @return
     */
    public static Room getRoom(String name){
        return rooms.get(name);
    }
    
    /**
     *
     * @return
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @param direction
     * @return
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     *
     * @param direction
     * @return
     */
    public Door getExitDoor(String direction){
        return doorways.get(direction);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getRoomItemsArray() {
        return roomItemsArray;
    }
}