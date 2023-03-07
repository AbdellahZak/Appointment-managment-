package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Appointment;
import AbdoC195.Model.Countries;
import AbdoC195.Model.Customer;
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
import java.util.ResourceBundle;

public class DirectoryController implements Initializable {

    Stage stage;
    Parent scene;
    public TableView<Customer> directoryViewCustomerTable;
    public TableColumn<Customer,Integer> directoryViewCustomerIdClmn;
    public TableColumn<Customer,String> directoryViewCustomerNameClmn;
    public TableColumn<Customer,String> directoryViewCustomerAddressClmn;
    public TableColumn<Customer,String> directoryViewCustomerPostalCodeClmn;
    public TableColumn<Customer,String> directoryViewCustomerPhoneNumberClmn;
    public TableColumn<Customer,String> directoryViewCustomerFirstLevelDClmn;
    public TableView<Appointment> directoryViewAppTable;
    public TableColumn directoryViewAppointmentIdClmn;
    public TableColumn directoryViewAppTitleClmn;
    public TableColumn directoryViewAppDesciptionClmn;
    public TableColumn directoryViewAppLocationClmn;
    public TableColumn directoryViewAppContactClmn;
    public TableColumn directoryViewAppTypeClmn;
    public TableColumn directoryViewAppStartDateClmn;
    public TableColumn directoryViewAppEndDateClmn;
    public TableColumn directoryViewAppCustomerIdClmn;
    public TableColumn directoryViewAppUserIdClmn;



    public void directoryViewCustomerAddButton(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/addCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void directoryViewCustomerDeleteButton(ActionEvent actionEvent) throws SQLException {
        Customer customer= directoryViewCustomerTable.getSelectionModel().getSelectedItem();
        for(Appointment appointment: DbHelper.allAppointments){
            if(appointment.getCustomerId()==customer.getCustomer_Id()){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Customer deletion unssucceful");
                alert.setContentText("Customer has pending appointments");
                alert.showAndWait();
                return;
                }
            }
        DbHelper.allCustomers.remove(customer);
        DbHelper.deleteCustomerByIdDb(customer.getCustomer_Id());
        }

    public void directoryViewCustomerUpdateButton(ActionEvent actionEvent) throws IOException {
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




    public void directoryViewAppAddButton(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/addAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void directoryViewAppDeleteButton(ActionEvent actionEvent) throws SQLException {
        Appointment appointment= directoryViewAppTable.getSelectionModel().getSelectedItem();
        Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Cancel Appointment");
        alert.setContentText("You will cancelling the selected appointment, are you sure?");
        alert.showAndWait();
        DbHelper.allAppointments.remove(appointment);
        DbHelper.deleteAppointmentByIdDb(appointment.getAppointmentId());
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Appointment Cancele ");
        alert.setContentText("Cancelling Appointment ID: "+appointment.getAppointmentId()+" Type: "+appointment.getType());
        alert.showAndWait();
    }

    public void directoryViewAppUpdateButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader= new FXMLLoader();    //passing selecxted object when modify button is clicked to mod part view
        loader.setLocation(getClass().getResource("/AbdoC195/Views/updateAppointment.fxml"));
        Parent modPartMenu =loader.load();
        Scene scene =new Scene(modPartMenu);
        updateAppointmentController pass=loader.getController();
        pass.passAppointment(directoryViewAppTable.getSelectionModel().getSelectedItem());
        stage= (Stage)((Button) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void directoryViewAppAllRadioBtn(ActionEvent actionEvent) {
    }
    public void directoryViewAppWeekRadioBtn(ActionEvent actionEvent) {
    }
    public void directoryViewAppAMonthRadioBtn(ActionEvent actionEvent) {
    }



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


    }



}
