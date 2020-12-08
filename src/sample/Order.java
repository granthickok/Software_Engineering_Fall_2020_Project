package sample;

import java.util.HashMap;
import java.util.Map;

public class Order {
    public String name;
    public String list;
    public int tempInt;
    public String tempGroceryItemName;
    Map<Integer, String> groceryItemMap = new HashMap<>();
    public double total;
    public String role;

    @Override
    public String toString() {
        return name + "," + groceryItemMap.toString() + "," + total + "," + role + "\n";
    }
    public void hashMapString() {
        System.out.println(groceryItemMap.toString());
    }
    public String getName() { return name; }
    public void setName(String n) { name = n; }
    public String getList() { return list; }
    public void setList(String l) { list = l; }
    public double getTotal() { return total; }
    public void setTotal(double t) { total = t; }
    public String getRole() { return role; }
    public void setRole(String r) { role = r; }
}
