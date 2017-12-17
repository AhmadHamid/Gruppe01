/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.language.WordList;
import business.Room;
import business.Command;
import java.awt.Robot;

/**
 * 
 * 
 */
public class Animal extends NPC{
    private int progress;
    private boolean follow;
    
    public Animal(Species species, Room room){
        this.species = species;
        this.currentRoom = room;
        this.follow = false;
    }
    
    /**
     *
     * @return Type of species as string.
     */
    public String speciesToString() {
        return species.toString();
    }

    /**
     *
     * @return true if the pet is following you, otherwise false.
     */
    public boolean isFollow() {
        return follow;
    }
    
    /**
     * Sets the follow attribute as true.
     * 
     */
    public void startFollow(){
        follow = true;
    }

    /**
     *
     * @param command 
     */
    @Override
    public void interact(Command command) {
        System.out.println("Animal interact.");
    }
    
    /**
     *
     * @param progress indication of how far you are in the game
     * @return a hint as a string based on the progression you have
     */
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
    
    /**
     *
     * @param i number that represents how far you are in the game
     * @return true if the progress is changed, otherwise false
     */
    public boolean setProgress(int i){
        if(progress < i){
            progress = i;
            return true;
        } else{
            return false;
        }
    }
    
    /**
     *
     * @param room the room you are entering
     * @return true if your pet is following you into a room, otherwise false
     */
    public boolean goPet(Room room){
        if (follow){
            currentRoom = room;
            return true;
        } else{
            return false;
        }
    }
}
