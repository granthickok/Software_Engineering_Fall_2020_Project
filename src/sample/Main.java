package sample;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
//      Main Class      //
public class Main extends Application {
    //  Main Class variables  //
    public String sampleDataCSVName = "sampleDatasetA.csv";
    public String sampleDataTXTName = "sampleDatasetA.txt";
    public String sampleEmployeesCSVName = "sampleEmployeesA.csv";
    public String sampleEmployeesTXTName = "sampleEmployeesA.txt";
    public String sampleOrdersCSVName = "sampleOrders.csv";
    public String sampleOrdersTXTName = "sampleOrders.txt";
    public class GroceryItem {
        private String type;
        private String name;
        private double price;
        private int stock;
        private int expDATE;
        @Override
        public String toString() {
            return type + "," + name + "," + price + "," + stock + "," + expDATE + "\n"; }
    }
    public class User {
        private String type;
        private String name;
        private String username;
        private String password;
        private String shifts;
        @Override
        public String toString() {
            return type + "," + name + "," + username + "," + password + "," + shifts + "\n"; }
    }
    public class Order {
        private String name;
        private String list;
        private double total;
        private String role;
        @Override
        public String toString() {
            return name + "," + list + "," + total + "," + role + "\n"; }
    }
    Map<String, GroceryItem> inventoryMap = new HashMap<String, GroceryItem>();
    Map<String, User> userMap = new HashMap<String, User>();
    Map<String, Order> orderMap = new HashMap<String, Order>();
    //      main Function        //
    public static void main(String[] args) { launch(args); }
    //      start Function      //
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load Files
        readEmployeesFile(sampleEmployeesTXTName);
        readInventoryFile(sampleDataTXTName);
        readOrdersFile(sampleOrdersTXTName);
        writeInventoryFile(sampleDataCSVName, inventoryMap);
        printInventoryMap(inventoryMap);
        // Views
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        primaryStage.setTitle("Grocery Delivery System Employee Portal");
        primaryStage.setScene(new Scene(root, 800, 500));
        ListView<String> list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "Single", "Double", "Suite", "Family App");
        list.setItems(items);
        list.setPrefSize(200, 250);
        StackPane groot = new StackPane();
        groot.getChildren().add(list);
        Scene invScreen = new Scene(groot, 200, 250);
        Stage newWindow = new Stage();
        newWindow.setTitle("Inventory");
        newWindow.setScene(invScreen);
        newWindow.show();
        primaryStage.show();
    }
    //      readInputFile Function      //
    public void readEmployeesFile(String csvFileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(csvFileName));
        while(input.hasNextLine()) {
            String line = input.nextLine();
            String[] token = line.split(",");
            User tempUser = new User();
            tempUser.type = token[0];
            tempUser.name = token[1];
            tempUser.username = token[2];
            tempUser.password = token[3];
            tempUser.shifts = token[4];
        }
        input.close();
    }
    public void readOrdersFile(String csvFileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(csvFileName));
        while(input.hasNextLine()) {
            String line = input.nextLine();
            String[] token = line.split(",");
            Order tempOrder = new Order();
            tempOrder.list = token[0];
            tempOrder.name = token[1];
            double tempPrice = Double.parseDouble(token[2]);
            tempOrder.total = tempPrice;
            tempOrder.role = token[3];
        }
        input.close();
    }
    public void readInventoryFile(String csvFileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(csvFileName));
        while(input.hasNextLine()) {
            String line = input.nextLine();
            String[] token = line.split(",");
            GroceryItem tempItem = new GroceryItem();
            tempItem.type = token[0];
            tempItem.name = token[1];
            double tempPrice = Double.parseDouble(token[2]);
            tempItem.price = tempPrice;
            int tempStock = Integer.parseInt(token[3]);
            tempItem.stock = tempStock;
            inventoryMap.put(token[1], tempItem);
        }
        input.close();
    }
    //      writeInputFile Function     //
    public void writeInventoryFile(String csvFileName, Map hashMap) throws IOException {
        FileWriter writer = new FileWriter(sampleDataCSVName);
        hashMap.forEach((k,v) -> {
            try {
                writer.write(hashMap.get(k).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }
    //      printInventoryMap       //
    public void printInventoryMap(Map hashMap) {
        System.out.println(hashMap.toString()); }
}
// EOF