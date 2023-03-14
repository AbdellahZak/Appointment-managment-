package AbdoC195.Model;
/** contact class */

public class Contact {
    private int contactId;
    private String contactName;
    private String contactEmail;
    /** contact constructor */

    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }
    /** get contact id */

    public int getContactId() {
        return contactId;
    }
    /** set contact id */

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /** get contact name */

    public String getContactName() {
        return contactName;
    }
    /** set contact name */

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /** get contact email */

    public String getContactEmail() {
        return contactEmail;
    }
    /** set contact email */

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    @Override
    public String toString(){
        return ("#" +Integer.toString(contactId) +" "+ contactName );
    }
}
