package sample;
public class GroceryItem {
    public String type;
    public String name;
    public double price;
    public int stock;
    public int expDATE;
    public boolean isFresh = true;
    @Override
    public String toString() {
        String out = type + "," + name + "," + price + "," + stock;
        if(stock < 10)
            out = out+","+"LOW STOCK";
        if(this.expDATE > 3)
            isFresh = false;
        if(!isFresh)
            out = out+","+"EXPIRES SOON";
        return out + "\n";
    }
    public String getType() {
        return type;
    }
    public void setType(String t) {
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