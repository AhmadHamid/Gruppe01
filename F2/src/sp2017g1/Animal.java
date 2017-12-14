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
    private int progress;
    private boolean follow;
    
    public Animal(Species species, Room room){
        this.species = species;
        this.currentRoom = room;
        this.follow = false;
    }
    
    public String speciesToString() {
        return species.toString();
    }

    public boolean isFollow() {
        return follow;
    }
    
    public void startFollow(){
        follow = true;
    }

    @Override
    public void interact(Command command) {
        System.out.println("Animal interact.");
    }
    
    public String interact(int progress) {
        switch(progress){
            case 1:
                System.out.println(WordList.AFTER_PET);
                return WordList.AFTER_PET;
            case 2:
                System.out.println(WordList.ASK_NEIGHBOR);
                return WordList.ASK_NEIGHBOR;
            case 3:
                System.out.println(WordList.CRAFT_LADDER);
                return WordList.CRAFT_LADDER;
            case 4:
                System.out.println(WordList.GET_SHOVEL);
                return WordList.GET_SHOVEL;
            case 5:
                System.out.println(WordList.GET_KEY);
                return WordList.GET_KEY;
            case 6:
                System.out.println(WordList.GO_HOME);
                return WordList.GO_HOME;
        }   
        return null;
    }
    
    public boolean setProgress(int i){
        if(progress < i){
            progress = i;
            return true;
        } else{
            return false;
        }
    }
    
    public boolean goPet(Room room){
        if (follow){
            currentRoom = room;
            return true;
        } else{
            return false;
        }
    }
}
