package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    @FXML
    public Button login = null; //button to change screens

    @FXML
    public Button quit = null; //button to change screens

    @FXML
    public TextField tfUser = null; // Lookup item input text field

    @FXML
    public TextField tfPassword = null; // Lookup item input text field

    @FXML
    public String eUser;

    @FXML
    public String ePassword;

    @FXML
    public AnchorPane rootPane1;

    @FXML
    public void LoadMenu(ActionEvent event) throws IOException { //Change to Main Menu

        eUser = tfUser.getText();

        ePassword = tfPassword.getText();

        if (eUser != "" && ePassword != "") {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainMenu.fxml"));
            rootPane1 = loader.load();
            Scene scene = new Scene(rootPane1);// pane you are GOING TO show
            MainMenuController main = loader.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
        } else {

            tfUser.clear();

            tfPassword.clear();

        }

    }

    @FXML
    public void Exit(ActionEvent event) throws IOException {

    System.exit(0);
    }
}
