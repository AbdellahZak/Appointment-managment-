package AbdoC195.Model;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
/** countries class  */

public class Countries {
    private int country_Id;
    private String country;
    /** countries constructor */

    public Countries(int country_Id, String country) {
        this.country_Id = country_Id;
        this.country = country;
    }

    /** get country id  */

    public int getCountry_Id() {
        return country_Id;
    }
    /** set country id */

    public void setCountry_Id(int country_Id) {
        this.country_Id = country_Id;
    }
    /** get country name */

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
