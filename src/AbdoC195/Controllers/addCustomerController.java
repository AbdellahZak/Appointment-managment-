package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static AbdoC195.DB.DbHelper.getCountriesDb;

public class addCustomerController implements Initializable {
    public ComboBox addCustomerViewCountriesComboStat;
    public ComboBox addCustomerViewStateComboStat;
    public TextField addCustomerViewNameTxt;
    public TextField addCustomerViewAddressTxt;
    public TextField addCustomerViewPostalCodeTxt;
    public TextField addCustomerViewPhoneNumberTxt;

    public void addCustomerViewCountriesCombo(ActionEvent actionEvent) {
    }
    public void addCustomerViewStatesCombo(ActionEvent actionEvent) {
    }
    public void addCustomerViewAddBtn(ActionEvent actionEvent) {
    }
    public void addCustomerViewCancelBtn(ActionEvent actionEvent) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addCustomerViewCountriesComboStat.setPromptText("PLease select a country");
        try {
            addCustomerViewCountriesComboStat.setItems(getCountriesDb().get);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addCustomerViewStateComboStat.setPromptText("please select a state");

    }

}
