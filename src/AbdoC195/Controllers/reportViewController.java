package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Appointment;
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
    public ComboBox reportViewContactComboBoxStat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DbHelper.getAppointmentForReport();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        reportsViewTableView.setItems(DbHelper.reportApps);
        reportsViewTypeClmn.setCellValueFactory(new PropertyValueFactory<>("type"));
        reportsViewMonthClmn.setCellValueFactory(new PropertyValueFactory<>("month"));
        reportsViewCountClmn.setCellValueFactory(new PropertyValueFactory<>("count"));

        reportViewContactComboBoxStat.setItems(DbHelper.allContacts);
        reportViewAppTable.setItems(DbHelper.reportApps2);
        reportViewAppointmentIdClmn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        reportViewAppTitleClmn.setCellValueFactory(new PropertyValueFactory<>("title"));
        reportViewAppDesciptionClmn.setCellValueFactory(new PropertyValueFactory<>("description"));
        reportViewAppStartDateClmn.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        reportViewAppEndDateClmn.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        reportViewAppCustomerIdClmn.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }

    public void reportViewContactComboBox(ActionEvent actionEvent) {

    }
}
