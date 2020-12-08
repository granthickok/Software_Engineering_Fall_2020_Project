package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Inventory {
    Map<String, GroceryItem> inventoryMap = new HashMap<String, GroceryItem>();
    public Inventory(Map hashMap) {
        this.inventoryMap = hashMap;
    }
    //      printInventoryMap       //
    public void printInventoryMap(Map hashMap) {
        System.out.println(hashMap.toString());
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
                    if(!entry.getValue().isFresh || entry.getValue().stock < 10)
                        newMap.put(entry.getValue().name, entry.getValue());
                }
                inventoryMap = newMap;
                break;
        }
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

}