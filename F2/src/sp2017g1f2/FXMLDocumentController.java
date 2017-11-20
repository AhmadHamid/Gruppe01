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
import sp2017g1.WriteToStory;
//import originalFiles.Game;
import originalFiles.*;


/**
 *
 * @author Student
 */
public class FXMLDocumentController implements Initializable, WriteToStory {
    
    private Game game;
    
    @FXML
    private TextArea storyField;
    @FXML
    private Button gameStart;
    @FXML
    private Button pickButton;
    @FXML
    private Button dropButton;
    @FXML
    private Button unlockButton;
    @FXML
    private TextField itemField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new Game(this);
        storyField.appendText("Nu kører vi!\n");
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
    }

    @FXML
    private void dropButtonAction(ActionEvent event) {
        
    }

    @FXML
    private void unlockButtonAction(ActionEvent event) {
        
    }
    
}
