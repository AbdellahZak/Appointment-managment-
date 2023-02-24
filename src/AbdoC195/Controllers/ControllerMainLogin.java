package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerMainLogin implements Initializable {
    public Label loginScreenLogingIdLbl;
    public Label loginScreenPasswordLbl;
    public TextField loginScreenLogingIdTxtFieldStat;
    public TextField loginScreenPasswordTxtFieldStat;

    public void loginScreenLogingIdTxtField(ActionEvent actionEvent) throws SQLException {
        String userName= loginScreenLogingIdTxtFieldStat.getText();
        String password= loginScreenPasswordTxtFieldStat.getText();
        DbHelper.VerifyNameAndPassword(userName,password);
    }

    public void loginScreenPasswordTxtField(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
