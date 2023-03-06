package AbdoC195.Controllers;

import AbdoC195.Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class updateAppointmentController implements Initializable {

    public TextField updateAppViewAppIdTxt;
    public TextField updateAppViewTitleTxt;
    public TextField updateAppViewDescriptionTxt;
    public TextField updateAppViewLocationTxt;
    public ComboBox updateAppViewContactComboStat;
    public TextField updateAppViewTypeTxt;
    public DatePicker updateAppViewStartDatePicker;
    public DatePicker updateAppViewEndDatePicker;
    public ComboBox updateAppViewCustomerIdComboStat;
    public ComboBox updateAppViewUserIdComboStat;

    public void passAppointment(Appointment appointment){

    }
    public void updateAppViewAddBtn(ActionEvent actionEvent) {
    }

    public void updateAppViewCancelBtn(ActionEvent actionEvent) {
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
