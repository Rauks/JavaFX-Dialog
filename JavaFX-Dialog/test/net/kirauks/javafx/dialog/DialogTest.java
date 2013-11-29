/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.kirauks.javafx.dialog;

import javafx.application.Application;
import javafx.stage.Stage;
import net.kirauks.javafx.dialog.Dialog.DialogListener;
import net.kirauks.javafx.dialog.Dialog.DialogOptions;
import net.kirauks.javafx.dialog.Dialog.DialogType;

/**
 *
 * @author Karl
 */
public class DialogTest extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        DialogListener listener = new DialogListener() {
            @Override
            public void onResponse(Dialog.DialogResponse response) {
                switch(response){
                    case CANCEL: System.out.println("CANCEL"); break;
                    case NO: System.out.println("NO"); break;
                    case YES: System.out.println("YES"); break;
                    case OK: System.out.println("OK"); break;
                }
            }
        };
        
        Dialog dialog1 = new Dialog("Erreur !", listener, DialogType.ERROR, DialogOptions.OK);
        dialog1.showAndWait();
        
        Dialog dialog2 = new Dialog("Info !", listener, DialogType.INFORMATION, DialogOptions.OK);
        dialog2.showAndWait();
        
        Dialog dialog3 = new Dialog("Warning !", listener, DialogType.WARNING, DialogOptions.OK_CANCEL);
        dialog3.showAndWait();
        
        Dialog dialog4 = new Dialog("Question ?", listener, DialogType.QUESTION, DialogOptions.YES_NO);
        dialog4.showAndWait();
        
        Dialog dialog5 = new Dialog("Question ?", listener, DialogType.QUESTION, DialogOptions.YES_NO_CANCEL);
        dialog5.showAndWait();
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
