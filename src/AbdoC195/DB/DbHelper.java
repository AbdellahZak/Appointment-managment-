package AbdoC195.DB;

import AbdoC195.Model.Appointment;
import AbdoC195.Model.Countries;
import AbdoC195.Model.Customer;
import AbdoC195.Model.Divison;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public abstract class DbHelper {
    public static int nextCustomerId = 3;

    //////////////////////////////////////////////Users///////////////////////////////////////////////




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
////////////////////////////////////////////////Customers////////////////////////////////////////////////
    public static ObservableList<Customer> allCustomers= FXCollections.observableArrayList();

    public static ObservableList<Customer> getCustomers() throws SQLException {
        return allCustomers;
    }
    public static void addCustomerList(Customer customer){
        allCustomers.add(customer);
    }

    public static void addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name,Address,Postal_Code,Phone,Division_ID) VALUES (?,?,?,?,?)";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
       // ps.setInt(1,customer.getCustomer_Id());
        ps.setString(1,customer.getCustomer_Name());
        ps.setString(2,customer.getAddress());
        ps.setString(3,customer.getPostalCode());
        ps.setString(4,customer.getPhoneNumber());
        ps.setInt(5,customer.getFirst_levelD());
        ps.executeUpdate();
    }

    public static void getCustomersDb() throws SQLException {
        String sql="SELECT Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID FROM customers";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            int customer_Id= rs.getInt("Customer_ID");
            String customer_Name= rs.getString("Customer_name");
            String address= rs.getString("Address");
            String postal_Code= rs.getString("Postal_Code");
            String phone= rs.getString("Phone");
            int divison_ID =rs.getInt("Division_ID");
            addCustomerList (new Customer(customer_Id,customer_Name,address,postal_Code,phone,divison_ID));
        }
    }
    public static void deleteCustomerByIdDb(int customerId) throws SQLException {
        String sql="DELETE FROM CUSTOMERS WHERE Customer_ID=?";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setInt(1,customerId);
        ps.executeUpdate();
    }
    public static void updateCustomerRowById(Customer customer) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name=?,Address=?,Postal_Code=?,Phone=?,Division_ID=? WHERE Customer_ID=?";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setInt(6,customer.getCustomer_Id());
        ps.setString(1,customer.getCustomer_Name());
        ps.setString(2,customer.getAddress());
        ps.setString(3,customer.getPostalCode());
        ps.setString(4,customer.getPhoneNumber());
        ps.setInt(5,customer.getFirst_levelD());
        ps.executeUpdate();
    }

    ///////////////////////////////////////////////Division////////////////////////////////////////////////////////


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


    /////////////////////////////////////////////countries///////////////////////////////////////

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
    /////////////////////////////////////////////////////Appointment///////////////////////////////////////////

   public static ObservableList<Appointment> allAppointments= FXCollections.observableArrayList();

    public static ObservableList<Appointment> getAppointments() throws SQLException {
        return allAppointments;
    }
    public static void addAppointmentList(Appointment appointment){
        allAppointments.add(appointment);
    }

   /* public static void addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name,Address,Postal_Code,Phone,Division_ID) VALUES (?,?,?,?,?)";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        // ps.setInt(1,customer.getCustomer_Id());
        ps.setString(1,appointment.getCustomer_Name());
        ps.setString(2,appointment.getAddress());
        ps.setString(3,appointment.getPostalCode());
        ps.setString(4,appointment.getPhoneNumber());
        ps.setInt(5,appointment.getFirst_levelD());
        ps.executeUpdate();
    }*/

    public static void getAppointmentDb() throws SQLException {
        String sql="SELECT Appointment_ID, Title, Description, Location,Contact_ID, Type, Start, End,Customer_ID,User_ID FROM appointments";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            int appointment_Id= rs.getInt("Appointment_ID");
            String title= rs.getString("Title");
            String description= rs.getString("Description");
            String location= rs.getString("Location");
            int contactId=rs.getInt("Contact_ID");
            String type= rs.getString("Type");
            Timestamp startTimeStamp =rs.getTimestamp("Start");
            LocalDateTime start =UtcToLocalZoned(startTimeStamp);
            Timestamp endTimeStamp =rs.getTimestamp("End");
            LocalDateTime end=UtcToLocalZoned(endTimeStamp);
            int customerId=rs.getInt("Customer_ID");
            int userId=rs.getInt("User_ID");
            addAppointmentList (new Appointment(appointment_Id,title,description,location,contactId,type,start,end,customerId,userId));
        }
    }
    /*
    public static void deleteAppointmentByIdDb(int customerId) throws SQLException {
        String sql="DELETE FROM CUSTOMERS WHERE Customer_ID=?";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setInt(1,customerId);
        ps.executeUpdate();
    }
    public static void updateAppointmentRowById(Customer customer) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name=?,Address=?,Postal_Code=?,Phone=?,Division_ID=? WHERE Customer_ID=?";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setInt(6,customer.getCustomer_Id());
        ps.setString(1,customer.getCustomer_Name());
        ps.setString(2,customer.getAddress());
        ps.setString(3,customer.getPostalCode());
        ps.setString(4,customer.getPhoneNumber());
        ps.setInt(5,customer.getFirst_levelD());
        ps.executeUpdate();
    } */



    ////////////////////////////////////////////////////Time conversions////////////////////////////////////////////////////////

    public static LocalDateTime UtcToLocalZoned(Timestamp dateTimeDb){
        LocalDateTime local=dateTimeDb.toLocalDateTime();
        return local;
    }

















}
