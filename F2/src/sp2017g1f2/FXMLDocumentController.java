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
import originalFiles.*;
import sp2017g1.WriteToStory;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        game = new Game(this);
        storyField.appendText("Nu kører vi!\n");
        game.play();
    }    

    @FXML
    private void northButtonAction(ActionEvent event) {
        storyField.appendText("North\n");
    }

    @FXML
    private void eastButtonAction(ActionEvent event) {
        storyField.appendText("East\n");
    }

    @FXML
    private void southButtonAction(ActionEvent event) {
        storyField.appendText("South\n");
    }

    @FXML
    private void westButtonAction(ActionEvent event) {
        storyField.appendText("West\n");
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
    
}
