package originalFiles;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import sp2017g1.*;


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
    private HashMap<String, Door> doorways;

    public Room(String description, String name) 
    {
        this.name = name;
        this.description = description;
        exits = new HashMap<String, Room>();
        this.roomItems = new HashMap<ItemEnum, Item>();
        doorways = new HashMap<String, Door>();
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

//    Return list of items in room
    public void getRoomItemsList() {
        for (ItemEnum item : roomItems.keySet()) {
            System.out.printf("%s ", roomItems.get(item).getItemName());
        }
        System.out.println();
    }
    
    public String getName()
    {
        return name;
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
}

