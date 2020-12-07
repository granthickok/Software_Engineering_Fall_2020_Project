package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderManagerController {

    @FXML
    public Button back = null; //button to change screens

    @FXML
    public Button logout = null; //button to change screens

    @FXML
    public Button notif = null; //button to pull up notifications

    @FXML
    public TextField sName = null;

    @FXML
    public TextField  sItem = null;

    @FXML
    public TextField sQuantity = null;

    @FXML
    public TextField sRole = null;

    @FXML
    public AnchorPane rootPane1;

    @FXML
    public Label logged = null;

    public String ouser;

    @FXML
    public TableView<OrderList.Order> ord = null;

    @FXML
    public TableColumn<OrderList.Order,String>  User = null;

    @FXML
    public TableColumn<OrderList.Order,String>  Item = null;

    @FXML
    public TableColumn<OrderList.Order,String>  Role = null;

    @FXML
    public TableColumn<OrderList.Order,String>  Quantity = null;

    @FXML
    public Button update = null;

    public OrderList orders = new OrderList();

    public void SetOrderList() throws IOException {
        orders.readOrdersFile(orders.sampleDataTXTName);
        //System.out.print(orders.orderMap);
        orders.writeInputFile(orders.sampleDataCSVName, orders.orderMap);
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


    public void FillTable() throws IOException{
        List<String> orderList = new ArrayList(orders.orderMap.values());
        ObservableList<String> ordee = FXCollections.observableArrayList(orderList);
        Map<String, OrderList.Order> copy;
        copy = orders.orderMap;
        ObservableList<OrderList.Order> pop = FXCollections.observableArrayList();
        for( OrderList.Order i : copy.values()){

            OrderList.Order temp = i;
            pop.add(temp);

        }
        User.setCellValueFactory(new PropertyValueFactory<>("name"));
        Item.setCellValueFactory(new PropertyValueFactory<>("list"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("total"));
        Role.setCellValueFactory(new PropertyValueFactory<>("role"));
        ord.getItems().addAll(pop);
        ord.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //table.getColumns().addAll(Category,Name,Price,Stock,Expired);
    }

    public void SearchOrders(ActionEvent event) throws IOException{ //Open inventory list
        // Load Files
        ord.getItems().clear();
        SetOrderList();
        orders.searchMap(sName.getText(), sItem.getText(), sQuantity.getText(), sRole.getText());
        FillTable();
        sName.clear();
        sItem.clear();
        sQuantity.clear();
        sRole.clear();
    }

    @FXML
    public void SetTable(ActionEvent event) throws IOException{

        ord.getItems().clear();
        SetOrderList();
        FillTable();
    }

    @FXML
    public void ClearTable(ActionEvent event) throws IOException{

        ord.getItems().clear();
    }

    @FXML
    public void FileOpen(ActionEvent event) throws IOException{

        try {
            File oFile = new File(orders.sampleDataTXTName);

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