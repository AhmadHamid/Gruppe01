/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import business.Game;
import business.ItemEnum;



/**
 *
 * @author Student
 */
public class FXMLDocumentController implements Initializable, WriteToStory {
    
    private static Game game;
    private business.Timer timer;
    
    @FXML
    private TextArea storyField;
    @FXML
    private Button pickButton;
    @FXML
    private Button dropButton, northButton, eastButton, southButton, westButton;
    @FXML
    private Button playButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button loadButton;
    
    @FXML
    private ListView<String> inventoryList;
    private ObservableList<String> inventoryItems;
    @FXML
    private ListView<String> itemList;
    private ObservableList<String> roomItems;
    
    @FXML
    private Label inventoryLabel;
    @FXML
    private Button combineButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button saveButton;
    @FXML
    private Label roomItemLabel;
    @FXML
    private AnchorPane window;
    @FXML
    private AnchorPane map;
    
    Image titleImage = new Image("presentation/resources/runawayTitle.png", 575, 95, true, true);
    BackgroundImage titleTextImage = new BackgroundImage(titleImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background titleText = new Background(titleTextImage);
    
    Image windowImage = new Image("presentation/resources/forest.jpg", 1920, 1080, true, true);
    BackgroundImage windowBackgroundImage = new BackgroundImage(windowImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background windowBackground = new Background(windowBackgroundImage);
    
    Image roomGarden = new Image("presentation/resources/roomGarden.png", 250, 250, true, true);
    BackgroundImage imageGarden = new BackgroundImage(roomGarden, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapGarden = new Background(imageGarden);
    
    Image roomShed = new Image("presentation/resources/roomShed.png", 250, 250, true, true);
    BackgroundImage imageShed = new BackgroundImage(roomShed, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapShed = new Background(imageShed);
    
    Image roomMountainside = new Image("presentation/resources/roomMountainside.png", 250, 250, true, true);
    BackgroundImage imageMountainside = new BackgroundImage(roomMountainside, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapMountainside = new Background(imageMountainside);
    
    Image roomMountain = new Image("presentation/resources/roomMountain.png", 250, 250, true, true);
    BackgroundImage imageMountain = new BackgroundImage(roomMountain, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapMountain = new Background(imageMountain);
    
    Image roomForest = new Image("presentation/resources/roomForest.png", 250, 250, true, true);
    BackgroundImage imageForest = new BackgroundImage(roomForest, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapForest = new Background(imageForest);
    
    Image roomBridge = new Image("presentation/resources/roomBridge.png", 250, 250, true, true);
    BackgroundImage imageBridge = new BackgroundImage(roomBridge, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapBridge = new Background(imageBridge);
    
    Image roomNeighbourhouse = new Image("presentation/resources/roomNeighbourhouse.png", 250, 250, true, true);
    BackgroundImage imageNeighbourhouse = new BackgroundImage(roomNeighbourhouse, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapNeighbourhouse = new Background(imageNeighbourhouse);
    
    Image roomRiver = new Image("presentation/resources/roomRiver.png", 250, 250, true, true);
    BackgroundImage imageRiver = new BackgroundImage(roomRiver, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapRiver = new Background(imageRiver);
    
    Image roomWaterfall = new Image("presentation/resources/roomWaterfall.png", 250, 250, true, true);
    BackgroundImage imageWaterfall = new BackgroundImage(roomWaterfall, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapWaterfall = new Background(imageWaterfall);
    
    Image imageCharacter = new Image("presentation/resources/player.png", 100, 100, true, true);
    BackgroundImage backgroundImageCharacter = new BackgroundImage(imageCharacter, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background backgroundCharacter = new Background(backgroundImageCharacter);
    
    @FXML
    private AnchorPane scene1;
    @FXML
    private AnchorPane scoreScene;
    @FXML
    private Button PlayAgain;
    @FXML
    private Label gameScore;
    @FXML
    private Label newHighscore;
    @FXML
    private Label highscore;
    @FXML
    private AnchorPane title;
    //@FXML
    //private ImageView mapImage;
    @FXML
    private AnchorPane characters;
    
    Alert saveAlert = new Alert(AlertType.INFORMATION);
    @FXML
    private Label startHighscore;
    @FXML
    private Label startHighscoreLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new Game(this);
        scene1();
        this.inventoryItems = FXCollections.observableArrayList();
        inventoryList.setItems(inventoryItems);
        this.roomItems = FXCollections.observableArrayList();
        itemList.setItems(roomItems);
        itemLoad();
        window.setBackground(windowBackground);
        map.setBackground(mapGarden);
        characters.setBackground(backgroundCharacter);
        //mapImage.setImage(roomGarden);
        title.setBackground(titleText);
        startHighscore.setText(game.highScoreLoad());
        //game.play();
    }

    /**
     * Method for updating the list of items in a room
     */
    private void roomItemLoad(){
        roomItems.clear();
        for (String item : game.getRoomItems()) {
            roomItems.add(item);
        }
    }
    
    /**
     * Method for updating the list of items in the players inventory
     */
    private void inventoryLoad(){
        inventoryItems.clear();
        for (String item : game.getInventoryItems()) {
            inventoryItems.add(item);
        }
    }
    
    /**
     * Method that calls both inventoryLoad and roomItemLoad
     */
    private void itemLoad(){
        roomItemLoad();
        inventoryLoad();
    }

    /**
     * method used to control visibility of the combine button
     */
    private void visableCombine() {
        if (game.getCurrentRoom() == game.shed) {
            combineButton.setDisable(false);
        } else {
            combineButton.setDisable(true);
        }
    }

    /**
     * method used to update the map
     * @param currentRoom location of the player
     */
    private void loadMap(String currentRoom) {
        
        imageCharacter = new Image("presentation/resources/" + game.characters() + ".png", 100, 100, true, true);
        backgroundImageCharacter = new BackgroundImage(imageCharacter, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);
        backgroundCharacter = new Background(backgroundImageCharacter);
        characters.setBackground(backgroundCharacter);
        
        switch(currentRoom){
            case "garden":
                map.setBackground(mapGarden);
                break;
            case "shed":
                map.setBackground(mapShed);
                break;
            case "mountainside":
                map.setBackground(mapMountainside);
                break;
            case "mountain":
                map.setBackground(mapMountain);
                break;
            case "forest":
                map.setBackground(mapForest);
                break;
            case "bridge":
                map.setBackground(mapBridge);
                break;
            case "neighbourHouse":
                map.setBackground(mapNeighbourhouse);
                break;
            case "river":
                map.setBackground(mapRiver);
                break;
            case "waterfall":
                map.setBackground(mapWaterfall);
                break;
        }       
    }
    
    @FXML
    private void northButtonAction(ActionEvent event) {
        game.goRoom("north");
        itemLoad();
        loadMap(game.getPlayerRoom());
        visableCombine();
        gameOver();
    }

    @FXML
    private void eastButtonAction(ActionEvent event) {
        game.goRoom("east");
        itemLoad();
        loadMap(game.getPlayerRoom());
        visableCombine();
        gameOver();
    }

    @FXML
    private void southButtonAction(ActionEvent event) {
        game.goRoom("south");
        if(game.getPlayerRoom() == "home") {
            scene3();
            scoreLoad();
        } else {
        itemLoad();
        loadMap(game.getPlayerRoom());
        }
        visableCombine();
        gameOver();
    }

    @FXML
    private void westButtonAction(ActionEvent event) {
        game.goRoom("west");
        itemLoad();
        loadMap(game.getPlayerRoom());
        visableCombine();
        gameOver();
    }
    
    /**
     * Writes string to story field, with new line
     * @param string text to be written in the games story field
     */
    public void toStoryField(String string) {
        storyField.appendText(string + "\n");
    }
    
    /**
     * Writes string to story field, with new line
     * @param string text to be written in the games story field
     */
    public void toStoryFieldnln(String string) {
        storyField.appendText(string);
    }

    @FXML
    private void pickButtonAction(ActionEvent event) {
        String item = itemList.getSelectionModel().getSelectedItem();
        game.pickItem(item);
        itemLoad();
    }

    @FXML
    private void quitButtonAction(ActionEvent event) {
        game.quit();
    }

    @FXML
    private void dropButtonAction(ActionEvent event) {
        game.dropItem(inventoryList.getSelectionModel().getSelectedItem());
        inventoryItems.remove(inventoryList.getSelectionModel().getSelectedItem());
        itemLoad();
    }

    @FXML
    private void playButtonAction(ActionEvent event) {
        scene2();
        game.play();
        itemLoad();
    }
    
    private void northKeyAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.UP)) {
            game.goRoom("north");
        }
    }

    private void eastKeyAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.RIGHT)) {
            game.goRoom("east");
        }
    }

    private void southKeyAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.DOWN)) {
            game.goRoom("south");
        }
    }

    private void westKeyAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.LEFT)) {
            game.goRoom("west");
        }
    }

    @FXML
    private void loadButtonAction(ActionEvent event) {
        game.load();
        scene2();
        itemLoad();
        loadMap(game.getPlayerRoom());
    }

    /**
     * makes so only scene 1 buttons are visible
     */
    private void scene1(){
        storyField.setVisible(false);
        pickButton.setVisible(false);
        dropButton.setVisible(false);
        itemList.setVisible(false);
        roomItemLabel.setVisible(false);
        inventoryList.setVisible(false);
        inventoryLabel.setVisible(false);
        northButton.setVisible(false);
        eastButton.setVisible(false);
        southButton.setVisible(false);
        westButton.setVisible(false);
        combineButton.setVisible(false);
        helpButton.setVisible(false);
        saveButton.setVisible(false);
        map.setVisible(false);
        scoreScene.setVisible(false);
    }
    
    /**
     * makes so only scene 2 buttons are visible
     */
    private void scene2(){
        title.setVisible(false);
        playButton.setVisible(false);
        loadButton.setVisible(false);
        quitButton.setVisible(false); // Needs to stay visible at all times, due to the game not closing properly if pressing the red X button in the top right corner (Windows), unless alternative is available.
        startHighscore.setVisible(false);
        startHighscoreLabel.setVisible(false);
        storyField.setVisible(true);
        pickButton.setVisible(true);
        dropButton.setVisible(true);
        itemList.setVisible(true);
        roomItemLabel.setVisible(true);
        inventoryList.setVisible(true);
        inventoryLabel.setVisible(true);
        northButton.setVisible(true);
        eastButton.setVisible(true);
        southButton.setVisible(true);
        westButton.setVisible(true);
        combineButton.setVisible(true);
        combineButton.setDisable(true);
        helpButton.setVisible(true);
        saveButton.setVisible(true);
        map.setVisible(true);
        scene1.setVisible(false);
        scoreScene.setVisible(false);
    }
    
    /**
     * makes so only scene 3 buttons are visible
     */
    private void scene3() {
        storyField.setVisible(false);
        pickButton.setVisible(false);
        dropButton.setVisible(false);
        itemList.setVisible(false);
        roomItemLabel.setVisible(false);
        inventoryList.setVisible(false);
        inventoryLabel.setVisible(false);
        northButton.setVisible(false);
        eastButton.setVisible(false);
        southButton.setVisible(false);
        westButton.setVisible(false);
        combineButton.setVisible(false);
        helpButton.setVisible(false);
        saveButton.setVisible(false);
        map.setVisible(false);
        scene1.setVisible(false);
        scoreScene.setVisible(true);
        newHighscore.setVisible(false);
    }

    /**
     * displays the game score
     */
    private void scoreLoad() {
        String score = game.calculateScore();
        gameScore.setText(score);
        if(game.highscore()) {
            highscore.setText(score);
            newHighscore.setVisible(true);
        } else {
            highscore.setText(game.highScoreLoad());
        }
    }
    
    @FXML
    private void combineButtonAction(ActionEvent event) {
        game.interact("stump");
        itemLoad();
    }

    @FXML
    private void helpButtonAction(ActionEvent event) {
        game.interact("pet");
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {
        game.save();
        saveAlert();
    }
    
    /**
     * 
     */
    private void saveAlert() {
        saveAlert.setTitle("Game saved");
        saveAlert.setHeaderText(null);
        saveAlert.setContentText("The game has been saved.\n" + "Thanks for playing!");
        
        saveAlert.showAndWait();
        
        System.exit(0);
    }

    @FXML
    private void PlayAgainAction(ActionEvent event) {
        game = new Game(this);
        scene2();
        storyField.clear();
        game.play();
        itemLoad();
        
        imageCharacter = new Image("presentation/resources/player.png", 100, 100, true, true);
        backgroundImageCharacter = new BackgroundImage(imageCharacter, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            BackgroundSize.DEFAULT);
        backgroundCharacter = new Background(backgroundImageCharacter);
        characters.setBackground(backgroundCharacter);
    }
    
    private void gameOver() {
        if (game.gameOver()) {
            scene3();
            scoreLoad();
        }
    }
}
