package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Appointment;
import javafx.fxml.Initializable;
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
    }
}
