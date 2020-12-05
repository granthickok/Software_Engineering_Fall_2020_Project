package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public AnchorPane rootPane1;

    @FXML
    public Label logged;

    @FXML
    public String iUser;

    @FXML
    public Button LogOut = null;

    @FXML
    public void LoadEmployeeMenu(ActionEvent event) throws IOException {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ManagerMainMenu.fxml"));
            rootPane1 = loader.load();
            Scene scene = new Scene(rootPane1);// pane you are GOING TO show
            ManagerMenuController main = loader.getController();
            main.muser = iUser;
            main.user.setText("Logged in as " + iUser);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
        }

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
