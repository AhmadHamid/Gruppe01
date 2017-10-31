/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

import language.*;
import java.awt.Robot;
import originalFiles.*;
/**
 *
 * @author Student
 */
public class Animal extends NPC{
    private boolean flying;
    private boolean swimming;
    private boolean predator;
    
    public Animal(Species species, Room room){
        this.species = species;
        this.swimming = true;
        this.flying = false;
        this.predator = false;
        this.currentRoom = room;
    }
    
    public String speciesToString() {
        return species.toString();
    }
    /*
    public Animal(String name, String species, boolean predator) {
        this.name = name;
        this.species = species;
        this.predator = predator;
    }
    
    public Animal(String name, String species, boolean predator, boolean canFly) {
        this.name = name;
        this.species = species;
        this.predator = predator;
        this.flying = canFly;
    }
    
    public Animal(String name, String species, boolean predator, boolean canSwim, int i) {
        this.name = name;
        this.species = species;
        this.predator = predator;
        this.swimming = canSwim;
    }
    */

//    public String getSpecies() {
//        return species;
//    }

    public boolean isFlying() {
        return flying;
    }

    public boolean isSwimming() {
        return swimming;
    }

    public boolean isPredator() {
        return predator;
    }

    @Override
    public void interact(Command command) {
        System.out.println("Animal interact.");
    }
    
    public void interact(int progress) {
        switch(progress){
            case 0:
                System.out.println(WordList.NO_PET);
                break;
            case 1:
                System.out.println(WordList.AFTER_PET);
                break;
            case 2:
                System.out.println(WordList.ASK_NEIGHBOR);
                break;
            case 3:
                System.out.println(WordList.FIND_SHOVEL);
                break;
            case 4:
                System.out.println(WordList.CRAFT_LADDER);
                break;
            case 5:
                System.out.println(WordList.GET_SHOVEL);
                break;
            case 6:
                System.out.println(WordList.GET_KEY);
                break;
            case 7:
                System.out.println(WordList.GO_HOME);
                break;
        }   
    }
}
