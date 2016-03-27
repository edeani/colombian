/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.facturador.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author EderArmando
 */
public class DashboardController implements Initializable {

    @FXML
    private Pane contentDashBoard;
    @FXML
    private Label lSeccion;
    
    @FXML
    public void cargarFacturar(ActionEvent event){
        FacturarController fc = new FacturarController();
        lSeccion.setText("Facturador");
        fc.crearFactura(contentDashBoard);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
