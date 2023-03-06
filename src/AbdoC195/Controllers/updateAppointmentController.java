package AbdoC195.Controllers;

import AbdoC195.Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class updateAppointmentController implements Initializable {
    Stage stage;
    Parent scene;

    public TextField updateAppViewAppIdTxt;
    public TextField updateAppViewTitleTxt;
    public TextField updateAppViewDescriptionTxt;
    public TextField updateAppViewLocationTxt;
    public ComboBox updateAppViewContactComboStat;
    public TextField updateAppViewTypeTxt;
    public DatePicker updateAppViewStartDatePicker;
    public DatePicker updateAppViewEndDatePicker;
    public ComboBox updateAppViewStartHourPickerCombo;
    public ComboBox updateAppViewStartMinutePickerCombo;
    public ComboBox updateAppViewEndHourPickerCombo;
    public ComboBox updateAppViewEndMinutePickerCombo;
    public ComboBox updateAppViewCustomerIdComboStat;
    public ComboBox updateAppViewUserIdComboStat;

    public void passAppointment(Appointment appointment){
        updateAppViewAppIdTxt.setText(Integer.toString(appointment.getAppointmentId()));
        updateAppViewTitleTxt.setText(appointment.getTitle());
        updateAppViewDescriptionTxt.setText(appointment.getDescription());
        updateAppViewLocationTxt.setText(appointment.getLocation());
        updateAppViewContactComboStat.setPromptText(appointment.getContact());
        updateAppViewTypeTxt.setText(appointment.getType());
        updateAppViewCustomerIdComboStat.setPromptText(Integer.toString(appointment.getCustomerId()));
        updateAppViewUserIdComboStat.setPromptText(Integer.toString(appointment.getUserId()));

    }
    public void updateAppViewAddBtn(ActionEvent actionEvent) {
    }

    public void updateAppViewCancelBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
