/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.kirauks.javafx.dialog;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Karl
 */
public class Dialog extends Stage{
    public enum DialogType{
        ERROR("dialog_error.png"),
        WARNING("dialog_warning.png"),
        QUESTION("dialog_question.png"),
        INFORMATION("dialog_information.png");
        
        private final String imageURL;
        
        private DialogType(String imageURL){
            this.imageURL = imageURL;
        }
        
        private String getImageURL(){
            return this.imageURL;
        }
    }
    public enum DialogOptions{
        OK,
        OK_CANCEL,
        YES_NO,
        YES_NO_CANCEL;
    }
    public enum DialogResponse{
        OK, CANCEL, YES, NO;
    }
    public interface DialogListener{
        public void onResponse(DialogResponse response);
    }
    
    public Dialog(String message, final DialogListener responseListener, final DialogType type, final DialogOptions options){
        super();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dialog.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            DialogController controller = fxmlLoader.<DialogController>getController();
            
            controller.setDialog(this);
            controller.setMessage(message);
            controller.setImage(new Image(Dialog.class.getResource(type.imageURL).toString()));
            
            if(options == DialogOptions.OK || options == DialogOptions.OK_CANCEL){
                controller.addButton("Ok", new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        if(responseListener != null){
                            responseListener.onResponse(DialogResponse.OK);
                        }
                    }
                }, false, options == DialogOptions.OK);
            }
            if(options == DialogOptions.YES_NO || options == DialogOptions.YES_NO_CANCEL){
                controller.addButton("Oui", new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        if(responseListener != null){
                            responseListener.onResponse(DialogResponse.YES);
                        }
                    }
                });
                controller.addButton("Non", new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        if(responseListener != null){
                            responseListener.onResponse(DialogResponse.NO);
                        }
                    }
                }, false, options == DialogOptions.YES_NO);
            }
            if(options == DialogOptions.OK_CANCEL || options == DialogOptions.YES_NO_CANCEL){
                controller.addButton("Annuler", new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        if(responseListener != null){
                            responseListener.onResponse(DialogResponse.CANCEL);
                        }
                    }
                }, true, true);
            }
            
            this.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    if(responseListener != null){
                        switch(options){
                            case OK:
                                responseListener.onResponse(DialogResponse.CANCEL);
                                break;
                            case YES_NO:
                                responseListener.onResponse(DialogResponse.NO);
                                break;
                            case OK_CANCEL:
                            case YES_NO_CANCEL:
                                responseListener.onResponse(DialogResponse.CANCEL);
                                break;
                        }
                    }
                }
            });
            
            this.setScene(new Scene(root));
        } catch (IOException ex) {
            Logger.getLogger(Dialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
