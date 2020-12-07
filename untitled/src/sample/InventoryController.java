package sample;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;

public class InventoryController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public AnchorPane rootPane1;

    @FXML
    public Label logged;

    @FXML
    public TextField ItemName = null;
    public TextField ItemType = null;
    public TextField ItemPrice = null;
    public TextField ItemStock = null;

    @FXML
    public String iUser;

    @FXML
    public Button LogOut = null;

    @FXML
    public TableView<Inventory.GroceryItem> table = null;

    @FXML
    public TableColumn<Inventory.GroceryItem,String> Category = null;

    @FXML
    public TableColumn<Inventory.GroceryItem,String> Name = null;

    @FXML
    public TableColumn<Inventory.GroceryItem,String> Price = null;

    @FXML
    public TableColumn<Inventory.GroceryItem,String> Stock = null;

    @FXML
    public TableColumn<Inventory.GroceryItem,String> Expired = null;

    private int sortNum = -1;

    public Inventory inv = new Inventory();

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

    public void SetInventory() throws IOException {
        inv.readInputFile(inv.sampleDataTXTName);
        inv.writeInputFile(inv.sampleDataCSVName, inv.inventoryMap);
    }

    public void FillTable() throws IOException{
        List<String> itemList = new ArrayList(inv.inventoryMap.values());
        ObservableList<String> items = FXCollections.observableArrayList(itemList);
        Map<String, Inventory.GroceryItem> copy;
        copy = inv.inventoryMap;
        ObservableList<Inventory.GroceryItem> pop = FXCollections.observableArrayList();
        for( Inventory.GroceryItem i : copy.values()){

            Inventory.GroceryItem temp = i;
            pop.add(temp);

        }
        Category.setCellValueFactory(new PropertyValueFactory<>("type"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        //Expired.setCellValueFactory(new PropertyValueFactory<>("isFresh"));
        table.getItems().addAll(pop);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //table.getColumns().addAll(Category,Name,Price,Stock,Expired);
    }

    /**
    public void ShowInventoryWindow() {
        ListView<String> list = new ListView<String>();
        List<String> itemList = new ArrayList(inv.inventoryMap.values());
        ObservableList<String> items = FXCollections.observableArrayList(itemList);
        list.setItems(items);
        list.setPrefSize(400, 500);
     //   StackPane groot = new StackPane();
    //    groot.getChildren().add(list);
    //    Scene invScreen = new Scene(groot, 200, 250);
        Stage newWindow = new Stage();
        newWindow.setTitle("Inventory");
    //    newWindow.setScene(invScreen);
        newWindow.show();
    }
     **/

    public void SearchInventory(ActionEvent event) throws IOException{ //Open inventory list
        // Load Files
        SetInventory();
        inv.searchMap(ItemName.getText(), ItemType.getText(), ItemPrice.getText(), ItemStock.getText());
        FillTable();
    }

    @FXML
    public void SortInventory(ActionEvent event) throws IOException { //Open inventory list
        // Load Files
        SetInventory();
        inv.sortMap(sortNum);
        FillTable();
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
        SortInventory(null);
    }
}