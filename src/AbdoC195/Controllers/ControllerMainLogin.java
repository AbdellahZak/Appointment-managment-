package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    }

    public void loginScreenPasswordTxtField(ActionEvent actionEvent) throws SQLException {
        String userName= loginScreenLogingIdTxtFieldStat.getText();
        String password= loginScreenPasswordTxtFieldStat.getText();
        if(DbHelper.VerifyNameAndPassword(userName,password)==true){
            System.out.println("all correct pas and id");
        }else{
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User Id or password is wrong");
            alert.setContentText("please retry again");
            alert.showAndWait();
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
