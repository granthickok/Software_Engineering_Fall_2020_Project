package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.*;


public class Inventory {
    public String sampleDataCSVName = "sampleDatasetA.csv";
    public String sampleDataTXTName = "sampleDatasetA.txt";
    public class GroceryItem {
        public String type;
        public String name;
        public double price;
        public int stock;
        public boolean isFresh;
        @Override
        public String toString() {
            return type + "," + name + "," + price + "," + stock + "\n"; }
        public String getType(){

            return type;

        }

        public void setType(String t){

            type = t;
        }
        public String getName(){

            return name;

        }

        public void setName(String n){

            name = n;
        }

        public double getPrice(){

            return price;

        }

        public void setPrice(double p ){

            price = p;
        }
        public int getStock(){

            return stock;

        }

        public void setStock(int s){

            stock = s;
        }
        public boolean getIsFresh(){

            return isFresh;

        }

        public void setisFresh(boolean b){

            isFresh = b;
        }
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

    public void searchMap(String inputName, String inputType, String inputPrice, String inputStock) {
        Map<String, GroceryItem> newMap = new HashMap<String, GroceryItem>();
        for (Map.Entry<String, GroceryItem> entry : inventoryMap.entrySet()) {
            if(!entry.getValue().name.toLowerCase().contains(inputName.toLowerCase()))
                continue;

            if(!entry.getValue().type.toLowerCase().contains(inputType.toLowerCase()))
                continue;

            if (inputPrice.length() != 0) {
                if(inputPrice.contains("+"))
                {
                    String stringPrice=inputPrice.split("\\+")[0]+inputPrice.split("\\+")[1];
                    if(entry.getValue().price < Double.parseDouble(stringPrice)){
                        continue;
                    }
                }
                else
                {
                    if(entry.getValue().price > Double.parseDouble(inputPrice))
                        continue;
                }
            }
            if (inputStock.length() != 0) {
                if(inputStock.contains("+"))
                {
                    String stringStock=inputStock.split("\\+")[0]+inputStock.split("\\+")[1];
                    if(entry.getValue().stock < Double.parseDouble(stringStock)){
                        continue;
                    }
                }
                else if(entry.getValue().stock > Double.parseDouble(inputStock))
                {
                    continue;
                }
            }
            newMap.put(entry.getValue().name, entry.getValue());
        }
        inventoryMap = newMap;
    }

    public void sortMap(int sortType)
    {
        Map<String, GroceryItem> newMap = new HashMap<String, GroceryItem>();
        List<GroceryItem> list = new ArrayList<GroceryItem>();
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
                    list.add(entry.getValue());
                }
                for(int j = 1; j < list.size(); j++)
                {
                    GroceryItem temp = list.get(j);
                    int i = j-1;
                    while((i>-1)&&(list.get(i).price>temp.price)){
                        list.set(i+1, list.get(i));
                        i--;
                    }
                    list.set(i+1, temp);
                }
                for (int i = 0; i < list.size(); i++) {
                    String keyval = ".";
                    for(int j = 0; j < i; j++)
                        keyval = keyval+".";
                    newMap.put(keyval, list.get(i));
                }
                inventoryMap = new TreeMap<String, GroceryItem>(newMap);
                break;
            case 3:
                for (Map.Entry<String, GroceryItem> entry : inventoryMap.entrySet()) {
                list.add(entry.getValue());
            }
                for(int j = 1; j < list.size(); j++)
                {
                    GroceryItem temp = list.get(j);
                    int i = j-1;
                    while((i>-1)&&(list.get(i).stock>temp.stock)){
                        list.set(i+1, list.get(i));
                        i--;
                    }
                    list.set(i+1, temp);
                }
                for (int i = 0; i < list.size(); i++) {
                    String keyval = ".";
                    for(int j = 0; j < i; j++)
                        keyval = keyval+".";
                    newMap.put(keyval, list.get(i));
                }
                inventoryMap = new TreeMap<String, GroceryItem>(newMap);
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
