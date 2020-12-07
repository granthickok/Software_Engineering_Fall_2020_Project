package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class OrderList {
    public String sampleDataCSVName = "sampleOrders.csv";
    public String sampleDataTXTName = "sampleOrders.txt";

    public class Order {
        public String name;
        public String list;
        public double total;
        public String role;

        @Override
        public String toString() {
            return name + "," + list + "," + total + "," + role + "\n";
        }

        public String getName() {

            return name;

        }

        public void setName(String n) {

            name = n;
        }

        public String getList() {

            return list;

        }

        public void setList(String l) {

            list = l;
        }

        public double getTotal() {

            return total;

        }

        public void setTotal(double t) {

            total = t;
        }

        public String getRole() {

            return role;

        }

        public void setRole(String r) {

            role = r;
        }

    }

    Map<String, Order> orderMap = new HashMap<String, Order>();

    // Read Order File //

    public void readOrdersFile(String csvFileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(csvFileName));
        while(input.hasNextLine()) {
            String line = input.nextLine();
            String[] token = line.split(",");
            Order tempOrder = new Order();
            tempOrder.name = token[0];
            tempOrder.list = token[1];
            double tempPrice = Double.parseDouble(token[2]);
            tempOrder.total = tempPrice;
            tempOrder.role = token[3];
            orderMap.put(token[0], tempOrder);
        }
        input.close();
    }

    //      writeInputFile Function     //
    public void writeInputFile(String csvFileName, Map hashMap) throws IOException {
        FileWriter writer = new FileWriter(sampleDataCSVName);
        hashMap.forEach((k, v) -> {
            try {
                writer.write(hashMap.get(k).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }

    public void searchMap(String inputName, String inputType, String inputPrice, String inputRole) {
        Map<String, OrderList.Order> newMap = new HashMap<String, OrderList.Order>();
        for (Map.Entry<String, OrderList.Order> entry : orderMap.entrySet()) {
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