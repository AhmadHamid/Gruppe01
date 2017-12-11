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
   // private static HashMap<ItemEnum, Item> inventory;
    private String stolenItem; 
//    private String[] items;
    
    
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
    /*
    public static HashMap getEvilInventory() {
        return inventory;
    }
    
    
    public void setItems(){
    if (getEvilNPCInventory() != null){
       String[] items =  getEvilNPCInventory().split(";");
     }
    else {
        
    }
    
    }
    
    public String getItems(){
    
}
    



    
    public String getEvilNPCInventory(){
        return inventory.keySet().toString().replace("[", "").replace("]", "").replace(";", "").replace(" ", "");
    }
        public void interact() {

      System.out.println("EvilNPC returns your " + items[0]);
    
    }
        public void printSteal(String item){
     System.out.println("EvilNPC stole your " + item + " and ran away");
}
    */
    public void setStolenItem(String stolenItem){
        this.stolenItem = stolenItem;
    }
    
    public String getStolenItem(){
        return stolenItem;
    }
    
    
/*
    if ( P1.currentroom == EvilNPC.currentroom){
       if (EvilNPC.hasitem == true){
    EvilNPC.GiveStolenItem
    }
    else{
    EvilNPC.steal(randomitem);
    }
    }
    
    */
    public void setLastRoom(Room currentRoom){
        this.lastRoom = currentRoom;
    }
    public Room getLastRoom(){
        return lastRoom;
    }
/*
    public void addEvilItem(String itemName){
        ItemEnum stolenItem = ItemEnum.valueOf(itemName);
        inventory.put(stolenItem, Item.getItem(stolenItem));
        
    }
    
    public void RemoveEvilItem(ItemEnum item){
       
        inventory.remove(item);
    }
    
    */
    /*@Override
    public void move(){
        double i = Math.random() * 10;
        if (i > 0 && i < 2.6){
            if (currentRoom.getExit("north") == null){
                
            } else {
            //lastRoom = currentRoom;
            currentRoom = currentRoom.getExit("north");
            }
        } else if (i > 2.5 && i < 5.1){
            if (currentRoom.getExit("east") == null){
                
            } else {
                lastRoom = currentRoom;
            currentRoom = currentRoom.getExit("east");
            }
        } else if (i > 5 && i < 7.6){
            if (currentRoom.getExit("west") == null){
                
            } else {
                lastRoom = currentRoom;
            currentRoom = currentRoom.getExit("west");
            }
        } else {
            if (currentRoom.getExit("south") == null){
                
            } else {
                lastRoom = currentRoom;
            currentRoom = currentRoom.getExit("south");
            }
        }
    }*/
    
}
        
    
    