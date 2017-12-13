package originalFiles;

import java.io.File;
import java.util.*;
import sp2017g1.*;
import language.*;
//import sp2017g1f2.*;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game 
{
    private WriteToStory c;// = new FXMLDocumentController();
    private Parser parser;
    private Room currentRoom;
    private static HashMap<ItemEnum, Item> inventory;
    private static int progress;
    private int effScore;
    private int timeScore;
    private int steps;
    public String scoreString;
    private ArrayList<String> pickedItems = new ArrayList<String>();
    
    public Room home, garden, bridge, river, waterfall, shed, mountainside, forest, mountain, neighbourHouse;
    
    Door door, ladderDoor;
    
    private Score score = new Score(this);
    private SaveAndLoad saveAndLoad = new SaveAndLoad(this);
    
    // Changed the access modifier of the Item variable to static so the hashmap key (not the actual item called key) can be accessed in TreeStump.java. No idea why it cannot be accessed from TreeStump.java without it.
    public static Item axe, key, hammer, nails, shovel, lumber, ladder, wood;
    
    Person neighbour;
    TreeStump treeStump;
    public Animal pet;
    EvilNPC evilNPC;
    sp2017g1.Timer time = new sp2017g1.Timer();
    
    public static void setProgress(int i){
        if(progress < i)
            progress = i;
    }
    public void evilNPCEncounter(String item){
        inventory.remove(ItemEnum.valueOf(item));
        evilNPC.setStolenItem(item);
        c.toStoryField("EvilNPC stole your " + evilNPC.getStolenItem() + " and ran away\n");
    }
    
    public Game(WriteToStory _c) 
    {
        createInventory();
        createRooms();
        createItems();
        createNPC();
        parser = new Parser();
        progress = 0;
        c = _c;
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
        
        door = new Door("The door to your house", ItemEnum.key, "home");
        ladderDoor = new Door("ladder to the top of the mountain", ItemEnum.ladder, "mountain");
   
        
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


        mountainside.setExit("north", ladderDoor);
        mountainside.setExit("east", forest);
        mountainside.setExit("south", shed);


        mountain.setExit("south", mountainside);

        forest.setExit("west", mountainside);


        neighbourHouse.setExit("east", bridge);
        
        door.setExit("south", home);
        
        ladderDoor.setExit("north", mountain);
        currentRoom = garden;
    }
    
    private void createItems() {
        
        // Found items, worth 15 points.
        axe = new Item(ItemEnum.axe, shed, 15);
        shovel = new Item(ItemEnum.shovel, mountain, 25);
        nails = new Item(ItemEnum.nails, bridge, 15);
        wood = new Item(ItemEnum.wood, waterfall, 15);
        
        // Made items and quest rewards, worth 40 points.
        key = new Item(ItemEnum.key, 40);
        hammer = new Item(ItemEnum.hammer, 40);
        lumber = new Item(ItemEnum.lumber, 40);
        ladder = new Item(ItemEnum.ladder, 40);
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
        evilNPC = new EvilNPC(waterfall);
        
    }

    public void play() 
    {            
        printWelcome();
        time.start();
        boolean finished = false;
    }
    
    
    
    private void printWelcome()
    {
        c.toStoryField(WordList.WELCOME);
        c.toStoryField(WordList.DESCRIPTION);
        c.toStoryField(WordList.GET_HELP);
        c.toStoryField("");
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            c.toStoryField(WordList.DONT_KNOW_WHAT_YOU_MEAN);
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
        else if (commandWord == CommandWord.INTERACT) {
            interact(command);
        }
        else if (commandWord == CommandWord.SAVE){
            saveAndLoad.save();
        }
        else if (commandWord == CommandWord.LOAD){
            saveAndLoad.load();
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        c.toStoryField(WordList.PRINT_HELP);
        parser.showCommands();
    }
    
    public void goRoom(String direction){
        CommandWord commandWord = CommandWord.GO;
        Command command = new Command(commandWord,direction);
        goRoom(command);
    }
    
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            c.toStoryField(WordList.GO_WHERE);
            return;
        }
        
        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);
        Door nextRoom1 = currentRoom.getExitDoor(direction);
        
        if(steps < 50) {
            steps++;
        }
        
        evilNPC.move();
        
        if (nextRoom == null && nextRoom1 == null) {
            c.toStoryField("There is no door!\n");
        }
        else if (nextRoom == null && nextRoom1.getLock() == true){
            if(nextRoom1.getName().equals("mountain")) {
                if (inventory.containsKey(ItemEnum.ladder)) {
                    nextRoom1.setLock(false);
                    currentRoom = nextRoom1.getExit(direction);
                    pet.goPet(nextRoom1.getExit(direction));
                    inventory.remove(ItemEnum.ladder);
                } else {
                    c.toStoryField(WordList.MOUNTAIN_DOOR + "\n");
                }
            } else if (nextRoom1.getName().equals("home")) {
                if (inventory.containsKey(ItemEnum.key) && pet.isFollow()) {
                    nextRoom1.setLock(false);
                    currentRoom = nextRoom1.getExit(direction);
                    pet.goPet(nextRoom1.getExit(direction));
                    inventory.remove(ItemEnum.key);
                } else if (inventory.containsKey(ItemEnum.key)) {
                    c.toStoryField(WordList.STILL_NEED_PET);
                } else {
                    c.toStoryField(WordList.HOME_DOOR + "\n");
                }
            }
            if (nextRoom1.getExit(direction).getRoomName() == "home"){
                if(progress == 0){
                    c.toStoryField("I should get my pet before going home");
                } else{
                    setProgress(2);
                }
            }
        }
        else if (nextRoom == null && nextRoom1.getDoor() == true){
            currentRoom = nextRoom1.getExit(direction);
            pet.goPet(nextRoom1.getExit(direction));
            if (currentRoom == home) {
                c.toStoryField(WordList.END_DESCRIPTION);
            }
            c.toStoryField("going through door");
        } else {
            currentRoom = nextRoom;
            pet.goPet(nextRoom);

            if (currentRoom == neighbour.getCurrentRoom()) {
                c.toStoryField("You see your neightbour in the room.");
                command.setSecondWord("neighbour");
                interact(command);
            } else if(currentRoom == treeStump.getCurrentRoom()) {
                c.toStoryField("you see a treestump in the room.");
            } else if (currentRoom == pet.getCurrentRoom() && !pet.isFollow()) {
                c.toStoryField(WordList.FOUND_PET);
                pet.startFollow();
                setProgress(1);
            }
            if (currentRoom == evilNPC.getCurrentRoom()) {
                if (getPlayerInventory().isEmpty()){
                }
                else{
                String stringInventory = getPlayerInventory().replace("[", "").replace("]", "").replace(";", "").replace(" ", "");
                String[] keys = stringInventory.split(",");
                

                if (!inventory.isEmpty()  && evilNPC.getStolenItem()== null) {

                    switch (inventory.size()) {
                        case 1: 
                            evilNPCEncounter(keys[0]);
                            break;
                        case 2: {
                            double j = Math.random() * 10;
                            if (j <= 5) {
                                evilNPCEncounter(keys[0]);
                            } else {
                                evilNPCEncounter(keys[1]);
                            }
                            break;
                        }
                        case 3: {
                            c.toStoryField(stringInventory);
                            double j = Math.random() * 10;
                            if (j < 3.33) {
                                evilNPCEncounter(keys[0]);
                            } else if (j >= 3.33 && j < 6.66) {
                                evilNPCEncounter(keys[1]);
                            } else if (j >= 6.66 && j <= 10) {
                                evilNPCEncounter(keys[2]);
                            }
                            break;
                        }
                        default:
                            break;
                    }

                }
                else if (inventory.isEmpty() && evilNPC.getStolenItem()== null){
                    
                }
             else if (evilNPC.getStolenItem()!= null) {                   
                    if (inventory.size() < 3) {
                        c.toStoryField("EvilNPC returns your " + evilNPC.getStolenItem());
                        inventory.put(ItemEnum.valueOf(evilNPC.getStolenItem()), Item.getItem(ItemEnum.valueOf(evilNPC.getStolenItem())));
                        evilNPC.setStolenItem(null);
                    } else {
                        c.toStoryField("EvilNPc wants to return your " + evilNPC.getStolenItem() + ", but your inventory is full.");
                    }
            } else  {
            }
                
                }
            }            
        }
        

        if (currentRoom == home) {
            c.toStoryField("You win!");
        }
    }

    public void quit() {
        CommandWord commandWord = CommandWord.QUIT;
        Command command = new Command(commandWord, null);
        quit(command);
    }
    
    private boolean quit(Command command) {
        if(command.hasSecondWord()) {
            c.toStoryField("Quit what?");
            return false;
        }
        else {
        c.toStoryField("Thank you for playing.  Good bye.");
        System.exit(0);
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
            
            if (!pickedItems.contains(inputItem.toString())) {
                pickedItems.add(inputItem.toString());
                time.addTime();
            }
            
        if(currentRoom.getRoomItems().containsKey(inputItem) && inventory.size() < 3) {
            inventory.put(inputItem, currentRoom.getRoomItems().get(inputItem));
            currentRoom.getRoomItems().remove(inputItem);
            Item.getAllItems().get(inputItem).setRoom(null);
            if(inventory.get(ItemEnum.valueOf(command.getSecondWord())).getItemName() == "shovel"){
                setProgress(5);
            }
            c.toStoryField(inventory.get(ItemEnum.valueOf(command.getSecondWord())).getItemName() + " is added to the inventory");

        } else if(!currentRoom.getRoomItems().containsKey(inputItem)) {
            c.toStoryField("That item is not in the room!");
        } else {
            c.toStoryField("Inventory is full");
        }
        } catch (NullPointerException e) {
            c.toStoryField("Pick what item?");
        } catch (IllegalArgumentException e) {
            c.toStoryField("That is not an item!");
        }

    }
    
    public void dropItem(String item) {
        CommandWord commandWord = CommandWord.DROP;
        Command command = new Command(commandWord, item);
        dropItem(command);
    }
    
    private void dropItem(Command command) {
        try {
            ItemEnum inputItem = ItemEnum.valueOf(command.getSecondWord().toLowerCase());
        
        if(inventory.containsKey(inputItem)) {
            currentRoom.getRoomItems().put(inputItem, inventory.get(inputItem));
            inventory.get(inputItem).setRoom(currentRoom);
            inventory.remove(inputItem);
            
            c.toStoryField(currentRoom.getRoomItems().get(inputItem).getItemName() + " is removed from the inventory");
        }
        } catch (NullPointerException e) {
            c.toStoryField("Drop what item?");
        } catch (IllegalArgumentException e) {
            c.toStoryField("That is not an item");
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

    public void interact(String npc) {
        CommandWord commandWord = CommandWord.INTERACT;
        Command command = new Command(commandWord, npc);
        interact(command);
    }
    
    private void interact(Command command) {
        try {
            String inputCommand = command.getSecondWord().toLowerCase();
            
            if (command.getSecondWord().isEmpty()) {
                System.out.println("SecondWord!");
            }
            
            if (inputCommand.equals("pet")){
                if (pet.getCurrentRoom() == currentRoom){
                    c.toStoryField(pet.interact(progress));
                    setProgress(1);
                } else {
                System.out.println(WordList.NO_PET);
                c.toStoryField(WordList.NO_PET);
                }
            } else if (inputCommand.equals("neighbour")){
                if (currentRoom == neighbour.getCurrentRoom()) {
                
                    c.toStoryField(neighbour.interactExtended(command, key, hammer, inventory));
                
                setProgress(3);
                if(inventory.containsKey(ItemEnum.hammer)){
                  } else if(inventory.containsKey(ItemEnum.key)){
                  setProgress(6);
                  }
                }
            } else if (inputCommand.equals("stump")){
                if (currentRoom == treeStump.getCurrentRoom()) {
                c.toStoryField(treeStump.interactExtendedStump(command, nails, hammer, wood, lumber, ladder, inventory));
                    if(inventory.containsKey(ItemEnum.ladder)){
                    setProgress(4);
                    }
                }
            } else {
                System.out.println(WordList.WRONG_INTERACT);
            }
                    
        } catch (NullPointerException e) {
            System.out.println("What interaction?");
        }
    }
    
    


    private void getRoomItemList (Room room) {
         for (ItemEnum item : room.getRoomItems().keySet()) {
            c.toStoryFieldnln(room.getRoomItems().get(item).getItemName() + " ");
        }
         c.toStoryField(" ");
    }
    
    public String calculateScore() {
        
        effScore = (1000 - (steps * 20));
        timeScore = (int) (time.getTime() * 2);
        int score = effScore + timeScore;
        scoreString = Integer.toString(score);
        
        return scoreString;
    }
    
    public int getSteps() {
        return steps;
    }
    
    public void setSteps(int loadSteps) {
        steps = loadSteps;
    }
    
    public boolean highscore() {
        int scoreInt = Integer.parseInt(calculateScore());
        int highscoreInt = Integer.parseInt(highScoreLoad());
        if(scoreInt > highscoreInt) {
            highScoreSave();
            return true;
        } else {
            return false;
        }
    }
    
    public void setItemLocation(Item item, String room) {
        switch(room) {
            case "garden":
                item.setRoom(garden);
                break;
            case "shed":
                item.setRoom(shed);
                break;
            case "mountainside":
                item.setRoom(mountainside);
                break;
            case "forest":
                item.setRoom(forest);
                break;
            case "mountain":
                item.setRoom(mountain);
                break;
            case "bridge":
                item.setRoom(bridge);
                break;
            case "river":
                item.setRoom(river);
                break;
            case "waterfall":
                item.setRoom(waterfall);
                break;
            case "neighbourHouse":
                item.setRoom(neighbourHouse);
                break;
        }
    }
    
    public String[] getRoomItems(){
        String items = currentRoom.getRoomItems().keySet().toString();
        items = items.replace("[", "");
        items = items.replace("]", "");
        return items.split(", ");
    }
    
    public String[] getInventoryItems() {
        String items = inventory.keySet().toString();
        items = items.replace("[", "");
        items = items.replace("]", "");
        return items.split(", ");
    }

    public int getGameTime() {
       return time.getTime();
    }
    
    public void setGameTime(int time) {
        this.time.setTime(time);
    }
    
    public String getPlayerRoom() {
        return currentRoom.getRoomName();
    }
    
    public void setPlayerRoom(String room) {
        this.currentRoom = Room.getRoom(room);
    }
    
    public String getPetRoom() {
        return pet.getCurrentRoom().getRoomName();
    }
    
    public void setPetRoom(String room) {
        this.pet.setCurrentRoom(Room.getRoom(room));
    }
    
    public String getEvilNPCRoom() {
        return evilNPC.getCurrentRoom().getRoomName();
    }
    
    public void setEvilNPCRoom(String room) {
        this.evilNPC.setCurrentRoom(Room.getRoom(room));
    }
    
    public int getNeighbourInteractCount() {
        return neighbour.getInteractCount();
    }
    
    public void setNeighbourInteractCount(int interactCount) {
        neighbour.setInteractCount(interactCount);
    }
    
    public int getProgress() {
        return progress;
    }
    
    public void setGameProgress(int progress) {
        Game.progress = progress;
    }
    
    public String getPlayerInventory() {
        return inventory.keySet().toString();
    }
    
    public void setInventory (String item) {
        Game.inventory.put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
    }
    
    public void save(){
        saveAndLoad.save();
    }
    
    public void load(){
        saveAndLoad.load();
    }
    
    public String getScore() {
        return scoreString;
    }
    
    public void highScoreSave() {
        score.Save();
    }
    
    public String highScoreLoad() {
        if(new File("highscore.txt").exists()){
            return score.Load();
        } else {
            return "0";
        }
    }
    
    public void setPickedItems(ArrayList<String> pickedItems) {
        this.pickedItems = pickedItems;
    }
    
    public ArrayList<String> getPickedItems() {
        return pickedItems;
    }
    
    public boolean getDoorLock() {
        return door.getLock();
    }
    
    public void setDoorLock(Boolean lock) {
        door.setLock(lock);
    }
    
    public boolean getLadderDoorLock() {
        return ladderDoor.getLock();
    }
    
    public void setLadderDoorLock(Boolean lock) {
        ladderDoor.setLock(lock);
    }
    
    public void itemClear() {
        garden.itemClear();
        shed.itemClear();
        mountainside.itemClear();
        forest.itemClear();
        mountain.itemClear();
        bridge.itemClear();
        river.itemClear();        
        waterfall.itemClear();
        neighbourHouse.itemClear();
    }
    
    public void loadItem(String item, String room) {
        switch(room) {
            case "garden":
                garden.getRoomItems().put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
                break;
            case "shed":
                shed.getRoomItems().put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
                break;
            case "mountainside":
                mountainside.getRoomItems().put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
                break;
            case "mountain":
                mountain.getRoomItems().put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
                break;
            case "forest":
                forest.getRoomItems().put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
                break;
            case "bridge":
                bridge.getRoomItems().put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
                break;
            case "river":
                river.getRoomItems().put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
                break;
            case "waterfall":
                waterfall.getRoomItems().put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
                break;
            case "neighbourHouse":
                neighbourHouse.getRoomItems().put(ItemEnum.valueOf(item), Item.getItem(ItemEnum.valueOf(item)));
                break;
        }
    }
    
    public String getEvilNPCItem() {
        return evilNPC.getStolenItem();
    }
    
    public void setEvilNPCItem(String item) {
        evilNPC.setStolenItem(item);
    }
    
    public String characters() {
        if (currentRoom == pet.getCurrentRoom()) {
            if (currentRoom == evilNPC.getCurrentRoom()) {
                if (currentRoom ==  neighbour.getCurrentRoom()) {
                    return "playerNPCPetNeighbour";
                }
                return "playerNPCPet";
            } else if (currentRoom ==  neighbour.getCurrentRoom()) {
                return "playerPetNeighbour";
            } else {
                return "playerPet";
            }
        } else if (currentRoom == evilNPC.getCurrentRoom()) {
            if (currentRoom ==  neighbour.getCurrentRoom()) {
                return "playerNPCNeighbour";
            } else {
                return "playerNPC";
            }
        } else if (currentRoom ==  neighbour.getCurrentRoom()) {
            return "playerNeighbour";
        } else {
            return "player";
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    
    
}