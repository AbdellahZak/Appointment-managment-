package AbdoC195.Controllers;

import AbdoC195.Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DirectoryController implements Initializable {
    public TableView<Customer> directoryViewCustomerTable;
    public TableColumn<Customer,Integer> directoryViewCustomerIdClmn;
    public TableColumn<Customer,String> directoryViewCustomerNameClmn;
    public TableColumn<Customer,String> directoryViewCustomerAddressClmn;
    public TableColumn<Customer,String> directoryViewCustomerPostalCodeClmn;
    public TableColumn<Customer,String> directoryViewCustomerPhoneNumberClmn;
    public TableColumn<Customer,String> directoryViewCustomerFirstLevelDClmn;
    public TableColumn<Customer,String> directoryViewCutomerCountryClmn;
    public TableView directoryViewAppTable;
    public TableColumn directoryViewAppointmentIdClmn;
    public TableColumn directoryViewAppTitleClmn;
    public TableColumn directoryViewAppLocationClmn;
    public TableColumn directoryViewAppContactClmn;
    public TableColumn directoryViewAppTypeClmn;
    public TableColumn directoryViewAppStartDateClmn;
    public TableColumn directoryViewAppEndDateClmn;
    public TableColumn directoryViewAppCustomerIdClmn;
    public TableColumn directoryViewAppUserIdClmn;



    public void directoryViewCustomerAddButton(ActionEvent actionEvent) {
    }

    public void directoryViewCustomerDeleteButton(ActionEvent actionEvent) {
    }

    public void directoryViewCustomerUpdateButton(ActionEvent actionEvent) {
    }




    public void directoryViewAppAddButton(ActionEvent actionEvent) {
    }

    public void directoryViewAppDeleteButton(ActionEvent actionEvent) {
    }

    public void directoryViewAppUpdateButton(ActionEvent actionEvent) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        directoryViewCustomerTable.setItems(ControllerHelper.getCustomers()); // filling tableView
        directoryViewCustomerIdClmn.setCellValueFactory(new PropertyValueFactory<>("customer_Id"));
        directoryViewCustomerNameClmn.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        directoryViewCustomerAddressClmn.setCellValueFactory(new PropertyValueFactory<>("address"));
        directoryViewCustomerPostalCodeClmn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        directoryViewCustomerPhoneNumberClmn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        directoryViewCustomerFirstLevelDClmn.setCellValueFactory(new PropertyValueFactory<>("first_levelD"));
        directoryViewCutomerCountryClmn.setCellValueFactory(new PropertyValueFactory<>("country"));

    }
}
