/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1f2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
import sp2017g1.WriteToStory;
import originalFiles.Game;
import sp2017g1.ItemEnum;



/**
 *
 * @author Student
 */
public class FXMLDocumentController implements Initializable, WriteToStory {
    
    private static Game game;
    
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
    
    Image windowImage = new Image("file:forest.jpg", true);
    BackgroundImage windowBackgroundImage = new BackgroundImage(windowImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background windowBackground = new Background(windowBackgroundImage);
    
    Image roomGarden = new Image("file:roomGarden.png", 250, 250, true, true);
    BackgroundImage imageGarden = new BackgroundImage(roomGarden, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
        BackgroundSize.DEFAULT);
    Background mapGarden = new Background(imageGarden);
    
    Image roomShed = new Image("file:roomShed.png", 250, 250, true, true);
    BackgroundImage imageShed = new BackgroundImage(roomShed, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background mapShed = new Background(imageShed);
    
    Image roomMountainside = new Image("file:roomMountainside.png", 250, 250, true, true);
    BackgroundImage imageMountainside = new BackgroundImage(roomMountainside, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background mapMountainside = new Background(imageMountainside);
    
    Image roomMountain = new Image("file:roomMountain.png", 250, 250, true, true);
    BackgroundImage imageMountain = new BackgroundImage(roomMountain, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background mapMountain = new Background(imageMountain);
    
    Image roomForest = new Image("file:roomForest.png", 250, 250, true, true);
    BackgroundImage imageForest = new BackgroundImage(roomForest, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background mapForest = new Background(imageForest);
    
    Image roomBridge = new Image("file:roomBridge.png", 250, 250, true, true);
    BackgroundImage imageBridge = new BackgroundImage(roomBridge, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background mapBridge = new Background(imageBridge);
    
    Image roomNeighbourhouse = new Image("file:roomNeighbourhouse.png", 250, 250, true, true);
    BackgroundImage imageNeighbourhouse = new BackgroundImage(roomNeighbourhouse, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background mapNeighbourhouse = new Background(imageNeighbourhouse);
    
    Image roomRiver = new Image("file:roomRiver.png", 250, 250, true, true);
    BackgroundImage imageRiver = new BackgroundImage(roomRiver, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background mapRiver = new Background(imageRiver);
    
    Image roomWaterfall = new Image("file:roomWaterfall.png", 250, 250, true, true);
    BackgroundImage imageWaterfall = new BackgroundImage(roomWaterfall, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    Background mapWaterfall = new Background(imageWaterfall);
    @FXML
    private AnchorPane keys;
    
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
        //game.play();
    }

    private void roomItemLoad(){
        roomItems.clear();
        for (String item : game.getRoomItems()) {
            roomItems.add(item);
        }
    }
    
    private void inventoryLoad(){
        inventoryItems.clear();
        for (String item : game.getInventoryItems()) {
            inventoryItems.add(item);
        }
    }
    
    private void itemLoad(){
        roomItemLoad();
        inventoryLoad();
    }

    private void loadMap(String currentRoom) {
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
    }

    @FXML
    private void eastButtonAction(ActionEvent event) {
        game.goRoom("east");
        itemLoad();
        loadMap(game.getPlayerRoom());
    }

    @FXML
    private void southButtonAction(ActionEvent event) {
        game.goRoom("south");
        itemLoad();
        loadMap(game.getPlayerRoom());
    }

    @FXML
    private void westButtonAction(ActionEvent event) {
        game.goRoom("west");
        itemLoad();
        loadMap(game.getPlayerRoom());
    }
    
    public void toStoryField(String string) {
        storyField.appendText(string + "\n");
    }
    
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

    }

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
        keys.setVisible(false);
    }
    
    private void scene2(){
        playButton.setVisible(false);
        loadButton.setVisible(false);
        quitButton.setVisible(false); // Needs to stay visible at all times, due to the game not closing properly if pressing the red X button in the top right corner (Windows), unless alternative is available.
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
        helpButton.setVisible(true);
        saveButton.setVisible(true);
        map.setVisible(true);
        keys.setVisible(true);
    }

    @FXML
    private void combineButtonAction(ActionEvent event) {
        game.interact("stump");
    }

    @FXML
    private void helpButtonAction(ActionEvent event) {
        game.interact("pet");
    }

    @FXML
    private void saveButtonAction(ActionEvent event) {
        game.save();
    }
}
