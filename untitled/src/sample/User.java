package sample;
public class User {
    public String type;
    public String name;
    public String username;
    public String password;
    public String shifts;
    @Override
    public String toString() {
        return type + "," + name + "," + username + "," + password + "," + shifts + "\n"; }
}
// EOF