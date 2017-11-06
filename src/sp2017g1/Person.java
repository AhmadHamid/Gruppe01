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
    int interactCount = 0;
    
    public Person(Room room){
        this.currentRoom = room;
        this.species = Species.HUMAN;
    }

    @Override
    public void interact(Command command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void interactExtended(Command command, Item k, Item h, HashMap<ItemEnum, Item> inv) {
//        Forslag 1
//        if (interactCount == 0) {
//            System.out.println("Me: FIRST TIME TALKING.");
//            System.out.println("N: Bla bla bla");
//            interactCount++;
//        } else if (interactCount == 1) {
//            System.out.println("N: Key or Hammer?");
//            Scanner input = new Scanner(System.in);
//            String option = input.next().toLowerCase();
//            if (option.equals(ItemEnum.hammer.toString()) && !Game.getInventory().containsKey(ItemEnum.hammer)) {
//                System.out.println("Hammer to inventory...");
//            } else if (option.equals(ItemEnum.hammer.toString()) && Game.getInventory().containsKey(ItemEnum.hammer)) {
//                System.out.println("Hammer is already in inventory...");
//            } else {
//                interactCount++;
//            }
//        } else if (interactCount == 2) {
//            System.out.println("Key to inventory...");
//        }

//        Forslag 1.1
        if (interactCount == 0) {
            System.out.println("1. First time.");
            interactCount++;
            Game.setProgress(3);
            
        } else if (interactCount == 1) {
            System.out.println("2. Key or Hammer?");
            Scanner input = new Scanner(System.in);
            String option = input.next();
            if (option.equalsIgnoreCase(ItemEnum.key.toString())) {
                System.out.println("Tell about key.");
            } else if(option.equalsIgnoreCase(ItemEnum.hammer.toString()) && !Game.getInventory().containsKey(ItemEnum.hammer)) {
                System.out.println("Give hammer...");
                getItemH(h, inv);
                interactCount++;
            }
        } else if (interactCount == 2) {
            if (!Game.getInventory().containsKey(ItemEnum.shovel)) {
                System.out.println("You have the hammer.");
                System.out.println("Go find the shovel!");
            } else {
                System.out.println("Here is the key!");
                getItemK(k, inv);
                Game.getInventory().remove(ItemEnum.shovel);
            }
        }
    }
    
    public void getItemH(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.hammer, item);
    }
    
    public void getItemK(Item item, HashMap<ItemEnum, Item> inv) {
        inv.put(ItemEnum.key, item);
    }
}
