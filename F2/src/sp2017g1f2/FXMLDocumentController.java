/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp2017g1f2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import sp2017g1.WriteToStory;
//import originalFiles.Game;
import originalFiles.*;


/**
 *
 * @author Student
 */
public class FXMLDocumentController implements Initializable, WriteToStory {
    
    private static Game game;
    
    @FXML
    private TextArea storyField;
    @FXML
    private Button gameStart, pickButton, dropButton, unlockButton, northButton, eastButton, southButton, westButton;
    @FXML
    private TextField itemField;
    @FXML
    private Button playButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new Game(this);
        storyField.appendText("Nu kører vi!\n");
        game.play();
        
    }    

    @FXML
    private void northButtonAction(ActionEvent event) {
        storyField.appendText("North\n");
        game.goRoom("north");
    }

    @FXML
    private void eastButtonAction(ActionEvent event) {
        storyField.appendText("East\n");
        game.goRoom("east");
    }

    @FXML
    private void southButtonAction(ActionEvent event) {
        storyField.appendText("South\n");
        game.goRoom("south");
    }

    @FXML
    private void westButtonAction(ActionEvent event) {
        storyField.appendText("West\n");
        game.goRoom("west");
    }
    
    public void toStoryField(String string) {
        storyField.appendText(string + "\n");
    }
    
    public void toStoryFieldnln(String string) {
        storyField.appendText(string);
    }

    @FXML
    private void gameButtonAction(ActionEvent event) {
        gameStart.setDisable(true);
        game.play();
    }

    @FXML
    private void pickButtonAction(ActionEvent event) {
        game.pickItem(itemField.getText());
        itemField.clear();
    }


    @FXML
    private void quitButtonAction(ActionEvent event) {
        game.quit();
    }

    @FXML
    private void dropButtonAction(ActionEvent event) {
    }

    @FXML
    private void unlockButtonAction(ActionEvent event) {
    }

    @FXML
    private void newScreenButtonAction(ActionEvent event) {
        
    }

    @FXML
    private void northKeyAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.UP)) {
            game.goRoom("north");
        }
    }

    @FXML
    private void eastKeyAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.RIGHT)) {
            game.goRoom("east");
        }
    }

    @FXML
    private void southKeyAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.DOWN)) {
            game.goRoom("south");
        }
    }

    @FXML
    private void westKeyAction(KeyEvent event) {
        if(event.getCode().equals(KeyCode.LEFT)) {
            game.goRoom("west");
        }
    }
    
    @FXML
    private void playButtonAction(ActionEvent event) {
//        playButton.setVisible(false);
//        storyField.setVisible(true);
//        gameStart.setVisible(true);
//        pickButton.setVisible(true);
//        dropButton.setVisible(true);
//        unlockButton.setVisible(true);
//        itemField.setVisible(true);
//        northButton.setVisible(true);
//        eastButton.setVisible(true);
//        southButton.setVisible(true);
//        westButton.setVisible(true);
//        game.play();
    }
    
}
