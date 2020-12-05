package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Inventory {
    public String sampleDataCSVName = "sampleDatasetA.csv";
    public String sampleDataTXTName = "sampleDatasetA.txt";
    public class GroceryItem {
        private String type;
        private String name;
        private double price;
        private int stock;
        private boolean isFresh;
        @Override
        public String toString() {
            return type + "," + name + "," + price + "," + stock + "\n"; }
    }
    Map<String, GroceryItem> inventoryMap = new HashMap<String, GroceryItem>();


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
        System.out.println(hashMap.toString());
    }

    public void sortMap(int sortType)
    {
        Map<String, GroceryItem> newMap = new HashMap<String, GroceryItem>();
        Map<Double, GroceryItem> newNumMap = new HashMap<Double, GroceryItem>();
        switch(sortType){
            case 0:
                for (Map.Entry<String, GroceryItem> entry : inventoryMap.entrySet()) {
                    newMap.put(entry.getValue().name, entry.getValue());
                }
                inventoryMap = new TreeMap<String, GroceryItem>(newMap);
                break;
            case 1:
                for (Map.Entry<String, GroceryItem> entry : inventoryMap.entrySet()) {
                    newMap.put(entry.getValue().type, entry.getValue());
                }
                inventoryMap = new TreeMap<String, GroceryItem>(newMap);
                break;
            case 2:
                for (Map.Entry<String, GroceryItem> entry : inventoryMap.entrySet()) {
                    newNumMap.put(entry.getValue().price, entry.getValue());
                }
                newNumMap = new TreeMap<Double, GroceryItem>(newNumMap);
                for (Map.Entry<Double, GroceryItem> entry : newNumMap.entrySet()) {
                    newMap.put(String.valueOf(entry.getKey()), entry.getValue());
                }
                inventoryMap = newMap;
                break;
            case 3:
                for (Map.Entry<String, GroceryItem> entry : inventoryMap.entrySet()) {
                    newNumMap.put(Double.valueOf(entry.getValue().stock), entry.getValue());
                }
                newNumMap = new TreeMap<Double, GroceryItem>(newNumMap);
                for (Map.Entry<Double, GroceryItem> entry : newNumMap.entrySet()) {
                    newMap.put(String.valueOf(entry.getKey()), entry.getValue());
                }
                inventoryMap = newMap;
                break;
            case 4:
                for (Map.Entry<String, GroceryItem> entry : inventoryMap.entrySet()) {
                    if(entry.getValue().isFresh || entry.getValue().stock < 10)
                        newMap.put(entry.getValue().name, entry.getValue());
                }
                inventoryMap = newMap;
                break;
        }


    }

}
