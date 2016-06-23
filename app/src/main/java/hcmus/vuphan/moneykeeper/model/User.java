package hcmus.vuphan.moneykeeper.model;

import com.orm.SugarRecord;

/**
 * Created by Join on 23/06/2016.
 */
public class User extends SugarRecord {
    String username;
    String password;

    public  User(){};
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    public String getUsername(){return  username;}
    public void setUsername(String value) {username = value;}
    public String getPassword(){return  password;}
    public void setPassword(String value) {password = value;}
    public String toString()
    {
        return "Tenuser: "+username+
                ",password"+password + "\n";
    }
}
