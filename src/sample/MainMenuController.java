package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public Button back = null; //button to change screens

    @FXML
    public AnchorPane rootPane1;

    @FXML
    public Label user = null;

    @FXML
    public void LoadLogin(ActionEvent event) throws IOException { //Change to Main Menu

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Start.fxml"));
            rootPane1 = loader.load();
            Scene scene = new Scene(rootPane1);// pane you are GOING TO show
            LoginController log = loader.getController();
            log.ePassword = "";
            log.eUser = "";
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();

    }
}
