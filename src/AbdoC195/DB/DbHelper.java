package AbdoC195.DB;

import AbdoC195.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public abstract class DbHelper {
    public static int nextCustomerId = 3;

    //////////////////////////////////////////////User///////////////////////////////////////////////

    public static ObservableList<User> allUsers= FXCollections.observableArrayList();

    public static ObservableList<User> getUsers(){
        return allUsers;
    }

    public static void addUserList(User user){
        allUsers.add(user);
    }

    public static void getUsersDb() throws SQLException {
        String sql="SELECT User_ID, User_Name,Password FROM users";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        User user = null;
        while (rs.next()){
            int userId= rs.getInt("User_ID");
            String userName= rs.getString("User_Name");
            String password= rs.getString("Password");
            addUserList(new User(userId, userName,password));
        }
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

   public static void addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO APPOintments (Title,Description,Location,Type,Contact_ID,Start, End, Customer_ID, User_ID ) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setString(1,appointment.getTitle());
        ps.setString(2,appointment.getDescription());
        ps.setString(3,appointment.getLocation());
        ps.setString(4,appointment.getType());
        ps.setInt(5,appointment.getContactId());
        LocalDateTime localStart=appointment.getStartDateTime();
        Timestamp startTimeDate= LocalToTimestamp(localStart);
        ps.setTimestamp(6,startTimeDate);
        LocalDateTime localEnd=appointment.getEndDateTime();
        Timestamp endTimeDate= LocalToTimestamp(localEnd);
        ps.setTimestamp(7,endTimeDate);
        ps.setInt(8,appointment.getCustomerId());
        ps.setInt(9,appointment.getUserId());
        ps.executeUpdate();
    }

    public static void getAppointmentDb() throws SQLException {
        String sql="SELECT Appointment_ID, Title, Description, Location,contacts.Contact_Name, Type, Start, End,Customer_ID,User_ID\n" +
                "FROM appointments\n" +
                "inner join contacts ON appointments.Contact_ID = contacts.Contact_ID";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            int appointment_Id= rs.getInt("Appointment_ID");
            String title= rs.getString("Title");
            String description= rs.getString("Description");
            String location= rs.getString("Location");
            String contact=rs.getString("Contact_Name");
            String type= rs.getString("Type");
            Timestamp startTimeStamp =rs.getTimestamp("Start");
            LocalDateTime start =UtcToLocalZoned(startTimeStamp);
            Timestamp endTimeStamp =rs.getTimestamp("End");
            LocalDateTime end=UtcToLocalZoned(endTimeStamp);
            int customerId=rs.getInt("Customer_ID");
            int userId=rs.getInt("User_ID");
            addAppointmentList (new Appointment(appointment_Id,title,description,location,contact,type,start,end,customerId,userId));
        }
    }

    public static void deleteAppointmentByIdDb(int customerId) throws SQLException {
        String sql="DELETE FROM appointments WHERE Appointment_ID=?";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setInt(1,customerId);
        ps.executeUpdate();
    }
  public static void updateAppointmentRowById(Appointment appointment) throws SQLException {
      String sql = "UPDATE appointments SET Title=?,Description=?,Location=?,Type=?,Start=?, End=?,Contact_ID=?,Customer_ID=?,User_ID=? WHERE Appointment_ID=?";
      PreparedStatement ps= JDBC.connection.prepareStatement(sql);
      ps.setInt(10,appointment.getAppointmentId());
      ps.setString(1,appointment.getTitle());
      ps.setString(2,appointment.getDescription());
      ps.setString(3,appointment.getLocation());
      ps.setString(4,appointment.getType());
      ps.setTimestamp(5, Timestamp.valueOf(appointment.getStartDateTime()));
      ps.setTimestamp(6,Timestamp.valueOf(appointment.getEndDateTime()));
      ps.setInt(7,appointment.getContactId());
      ps.setInt(8, appointment.getCustomerId());
      ps.setInt(9,appointment.getUserId());
      ps.executeUpdate();
  }
    /////////////////////////////////////////////////////Contact//////////////////////////////////////////////////////////////
    public static ObservableList<Contact> allContacts= FXCollections.observableArrayList();

    public static ObservableList<Contact> getContacts(){
        return allContacts;
    }

    public static void addContact(Contact contact){
        allContacts.add(contact);
    }

    public static ObservableList<Contact> getContactsDb() throws SQLException {
        String sql="SELECT Contact_ID, Contact_Name, Email FROM contacts";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        Contact contact = null;
        while (rs.next()){
            int contact_Id= rs.getInt("Contact_ID");
            String contactName= rs.getString("Contact_Name");
            String email= rs.getString("Email");
            addContact(new Contact(contact_Id, contactName,email));
        }
        return getContacts();
    }

    ////////////////////////////////////////////////////Time conversions////////////////////////////////////////////////////////
    //timestamp to localdatetime
    public static LocalDateTime UtcToLocalZoned(Timestamp dateTimeDb){
        LocalDateTime local=dateTimeDb.toLocalDateTime();
        return local;
    }
    //localdatetime to timestamp
    public static Timestamp LocalToTimestamp(LocalDateTime localDateTime){
        Timestamp time=Timestamp.valueOf(localDateTime);
        return time;
    }
    public static Boolean isWithinBusinessHours(LocalDateTime localDateTime){
        Boolean result=false;
        LocalDate localeDate=localDateTime.toLocalDate();
        LocalTime localTime8Am=LocalTime.of(8,0);
        LocalTime localTime10Pm=LocalTime.of(22,0);
        ZoneId zoneIdEst=ZoneId.of("America/New_York");
        LocalDateTime localstart=LocalDateTime.of(localeDate,localTime8Am);  //local date time for start and end of business hours
        LocalDateTime localend=LocalDateTime.of(localeDate,localTime10Pm);
        ZonedDateTime zonedEstStartTime=localstart.atZone(zoneIdEst);
        ZonedDateTime zonedEstEndTime=localend.atZone(zoneIdEst);
        ZonedDateTime zonedUserStartTime=zonedEstStartTime.withZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime zonedUserEndTime=zonedEstEndTime.withZoneSameInstant(ZoneId.systemDefault());
        LocalDateTime start=zonedUserStartTime.toLocalDateTime();
        LocalDateTime end=zonedUserEndTime.toLocalDateTime();
        if(localDateTime.isAfter(start)&&(localDateTime.isBefore(end))){
            result= true;
        }else{result= false;}
        return result;
    }

    ////////////////////////////////////////////////// Overlap ///////////////////////////////////////////////////////////////
    public static boolean LookForOverlap(Appointment appointment){
        LocalDateTime a=appointment.getStartDateTime();
        LocalDateTime b=appointment.getEndDateTime();
        boolean result=true;
        for(Appointment appointment1: allAppointments){
            if((appointment1.getCustomerId()==appointment.getCustomerId())){
                LocalDateTime c=appointment1.getStartDateTime();
                LocalDateTime d=appointment1.getEndDateTime();
                if((a.isAfter(c)||a.isEqual(c))&&(a.isBefore(d))){ result= false;}
                if(b.isAfter(c)&&(b.isBefore(d)||b.isEqual(d))){result= false;}
                if((a.isBefore(c)||a.isEqual(c))&&((b.isAfter(d))||(b.isEqual(d)))){result= false;}
            }
        }
        return result;
    }

    ///////////////////////////////////////////////////////15 min appointment alert///////////////////////////////////////
    public static Appointment isAppWithin15 (){
        LocalDateTime now=LocalDateTime.now();
        Appointment retAppointment = null;
        LocalDateTime after15=now.plus(15, ChronoUnit.MINUTES);
        for(Appointment appointment: allAppointments){
            LocalDateTime a=appointment.getStartDateTime();
            if((a.isAfter(now)||a.isEqual(now))&&(a.isBefore(after15)||(a.isEqual(after15)))){
                retAppointment=appointment;
            }
        }
        return retAppointment;
    }










}
