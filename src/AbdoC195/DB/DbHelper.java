package AbdoC195.DB;

import AbdoC195.Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
/** helper class */

public abstract class DbHelper {

    //////////////////////////////////////////////User///////////////////////////////////////////////
    /** users observable list */

    public static ObservableList<User> allUsers= FXCollections.observableArrayList();
    /** add user to obsevable list methode */
    public static void addUserList(User user){
        allUsers.add(user);
    }
    /** get users from db  */

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
    /** verify login details */
    public static boolean VerifyNameAndPassword(String userName,String password) throws SQLException, IOException {
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
            if (Bname==true && Bpass==true){
                FileWriter myWriter = new FileWriter("login_activity.txt",true);
                myWriter.write("\n User: "+userName+ " " +LocalDateTime.now()+ " Attempt Successful");
                myWriter.close();
                return true;}
        }
        FileWriter myWriter = new FileWriter("login_activity.txt",true);
        myWriter.write("\n User: "+userName+ " " +LocalDateTime.now()+ " Attempt unsuccessful");
        myWriter.close();
    return false;
    }
    /** get user by user id */
    public static User getUserById(int userId){
        User user=null;
        for(User user1: allUsers){
            if(user1.getUser_ID()==userId){
                user=user1;
            }
        }
        return user;
    }
