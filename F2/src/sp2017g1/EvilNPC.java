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
    
    
    public EvilNPC(Room room){
        this.currentRoom = room;
        this.species = Species.HUMAN;
        lastRoom = null;
    }
@Override
    public void interact(Command command) {
      System.out.println("EvilNPC returns your ");
    
    }
  @Override
    public  Room getCurrentRoom() {
        return currentRoom;
    }
    @Override
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setStolenItem(String stolenItem){
        this.stolenItem = stolenItem;
    }
    
    public String getStolenItem(){
        return stolenItem;
    }

    public void setLastRoom(Room currentRoom){
        this.lastRoom = currentRoom;
    }
    public Room getLastRoom(){
        return lastRoom;
    }
}
        
    
    