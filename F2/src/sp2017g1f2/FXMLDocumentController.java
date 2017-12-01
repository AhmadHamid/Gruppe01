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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new Game(this);
        storyField.appendText("Nu kører vi!\n");
        scene1();
        this.inventoryItems = FXCollections.observableArrayList();
        inventoryList.setItems(inventoryItems);
        this.roomItems = FXCollections.observableArrayList();
        itemList.setItems(roomItems);
        itemLoad();

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

    @FXML
    private void northButtonAction(ActionEvent event) {
        storyField.appendText("North\n");
        game.goRoom("north");
        itemLoad();
    }

    @FXML
    private void eastButtonAction(ActionEvent event) {
        storyField.appendText("East\n");
        game.goRoom("east");
        itemLoad();
    }

    @FXML
    private void southButtonAction(ActionEvent event) {
        storyField.appendText("South\n");
        game.goRoom("south");
        itemLoad();
    }

    @FXML
    private void westButtonAction(ActionEvent event) {
        storyField.appendText("West\n");
        game.goRoom("west");
        itemLoad();
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
    }
    
    private void scene2(){
        playButton.setVisible(false);
        loadButton.setVisible(false);
        quitButton.setVisible(false);
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