////////////////////////////////////////////////Customers////////////////////////////////////////////////
    /**  customers observable list   */
    public static ObservableList<Customer> allCustomers= FXCollections.observableArrayList();
    /** get customers OL */
    public static ObservableList<Customer> getCustomers() throws SQLException {
        return allCustomers;
    }
    /** add to customer observable list  */
    public static void addCustomerList(Customer customer){
        allCustomers.add(customer);
    }
    /** add customer object to db */
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
    /** get customers from db */
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
    /** delete customer by customer id */
    public static void deleteCustomerByIdDb(int customerId) throws SQLException {
        String sql="DELETE FROM CUSTOMERS WHERE Customer_ID=?";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setInt(1,customerId);
        ps.executeUpdate();
    }
    /** update customer */

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
    /**  get customer object */

    public static Customer getCustomerById(int customerId){
        Customer customer=null;
        for(Customer customer1: allCustomers){
            if(customer1.getCustomer_Id()==customerId){
                customer=customer1;
            }
        }
        return customer;
    }

    ///////////////////////////////////////////////Division////////////////////////////////////////////////////////

    /** all divisions/states observable list */

    public static ObservableList<Divison> allDivisions= FXCollections.observableArrayList();
    /** get divisions ol */

    public static ObservableList<Divison> getDivisions(){
        return allDivisions;
    }
    /** add division to ol */

    public static void addDiviosn(Divison division){
        allDivisions.add(division);
    }
    /** get division from db */

    public static void getDivisionsDb() throws SQLException {
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
    }
    /** filter divisions by country
     * lambda expression included in FilterByCountryId */

    public static ObservableList<Divison> FilterByCountryId(int countryId) throws SQLException {
        ObservableList<Divison> namedStates = FXCollections.observableArrayList();
        ObservableList<Divison> allDivisions= DbHelper.getDivisions();

        /*for(Divison division: allDivisions){
            if(division.getCountry_Id()==countryId){
                namedStates.add(division);
            }
        }*/

        allDivisions.forEach(divison -> {
            if(divison.getCountry_Id()==countryId){
                namedStates.add(divison);
            }
        });
        return namedStates;
    }




    /////////////////////////////////////////////countries/////////////////////////////////////////////////////////////////////
    /** countries observable list */

    public static ObservableList<Countries> allCountries= FXCollections.observableArrayList();
    /** get countries ol */

    public static ObservableList<Countries> getCountries(){
        return allCountries;
    }
    /** add countries to observable list */

    public static void addCountry(Countries country){
        allCountries.add(country);
    }
    /** get countries from db */

    public static void getCountriesDb() throws SQLException {
        String sql="SELECT Country_ID, Country FROM countries";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        Customer customer = null;
        while (rs.next()){
            int country_Id= rs.getInt("Country_ID");
            String country= rs.getString("Country");
            addCountry(new Countries(country_Id, country));
        }
    }
    /////////////////////////////////////////////////////Appointment///////////////////////////////////////////
    /** appointment observable list */

    public static ObservableList<Appointment> allAppointments= FXCollections.observableArrayList();
    /** add app to observable list  */

    public static void addAppointmentList(Appointment appointment){
        allAppointments.add(appointment);
    }
    /** add app to db  */

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
    /** get app from db */

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
    /** Delete app from db */

    public static void deleteAppointmentByIdDb(int customerId) throws SQLException {
        String sql="DELETE FROM appointments WHERE Appointment_ID=?";
        PreparedStatement ps= JDBC.connection.prepareStatement(sql);
        ps.setInt(1,customerId);
        ps.executeUpdate();
    }
    /** update app in db */

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

                                                        ///Appointment reports///
    /** report app ol */

    public static ObservableList<Appointment> reportApps= FXCollections.observableArrayList();
    /** add report app to ol */

    public static void addAppointmentreportList(Appointment appointment){
        reportApps.add(appointment);
    }
    /** get report apps from db */

    public static void getAppointmentForReport() throws SQLException {
        String sql="SELECT Type,EXTRACT(MONTH FROM Start) as Month,count(*) as Count\n" +
                "FROM appointments\n" +
                "group by Type, Month";
        PreparedStatement ps=JDBC.connection.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            String type= rs.getString("Type");
            int month=rs.getInt("Month");
            int count=rs.getInt("Count");
            addAppointmentreportList (new Appointment(type,month,count));
        }
    }

    /////////////////////////////////////////////////////Contact//////////////////////////////////////////////////////////////
    /** contacts observable list */
    public static ObservableList<Contact> allContacts= FXCollections.observableArrayList();

    /** get contacts  */
    public static ObservableList<Contact> getContacts(){
        return allContacts;
    }

    /** add contact obj to ol */
    public static void addContact(Contact contact){
        allContacts.add(contact);
    }
    /** get contacts from db */

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

    /** get contact obj by name */
    public static Contact getContactByName(String contactName){
        Contact contact=null;
        for(Contact contact1: allContacts){
            if(contact1.getContactName().equals(contactName)){
                contact=contact1;
            }
        }
        return contact;
    }

    ////////////////////////////////////////////////////Time conversions////////////////////////////////////////////////////////
    /** timestamp to localdatetime */
    public static LocalDateTime UtcToLocalZoned(Timestamp dateTimeDb){
        LocalDateTime local=dateTimeDb.toLocalDateTime();
        return local;
    }
    /** localdatetime to timestamp */
    public static Timestamp LocalToTimestamp(LocalDateTime localDateTime){
        Timestamp time=Timestamp.valueOf(localDateTime);
        return time;
    }
    /** verify local date time is withing est business hours */
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
    /** verify overlap of apps */
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
    /** verify app with 15 min */
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

    /////////////////////////////////////////////////////Reports///////////////////////////////////////////////////////////
    /** app for report observable list */
    public static ObservableList<Appointment> reportApps2= FXCollections.observableArrayList();
    public static void addAppointmentreport2List(Appointment appointment){
        reportApps2.add(appointment);
    }
    /** Lambda to filter by contact name */
    public static void getAppointmentForReport2(Contact contact) {
            /*for(Appointment appointment:allAppointments){
                if(contact.getContactName().equals(appointment.getContact())){
                    addAppointmentreport2List (appointment);
                }
            }*/
            allAppointments.forEach(appointment -> {
                if (contact.getContactName().equals(appointment.getContact())){
                    addAppointmentreport2List (appointment);
                }
            });
    }

    public static ObservableList<Appointment> reportApps3= FXCollections.observableArrayList();
    public static void addAppointmentreport3List(Appointment appointment){
        reportApps3.add(appointment);
    }
    public static void getAppointmentForReport3(User user) {
        for(Appointment appointment:allAppointments){
            if(user.getUser_ID()==appointment.getUserId()){
                addAppointmentreport3List (appointment);
            }
        }
    }
    ///////////////////////////////////////////////////////Combo box auto select/////////////////////////////////////////////
    /** get country object by division id  */

    public static Countries getCountryByDivisonId(int divisionId) throws SQLException {
        String sql = "SELECT Country \n" +
                "FROM countries\n" +
                "inner join first_level_divisions ON countries.Country_ID = first_level_divisions.Country_ID\n" +
                "Where Division_ID=?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionId);
        ResultSet rs = ps.executeQuery();
        Countries country1 = null;
        while (rs.next()) {
            String countryName = rs.getString("Country");
            for (Countries country : allCountries) {
                if (country.getCountry().equals(countryName)) {
                    country1 = country;
                }

            }

        }
        return country1;
    }
    /** get division obj by division id */

    public static Divison getDivisionByDivisonId(int divisionId) throws SQLException {
        String sql = "SELECT Division\n" +
                "FROM first_level_divisions\n" +
                "Where Division_ID=?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, divisionId);
        ResultSet rs = ps.executeQuery();
        Divison divison1 = null;
        while (rs.next()) {
            String divisionName = rs.getString("Division");
            for (Divison divison : allDivisions) {
                if (divison.getDivision().equals(divisionName)) {
                    divison1 = divison;
                }

            }

        }
        return divison1;
    }









}
