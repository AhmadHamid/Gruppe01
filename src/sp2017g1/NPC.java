/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

import originalFiles.Room;

/**
 *
 * @author Student
 */
public abstract class NPC {
    protected Species species;
    protected Room currentRoom;
    
    public Species getSpecies(){
        return species;
    }
    
    abstract void interact();
    
}
