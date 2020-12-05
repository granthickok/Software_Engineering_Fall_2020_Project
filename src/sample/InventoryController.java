package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    private int sortNum = -1;

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

    public void SetInventory(){

    }

    @FXML
    public void OpenInventory(ActionEvent event) throws IOException { //Open inventory list
        // Load Files
        Inventory inv = new Inventory();
        inv.readInputFile(inv.sampleDataTXTName);
        inv.writeInputFile(inv.sampleDataCSVName, inv.inventoryMap);
        inv.printInventoryMap(inv.inventoryMap);
        inv.sortMap(sortNum);
        ListView<String> list = new ListView<String>();
        List<String> itemList = new ArrayList(inv.inventoryMap.values());
        ObservableList<String> items = FXCollections.observableArrayList(itemList);
        list.setItems(items);
        list.setPrefSize(400, 500);
        StackPane groot = new StackPane();
        groot.getChildren().add(list);
        Scene invScreen = new Scene(groot, 200, 250);
        Stage newWindow = new Stage();
        newWindow.setTitle("Inventory");
        newWindow.setScene(invScreen);
        newWindow.show();
    }

    @FXML
    public void SortName(ActionEvent event) throws IOException { //Open inventory list
        sortNum = 0;
    }

    @FXML
    public void SortType(ActionEvent event) throws IOException { //Open inventory list
        sortNum = 1;
    }

    @FXML
    public void SortPrice(ActionEvent event) throws IOException { //Open inventory list
        sortNum = 2;
    }

    @FXML
    public void SortStock(ActionEvent event) throws IOException { //Open inventory list
        sortNum = 3;
    }

    public void ShowExp(ActionEvent event) throws IOException { //Open inventory list
        sortNum = 4;
        OpenInventory(null);
    }
}