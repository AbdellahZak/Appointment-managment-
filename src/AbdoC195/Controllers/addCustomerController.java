package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Countries;
import AbdoC195.Model.Divison;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static AbdoC195.DB.DbHelper.*;

public class addCustomerController implements Initializable {
    public ComboBox<Countries> addCustomerViewCountriesComboStat;
    public ComboBox<Divison> addCustomerViewStateComboStat;
    public TextField addCustomerViewNameTxt;
    public TextField addCustomerViewAddressTxt;
    public TextField addCustomerViewPostalCodeTxt;
    public TextField addCustomerViewPhoneNumberTxt;

    public void addCustomerViewCountriesCombo(ActionEvent actionEvent) throws SQLException {

       Countries country= addCustomerViewCountriesComboStat.getSelectionModel().getSelectedItem();
       int countryId= country.getCountry_Id();
       ObservableList<Divison> filteredStatesByCountry =FilterByCountryId(countryId);
       addCustomerViewStateComboStat.setItems(filteredStatesByCountry);
    }
    public void addCustomerViewStatesCombo(ActionEvent actionEvent) {
        Divison divison= addCustomerViewStateComboStat.getSelectionModel().getSelectedItem();
        int divisionId=divison.getDivision_Id();
    }
    public void addCustomerViewAddBtn(ActionEvent actionEvent) {

    }
    public void addCustomerViewCancelBtn(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerViewCountriesComboStat.setPromptText("PLease select a country");
        try {
            addCustomerViewCountriesComboStat.setItems(getCountriesDb());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addCustomerViewStateComboStat.setPromptText("please select a state");
    }

}
