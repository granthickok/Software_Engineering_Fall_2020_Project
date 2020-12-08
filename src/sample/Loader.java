package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Loader {
    // local variables
    public String loaderName;
    public String sampleDataTXTName = "sampleDatasetA.txt";
    public String sampleEmployeesTXTName = "sampleEmployeesA.txt";
    public String sampleOrdersTXTName = "sampleOrders.txt";
    public String sampleGuideTXTName = "UserGuide.txt";
    Map<String, GroceryItem> inventoryMap = new HashMap<String, GroceryItem>();
    Map<String, User> userMap = new HashMap<String, User>();
    Map<String, Order> orderMap = new HashMap<String, Order>();
    //      readInventoryFile        //
    public Inventory loadInventory() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(sampleDataTXTName));
        Inventory inventory = new Inventory(inventoryMap);
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
            int tempDate = Integer.parseInt(token[4]);
            tempItem.expDATE = tempDate;
            inventoryMap.put(token[1], tempItem);
        }
        input.close();
        return inventory;
    }
    //      readInputFile Function      //
    public void readEmployeesFile() throws FileNotFoundException {
        Scanner input = new Scanner(new File(sampleEmployeesTXTName));
        while(input.hasNextLine()) {
            String line = input.nextLine();
            String[] token = line.split(",");
            User tempUser = new User();
            tempUser.type = token[0];
            tempUser.name = token[1];
            tempUser.username = token[2];
            tempUser.password = token[3];
            tempUser.shifts = token[4];
            userMap.put(token[1], tempUser);
        }
        input.close();
    }
    public void readOrdersFile() throws FileNotFoundException {
        Scanner input = new Scanner(new File(sampleOrdersTXTName));
        while(input.hasNextLine()) {
            String line = input.nextLine();
            String[] token = line.split(",");
            Order tempOrder = new Order();
            tempOrder.name = token[0];
            tempOrder.list = token[1];
            String[] tempList = tempOrder.list.split("#");
            int i = 0;
            while(i < tempList.length) {
                if(tempList[i] == null) { break; }
                String[] newList = tempList[i].split("=");
                tempOrder.tempInt = Integer.parseInt(newList[0]);
                tempOrder.tempGroceryItemName = newList[1];
                tempOrder.groceryItemMap.put(tempOrder.tempInt
                        ,tempOrder.tempGroceryItemName);
                i++;
            }
            double tempPrice = Double.parseDouble(token[2]);
            tempOrder.total = tempPrice;
            tempOrder.role = token[3];
            orderMap.put(token[0], tempOrder);
        }
        input.close();
    }
}
// EOF