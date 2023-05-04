package hr.mvc.employee.service;

import hr.mvc.employee.dto.*;
import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.employee.enums.EmployeeRoleEnum;
import hr.mvc.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginDTO GetLoginDTOByUsername(String username){
        EmployeeEntity entity = employeeRepository.findByUsername(username);
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setId(entity.getId());
        loginDTO.setVerified(true);
        loginDTO.setUsername(entity.getUsername());
        loginDTO.setPost(entity.getEmployeeRole());
        return loginDTO;
    }

    public EmployeeEntity getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    public EmployeeEntity getEmployeeByUsername(String username){
        return employeeRepository.findByUsername(username);
    }


    @Transactional
    public void register(RegisterDTO dto) {
        EmployeeEntity entity = new EmployeeEntity();

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setUsername(dto.getUsername());
        entity.setEmployeeRole(EmployeeRoleEnum.ROLE_EMPLOYEE);
        entity.setVerified(Boolean.FALSE);
        entity.setEmail(dto.getEmail());
        employeeRepository.insertEmployee(entity);
    }

    public Boolean isExist(String username){
        EmployeeEntity entity = employeeRepository.findByUsername(username);
        if(entity.getId()==null){
            return Boolean.FALSE;
        }
        else return Boolean.TRUE;
    }

    public EmployeeEntity getDetailedUser(Long id) {
        EmployeeEntity entity = employeeRepository.findById(id);
        return entity;
    }

    public List<EmployeeEntity> getAllUser(){
        List<EmployeeEntity> entities = employeeRepository.findAll();
        return entities;
    }

    @Transactional
    public void deleteUser(Long userId) {
        employeeRepository.delete(userId);
    }

    @Transactional
    public void verifyUser(Long userId) {
        EmployeeEntity employee = employeeRepository.findById(userId);
        employee.setVerified(Boolean.TRUE);
        employeeRepository.update(employee);
        /*
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("devoopsemail@gmail.com");
        message.setTo(employee.getEmail());
        message.setText("Nagyszerű hírünk van " +employee.getFirstName() + "! \n" +
                "\n"+
                "A regisztrációját elfogadták. Mostantól bejelentkezhet az alkalmazásba! \n"  +
                "http://localhost:4200/login");
        message.setSubject("Regisztrációd elfogadva ;)");

        emailSender.send(message);

         */
    }

    public List<UserForProjectSettings> getAll() {
        List<EmployeeEntity> entities = employeeRepository.findAll();
        return entitiesToUserForSettingsDto(entities);
    }


    public List<UserForProjectSettings> entitiesToUserForSettingsDto(List<EmployeeEntity> users){
        List<UserForProjectSettings> usersForSettings = new ArrayList<>();
        for(EmployeeEntity entity : users) {
            UserForProjectSettings userForSettings = new UserForProjectSettings();
            userForSettings.setId(entity.getId());
            userForSettings.setFirstName(entity.getFirstName());
            userForSettings.setLastName(entity.getLastName());
            userForSettings.setUsername(entity.getUsername());
            usersForSettings.add(userForSettings);
        }
        return usersForSettings;
    }

    @Transactional
    public EmployeeEntity updateUserData(UserDataDTO employee) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employee.getId());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setUsername(employee.getUsername());
        employeeEntity.setPost(employee.getPost());
        employeeEntity.setPostDescription(employee.getPostDescription());
        employeeRepository.update(employeeEntity);
        return employeeRepository.findById(employee.getId());
    }

    @Transactional
    public EmployeeEntity updateEmployeeData(EmployeeDataDTO dto){
        EmployeeEntity entity = employeeRepository.findById(dto.getUserId());
        entity.setAddress(dto.getAddress());
        entity.setBankAccountNumber(dto.getBankAccountNumber());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setFirstName(dto.getFirstName());
        entity.setIdentityCardNumber(dto.getIdentityCardNumber());
        entity.setLastName(dto.getLastName());
        entity.setPlaceOfBirth(dto.getPlaceOfBirth());
        employeeRepository.update(entity);
        return employeeRepository.findById(dto.getUserId());
    }

    public Boolean changePassword(ChangePasswordDTO dto) {
        if(passwordEncoder.matches(dto.getOldPassword(), employeeRepository.findById(dto.getEmployeeId()).getPassword())){
            EmployeeEntity entity = employeeRepository.findById(dto.getEmployeeId());
            entity.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            employeeRepository.update(entity);
            return Boolean.TRUE;
        }
        else throw new RuntimeException("your pw is incorrect");

    }
}
