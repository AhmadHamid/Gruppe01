package originalFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static HashMap<ItemEnum, Item> inventory;
    private static int progress;
    
    public Room home, garden, bridge, river, waterfall, shed, mountainside, forest, mountain, neighbourHouse;
    
    Door door, Ladderdoor;
    
    // Changed the access modifier of the Item variable to static so the hashmap key (not the actual item called key) can be accessed in TreeStump.java. No idea why it cannot be accessed from TreeStump.java without it.
    public static Item key, hammer, nails, axe, shovel, lumber, block, ladder, test, test1, test2, test3, test4, wood;
    
    Person neighbour;
    TreeStump treeStump;
    public Animal pet;
    Person evilNPC;
    
    sp2017g1.Timer timer;

    public static void setProgress(int i){
        if(progress < i)
            progress = i;
    }
    
    public Game() 
    {
        createInventory();
        createRooms();
        createItems();
        createNPC();
        parser = new Parser();
        progress = 0;
    }
    
    private void save() {
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
        
        String timerString = Integer.toString(timer.getTime());

        String currentRoomPlayer = currentRoom.getName();
        String currentRoomPet = pet.getCurrentRoom().getName();
        String currentRoomEvilNPC = evilNPC.getCurrentRoom().getName();
        String progressString = Integer.toString(progress);
        String petFollowString = Boolean.toString(pet.isFollow());
        String inventoryString = inventory.keySet().toString();
        
        
        
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("savefile.txt"));

            writer.write(
                    currentRoomPlayer + ";" 
                    + currentRoomPet + ";" 
                    + currentRoomEvilNPC + ";"
                    + progressString + ";" 
                    + inventoryString + ";" 
                    + petFollowString + ";"
                    + timerString);
            
            writer.close();
            
        } catch (IOException e) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, e);
}
        System.out.println("Game Saved");
        
    }
    
    private void load() {
        try {
            BufferedReader reader = new BufferedReader (new FileReader ("savefile.txt"));
            StringBuilder builder = new StringBuilder();
            
            String loadData = reader.readLine();
            builder.append(loadData);
            String[] loadArray = loadData.split(";");
            
            System.out.println(loadArray);
            
            reader.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    private void createRooms() {
        //Creates and defines the rooms used in the game.
        
        //Each room has a unique name and description.
        home = new Room("home", "home");
        garden = new Room("in your garden outside your home","garden");
        bridge   = new Room("on the bridge that crosses the local river", "bridge");
        river = new Room("by the river with a great waterfall", "river");
        waterfall = new Room("at the waterfall", "waterfall");
        shed = new Room("at your shed", "shed");
        mountainside = new Room("at the side of the mountain", "mountainside");
        forest = new Room("at a giant oak tree", "forest");
        mountain = new Room("on a mountain cliff", "mountain");
        neighbourHouse = new Room("at your neighbours house", "neighbourHouse");
        
        door = new Door("The door to your house", ItemEnum.key);
        Ladderdoor = new Door("ladder to the top of the mountain", ItemEnum.ladder);
   
        
        //Defines the exits of each room and where they lead.

        garden.setExit("east", shed);
        garden.setExit("south", door);
        garden.setExit("west", bridge);

        
        bridge.setExit("north", river);
        bridge.setExit("east", garden);
        bridge.setExit("west", neighbourHouse);
        
        river.setExit("north", waterfall);
        river.setExit("south", bridge);

        waterfall.setExit("south", river);

        shed.setExit("north", mountainside);
        shed.setExit("west", garden);


        mountainside.setExit("north", Ladderdoor);
        mountainside.setExit("east", forest);
        mountainside.setExit("south", shed);


        mountain.setExit("south", mountainside);

        forest.setExit("west", mountainside);


        neighbourHouse.setExit("east", bridge);
        
        door.setExit("south", home);
        
        Ladderdoor.setExit("north", mountain);
        currentRoom = garden;
    }
    
    private void createItems() {
        axe = new Item(ItemEnum.axe, shed);
//        block = new Item(ItemEnum.block, shed);
        shovel = new Item(ItemEnum.shovel, mountain);
        nails = new Item(ItemEnum.nails, bridge);
        wood = new Item(ItemEnum.wood, waterfall);
        test = new Item(ItemEnum.test, garden);
        test1 = new Item(ItemEnum.test1, garden);
        test2 = new Item(ItemEnum.test2, garden);
        test3 = new Item(ItemEnum.test3, garden);
        test4 = new Item(ItemEnum.test4, garden);

        key = new Item(ItemEnum.key);
        hammer = new Item(ItemEnum.hammer);
        lumber = new Item(ItemEnum.lumber);
        ladder = new Item(ItemEnum.ladder);
    }
    
    private static void createInventory() {
        inventory = new HashMap<ItemEnum, Item>();
    }

    public static HashMap<ItemEnum, Item> getInventory() {
        return inventory;
    }
    
    private void createNPC() {
        neighbour = new Person(neighbourHouse);
        treeStump = new TreeStump(shed);
        pet = new Animal(Species.DOG, forest);
        evilNPC = new Person(waterfall);
    }

    public void play()
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            try {
                Command command = parser.getCommand();
                finished = processCommand(command);
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        /*System.out.println("TEST");
        Item.enumAllItems();*/
    }

    private boolean processCommand(Command command) throws IOException 
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
        /* The combine command has been replaced by the Tree Stump interaction.
        else if (commandWord == CommandWord.COMBINE) {
        combineItems(command);
        }*/
        else if (commandWord == CommandWord.INTERACT) {
            interact(command);
        }
        else if (commandWord == CommandWord.SAVE){
            save();
        }
        else if (commandWord == CommandWord.LOAD){
            load();
        }
                
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println(WordList.PRINT_HELP);
        parser.showCommands();
    }
    
    public void goRoom(String direction){
        CommandWord commandWord = CommandWord.GO;
        Command command = new Command(commandWord, direction);
        goRoom(command);
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
            if (nextRoom1.getExit(direction).getShortDescription() == "home"){
                if(progress == 0){
                    System.out.println("I should get my pet before going home");
                } else{
                    setProgress(2);
                }
            }
        }
        else if (nextRoom == null && nextRoom1.getDoor() == true){
            currentRoom = nextRoom1.getExit(direction);
            pet.goPet(nextRoom1.getExit(direction));
            if (currentRoom == home) {
                System.out.println(WordList.END_DESCRIPTION);
                System.exit(0);
            }
            System.out.println("Going through door");
            System.out.println(currentRoom.getLongDescription());
            /*if (neighbour.getCurrentRoom() == currentRoom) {
                neighbour.interact(command);
            }*/
            System.out.println(WordList.ITEMS_IN_ROOM);
            currentRoom.getRoomItemsList();
        } else {
            currentRoom = nextRoom;
            pet.goPet(nextRoom);
            
            System.out.println(currentRoom.getLongDescription());
            if (currentRoom == neighbour.getCurrentRoom()) {
                System.out.println("You see your neighbour in the room.");
                command.setSecondWord("neighbour");
                interact(command);
            } else if(currentRoom == treeStump.getCurrentRoom()) {
                System.out.println("You see a treestump in the room.");
            } else if (currentRoom == pet.getCurrentRoom() && !pet.isFollow()) {
                System.out.println("You see a pet. It will now follow you.");
                pet.startFollow();
            }
            if (!currentRoom.getRoomItems().isEmpty()) {
                System.out.println(WordList.ITEMS_IN_ROOM);
                currentRoom.getRoomItemsList();   
            } else {
                System.out.println("No items in room.");
            }
        }
        

        if (currentRoom == home) {
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
        else if(!inventory.containsKey(ItemEnum.valueOf(nextRoom1.getKey()))) {
            System.out.println("You don't have the key!");
        } else {
            nextRoom1.setLock(false);
            System.out.println("Room is now unlocked");
            //inventory.remove(nextRoom1.getKey());
            inventory.remove(ItemEnum.valueOf(nextRoom1.getKey()));
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
    
    public void pickItem(String item){
        CommandWord commandWord = CommandWord.GO;
        Command command = new Command(commandWord, item);
        pickItem(command);
    }
    
    private void pickItem(Command command) {
        try {
            ItemEnum inputItem = ItemEnum.valueOf(command.getSecondWord().toLowerCase());
      
        if(currentRoom.getRoomItems().containsKey(inputItem) && inventory.size() < 3) {
            inventory.put(inputItem, currentRoom.getRoomItems().get(inputItem));
            currentRoom.getRoomItems().remove(inputItem);
            if(inventory.get(ItemEnum.valueOf(command.getSecondWord())).getItemName() == "shovel"){
                setProgress(5);
            }
            System.out.println(inventory.get(ItemEnum.valueOf(command.getSecondWord())).getItemName() + " is added to the inventory");
        } else if(!currentRoom.getRoomItems().containsKey(inputItem)) {
            System.out.println("That item is not in the room!");
        } else {
            System.out.println("Inventory is full");
        }
        } catch (NullPointerException e) {
            System.out.println("Pick what item?");
        } catch (IllegalArgumentException e) {
            System.out.println("That is not an item!");
        }
    }
    
    private void useItem(Command command) {
        if(!command.hasSecondWord()) {
            System.out.println("Use what item?");
        }
    }
    
    private void dropItem(Command command) {
        try {
            ItemEnum inputItem = ItemEnum.valueOf(command.getSecondWord().toLowerCase());
        
        if(inventory.containsKey(inputItem)) {
            currentRoom.getRoomItems().put(inputItem, inventory.get(inputItem));
            inventory.remove(inputItem);
            
            System.out.println(currentRoom.getRoomItems().get(inputItem).getItemName() + " is removed from the inventory");
        }
        } catch (NullPointerException e) {
            System.out.println("Drop what item?");
        } catch (IllegalArgumentException e) {
            System.out.println("That is not an item");
        }
    }
    
    private void printInventory(Command command) {
        if(!inventory.isEmpty()) {
            System.out.println("In your inventory is: ");
            for (ItemEnum item : inventory.keySet()) {
                System.out.printf("%s  ", inventory.get(item).getItemName());
            } System.out.println();
        } else {
            System.out.println("No items in the inventory");
        }
    }

    // Move this method to TreeStump.java.
    /*    private void combineItems(Command command) {
    if(inventory.containsKey(ItemEnum.nails)) {
    inventory.put(ItemEnum.ladder, ladder);
    inventory.remove(ItemEnum.nails);
    System.out.println(nails.getItemName() + " and x " + "has been combined to " + ladder.getItemName());
    }
    
    else {
    System.out.println("None of the required items are in your inventory.");
    }
    }*/
    
    private void interact(Command command) {
        try {
            String inputCommand = command.getSecondWord().toLowerCase();
            
            if (command.getSecondWord().isEmpty()) {
                System.out.println("SecondWord!");
            }
            
            if (inputCommand.equals("pet")){
                if (pet.getCurrentRoom() == currentRoom){
                    pet.interact(progress);
                    setProgress(1);
                } else {
                System.out.println(WordList.NO_PET);
                }
            } else if (inputCommand.equals("neighbour")){
                if (currentRoom == neighbour.getCurrentRoom()) {
                neighbour.interactExtended(command, key, hammer, inventory);
                setProgress(3);
                if(inventory.containsKey(ItemEnum.hammer)){
                    //setProgress();
                  } else if(inventory.containsKey(ItemEnum.key)){
                  setProgress(6);
                  }
                }
            } else if (inputCommand.equals("stump")){
                if (currentRoom == treeStump.getCurrentRoom()) {
                treeStump.interactExtendedStump(command, nails, hammer, wood, lumber, ladder, inventory);
                    if(inventory.containsKey(ItemEnum.ladder)){
                    setProgress(4);
                    }
                }
            } else {
                System.out.println(WordList.WRONG_INTERACT);
            }
                
            /*
            if (currentRoom == neighbour.getCurrentRoom()) {
                neighbour.interactExtended(command, key, hammer, inventory);
            } else if (currentRoom == treeStump.getCurrentRoom()) {
                treeStump.interactExtendedStump(command, nails, hammer, wood, lumber, ladder, inventory);
            }

            */
            /*if (currentRoom == treeStump.getCurrentRoom()) {
                treeStump.interactExtendedStump(command, nails, hammer, wood, lumber, ladder, inventory);
            } else {
            System.out.println("There is no Tree Stump in this room.");
            }*/
                    
        } catch (NullPointerException e) {
            System.out.println("What interaction?");
        }
    }
}

