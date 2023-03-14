package AbdoC195.Controllers;

import AbdoC195.DB.DbHelper;
import AbdoC195.Model.Countries;
import AbdoC195.Model.Customer;
import AbdoC195.Model.Divison;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static AbdoC195.DB.DbHelper.*;
/** customer add controller */

public class addCustomerController implements Initializable {
    /** alert text field */

    public Text addCustomerTextArea;
    Stage stage;
    Parent scene;
    /** countries combo box */

    public ComboBox<Countries> addCustomerViewCountriesComboStat;
    /** states combo box  */
    public ComboBox<Divison> addCustomerViewStateComboStat;
    /** customer name field */
    public TextField addCustomerViewNameTxt;
    /**  customer address field */
    public TextField addCustomerViewAddressTxt;
    /** address field */
    public TextField addCustomerViewPostalCodeTxt;
    /** postal code field */
    public TextField addCustomerViewPhoneNumberTxt;
    public TextField addCustomerViewIdTxt;
    /** countied combo boc action event */

    public void addCustomerViewCountriesCombo(ActionEvent actionEvent) throws SQLException {

       Countries country= addCustomerViewCountriesComboStat.getSelectionModel().getSelectedItem();
       int countryId= country.getCountry_Id();
       ObservableList<Divison> filteredStatesByCountry =FilterByCountryId(countryId);
       addCustomerViewStateComboStat.setItems(filteredStatesByCountry);
    }
    /** states combo box action event */

    public void addCustomerViewStatesCombo(ActionEvent actionEvent) {

    }
    /** add button action event */

    public void addCustomerViewAddBtn(ActionEvent actionEvent) throws SQLException, IOException {
        int customer_Id=0;
        String customer_Name=addCustomerViewNameTxt.getText();
        String address=addCustomerViewAddressTxt.getText();
        String postalCode=addCustomerViewPostalCodeTxt.getText();
        String phoneNumber=addCustomerViewPhoneNumberTxt.getText();
        if(customer_Name.isBlank()||address.isBlank()||postalCode.isBlank()||phoneNumber.isBlank()){
            addCustomerTextArea.setText(" PLease make sure all text fields are filled. ");
            return;
        }
        try{
            Divison divison= addCustomerViewStateComboStat.getSelectionModel().getSelectedItem();
            int  first_levelD=divison.getDivision_Id();
            Customer customer=new Customer(customer_Id,customer_Name,address,postalCode,phoneNumber,first_levelD);
            addCustomer(customer);}
        catch(NullPointerException e){
            addCustomerTextArea.setText(" PLease make sure the customer's state and country are selected ");
            return;
        }

        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** cancel button action event */

    public void addCustomerViewCancelBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** Class initialize action event */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCountries.clear();
        try {
            DbHelper.getCountriesDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        addCustomerViewCountriesComboStat.setPromptText("PLease select a country");

        addCustomerViewCountriesComboStat.setItems(allCountries);

        addCustomerViewStateComboStat.setPromptText("please select a state");
    }

}
