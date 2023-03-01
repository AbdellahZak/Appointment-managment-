package AbdoC195.Controllers;

import AbdoC195.Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
        directoryViewAppTable.setItems(DirectoryController.getCustomers()); // filling tableView
        mainViewPartIdClmn.setCellValueFactory(new PropertyValueFactory<>("id"));  //Javafx makes the i capital I and add a get before Id
        mainViewPartnamedClmn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainViewPartInvClmn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainViewPartPriceClmn.setCellValueFactory(new PropertyValueFactory<>("price"));
        mainViewProductIdClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        mainViewProductnameClmn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mainViewProductInvClmn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mainViewProductPriceClmn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
