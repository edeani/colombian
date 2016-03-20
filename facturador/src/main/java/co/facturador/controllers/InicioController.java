package co.facturador.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class InicioController implements Initializable {
    
    @FXML
    private TextField fUsuarioLogin;
    @FXML
    private TextField fPasswordLogin;
    @FXML
    private Button bIngresarLogin;
    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("You clicked me!");
        fUsuarioLogin.requestFocus();
    }
    
    @FXML
    private void enterUsuario(KeyEvent event){
        System.out.println("Enter");
        if(event.getCode().toString().equals("ENTER"))
        {
            if(event.getSource()==fUsuarioLogin)
            {
                fPasswordLogin.requestFocus();
            }else if(event.getSource()==fPasswordLogin)
            {
                bIngresarLogin.requestFocus();
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializo");
    }    
}
