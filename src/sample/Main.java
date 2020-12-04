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
    public class GroceryItem {
        private String type;
        private String name;
        private double price;
        private int stock;
        @Override
        public String toString() {
            return type + "," + name + "," + price + "," + stock + "\n"; }
    }
    Map<String, GroceryItem> inventoryMap = new HashMap<String, GroceryItem>();
    //      main Function        //
    public static void main(String[] args) { launch(args); }
    //      start Function      //
    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load Files
        readInputFile(sampleDataTXTName);
        writeInputFile(sampleDataCSVName, inventoryMap);
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
    public void readInputFile(String csvFileName) throws FileNotFoundException {
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
    public void writeInputFile(String csvFileName, Map hashMap) throws IOException {
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
