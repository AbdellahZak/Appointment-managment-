package AbdoC195.DB;

import AbdoC195.Model.Countries;
import AbdoC195.Model.Customer;
import AbdoC195.Model.Divison;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DbHelper {
    public static int nextCustomerId = 0;

    ///////////////////////////////////////////////////////////////////////////////////////////// Customer

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////// Division


    public static ObservableList<Divison> allDivisions= FXCollections.observableArrayList();

    public static ObservableList<Divison> getDivisions(){
        return allDivisions;
    }

    public static void addDiviosn(Divison division){
        allDivisions.add(division);
    }

    public static ObservableList<Divison> getDivisionsDb() throws SQLException {
        String sql="SELECT Division_ID, Division, Country_ID FROM first_level_divisions";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        Customer customer = null;
        while (rs.next()){
            int division_Id= rs.getInt("Division_ID");
            String division= rs.getString("Division");
            int country_ID =rs.getInt("Country_ID");
            addDiviosn(new Divison(division_Id,division,country_ID));
        }
        return getDivisions();
    }
    public static ObservableList<Divison> FilterByCountryId(int countryId) throws SQLException {
        ObservableList<Divison> namedStates = FXCollections.observableArrayList();
        ObservableList<Divison> allDivisions= DbHelper.getDivisionsDb();

        for(Divison division: allDivisions){
            if(division.getCountry_Id()==countryId){
                namedStates.add(division);
            }
        }
        return namedStates;
    }


    //////////////////////////////////////////////////////////////////////////////////// countries

    public static ObservableList<Countries> allCountries= FXCollections.observableArrayList();

    public static ObservableList<Countries> getCountries(){
        return allCountries;
    }

    public static void addCountry(Countries country){
        allCountries.add(country);
    }

    public static ObservableList<Countries> getCountriesDb() throws SQLException {
        String sql="SELECT Country_ID, Country FROM countries";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        Customer customer = null;
        while (rs.next()){
            int country_Id= rs.getInt("Country_ID");
            String country= rs.getString("Country");
            addCountry(new Countries(country_Id, country));
        }
        return getCountries();
    }

}
