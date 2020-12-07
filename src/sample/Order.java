package sample;

public class Order {
    public String name;
    public String list;
    public double total;
    public String role;
    @Override
    public String toString() {
        return name + "," + list + "," + total + "," + role + "\n";
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

