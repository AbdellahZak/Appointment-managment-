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
/** customer view controller class */
public class modifyCustomerController implements Initializable {

    /** alert text field  */
    public Text updateCustomerTextArea;
    Stage stage;
    Parent scene;
    /** customer id field */
    public TextField modifyCustomerViewIdTxt;
    /** cust name field */
    public TextField modifyCustomerViewNameTxt;
    /** cust name field */
    public TextField modifyCustomerViewAddressTxt;
    /** postal code field */
    public TextField modifyCustomerViewPostalCodeTxt;
    /** phone number field */
    public TextField modifyCustomerViewPhoneNumberTxt;
    /** country combo box */
    public ComboBox<Countries> modifyCustomerViewCountriesComboStat;
    /** state combo box */
    public ComboBox<Divison> modifyCustomerViewStateComboStat;

    /** modify customer button */
    public void modifyCustomerViewmodifyBtn(ActionEvent actionEvent) throws SQLException, IOException {
        Integer customerId= Integer.parseInt(modifyCustomerViewIdTxt.getText());
        String customerName= modifyCustomerViewNameTxt.getText();
        String customerAdrress=modifyCustomerViewAddressTxt.getText();
        String customerPostalCode= modifyCustomerViewPostalCodeTxt.getText();
        String customerPhoneNumber= modifyCustomerViewPhoneNumberTxt.getText();
        if(customerName.isBlank()||customerAdrress.isBlank()||customerPostalCode.isBlank()||customerPhoneNumber.isBlank()){
            updateCustomerTextArea.setText(" PLease make sure all text fields are filled. ");
            return;
        }
        try{
            int divisionId= modifyCustomerViewStateComboStat.getSelectionModel().getSelectedItem().getDivision_Id();
            DbHelper.updateCustomerRowById(new Customer(customerId,customerName,customerAdrress,customerPostalCode,customerPhoneNumber,divisionId));}
        catch (NullPointerException e){
            updateCustomerTextArea.setText(" PLease make sure the customer's state and country are selected ");
            return;}
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /** cancel button action event */
    public void modifyCustomerViewCancelBtn(ActionEvent actionEvent) throws IOException {
        stage =(Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/AbdoC195/Views/directoryView.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }
    /** customer object view transfer   */
    public void passCustomer(Customer customer) throws SQLException {
        modifyCustomerViewIdTxt.setText(Integer.toString(customer.getCustomer_Id()));
        modifyCustomerViewNameTxt.setText(customer.getCustomer_Name());
        modifyCustomerViewAddressTxt.setText(customer.getAddress());
        modifyCustomerViewPhoneNumberTxt.setText(customer.getPhoneNumber());
        modifyCustomerViewPostalCodeTxt.setText(customer.getPostalCode());
        Countries country=DbHelper.getCountryByDivisonId(customer.getFirst_levelD());
        modifyCustomerViewCountriesComboStat.setValue(country);
        Divison divison=DbHelper.getDivisionByDivisonId(customer.getFirst_levelD());
        modifyCustomerViewStateComboStat.setValue(divison);
    }

    /** country combo box action event */
    public void modifyCustomerViewCountriesCombo(ActionEvent actionEvent) throws SQLException {
        Countries country= modifyCustomerViewCountriesComboStat.getSelectionModel().getSelectedItem();
        try {
            int countryId= country.getCountry_Id();
            ObservableList<Divison> filteredStatesByCountry =FilterByCountryId(countryId);
            modifyCustomerViewStateComboStat.setItems(filteredStatesByCountry);
        }catch (NullPointerException e){return;}




    }

    public void modifyCustomerViewStatesCombo(ActionEvent actionEvent) {
    }
    /** class initialize methode */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allCountries.clear();
        allDivisions.clear();
        try {
            DbHelper.getDivisionsDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            DbHelper.getCountriesDb();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            modifyCustomerViewCountriesComboStat.setItems(allCountries);
            modifyCustomerViewStateComboStat.setItems(allDivisions);

    }
}
