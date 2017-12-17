/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import business.Game;


/**
 *
 * @author Student
 */
public class SaveAndLoad {
    
    private Game game;
    
    /**
     * Constructor for SaveAndLoad
     * @param game the current game object
     */
    public SaveAndLoad(Game game){
        this.game = game;
    }
    
    /**
     * Method used to save the  progression of a game to a file
     */
    public void save() {
        
        String timerString = Integer.toString(game.getGameTime());
        String currentRoomPlayer = game.getPlayerRoom();
        String currentRoomPet = game.getPetRoom();
        String currentRoomEvilNPC = game.getEvilNPCRoom();
        String progressString = Integer.toString(game.getProgress());
        String inventoryString = game.getPlayerInventory();
        String neighbourString = Integer.toString(game.getNeighbourInteractCount());
        ArrayList<String> pickedItemsString = game.getPickedItems();
        String doorString = Boolean.toString(game.getDoorLock());
        String ladderDoorString = Boolean.toString(game.getLadderDoorLock());
        String axeString = Game.axe.getItemLocation();
        String keyString = Game.key.getItemLocation();
        String hammerString = Game.hammer.getItemLocation();
        String nailsString = Game.nails.getItemLocation();
        String shovelString = Game.shovel.getItemLocation();
        String lumberString = Game.lumber.getItemLocation();
        String ladderString = Game.ladder.getItemLocation();
        String woodString = Game.wood.getItemLocation();
        String stolenItemString = game.getEvilNPCItem();
        String stepsString = Integer.toString(game.getSteps());
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/foundation/savefile.txt"));

            writer.write(
                    currentRoomPlayer + ";" 
                    + currentRoomPet + ";" 
                    + currentRoomEvilNPC + ";"
                    + progressString + ";" 
                    + timerString + ";" 
                    + neighbourString + ";"
                    + doorString + ";"
                    + ladderDoorString + ";"
                    + pickedItemsString + ";"
                    + inventoryString + ";"
                    + axeString + ","
                    + keyString + ","
                    + hammerString + ","
                    + nailsString + ","
                    + shovelString + ","
                    + lumberString + ","
                    + ladderString + ","
                    + woodString + ";"
                    + stolenItemString + ";"
                    + stepsString);
                    
            /*Flere ting der skal gemmes: room item locations, evil NPC inventory?, nabo tilstand, 
            døre tilstand åben/lukket, item ispicked(), score, antal skridt
            */        
            
            writer.close();
            
        } catch (NullPointerException e) {
            System.out.println("What interaction?");
        } catch (IOException e) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    /**
     * Method used to load game progression from a file
     */
    public void load() {
        try {
            BufferedReader reader = new BufferedReader (new FileReader ("src/foundation/savefile.txt"));
            
            String[] loadArray = reader.readLine().split(";");
            
            if (game.getPetRoom().equals(game.getPlayerRoom())) {
                game.pet.startFollow();
            }
            
            game.setPlayerRoom(loadArray[0]);
            game.setPetRoom(loadArray[1]);
            game.setEvilNPCRoom(loadArray[2]);
            game.setGameProgress(Integer.parseInt(loadArray[3]));
            game.setGameTime(Integer.parseInt(loadArray[4]));
            game.setNeighbourInteractCount(Integer.parseInt(loadArray[5]));
            game.setDoorLock(Boolean.parseBoolean(loadArray[6]));
            game.setLadderDoorLock(Boolean.parseBoolean(loadArray[7]));
            if (!"null".equals(loadArray[11])) {
                game.setEvilNPCItem(loadArray[11]);
            }
            game.setSteps(Integer.parseInt(loadArray[12]));
            
            loadArray[8] = loadArray[8].replace("[", "");
            loadArray[8] = loadArray[8].replace("]", "");
            if (!loadArray[8].isEmpty()) {
                
                ArrayList<String> loadPickedItems = new ArrayList<String>(Arrays.asList(loadArray[8].split(", ")));
            
                game.setPickedItems(loadPickedItems);
            }
            
            loadArray[9] = loadArray[9].replace("[", "");
            loadArray[9] = loadArray[9].replace("]", "");
            if (!loadArray[9].isEmpty()) {
            String[] loadInventory = loadArray[9].split(", ");
            
            for (String item : loadInventory) {
                game.setInventory(item);
            
            }
            
            game.itemClear();
            String[] loadItemLocations = loadArray[10].split(",");
                for (int i = 0; i < 8; i++) {
                    String location = loadItemLocations[i]; 
                    if(location != null) {
                        switch(i){
                            case 0:
                            game.loadItem("axe", loadItemLocations[i]);
                            game.setItemLocation(Game.axe, loadItemLocations[i]);
                            break;
                            case 1:
                            game.loadItem("key", loadItemLocations[i]);
                            game.setItemLocation(Game.key, loadItemLocations[i]);
                            break;
                            case 2:
                            game.loadItem("hammer", loadItemLocations[i]);
                            game.setItemLocation(Game.hammer, loadItemLocations[i]);
                            break;
                            case 3:
                            game.loadItem("nails", loadItemLocations[i]);
                            game.setItemLocation(Game.nails, loadItemLocations[i]);
                            break;
                            case 4:
                            game.loadItem("shovel", loadItemLocations[i]);
                            game.setItemLocation(Game.shovel, loadItemLocations[i]);
                            break;
                            case 5:
                            game.loadItem("lumber", loadItemLocations[i]);
                            game.setItemLocation(Game.lumber, loadItemLocations[i]);
                            break;
                            case 6:
                            game.loadItem("ladder", loadItemLocations[i]);
                            game.setItemLocation(Game.ladder, loadItemLocations[i]);
                            break;
                            case 7:
                            game.loadItem("wood", loadItemLocations[i]);
                            game.setItemLocation(Game.wood, loadItemLocations[i]);
                            break;
                        } 
                        
                    } else {
                        switch(i){
                            case 0:
                            game.loadItem("axe", null);
                            game.setItemLocation(Game.axe, null);
                            break;
                            case 1:
                            game.loadItem("key", null);
                            game.setItemLocation(Game.key, null);
                            break;
                            case 2:
                            game.loadItem("hammer", null);
                            game.setItemLocation(Game.hammer, null);
                            break;
                            case 3:
                            game.loadItem("nails", null);
                            game.setItemLocation(Game.nails, null);
                            break;
                            case 4:
                            game.loadItem("shovel", null);
                            game.setItemLocation(Game.shovel, null);
                            break;
                            case 5:
                            game.loadItem("lumber", null);
                            game.setItemLocation(Game.lumber, null);
                            break;
                            case 6:
                            game.loadItem("ladder", null);
                            game.setItemLocation(Game.ladder, null);
                            break;
                            case 7:
                            game.loadItem("wood", null);
                            game.setItemLocation(Game.wood, null);
                            break;
                    }
                }
            }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
