package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Appointment;
import AbdoC195.Model.Countries;
import AbdoC195.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Formatter;
import java.util.ResourceBundle;
/** Controller for directory view */
public class DirectoryController implements Initializable {

    Stage stage;
    Parent scene;
    /** Customer table */
    public TableView<Customer> directoryViewCustomerTable;
    /** customer id column */
    public TableColumn<Customer,Integer> directoryViewCustomerIdClmn;
    /** customer name column */
    public TableColumn<Customer,String> directoryViewCustomerNameClmn;
    /** customer address column */
    public TableColumn<Customer,String> directoryViewCustomerAddressClmn;
    /** postal code column */
    public TableColumn<Customer,String> directoryViewCustomerPostalCodeClmn;
    /** phone number column */
    public TableColumn<Customer,String> directoryViewCustomerPhoneNumberClmn;
    /** state is column */
    public TableColumn<Customer,String> directoryViewCustomerFirstLevelDClmn;
    /** appointment table */
    public TableView<Appointment> directoryViewAppTable;
    /** app id column */
    public TableColumn directoryViewAppointmentIdClmn;
    /** app title column */
    public TableColumn directoryViewAppTitleClmn;
    /** app description column */
    public TableColumn directoryViewAppDesciptionClmn;
    /** app location column */
    public TableColumn directoryViewAppLocationClmn;
    /** app location column */
    public TableColumn directoryViewAppContactClmn;
    /** app type column */
    public TableColumn directoryViewAppTypeClmn;
    /** app start date time column */
    public TableColumn directoryViewAppStartDateClmn;
    /** app end date time column */
    public TableColumn directoryViewAppEndDateClmn;
    public TableColumn directoryViewAppCustomerIdClmn;
    public TableColumn directoryViewAppUserIdClmn;


