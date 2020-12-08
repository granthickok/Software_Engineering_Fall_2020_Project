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
    //  Main Class Constructors  //
    Loader dataLoader = new Loader();
    Inventory inventory = new Inventory();
    //      main Function        //
    public static void main(String[] args) { launch(args); }
    //      start Function      //
    @Override
    public void start(Stage primaryStage) throws Exception{
        //      Load Files       //
        this.inventory = dataLoader.loadInventory();
        dataLoader.readOrdersFile();
        dataLoader.readEmployeesFile();
        //      show input and write files       //
        Saver saver = new Saver(dataLoader.inventoryMap
                , dataLoader.userMap
                , dataLoader.orderMap);
        saver.writeAll();
        printAll(dataLoader.inventoryMap, dataLoader.userMap, dataLoader.orderMap);
        //      Views       //
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        primaryStage.setTitle("Grocery Delivery System Employee Portal");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }
    //      printMap       //
    public void printMap(Map hashMap) {
        System.out.println(hashMap.toString());
    }
    //      printAll        //
    public void printAll(Map hashMap, Map hashMap2, Map hashMap3) {
        printMap(hashMap);
        printMap(hashMap2);
        printMap(hashMap3);
    }
}
// EOF