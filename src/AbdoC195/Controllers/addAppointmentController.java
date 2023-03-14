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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
/** Controller for add appointment View */
public class addAppointmentController implements Initializable {
    /** alert text */
    public Text addViewTextArea;
    Stage stage;
    Parent scene;
    /** app id field */
    public TextField addAppViewAppIdTxt;
    /** app title field  */
    public TextField addAppViewTitleTxt;
    /** app description field */
    public TextField addAppViewDescriptionTxt;
    /** app location field */
    public TextField addAppViewLocationTxt;
    /** app type field  */
    public TextField addAppViewTypeTxt;
    /** contact combo */
    public ComboBox<Contact> addAppViewContactComboStat;
    /** start time combo */
    public ComboBox addAppViewStartTimeComboStat;
    /** end time combo */
    public ComboBox addAppViewEndTimeComboStat;
    /** customer id field */
    public TextField addAppViewCustomerIdTxt;
    /** user id txt field  */
    public TextField addAppViewUserIdTxt;
    /** date picker combo */
    public DatePicker addAppViewStartDatePicker;
    /** date picker */
    public DatePicker addAppViewEndDatePicker;
    /** hour combo */
    public ComboBox addAppViewStartHourPickerCombo;
    /** MINUTE combo */
    public ComboBox addAppViewStartMinutePickerCombo;
    public ComboBox addAppViewEndHourPickerCombo;
    public ComboBox addAppViewEndMinutePickerCombo;
    public ComboBox<Customer> addAppViewCustomerIdComboStat;
    public ComboBox<User> addAppViewUserIdComboStat;
    ObservableList<String> hours = FXCollections.observableArrayList(); // from the code repository.
    ObservableList<String> minutes = FXCollections.observableArrayList();


    public void addAppViewStartTimeCombo(ActionEvent actionEvent) {
    }

    public void addAppViewEndTimeCombo(ActionEvent actionEvent) {
    }

    public void addAppViewContactComboStat(ActionEvent actionEvent) {
    }
    /** add button action event */

    public void addAppViewAddBtn(ActionEvent actionEvent) throws IOException, SQLException {
        int appointmentId=0;
        String title=addAppViewTitleTxt.getText();
        String description=addAppViewDescriptionTxt.getText();
        String location =addAppViewLocationTxt.getText();
        String type= addAppViewTypeTxt.getText();
        try{
            LocalDate startDate = addAppViewStartDatePicker.getValue();
            LocalDate endDate = addAppViewEndDatePicker.getValue();
            String startHour = addAppViewStartHourPickerCombo.getValue().toString();
            String startMinute = addAppViewStartMinutePickerCombo.getValue().toString();
            String endHour = addAppViewEndHourPickerCombo.getValue().toString();
            String endMinute = addAppViewEndMinutePickerCombo.getValue().toString();
            LocalDateTime ldtStart = LocalDateTime.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));
            LocalDateTime ldtEnd = LocalDateTime.of(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth(), Integer.parseInt(endHour), Integer.parseInt(endMinute));
            Contact contactObject=addAppViewContactComboStat.getSelectionModel().getSelectedItem();
            int contactId=contactObject.getContactId();
            Customer customerObject=addAppViewCustomerIdComboStat.getSelectionModel().getSelectedItem();
            int customerId=customerObject.getCustomer_Id();
            User userObject=addAppViewUserIdComboStat.getSelectionModel().getSelectedItem();
            int userId=userObject.getUser_ID();
            Appointment appointment= new Appointment(appointmentId,title,description,location,contactId,type,ldtStart,ldtEnd,customerId,userId);
            if (title.isBlank()||description.isBlank()||location.isBlank()||type.isBlank()){
                addViewTextArea.setText("   PLease make sure all text fields are filled ");
                return;
            }
            if(DbHelper.isWithinBusinessHours(ldtStart)==false){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" Appointment time error ");
                alert.setContentText(" the appointment time chosen is not within range (8AM-10PM) EST ");
                alert.showAndWait();
                return;
            }
            if(DbHelper.isWithinBusinessHours(ldtEnd)==false){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" Appointment time error ");
                alert.setContentText(" the appointment time chosen is not within range (8AM-10PM) EST ");
                alert.showAndWait();
                return;
            }
            if(DbHelper.LookForOverlap(appointment)==false){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle(" Overlap ");
                alert.setContentText(" The start and End dates and times chosen contain an overlap with \n previous appointments for this customer. ");
                alert.showAndWait();
                return;
            }
            DbHelper.addAppointment(appointment);
        }catch (NullPointerException e){
            addViewTextArea.setText("   PLease make sure all Users,customers and contacts are selected \n Dates and correct times as well. ");
            return;

        }

        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** cancel button action event */

    public void addAppViewCancelBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** Class initialize methode */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DbHelper.allUsers.clear();
        DbHelper.allContacts.clear();
        try {
            DbHelper.getContactsDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addAppViewContactComboStat.setItems(DbHelper.allContacts);
        addAppViewCustomerIdComboStat.setItems(DbHelper.allCustomers);
        try {
            DbHelper.getUsersDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addAppViewUserIdComboStat.setItems(DbHelper.allUsers);
        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00", "15", "30", "45");
        addAppViewStartHourPickerCombo.setItems(hours);
        addAppViewStartMinutePickerCombo.setItems(minutes);
        addAppViewEndHourPickerCombo.setItems(hours);
        addAppViewEndMinutePickerCombo.setItems(minutes);
    }
}
