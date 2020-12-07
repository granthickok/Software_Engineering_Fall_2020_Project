package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderManagerController {

    @FXML
    public Button back = null; //button to change screens

    @FXML
    public Button logout = null; //button to change screens

    @FXML
    public Button notif = null; //button to pull up notifications

    @FXML
    public AnchorPane rootPane1;

    @FXML
    public Label logged = null;

    public String ouser;

    @FXML
    public TableView inv = null;

    @FXML
    public TableColumn Category = null;

    @FXML
    public TableColumn Name = null;

    @FXML
    public TableColumn Price = null;

    @FXML
    public TableColumn Quantity = null;

    @FXML
    public Button update = null;

    @FXML
    public void TestUpdate(ActionEvent event){

        Category.setCellValueFactory(new PropertyValueFactory<>("A"));
        Name.setCellValueFactory(new PropertyValueFactory<>("B"));
        Price.setCellValueFactory(new PropertyValueFactory<>("C"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("D"));
        System.out.println(Main.inventoryMap);
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

    @FXML
    public void LoadEmployeeMenu(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManagerMainMenu.fxml"));
        rootPane1 = loader.load();
        Scene scene = new Scene(rootPane1);// pane you are GOING TO show
        ManagerMenuController main = loader.getController();
        main.muser = ouser;
        main.user.setText("Logged in as " + ouser);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();
    }
}
