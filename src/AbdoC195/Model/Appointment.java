package AbdoC195.Model;

import java.time.LocalDateTime;
/** appointment class */
public class Appointment {
    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private int contactId;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int customerId;
    private int userId;
    private int month;
    private int count;

    /** app constructor with contact string */

    public Appointment(int appointmentId, String title, String description, String location, String contact, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
        this.userId = userId;
    }


    /** app contructor with contact id int */

    public Appointment(int appointmentId, String title, String description, String location, int contactId, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactId = contactId;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
        this.userId = userId;
    }
    /** constructor for apps  */

    public Appointment(String type,int month, int count) {
        this.type = type;
        this.month = month;
        this.count = count;
    }
    /** get app id methode */

    public int getAppointmentId() {
        return appointmentId;
    }
    /** set app id */

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    /** get title */

    public String getTitle() {
        return title;
    }
    /** set title */

    public void setTitle(String title) {
        this.title = title;
    }
    /** get app description */

    public String getDescription() {
        return description;
    }
    /** set description */

    public void setDescription(String description) {
        this.description = description;
    }
    /** get location */

    public String getLocation() {
        return location;
    }
    /** set location */

    public void setLocation(String location) {
        this.location = location;
    }
    /** get contact */

    public String getContact() {
        return contact;
    }
    /** set contact */

    public void setContact(String contact) {
        this.contact = contact;
    }
    /** get contact id */

    public int getContactId() {
        return contactId;
    }
    /** set contact id */

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /** get type  */

    public String getType() {
        return type;
    }
    /** set type  */

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
