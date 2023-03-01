package AbdoC195.Model;

public class Divison {
    private int division_Id;
    private String division;
    private int country_Id;

    public Divison(int division_Id, String division, int country_Id) {
        this.division_Id = division_Id;
        this.division = division;
        this.country_Id = country_Id;
    }

    public int getDivision_Id() {
        return division_Id;
    }

    public void setDivision_Id(int division_Id) {
        this.division_Id = division_Id;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCountry_Id() {
        return country_Id;
    }

    public void setCountry_Id(int country_Id) {
        this.country_Id = country_Id;
    }
}
