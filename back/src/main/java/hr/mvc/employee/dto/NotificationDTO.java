package hr.mvc.employee.dto;

import hr.mvc.employee.enums.NotificationEnum;

public class NotificationDTO {

    private String firstName;

    private String lastName;

    private NotificationEnum notificationType;

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public NotificationEnum getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationEnum notificationType) {
        this.notificationType = notificationType;
    }
}
