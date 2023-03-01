package AbdoC195.Controllers;

import AbdoC195.Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControllerHelper {
    public static int nextCustomerId = 0;

    public static ObservableList<Customer> allCustomers= FXCollections.observableArrayList();

    public static ObservableList<Customer> getCustomers(){
        return allCustomers;
    }

    public static void addCustomer(Customer customer){
        allCustomers.add(customer);
    }
}

