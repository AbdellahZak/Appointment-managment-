package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/** login view controller class   */

public class ControllerMainLogin implements Initializable {
    Stage stage;
    Parent scene;
    /** log in label */

    public Label loginScreenLogingIdLbl;
    /** log in label */
    public Label loginScreenPasswordLbl;
    /** pass label */
    public TextField loginScreenLogingIdTxtFieldStat;
    /** log in field */
    public TextField loginScreenPasswordTxtFieldStat;
    /** pass field */
    public TextField mainViewTimeZoneTxtField;
    /** timezone text */
    public Button loginScreenEnterBtnStat;
    /** enter button */
    public Label loginScreenZoneIdLbl;

    public void loginScreenLogingIdTxtField(ActionEvent actionEvent) throws SQLException {

    }

    public void loginScreenPasswordTxtField(ActionEvent actionEvent) throws SQLException {

    }
    /** initialize methode */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zoneId = ZoneId.systemDefault();  //detcting default Zone Id
        mainViewTimeZoneTxtField.setText(zoneId.getId());
        try {
        ResourceBundle resBundle= ResourceBundle.getBundle("AbdoC195/Res", Locale.getDefault()); //detecting default language
        if(Locale.getDefault().getLanguage().equals("fr")){
            loginScreenZoneIdLbl.setText(resBundle.getString("ZoneId"));
            loginScreenEnterBtnStat.setText(resBundle.getString("Enter"));
            loginScreenLogingIdLbl.setText(resBundle.getString("LoginID"));
            loginScreenPasswordLbl.setText(resBundle.getString("Password"));
        }
       }catch (MissingResourceException e){
         }

    }
    /** enter button action event */

    public void loginScreenEnterBtn(ActionEvent actionEvent) throws IOException, SQLException {
        String userName= loginScreenLogingIdTxtFieldStat.getText();
        String password= loginScreenPasswordTxtFieldStat.getText();
        if(DbHelper.VerifyNameAndPassword(userName,password)==true){
            stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }else{
            if(Locale.getDefault().getLanguage().equals("fr")){
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("le login ou le mot de pass est errone");
                alert.setContentText("essayer une autre fois STP");
                alert.showAndWait();
            }else{
                Alert alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User Id or password is wrong");
                alert.setContentText("please retry again");
                alert.showAndWait();}
        };

    }
}
