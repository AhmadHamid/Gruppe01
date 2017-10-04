package originalFiles;

import java.util.*;
import sp2017g1.*;
import language.*;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private HashMap<String, Item> inventory;
    
    Room outside, theatre, pub, lab, office;
    Item handske, ske, sten, ble;
        
    public Game() 
    {
        createRooms();
        createItems();
        createInventory();
        parser = new Parser();
    }

    private void createRooms()
    {
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;
    }
    
    private void createItems() {
        ske = new Item("ske", outside);
        handske = new Item("handske", outside);
        
        ble = new Item("ble", theatre);
        
        sten = new Item("sten", lab, true);
    }
    
    private void createInventory() {
        inventory = new HashMap<String, Item>();
    }

    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println(WordList.WELCOME);
        System.out.println(WordList.DESCRIPTION);
        System.out.println(WordList.GET_HELP);
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        System.out.println(WordList.ITEMS_IN_ROOM);
        currentRoom.getRoomItemsList();
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println(WordList.DONT_KNOW_WHAT_YOU_MEAN);
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.PICK) {
            pickItem(command);
        }
        else if (commandWord == CommandWord.USE) {
            dropItem(command);
        }
        else if (commandWord == CommandWord.DROP) {
            dropItem(command);
        }
        else if (commandWord == CommandWord.INVENTORY) {
            printInventory(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println(WordList.PRINT_HELP);
        parser.showCommands();
    }

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println(WordList.GO_WHERE);
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            
            System.out.print("These items is in the room: ");
            currentRoom.getRoomItemsList();
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
    
    private void pickItem(Command command) {
        String inputItem = command.getSecondWord().toLowerCase();
        
        if(!command.hasSecondWord()) {
            System.out.println("Pick what item?");
        } else if (!currentRoom.getRoomItems().containsKey(inputItem)) {
            System.out.println("That is not an item!");
        } else if (currentRoom.getRoomItems().get(inputItem).isNotCollectable()) {
            System.out.println("Item is not collectable");
        }
        
        if(currentRoom.getRoomItems().containsKey(inputItem)) {
            inventory.put(inputItem, currentRoom.getRoomItems().get(inputItem));
            currentRoom.getRoomItems().remove(inputItem);
            
            System.out.println(inventory.get(inputItem).getItemName() + " is added to the inventory");
        }
    }
    
    private void useItem(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Use what item?");
        }
    }
    
    private void dropItem(Command command) {
        String inputItem = command.getSecondWord().toLowerCase();
        
        if(!command.hasSecondWord()) {
            System.out.println("Drop what item?");
        } else if (!inventory.containsKey(inputItem)) {
            System.out.println("That is not an item");
        }
        
        if(inventory.containsKey(inputItem)) {
            currentRoom.getRoomItems().put(inputItem, inventory.get(inputItem));
            inventory.remove(inputItem);
            
            System.out.println(currentRoom.getRoomItems().get(inputItem).getItemName() + " is removed from the inventory");
        }
    }
    
    private void printInventory(Command command) {
        if(!inventory.isEmpty()) {
            System.out.println("In your inventory is: ");
            for (String item : inventory.keySet()) {
                System.out.printf("%s\t", inventory.get(item).getItemName());
            } System.out.println();
        } else {
            System.out.println("No items in the inventory");
        }
    }
}
