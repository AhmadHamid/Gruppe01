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
    private static HashMap<String, Item> inventory;
    /*private static HashMap<ItemEnum, Item> inventory;*/
    
    Room inside, outside, west, river, waterfall, east, crossroad, oakTree, mountainside, neighbour;
    
    Door door, door2;
    
    Item key, hammer, nails, axe, shovel, lumber, block, test;
        
    public Game() 
    {
        createInventory();
        createRooms();
        createItems();
        parser = new Parser();
    }

    private void createRooms() {
        //Creates and defines the rooms used in the game.
        
        //Each room has a unique name and description.
        inside = new Room("inside a cottage");
        outside = new Room("in the forest just outside of your cottage");
        west = new Room("on the path to the local river");
        river = new Room("by the river with a great waterfall");
        waterfall = new Room("at the waterfall");
        east = new Room("at your shed");
        crossroad = new Room("at a crossroad with multiple paths");
        oakTree = new Room("at a giant oak tree");
        mountainside = new Room("at the side of a mountain");
        neighbour = new Room("at your neighbours house");
        
        door = new Door("Door to house", "south", inside, /*ItemEnum.test*/ "nails");

        
        
        //Defines the exits of each room and where they lead.
        inside.setExit("north", outside);

        outside.setExit("east", east);
        outside.setDoorway("south", door);
        outside.setExit("west", west);
        
        west.setExit("north", river);
        west.setExit("east", outside);
        
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
        axe = new Item("axe"/*ItemEnum.axe*/, inside);
        block = new Item("block"/*ItemEnum.block*/, east);
        
        shovel = new Item("shovel"/*ItemEnum.shovel*/, mountainside);
        
        nails = new Item("nails"/*ItemEnum.nails*/, west);
        
        lumber = new Item("lumber"/*ItemEnum.lumber*/, waterfall);
        
        key = new Item("key"/*ItemEnum.key*/, neighbour);
        hammer = new Item("hammer"/*ItemEnum.hammer*/, neighbour);
        
        test = new Item("test"/*ItemEnum.test*/, outside);
    }
    
    private static void createInventory() {
        inventory = new HashMap<String, Item>();
        /*inventory = new HashMap<ItemEnum, Item>();*/
    }

    public static HashMap<String/*ItemEnum*/, Item> getInventory() {
        return inventory;
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
        if(!currentRoom.getRoomItems().isEmpty()) {
            System.out.println(WordList.ITEMS_IN_ROOM);
        } else {
            
        }
        currentRoom.getRoomItemsList();
        System.out.println("TEST");
        Item.enumAllItems();
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
        else if (commandWord == CommandWord.UNLOCK) {
            unlockRoom(command);
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
        Door nextRoom1 = currentRoom.getExitDoor(direction);
        
        if (nextRoom == null && nextRoom1 == null) {
            System.out.println("There is no door!");
        }
        else if (nextRoom == null && nextRoom1.getLock() == true){
            System.out.println("The door is locked");
        }
        else if (nextRoom == null && nextRoom1.getDoor() == true){
            currentRoom = nextRoom1.getExit(direction);
            System.out.println("Going through door");
            System.out.println(currentRoom.getLongDescription());
            System.out.println(WordList.ITEMS_IN_ROOM);
            currentRoom.getRoomItemsList();
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            System.out.println(WordList.ITEMS_IN_ROOM);
            currentRoom.getRoomItemsList();
        }
        
        if (currentRoom == inside) {
            System.out.println("You win!");
            System.exit(0);
        }
    }
    
    private void unlockRoom(Command command){
        if(!command.hasSecondWord()) {
            System.out.println("Unlock what?");
            return;
        }
        
        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);
        Door nextRoom1 = currentRoom.getExitDoor(direction);
        
        if (nextRoom != null){
            System.out.println("Room is not locked");
        }
        else if (nextRoom == null && nextRoom1 == null) {
            System.out.println("There is no door!");
        }
        else if (nextRoom == null && nextRoom1.getLock() == false){
            System.out.println("Room is not locked");
        }
        else if(!inventory.containsKey(nextRoom1.getKey())) {
            System.out.println("You don't have the key!");
        } else {
            nextRoom1.setLock(false);
            System.out.println("Room is now unlocked");
            inventory.remove(nextRoom1.getKey());
            goRoom(command);
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
        } else if (!currentRoom.getRoomItems().containsKey(inputItem/*ItemEnum.valueOf(inputItem)*/)) {
            System.out.println("That is not an item!");
        }
        
        if(currentRoom.getRoomItems().containsKey(inputItem/*ItemEnum.valueOf(inputItem)*/)) {
            inventory.put(inputItem/*ItemEnum.test*/, currentRoom.getRoomItems().get(inputItem));
            currentRoom.getRoomItems().remove(inputItem/*ItemEnum.test*/);
            
            System.out.println(inventory.get(inputItem).getItemName() + " is added to the inventory");
            //System.out.println(inventory.get(ItemEnum.valueOf(inputItem)).getItemName());
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
                System.out.printf("%s  ", inventory.get(item).getItemName());
            /*for (ItemEnum item : inventory.keySet()) {
                System.out.printf("%s  ", inventory.get(item).getItemName());*/
            } System.out.println();
        } else {
            System.out.println("No items in the inventory");
        }
    }
}
