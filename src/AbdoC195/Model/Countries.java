package AbdoC195.Model;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Countries {
    private int country_Id;
    private String country;

    public Countries(int country_Id, String country) {
        this.country_Id = country_Id;
        this.country = country;
    }

    public int getCountry_Id() {
        return country_Id;
    }

    public void setCountry_Id(int country_Id) {
        this.country_Id = country_Id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @Override
    public String toString(){
        return ("#" +Integer.toString(country_Id) +" "+ country );
    }
}
