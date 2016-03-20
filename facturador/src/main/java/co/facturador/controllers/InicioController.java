package co.facturador.controllers;

import co.com.facturador.services.LoginService;
import co.com.facturador.services.LoginServiceImpl;
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
        LoginService loginService = new LoginServiceImpl();
        boolean succes = loginService.loguearUsuario("BABYMADFHS", "02062206");
         if(succes){
               System.out.println("Succes");
           }else{
               System.out.println("Not Succes");
           }
        System.out.println("You clicked me!");
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
