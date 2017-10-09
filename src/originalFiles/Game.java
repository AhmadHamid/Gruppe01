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
    
    Room inside, outside, west, river, waterfall, east, crossroad, oakTree, mountainside, neighbour;
    Item handske, ske, sten, ble;
        
    public Game() 
    {
        createRooms();
        createItems();
        createInventory();
        parser = new Parser();
    }

    private void createRooms() {
        //Creates and defines the rooms used in the game.
        
        //Each room has a unique name and description.
        inside = new Room("inside a cottage");
        outside = new Room("in the forest outside the cottage");
        west = new Room("west of the cottage");
        river = new Room("by the river");
        waterfall = new Room("at the waterfall");
        east = new Room("east of the cottage");
        crossroad = new Room("at a crossroad with multiple paths");
        oakTree = new Room("at a giant oak tree");
        mountainside = new Room("at the side of a mountain");
        neighbour = new Room("at your neighbours house");
        
        //Defines the exits of each room and where they lead.
        inside.setExit("north", outside);

        outside.setExit("east", east);
        outside.setExit("south", inside);
        outside.setExit("west", west);

        west.setExit("north", river);
        west.setExit("east", outside);
        west.setExit("west", neighbour);

        river.setExit("north", waterfall);
        river.setExit("south", west);

        waterfall.setExit("south", river);

        east.setExit("north", crossroad);
        east.setExit("west", outside);

        crossroad.setExit("north", mountainside);
        crossroad.setExit("east", oakTree);
        crossroad.setExit("south", east);

        mountainside.setExit("south", crossroad);

        oakTree.setExit("west", crossroad);

        neighbour.setExit("east", west);
        currentRoom = outside;
    }
    
    private void createItems() {
        ske = new Item("ske", outside);
        handske = new Item("handske", outside);
        
        ble = new Item("ble", west);

        sten = new Item("sten", east, true);
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
        
        if(currentRoom.getRoomItems().get(inputItem).isNotCollectable()) {
            
        } else if(currentRoom.getRoomItems().containsKey(inputItem)) {
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
                System.out.printf("%s   ", inventory.get(item).getItemName());
            } System.out.println();
        } else {
            System.out.println("No items in the inventory");
        }
    }
}
