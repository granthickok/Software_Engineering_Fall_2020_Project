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
    public Button Mlogin = null; //button to change screens

    @FXML
    public Button Dlogin = null; //button to change screens


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
    public void LoadManagerMenu(ActionEvent event) throws IOException { //Change to Main Menu

        eUser = tfUser.getText();

        ePassword = tfPassword.getText();

        if (eUser != "" && ePassword != "") {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManagerMainMenu.fxml"));
            rootPane1 = loader.load();
            Scene scene = new Scene(rootPane1);// pane you are GOING TO show
            ManagerMenuController main = loader.getController();
            main.user.setText("Logged in as " + eUser);
            main.muser = eUser;
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
        } else {

            tfUser.clear();

            tfPassword.clear();

            eUser = "";

            ePassword = "";

        }

    }

    @FXML
    public void LoadDriverMenu(ActionEvent event) throws IOException { //Change to Main Menu

        eUser = tfUser.getText();

        ePassword = tfPassword.getText();

        if (eUser != "" && ePassword != "") {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("DriverMainMenu.fxml"));
            rootPane1 = loader.load();
            Scene scene = new Scene(rootPane1);// pane you are GOING TO show
            ManagerMenuController main = loader.getController();
            main.user.setText("Logged in as " + eUser);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
        } else {

            tfUser.clear();

            tfPassword.clear();

            eUser = "";

            ePassword = "";

        }

    }


    @FXML
    public void Exit(ActionEvent event) throws IOException {

        System.exit(0);
    }
}