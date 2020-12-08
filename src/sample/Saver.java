package sample;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Saver{
    public String sampleDataCSVName = "sampleDatasetA.csv";
    public String sampleEmployeesCSVName = "sampleEmployeesA.csv";
    public String sampleOrdersCSVName = "sampleOrders.csv";
    Map<String, GroceryItem> inventoryMap = new HashMap<String, GroceryItem>();
    Map<String, User> userMap = new HashMap<String, User>();
    Map<String, Order> orderMap = new HashMap<String, Order>();

    public Saver(Map hashMap, Map hashMap2, Map hashMap3) {
        this.inventoryMap = hashMap;
        this.userMap = hashMap2;
        this.orderMap = hashMap3;
    }

    //      writeCSVFile Function     //
    public void writeCSVFile(String csvFileName, Map hashMap) throws IOException {
        FileWriter writer = new FileWriter(csvFileName);
        hashMap.forEach((k,v) -> {
            try {
                writer.write(hashMap.get(k).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }
    //      writeALL Function       //
    public void writeAll() throws IOException {
        writeCSVFile(sampleDataCSVName, inventoryMap);
        writeCSVFile(sampleEmployeesCSVName, userMap);
        writeCSVFile(sampleOrdersCSVName, orderMap);
    }
    //      writeCSVFile Function     //
    public void writeOrders(Map hashMap) throws IOException {
        FileWriter writer = new FileWriter(sampleOrdersCSVName);
        hashMap.forEach((k,v) -> {
            try {
                writer.write(hashMap.get(k).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }
    //      writeCSVFile Function     //
    public void writeInventory(Map hashMap) throws IOException {
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
}
// EOF