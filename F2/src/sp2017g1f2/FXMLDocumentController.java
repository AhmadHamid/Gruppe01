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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author Student
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextArea storyField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        storyField.appendText("South");
    }

    @FXML
    private void westButtonAction(ActionEvent event) {
        storyField.appendText("West");
    }
    
}
