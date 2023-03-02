package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Countries;
import AbdoC195.Model.Customer;
import AbdoC195.Model.Divison;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static AbdoC195.DB.DbHelper.*;

public class addCustomerController implements Initializable {
    Stage stage;
    Parent scene;
    public ComboBox<Countries> addCustomerViewCountriesComboStat;
    public ComboBox<Divison> addCustomerViewStateComboStat;
    public TextField addCustomerViewNameTxt;
    public TextField addCustomerViewAddressTxt;
    public TextField addCustomerViewPostalCodeTxt;
    public TextField addCustomerViewPhoneNumberTxt;
    public TextField addCustomerViewIdTxt;

    public void addCustomerViewCountriesCombo(ActionEvent actionEvent) throws SQLException {

       Countries country= addCustomerViewCountriesComboStat.getSelectionModel().getSelectedItem();
       int countryId= country.getCountry_Id();
       ObservableList<Divison> filteredStatesByCountry =FilterByCountryId(countryId);
       addCustomerViewStateComboStat.setItems(filteredStatesByCountry);
    }
    public void addCustomerViewStatesCombo(ActionEvent actionEvent) {

    }
    public void addCustomerViewAddBtn(ActionEvent actionEvent) {
        int customer_Id= ++nextCustomerId;
        String customer_Name=addCustomerViewNameTxt.getText();
        String address=addCustomerViewAddressTxt.getText();
        String postalCode=addCustomerViewPostalCodeTxt.getText();
        String phoneNumber=addCustomerViewPhoneNumberTxt.getText();
        Divison divison= addCustomerViewStateComboStat.getSelectionModel().getSelectedItem();
        int  first_levelD=divison.getDivision_Id();
        addCustomer(new Customer(customer_Id,customer_Name,address,postalCode,phoneNumber,first_levelD));
    }
    public void addCustomerViewCancelBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerViewIdTxt.setText(String.valueOf(nextCustomerId));
        addCustomerViewCountriesComboStat.setPromptText("PLease select a country");
        try {
            addCustomerViewCountriesComboStat.setItems(getCountriesDb());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addCustomerViewStateComboStat.setPromptText("please select a state");
    }

}
