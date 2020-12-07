package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotificationController extends InventoryController {
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

    @FXML
    public void OpenLowInventory(ActionEvent event) throws IOException { //Open inventory list
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
