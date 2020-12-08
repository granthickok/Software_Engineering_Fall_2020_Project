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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public String iUser;


    @FXML
    public Button LogOut = null;

    @FXML
    public Button search = null;

    @FXML
    public Button viewAll = null;

    @FXML
    public Button update = null;

    @FXML
    public Button clear = null;

    @FXML
    public TableView<GroceryItem> table = null;

    @FXML
    public TableColumn<GroceryItem,String> Category = null;

    @FXML
    public TableColumn<GroceryItem,String> Name = null;

    @FXML
    public TableColumn<GroceryItem,String> Price = null;

    @FXML
    public TableColumn<GroceryItem,String> Stock = null;

    @FXML
    public TableColumn<GroceryItem,String> Expired = null;




    public Loader dataLoader = new Loader();
    public Inventory inv = new Inventory(dataLoader.inventoryMap);
    public Saver saver = new Saver(dataLoader.inventoryMap, dataLoader.userMap, dataLoader.orderMap);
    public TextField ItemName = null;
    public TextField ItemType = null;
    public TextField ItemPrice = null;
    public TextField ItemStock = null;

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


    @FXML
    public void ClearTable(ActionEvent event) throws IOException{

        table.getItems().clear();
    }

    public void SetInventory() throws IOException {
        dataLoader.loadInventory();
        saver.writeInventory(dataLoader.inventoryMap);
    }

    public void FillTable() throws IOException{
        List<String> itemList = new ArrayList(inv.inventoryMap.values());
        ObservableList<String> items = FXCollections.observableArrayList(itemList);
        Map<String, GroceryItem> copy;
        copy = inv.inventoryMap;
        ObservableList<GroceryItem> pop = FXCollections.observableArrayList();
        for( GroceryItem i : copy.values()){

            GroceryItem temp = i;
            pop.add(temp);

        }
        Category.setCellValueFactory(new PropertyValueFactory<>("type"));
        Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("price"));
        Stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        Expired.setCellValueFactory(new PropertyValueFactory<>("isFresh"));
        table.getItems().addAll(pop);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //table.getColumns().addAll(Category,Name,Price,Stock,Expired);
    }

    public void SearchInventory(ActionEvent event) throws IOException{ //Open inventory list
        // Load Files
        SetInventory();
        inv.searchMap(ItemName.getText(), ItemType.getText(), ItemPrice.getText(), ItemStock.getText());
        FillTable();
    }

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

    @FXML
    public void FileOpen(ActionEvent event) throws IOException{

        try {
            File oFile = new File(dataLoader.sampleDataTXTName);

            Desktop desktop = Desktop.getDesktop();

            if (oFile.exists()) {

                desktop.open(oFile);
            }
        }
        catch(Exception e){

            e.printStackTrace();
        }
    }

}