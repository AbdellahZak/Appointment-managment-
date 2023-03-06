package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {
    public TextField addAppViewAppIdTxt;
    public TextField addAppViewTitleTxt;
    public TextField addAppViewDescriptionTxt;
    public TextField addAppViewLocationTxt;
    public TextField addAppViewTypeTxt;
    public ComboBox addAppViewContactComboStat;
    public ComboBox addAppViewStartTimeComboStat;
    public ComboBox addAppViewEndTimeComboStat;
    public TextField addAppViewCustomerIdTxt;
    public TextField addAppViewUserIdTxt;
    public DatePicker addAppViewStartDatePicker;
    public DatePicker addAppViewEndDatePicker;

    public void addAppViewStartTimeCombo(ActionEvent actionEvent) {
    }

    public void addAppViewEndTimeCombo(ActionEvent actionEvent) {
    }

    public void addAppViewContactComboStat(ActionEvent actionEvent) {
    }

    public void addAppViewAddBtn(ActionEvent actionEvent) {
    }

    public void addAppViewCancelBtn(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addAppViewContactComboStat.setItems(DbHelper.getContactsDb());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
