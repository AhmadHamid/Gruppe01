package sp2017g1;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import originalFiles.*;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Door 
{
    private String description;
    private HashMap<String, Room> exits;
    private boolean lock = true;
    private final boolean door = true;
    private String lockItem;
    
    public Door(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    public void setExitDoor(String direction, Room neighbor, String lockItem) 
    {
        exits.put(direction, neighbor);
        this.lockItem = lockItem;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }
    
    public boolean getLock(){
        return lock;
    }
    
    public boolean getDoor(){
        return door;
    }
    
    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You walk through " + description + ".\n";
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

