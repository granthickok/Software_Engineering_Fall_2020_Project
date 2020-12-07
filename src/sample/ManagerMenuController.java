package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerMenuController {

    @FXML
    public Button back = null; //button to change screens

    @FXML
    public Button invent = null; //button to change screens

    @FXML
    public Button notif = null; //button to pull up notifications

    @FXML
    public Button order = null; //button to pull up notifications

    @FXML
    public AnchorPane rootPane1;

    @FXML
    public Label user = null;

    public String muser;

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
    public void LoadInventory(ActionEvent event) throws IOException { //Change to Main Menu

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Inventory.fxml"));
        rootPane1 = loader.load();
        Scene scene = new Scene(rootPane1);// pane you are GOING TO show
        InventoryController inv = loader.getController();
        inv.iUser = muser;
        inv.logged.setText("Logged in as " + muser);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();

    }

    @FXML
    public void LoadOrder(ActionEvent event) throws IOException { //Change to Main Menu

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Order.fxml"));
        rootPane1 = loader.load();
        Scene scene = new Scene(rootPane1);// pane you are GOING TO show
        OrderManagerController or = loader.getController();
        or.ouser = muser;
        or.logged.setText("Logged in as " + muser);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();// pane you are ON
        window.setScene(scene);
        window.show();

    }

    @FXML
    public void OpenExpiredInventory(ActionEvent event) throws IOException { //Open inventory list
        // Load Files
        Inventory inv = new Inventory();
        inv.readInputFile(inv.sampleDataTXTName);
        inv.writeInputFile(inv.sampleDataCSVName, inv.inventoryMap);
        inv.printInventoryMap(inv.inventoryMap);
        inv.sortMap(4);
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
}
