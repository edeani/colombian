package co.facturador.controllers;

import co.facturador.controllers.util.UtilController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        UtilController utilController = new UtilController();
        //utilController.goToScene(primaryStage,"/fxml/Login.fxml", "/styles/Styles.css", "Login");
        utilController.goToScene(primaryStage,"/fxml/Dashboard.fxml", "/styles/Styles.css", "Login");
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
