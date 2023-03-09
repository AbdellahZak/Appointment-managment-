package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Appointment;
import AbdoC195.Model.Contact;
import AbdoC195.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class reportViewController implements Initializable {
    public TableView<Appointment> reportsViewTableView;
    public TableColumn reportsViewTypeClmn;
    public TableColumn reportsViewMonthClmn;
    public TableColumn reportsViewCountClmn;
    public TableView reportViewAppTable;
    public TableColumn reportViewAppointmentIdClmn;
    public TableColumn reportViewAppTitleClmn;
    public TableColumn reportViewAppDesciptionClmn;
    public TableColumn reportViewAppLocationClmn;
    public TableColumn reportViewAppTypeClmn;
    public TableColumn reportViewAppStartDateClmn;
    public TableColumn reportViewAppEndDateClmn;
    public TableColumn reportViewAppCustomerIdClmn;
    public ComboBox<Contact> reportViewContactComboBoxStat;
    public TableView<Appointment> reportViewAppUserTable;
    public TableColumn reportViewUserAppointmentIdClmn;
    public TableColumn reportViewUserAppTitleClmn;
    public TableColumn reportViewUserAppStartDateClmn;
    public TableColumn reportViewUserAppEndDateClmn;
    public ComboBox<User> reportViewUserComboBoxStat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    public void reportViewContactComboBox(ActionEvent actionEvent) {
        DbHelper.reportApps2.clear();
        Contact contact=reportViewContactComboBoxStat.getSelectionModel().getSelectedItem();
        DbHelper.getAppointmentForReport2(contact);
    }

    public void reportViewUserComboBox(ActionEvent actionEvent) {
        DbHelper.reportApps3.clear();
        User user=reportViewUserComboBoxStat.getSelectionModel().getSelectedItem();
        DbHelper.getAppointmentForReport3(user);
    }
}
