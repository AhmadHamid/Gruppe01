/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;


import java.util.HashMap;

import originalFiles.Command;

import originalFiles.Room;


/**
 *
 * @author Sebastian
 */
public class EvilNPC extends NPC {

    

    private Room lastRoom;
    private String stolenItem; 
    
    /**
     * 
     * @param room spawn location
     */
    public EvilNPC(Room room){
        this.currentRoom = room;
        this.species = Species.HUMAN;
        lastRoom = null;
    }
    
    /**
     * 
     * @param command 
     */
    @Override
    public void interact(Command command) {
    
    }
    
    /**
     * 
     * @return location of the NPC 
     */
    @Override
    public  Room getCurrentRoom() {
        return currentRoom;
    }
    
    /**
     * Method for changing the location of the NPC
     * @param currentRoom room the NPC is moved to
     */
    @Override
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    /**
     * 
     * @param stolenItem item that is taking from players inventory
     */
    public void setStolenItem(String stolenItem){
        this.stolenItem = stolenItem;
    }
    
    /**
     * 
     * @return the stolen item
     */
    public String getStolenItem(){
        return stolenItem;
    }

    /**
     * 
     * @param currentRoom 
     */
    public void setLastRoom(Room currentRoom){
        this.lastRoom = currentRoom;
    }
    
    /**
     * 
     * @return the last room the was in 
     */
    public Room getLastRoom(){
        return lastRoom;
    }
}
        
    
    