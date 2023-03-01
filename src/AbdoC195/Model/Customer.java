package AbdoC195.Model;

public class Customer {
    private int customer_Id;
    private String customer_Name;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private int first_levelD;
    private String country;

    public Customer(int customer_Id, String customer_Name, String address, String postalCode, String phoneNumber, int first_levelD) {
        this.customer_Id = customer_Id;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.first_levelD = first_levelD;
    }

    public int getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(int customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getFirst_levelD() {
        return first_levelD;
    }

    public void setFirst_levelD(int first_levelD) {
        this.first_levelD = first_levelD;
    }

}
