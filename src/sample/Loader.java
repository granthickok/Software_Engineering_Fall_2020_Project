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
    Map<String, GroceryItem> inventoryMap = new HashMap<String, GroceryItem>();
    //      readInventoryFile        //
    public Inventory loadInventory() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(sampleDataTXTName));
        Inventory inventory = new Inventory();
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
}
// EOF