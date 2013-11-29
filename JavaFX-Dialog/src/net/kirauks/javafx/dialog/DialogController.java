/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.kirauks.javafx.dialog;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Karl
 */
public class DialogController implements Initializable {
    private static final double DEFAULT_BUTTON_WIDTH = 70;
    
    @FXML
    private Label message;
    @FXML
    private HBox buttonsBox;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setImage(Image image){
        this.image.setImage(image);
    }
    
    public void addButton(String name, final EventHandler<ActionEvent> onAction, boolean cancelButton, boolean defaultButton){
        Button b = new Button(name);
        b.setCancelButton(cancelButton);
        b.setDefaultButton(defaultButton);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                DialogController.this.dialog.close();
                onAction.handle(t);
            }
        });
        b.setMinWidth(DEFAULT_BUTTON_WIDTH);
        b.setPrefWidth(DEFAULT_BUTTON_WIDTH);
        b.setMaxWidth(DEFAULT_BUTTON_WIDTH);
        this.buttonsBox.getChildren().add(b);
    }
    public void addButton(String name, EventHandler<ActionEvent> onAction){
        this.addButton(name, onAction, false, false);
    }
    
    public void setMessage(String message){
        this.message.setText(message);
    }
    
    private Dialog dialog;
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }
}
