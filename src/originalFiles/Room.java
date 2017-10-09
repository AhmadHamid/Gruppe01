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
    private HashMap<String, Room> exits;
    private HashMap<String, Item> roomItems;
    private HashMap<String, Door> doorways;

    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.roomItems = new HashMap<String, Item>();
        doorways = new HashMap<String, Door>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    
//    Return HashMap with items in room
    public HashMap<String, Item> getRoomItems() {
        return roomItems;
    }

//    Return list of items in room
    public void getRoomItemsList() {
        for (String item : roomItems.keySet()) {
            System.out.printf("%s  ", roomItems.get(item).getItemName());
        }
        System.out.println();
    }
    
    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
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

