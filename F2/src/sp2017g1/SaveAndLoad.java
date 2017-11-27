/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1;

import originalFiles.*;
import language.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import originalFiles.Game;
import originalFiles.Room;


/**
 *
 * @author Student
 */
public class SaveAndLoad {
    
    private Game game;
    
    public SaveAndLoad(Game game){
        this.game = game;
    }
    
    public void save() {
        /*
        * Der skal laves en fil (som overwriter), der skal gemmes i.
        * 
        * Hent data (Strings): CurrentRoom Player/NPC/EvilNPC, Inventory.KeySet 
        * Items + Item Location, AnimalFollow, Progress, Timer
        * 
        * Skriv data til fil (hver string har sin egen linje)
        * 
        * (Return true)
        * 
        * 
        * inventoryString = Inventory.keyset();
        * Gem item lokationer
        * 
        */ 
        
        String timerString = Integer.toString(game.getGameTime());
        //String timerString = Long.toString(sp2017g1.Timer.getTimeSeconds());
        String currentRoomPlayer = game.getPlayerRoom();
        String currentRoomPet = game.getPetRoom();
        String currentRoomEvilNPC = game.getEvilNPCRoom();
        String progressString = Integer.toString(game.getProgress());
        //String petFollowString = Boolean.toString(pet.isFollow());
        String inventoryString = game.getPlayerInventory();
        
        
        
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("savefile.txt"));

            writer.write(
                    currentRoomPlayer + ";" 
                    + currentRoomPet + ";" 
                    + currentRoomEvilNPC + ";"
                    + progressString + ";" 
                    + timerString + ";" 
                    //+ petFollowString + ";"
                    + inventoryString);
            
            writer.close();
            
        } catch (NullPointerException e) {
            System.out.println("What interaction?");
        } catch (IOException e) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
        }
        
    
       
    }
    
    public void load() {
        try {
            BufferedReader reader = new BufferedReader (new FileReader ("savefile.txt"));
            //StringBuilder builder = new StringBuilder();
            
            String loadData = reader.readLine();
            //builder.append(loadData);
            String[] loadArray = loadData.split(";");
            
            System.out.println(loadArray[0] + " " + loadArray[1] + " " + loadArray[2] + " " + loadArray[4]);
            game.setPlayerRoom(loadArray[0]);
            game.setPetRoom(loadArray[1]);
            game.setEvilNPCRoom(loadArray[2]);
            game.setGameProgress(Integer.parseInt(loadArray[3]));
            game.setGameTime(Integer.parseInt(loadArray[4]));
            
            if (game.getPetRoom().equals(game.getPlayerRoom())) {
                game.pet.startFollow();
            }
            
            loadArray[5] = loadArray[5].replace("[", "");
            loadArray[5] = loadArray[5].replace("]", "");
            if (!loadArray[5].isEmpty()) {
            String[] loadInventory = loadArray[5].split(", ");
            
            
            for (String item : loadInventory) {
                game.setInventory(item);
            
            }
            
            System.out.println(loadInventory[0] + " " + loadInventory[1]);
            }
            
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
