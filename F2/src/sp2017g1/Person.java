/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;
import java.util.*;
import originalFiles.*;
/**
 *
 * @author Student
 */
public class Person extends NPC {
    private int interactCount = 0;
    
    public Person(Room room){
        this.currentRoom = room;
        this.species = Species.HUMAN;
    }

    @Override
    public void interact(Command command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String interactExtended(Command command, Item k, Item h, HashMap<ItemEnum, Item> inv) {
        if (interactCount == 0) {
            System.out.println("WELCOME!");
            
            System.out.println("1. First time.");
            interactCount++;
            Game.setProgress(3);
            
        } else if (interactCount == 1) {
            
//            System.out.println("2. Key or Hammer?");
//            Scanner input = new Scanner(System.in);
//            String option = input.next();
//            if (option.equalsIgnoreCase(ItemEnum.key.toString())) {
//                System.out.println("Tell about key.");
//            } else if(option.equalsIgnoreCase(ItemEnum.hammer.toString()) && !Game.getInventory().containsKey(ItemEnum.hammer)) {
//                System.out.println(ItemEnum.hammer.toString() + " is added to the inventory");
//                getItemH(h, inv);
//                interactCount++;
//            }
            getItemH(h, inv);
            interactCount++;
            return ItemEnum.hammer.toString() + " is added to the inventory";
        } else if (interactCount == 2) {
            if (!Game.getInventory().containsKey(ItemEnum.shovel)) {
                System.out.println("You have the hammer.");
                System.out.println("Go find the shovel!");
                return "You have the hammer"  + "\n" + "Go find the shovel!";
            } else {
                System.out.println("Here is the key!");
                System.out.println(ItemEnum.key.toString() +  " is added to the inventory");
                getItemK(k, inv);
                Game.getInventory().remove(ItemEnum.shovel);
                return "Here is the key!" + "\n" + ItemEnum.key.toString() +  " is added to the inventory";
            }
        } 
        return null;
    }
    
    public void getItemH(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.hammer, item);
    }
    
    public void getItemK(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.key, item);
    }

    @Override
    public String introMessage(String string) {
        return super.introMessage(string); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getInteractCount() {
        return interactCount;
    }
    
    public void setInteractCount(int interactCount) {
        this.interactCount = interactCount;
    }
    
}
