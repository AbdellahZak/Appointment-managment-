package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Appointment;
import AbdoC195.Model.Contact;
import AbdoC195.Model.Customer;
import AbdoC195.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class updateAppointmentController implements Initializable {
    Stage stage;
    Parent scene;

    public TextField updateAppViewAppIdTxt;
    public TextField updateAppViewTitleTxt;
    public TextField updateAppViewDescriptionTxt;
    public TextField updateAppViewLocationTxt;
    public ComboBox<Contact> updateAppViewContactComboStat;
    public TextField updateAppViewTypeTxt;
    public DatePicker updateAppViewStartDatePicker;
    public DatePicker updateAppViewEndDatePicker;
    public ComboBox updateAppViewStartHourPickerCombo;
    public ComboBox updateAppViewStartMinutePickerCombo;
    public ComboBox updateAppViewEndHourPickerCombo;
    public ComboBox updateAppViewEndMinutePickerCombo;
    public ComboBox<Customer> updateAppViewCustomerIdComboStat;
    public ComboBox<User> updateAppViewUserIdComboStat;
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

    public void updateAppViewUpdateBtn(ActionEvent actionEvent) throws IOException, SQLException {
        int appointmentId= Integer.parseInt(updateAppViewAppIdTxt.getText());
        String title=updateAppViewTitleTxt.getText();
        String desription=updateAppViewDescriptionTxt.getText();
        String location= updateAppViewLocationTxt.getText();
        Contact contactobject=updateAppViewContactComboStat.getSelectionModel().getSelectedItem();
        int contactId=contactobject.getContactId();
        String type= updateAppViewTypeTxt.getText();
        LocalDate startDate =updateAppViewStartDatePicker.getValue();
        LocalTime startTime= LocalTime.of(Integer.parseInt(updateAppViewStartHourPickerCombo.getValue().toString()),Integer.parseInt(updateAppViewStartMinutePickerCombo.getValue().toString()));
        LocalDateTime start=LocalDateTime.of(startDate,startTime);
        LocalDate endDate =updateAppViewStartDatePicker.getValue();
        LocalTime endTime= LocalTime.of(Integer.parseInt(updateAppViewEndHourPickerCombo.getValue().toString()),Integer.parseInt(updateAppViewEndMinutePickerCombo.getValue().toString()));
        LocalDateTime end=LocalDateTime.of(endDate,endTime);
        Customer customer=updateAppViewCustomerIdComboStat.getSelectionModel().getSelectedItem();
        int customerId=customer.getCustomer_Id();
        User user=updateAppViewUserIdComboStat.getSelectionModel().getSelectedItem();
        int userId=user.getUser_ID();
        if(DbHelper.isWithinBusinessHours(start)==false){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Appointment time error ");
            alert.setContentText(" the appointment time chosen is not within range (8AM-10PM) EST ");
            alert.showAndWait();
            return;
        }
        if(DbHelper.isWithinBusinessHours(end)==false){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Appointment time error ");
            alert.setContentText(" the appointment time chosen is not within range (8AM-10PM) EST ");
            alert.showAndWait();
            return;
        }
        DbHelper.updateAppointmentRowById(new Appointment(appointmentId,title,desription,location,contactId,type,start,end,customerId,userId));
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void updateAppViewCancelBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
