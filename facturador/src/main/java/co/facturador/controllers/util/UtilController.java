/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.controllers.util;

import co.facturador.controllers.MainApp;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author EderArmando
 */
public class UtilController implements Initializable{
    
    private String sceneText;
    
    public void goToScene(Stage stage,String ruta, String styles, String title) { 
        try {
            this.sceneText = title;
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(styles);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Logger.getLogger(sceneText);
        System.out.println(sceneText);
    }  
}
