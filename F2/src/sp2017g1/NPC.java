/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

import originalFiles.*;

/**
 *
 * @author Student
 */
public abstract class NPC {
    protected Species species;
    protected Room currentRoom;
    protected int interactCount;
    
    /**
     * 
     * @return NPC type
     */
    public Species getSpecies(){
        return species;
    }
    
    /**
     * 
     * @param command 
     */
    public abstract void interact(Command command);
    
    /**
     * 
     * @param string message
     * @return message
     */
    public String introMessage(String string) {
        return string;
    }

    /**
     * 
     * @return location of the NPC 
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * 
     * @param currentRoom new location for the NPC
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
    
    /**
     * Method that makes the NPC's able to move directions at random
     */
    public void move(){
        double i = Math.random() * 10;
        if (i > 0 && i < 2.6){
            if (currentRoom.getExit("north") == null){
                
            } else {
            currentRoom = currentRoom.getExit("north");
            }
        } else if (i > 2.5 && i < 5.1){
            if (currentRoom.getExit("east") == null){
                
            } else {
            currentRoom = currentRoom.getExit("east");
            }
        } else if (i > 5 && i < 7.6){
            if (currentRoom.getExit("west") == null){
                
            } else {
            currentRoom = currentRoom.getExit("west");
            }
        } else {
            if (currentRoom.getExit("south") == null){
                
            } else {
            currentRoom = currentRoom.getExit("south");
            }
        }
        
    }
}
