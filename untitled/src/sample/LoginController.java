package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
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
    public Text error = null;

    @FXML
    public String eUser;

    @FXML
    public String ePassword;

    @FXML
    public AnchorPane rootPane1;

    Loader loader = new Loader();

    public int Authorize(String uName, String pWord) throws IOException{
        loader.readEmployeesFile();
        Map<String, User> userMap = loader.userMap;
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            if(uName.toLowerCase().compareTo(entry.getValue().username.toLowerCase()) == 0){
                if(pWord.compareTo(entry.getValue().password) == 0){
                    return LogIn(entry.getValue().type);
                }
            }
        }
        return -1;
    }

    public int LogIn(String permission) throws IOException {
        if(permission.compareTo("Manager") == 0)
            return 1;
        else if(permission.compareTo("Driver") == 0)
            return 2;
        else if(permission.compareTo("Customer") == 0)
            return 3;
        else if(permission.compareTo("Maintenance") == 0)
            return 4;
        return 0;
    }

    @FXML
    public void AttemptLogin(ActionEvent event) throws IOException { //Change to Main Menu

        eUser = tfUser.getText();
        ePassword = tfPassword.getText();

        int status = Authorize(eUser, ePassword);

        if (status == -1) {
            error.setText("Incorrect username or password");
            tfPassword.clear();
        } else if (status == 0) {
                error.setText("Permission not given");
                tfUser.clear();
                tfPassword.clear();
        } else if (status == 1) {
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
        } else if(status == 2){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("DriverMainMenu.fxml"));
            rootPane1 = loader.load();
            Scene scene = new Scene(rootPane1);// pane you are GOING TO show
            ManagerMenuController main = loader.getController();
            main.user.setText("Logged in as " + eUser);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
        }
        else if (status == 3) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CustomerMainMenu.fxml"));
            rootPane1 = loader.load();
            Scene scene = new Scene(rootPane1);// pane you are GOING TO show
            ManagerMenuController main = loader.getController();
            main.user.setText("Logged in as " + eUser);
            main.muser = eUser;
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
        }
        else if (status == 4) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MaintenanceMainMenu.fxml"));
            rootPane1 = loader.load();
            Scene scene = new Scene(rootPane1);// pane you are GOING TO show
            ManagerMenuController main = loader.getController();
            main.user.setText("Logged in as " + eUser);
            main.muser = eUser;
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
            window.setScene(scene);
            window.show();
        } else {
            error.setText("Unresolved Error");
            tfUser.clear();
            tfPassword.clear();
        }

    }

    @FXML
    public void Exit(ActionEvent event) throws IOException {

        System.exit(0);
    }
}