    /** customer add button action event */
    public void directoryViewCustomerAddButton(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** customer delete button action event */
    public void directoryViewCustomerDeleteButton(ActionEvent actionEvent) throws SQLException {
        Customer customer= directoryViewCustomerTable.getSelectionModel().getSelectedItem();
        for(Appointment appointment: DbHelper.allAppointments){
            if(appointment.getCustomerId()==customer.getCustomer_Id()){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer deletion unsuccessful");
                alert.setContentText("Customer has pending appointments");
                alert.showAndWait();
                return;
                }
            }
        DbHelper.allCustomers.remove(customer);
        DbHelper.deleteCustomerByIdDb(customer.getCustomer_Id());
        }
    /** cutomer update button action event */
    public void directoryViewCustomerUpdateButton(ActionEvent actionEvent) throws IOException, SQLException {
        try {
            FXMLLoader loader= new FXMLLoader();    //passing selecxted object when modify button is clicked to mod part view
            loader.setLocation(getClass().getResource("/AbdoC195/Views/modifyCustomer.fxml"));
            Parent modPartMenu =loader.load();
            Scene scene =new Scene(modPartMenu);
            modifyCustomerController pass=loader.getController();
            pass.passCustomer(directoryViewCustomerTable.getSelectionModel().getSelectedItem());
            stage= (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Selection Error ");
            alert.setContentText(" No customers selected");
            alert.showAndWait();
        }

    }



    /** app add button action event */
    public void directoryViewAppAddButton(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/addAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** app delete button action event */
    public void directoryViewAppDeleteButton(ActionEvent actionEvent) throws SQLException {
        Appointment appointment= directoryViewAppTable.getSelectionModel().getSelectedItem();
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cancel Appointment");
        alert.setContentText("You will cancelling the selected appointment, are you sure?");
        alert.showAndWait();
        DbHelper.allAppointments.remove(appointment);
        DbHelper.deleteAppointmentByIdDb(appointment.getAppointmentId());
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(" Appointment Canceled ");
        alert.setContentText(" Cancelling Appointment ID: "+appointment.getAppointmentId()+" Type: "+appointment.getType());
        alert.showAndWait();
    }
    /** app update button action event */
    public void directoryViewAppUpdateButton(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader= new FXMLLoader();    //passing selecxted object when modify button is clicked to mod part view
            loader.setLocation(getClass().getResource("/AbdoC195/Views/updateAppointment.fxml"));
            Parent modPartMenu =loader.load();
            Scene scene =new Scene(modPartMenu);
            updateAppointmentController pass=loader.getController();
            pass.passAppointment(directoryViewAppTable.getSelectionModel().getSelectedItem());
            stage= (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Selection Error ");
            alert.setContentText(" No appointments selected");
            alert.showAndWait();
        }

    }
    /** all app radio button action event */
    public void directoryViewAppAllRadioBtn(ActionEvent actionEvent) {
        directoryViewAppTable.setItems(DbHelper.allAppointments);

    }
    /** app week radio button action event */
    public void directoryViewAppWeekRadioBtn(ActionEvent actionEvent) {
        ObservableList<Appointment> appointmentsWithinWeek= FXCollections.observableArrayList();
        LocalDateTime localNow=LocalDateTime.now();
        LocalDateTime plusHalfWeek=localNow.plus(84, ChronoUnit.HOURS);
        LocalDateTime minusHalfWeek=localNow.minus(84,ChronoUnit.HOURS);
        for(Appointment appointment: DbHelper.allAppointments){
            if(appointment.getStartDateTime().isAfter(minusHalfWeek)&& appointment.getStartDateTime().isBefore(plusHalfWeek)){
                appointmentsWithinWeek.add(appointment);
            }
        }
    directoryViewAppTable.setItems(appointmentsWithinWeek);
    }
    /** app month radio button action event */
    public void directoryViewAppAMonthRadioBtn(ActionEvent actionEvent) {
        ObservableList<Appointment> appointmentsWithinMonth= FXCollections.observableArrayList();
        LocalDateTime localNow=LocalDateTime.now();
        LocalDateTime plusHalfMonth=localNow.plus(365, ChronoUnit.HOURS);
        LocalDateTime minusHalfMonth=localNow.minus(365,ChronoUnit.HOURS);
        for(Appointment appointment: DbHelper.allAppointments){
            if(appointment.getStartDateTime().isAfter(minusHalfMonth)&& appointment.getStartDateTime().isBefore(plusHalfMonth)){
                appointmentsWithinMonth.add(appointment);
            }
        }
        directoryViewAppTable.setItems(appointmentsWithinMonth);
    }


    /** class initiliaze methode */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ///////////customer Table Init
        DbHelper.allCustomers.clear();
        try {
            DbHelper.getCustomersDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        directoryViewCustomerTable.setItems(DbHelper.allCustomers);// filling tableVie
        directoryViewCustomerIdClmn.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
        directoryViewCustomerNameClmn.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        directoryViewCustomerAddressClmn.setCellValueFactory(new PropertyValueFactory<>("address"));
        directoryViewCustomerPostalCodeClmn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        directoryViewCustomerPhoneNumberClmn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        directoryViewCustomerFirstLevelDClmn.setCellValueFactory(new PropertyValueFactory<>("first_levelD"));
        /////////////Appointment Table init
        DbHelper.allAppointments.clear();
        try {
            DbHelper.getAppointmentDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        directoryViewAppTable.setItems(DbHelper.allAppointments);// filling tableVie
        directoryViewAppointmentIdClmn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        directoryViewAppTitleClmn.setCellValueFactory(new PropertyValueFactory<>("title"));
        directoryViewAppDesciptionClmn.setCellValueFactory(new PropertyValueFactory<>("description"));
        directoryViewAppLocationClmn.setCellValueFactory(new PropertyValueFactory<>("location"));
        directoryViewAppContactClmn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        directoryViewAppTypeClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        directoryViewAppStartDateClmn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        directoryViewAppEndDateClmn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        directoryViewAppCustomerIdClmn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        directoryViewAppUserIdClmn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        // alert

        if(DbHelper.isAppWithin15()!=null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" Appointment Alert ");
            alert.setContentText(" An appointment ID "+DbHelper.isAppWithin15().getAppointmentId()+" is scheduled for "+DbHelper.isAppWithin15().getStartDateTime());
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(" Appointment Alert ");
            alert.setContentText(" No appointments scheduled within 15 minutes");
            alert.showAndWait();
        }

    }

    /** reports button */
    public void directoryViewAppReportsButton(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/reportsView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
