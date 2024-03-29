package AbdoC195.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.util.Date;
/** User Model class. */
public class User {
    private int User_ID;
    private String User_Name;
    private String Password;
    private Timestamp Create_Date;
    private String Created_By;
    private Timestamp Last_Update;

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Date getCreate_Date() {
        return Create_Date;
    }

    public void setCreate_Date(Timestamp create_Date) {
        Create_Date = create_Date;
    }

    public String getCreated_By() {
        return Created_By;
    }

    public void setCreated_By(String created_By) {
        Created_By = created_By;
    }

    public Date getLast_Update() {
        return Last_Update;
    }

    public void setLast_Update(Timestamp last_Update) {
        Last_Update = last_Update;
    }

    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    public void setLast_Updated_By(String last_Updated_By) {
        Last_Updated_By = last_Updated_By;
    }

    private String Last_Updated_By;

    public User(int user_ID, String user_Name, String password, Timestamp create_Date, String created_By, Timestamp last_Update, String last_Updated_By) {
        User_ID = user_ID;
        User_Name = user_Name;
        Password = password;
        Create_Date = create_Date;
        Created_By = created_By;
        Last_Update = last_Update;
        Last_Updated_By = last_Updated_By;
    }
    public User(int user_ID, String user_Name, String password){
        User_ID = user_ID;
        User_Name = user_Name;
        Password = password;
    }
    @Override
    public String toString(){
        return ("#" +Integer.toString(User_ID) +" "+ User_Name );
    }
}

