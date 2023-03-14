package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Appointment;
import AbdoC195.Model.Contact;
import AbdoC195.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
/** reports view controller class */

public class reportViewController implements Initializable {
    Stage stage;
    Parent scene;
    /**  */

    public TableView<Appointment> reportsViewTableView;
    /** type column */
    public TableColumn reportsViewTypeClmn;
    /** month column */
    public TableColumn reportsViewMonthClmn;
    /** count column */
    public TableColumn reportsViewCountClmn;
    /** app table */
    public TableView reportViewAppTable;
    /** app id column */
    public TableColumn reportViewAppointmentIdClmn;
    /** app title column */
    public TableColumn reportViewAppTitleClmn;
    /** app description column */
    public TableColumn reportViewAppDesciptionClmn;
    /** app location column */
    public TableColumn reportViewAppLocationClmn;
    /** app type column */
    public TableColumn reportViewAppTypeClmn;
    /** app start date column */
    public TableColumn reportViewAppStartDateClmn;
    /** app end date column */
    public TableColumn reportViewAppEndDateClmn;
    /** app customer id column */
    public TableColumn reportViewAppCustomerIdClmn;
    /** contact combo box */
    public ComboBox<Contact> reportViewContactComboBoxStat;
    /** app user table */
    public TableView<Appointment> reportViewAppUserTable;
    /** app id column */
    public TableColumn reportViewUserAppointmentIdClmn;
    /** app title column */
    public TableColumn reportViewUserAppTitleClmn;
    /** app start date column */
    public TableColumn reportViewUserAppStartDateClmn;
    /** app end date column */
    public TableColumn reportViewUserAppEndDateClmn;
    public ComboBox<User> reportViewUserComboBoxStat;
    /** initialize methode */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DbHelper.allUsers.clear();
        DbHelper.allContacts.clear();
        try {
            DbHelper.getUsersDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        reportViewUserComboBoxStat.setItems(DbHelper.allUsers);
        try {
            DbHelper.getContactsDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        reportViewContactComboBoxStat.setItems(DbHelper.allContacts);
        try {
            DbHelper.getAppointmentForReport();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        reportsViewTableView.setItems(DbHelper.reportApps);
        reportsViewTypeClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportsViewMonthClmn.setCellValueFactory(new PropertyValueFactory<>("month"));
        reportsViewCountClmn.setCellValueFactory(new PropertyValueFactory<>("count"));

        reportViewAppTable.setItems(DbHelper.reportApps2);
        reportViewAppointmentIdClmn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        reportViewAppTitleClmn.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportViewAppDesciptionClmn.setCellValueFactory(new PropertyValueFactory<>("description"));
        reportViewAppStartDateClmn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        reportViewAppEndDateClmn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        reportViewAppCustomerIdClmn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        reportViewAppTypeClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportViewAppLocationClmn.setCellValueFactory(new PropertyValueFactory<>("location"));

        reportViewAppUserTable.setItems(DbHelper.reportApps3);
        reportViewUserAppointmentIdClmn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        reportViewUserAppTitleClmn.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportViewUserAppStartDateClmn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        reportViewUserAppEndDateClmn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
    }
    /** contact combo box action event */

    public void reportViewContactComboBox(ActionEvent actionEvent) {
        DbHelper.reportApps2.clear();
        Contact contact=reportViewContactComboBoxStat.getSelectionModel().getSelectedItem();
        DbHelper.getAppointmentForReport2(contact);
    }
    /** user combo box action event */

    public void reportViewUserComboBox(ActionEvent actionEvent) {
        DbHelper.reportApps3.clear();
        User user=reportViewUserComboBoxStat.getSelectionModel().getSelectedItem();
        DbHelper.getAppointmentForReport3(user);
    }
    /** cancel button action event */

    public void reportsViewCancelBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
