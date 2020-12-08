package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class OrderList {
    Loader dataLoader = new Loader();
    Map<String, Order> orderMap = new HashMap<String, Order>();

    public void searchMap(String inputName, String inputType, String inputPrice, String inputRole) {
        Map<String, Order> newMap = new HashMap<String, Order>();
        for (Map.Entry<String, Order> entry : orderMap.entrySet()) {
            if(!entry.getValue().name.toLowerCase().contains(inputName.toLowerCase()))
                continue;
            if(!entry.getValue().list.toLowerCase().contains(inputType.toLowerCase()))
                continue;
            if(!entry.getValue().role.toLowerCase().contains(inputRole.toLowerCase()))
                continue;
            if (inputPrice.length() != 0) {
                if(inputPrice.contains("+"))
                {
                    String stringPrice=inputPrice.split("\\+")[0]+inputPrice.split("\\+")[1];
                    if(entry.getValue().total < Double.parseDouble(stringPrice)){
                        continue;
                    }
                }
                else
                {
                    if(entry.getValue().total > Double.parseDouble(inputPrice))
                        continue;
                }
            }
            newMap.put(entry.getValue().name, entry.getValue());
        }
        orderMap = newMap;
    }
}