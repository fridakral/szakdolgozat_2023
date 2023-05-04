package hr.mvc.employee.service;

import hr.mvc.employee.dto.NotificationDTO;
import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.employee.enums.NotificationEnum;
import hr.mvc.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<NotificationDTO> getAllNotification(){
        List<NotificationDTO> notifications = new ArrayList<>();
        List<EmployeeEntity> entities = employeeRepository.findAllUnverified();
        for (EmployeeEntity entity : entities){
            NotificationDTO notification = new NotificationDTO();
            notification.setNotificationType(NotificationEnum.VERIFICATION);
            notification.setFirstName(entity.getFirstName());
            notification.setLastName(entity.getLastName());
            notification.setUserId(entity.getId());
            notifications.add(notification);
        }
        return notifications;
    }

}
