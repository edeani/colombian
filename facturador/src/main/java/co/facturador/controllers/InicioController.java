package co.facturador.controllers;

import co.com.facturador.services.LoginService;
import co.com.facturador.services.LoginServiceImpl;
import co.facturador.controllers.util.UtilController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class InicioController implements Initializable {
    
    @FXML
    private TextField fUsuarioLogin;
    @FXML
    private PasswordField fPasswordLogin;
    @FXML
    private Button bIngresar;
    @FXML
    private void handleLogin(ActionEvent event) {        
        LoginService loginService = new LoginServiceImpl();
       
        boolean succes = loginService.loguearUsuario(fUsuarioLogin.getText(),fPasswordLogin.getText());
         if(succes){
               System.out.println("Succes");
               //Obtengo Stage actual para cerra la ventana de login
               Stage actualStageButton = (Stage) bIngresar.getScene().getWindow();
               actualStageButton.close();
               
               Stage stageDashboard= new Stage();
               UtilController utilController = new UtilController();
               utilController.goToScene(stageDashboard, "/fxml/Dashboard.fxml", "/styles/dashboard.css", "Dasboard Usuario::"+fUsuarioLogin.getText());
           }else{
               System.out.println("Not Succes");
           }
        
        
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
                bIngresar.requestFocus();
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializo");
    }    
}
