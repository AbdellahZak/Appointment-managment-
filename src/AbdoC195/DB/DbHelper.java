package AbdoC195.DB;

import AbdoC195.Controllers.ControllerHelper;
import AbdoC195.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.security.PublicKey;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DbHelper {
    public static int nextCustomerId = 0;

    public static ObservableList<Customer> allCustomers= FXCollections.observableArrayList();

    public static ObservableList<Customer> getCustomers(){
        return allCustomers;
    }

    public static void addCustomer(Customer customer){
        allCustomers.add(customer);
    }


    public static int insert(String userID, String userPassword) throws SQLException {
        String sql = "INSERT INTO USERS (User_Name, Password) VALUES (?,?)";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setString(1,userID);
        ps.setString(2,userPassword);
        int rowsAffected =ps.executeUpdate();
        return rowsAffected;
    }
    public static int delete(int id) throws SQLException {
        String sql="DELETE FROM USERS WHERE User_ID=?";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setInt(1,id);
        int rowsAffected =ps.executeUpdate();
        return rowsAffected;
    }
    public static boolean VerifyNameAndPassword(String userName,String password) throws SQLException {
        Boolean Bname=false;
        Boolean Bpass=false;
        String sql="SELECT User_Name, Password FROM users";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            String uName =rs.getString("User_Name");
            if(uName.equals(userName)){
                Bname=true;
            };
            String pass =rs.getString("Password");
            if(pass.equals(password)){
                Bpass=true;
            }
            if (Bname==true && Bpass==true){return true;}
        }

    return false;
    }
    public static ObservableList<Customer> getCustomersDb() throws SQLException {
        String sql="SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        Customer customer = null;
        while (rs.next()){
            int customer_Id= rs.getInt("Customer_ID");
            String customer_Name= rs.getString("Customer_name");
            String address= rs.getString("Address");
            String postal_Code= rs.getString("Postal_Code");
            String phone= rs.getString("Phone");
            int divison_ID =rs.getInt("Division_ID");
            addCustomer(new Customer(customer_Id,customer_Name,address,postal_Code,phone,divison_ID));
        }
        return getCustomers();
    }


}
