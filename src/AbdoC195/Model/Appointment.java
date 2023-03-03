package AbdoC195.Model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class Appointment {
    private int appointment_Id;
    private String title;
    private String description;
    private String location;
    private int contact_Id;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int customerId;
    private int userId;

    public Appointment(int appointment_Id, String title, String description, String location, int contact_Id, String type, LocalDateTime startDateTime, LocalDateTime endDateTime, int customerId, int userId) {
        this.appointment_Id = appointment_Id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact_Id = contact_Id;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
        this.userId = userId;
    }

    public int getAppointment_Id() {
        return appointment_Id;
    }

    public void setAppointment_Id(int appointment_Id) {
        this.appointment_Id = appointment_Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getContact_Id() {
        return contact_Id;
    }

    public void setContact_Id(int contact_Id) {
        this.contact_Id = contact_Id;
    }

    public String getType() {
        return type;
    }

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
}
