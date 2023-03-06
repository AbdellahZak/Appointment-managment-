package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
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
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {
    Stage stage;
    Parent scene;
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
    public ComboBox addAppViewStartHourPickerCombo;
    public ComboBox addAppViewStartMinutePickerCombo;
    public ComboBox addAppViewEndHourPickerCombo;
    public ComboBox addAppViewEndMinutePickerCombo;
    ObservableList<String> hours = FXCollections.observableArrayList(); // from the code repository.
    ObservableList<String> minutes = FXCollections.observableArrayList();


    public void addAppViewStartTimeCombo(ActionEvent actionEvent) {
    }

    public void addAppViewEndTimeCombo(ActionEvent actionEvent) {
    }

    public void addAppViewContactComboStat(ActionEvent actionEvent) {
    }

    public void addAppViewAddBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void addAppViewCancelBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addAppViewContactComboStat.setItems(DbHelper.getContactsDb());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00", "15", "30", "45");
        addAppViewStartHourPickerCombo.setItems(hours);
        addAppViewStartMinutePickerCombo.setItems(minutes);
        addAppViewEndHourPickerCombo.setItems(hours);
        addAppViewEndMinutePickerCombo.setItems(minutes);
    }
}
