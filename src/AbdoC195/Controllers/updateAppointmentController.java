package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.SQLException;
import java.time.LocalDate;
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
    ObservableList<String> hours = FXCollections.observableArrayList(); // from the code repository.
    ObservableList<String> minutes = FXCollections.observableArrayList();
    public void passAppointment(Appointment appointment){

        updateAppViewAppIdTxt.setText(Integer.toString(appointment.getAppointmentId()));
        updateAppViewTitleTxt.setText(appointment.getTitle());
        updateAppViewDescriptionTxt.setText(appointment.getDescription());
        updateAppViewLocationTxt.setText(appointment.getLocation());
        updateAppViewContactComboStat.setPromptText(appointment.getContact());
        updateAppViewTypeTxt.setText(appointment.getType());
        updateAppViewCustomerIdComboStat.setPromptText(Integer.toString(appointment.getCustomerId()));
        updateAppViewUserIdComboStat.setPromptText(Integer.toString(appointment.getUserId()));
        LocalDate startDate=appointment.getStartDateTime().toLocalDate();
        LocalDate endDate=appointment.getStartDateTime().toLocalDate();
        updateAppViewStartDatePicker.setPromptText(startDate.toString());
        updateAppViewEndDatePicker.setPromptText(endDate.toString());
        String startHour = String.valueOf(appointment.getStartDateTime().getHour());
        String startMinute = String.valueOf(appointment.getStartDateTime().getMinute());
        String endHour = String.valueOf(appointment.getEndDateTime().getHour());
        String endMinute = String.valueOf(appointment.getEndDateTime().getMinute());
        updateAppViewStartHourPickerCombo.setPromptText(startHour);
        updateAppViewEndHourPickerCombo.setPromptText(endHour);
        updateAppViewStartMinutePickerCombo.setPromptText(startMinute);
        updateAppViewEndMinutePickerCombo.setPromptText(endMinute);

    }

    public void updateAppViewUpdateBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void updateAppViewCancelBtn(ActionEvent actionEvent) throws IOException {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DbHelper.allUsers.clear();
        DbHelper.allContacts.clear();
        try {
            DbHelper.getContactsDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        updateAppViewContactComboStat.setItems(DbHelper.allContacts);
        updateAppViewCustomerIdComboStat.setItems(DbHelper.allCustomers);
        try {
            DbHelper.getUsersDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        updateAppViewUserIdComboStat.setItems(DbHelper.allUsers);
        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00", "15", "30", "45");
        updateAppViewStartHourPickerCombo.setItems(hours);
        updateAppViewStartMinutePickerCombo.setItems(minutes);
        updateAppViewEndHourPickerCombo.setItems(hours);
        updateAppViewEndMinutePickerCombo.setItems(minutes);

    }


}
