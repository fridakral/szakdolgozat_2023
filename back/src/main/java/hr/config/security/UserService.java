package hr.config.security;

import hr.config.exceptions.UserIsNotValidatedException;
import hr.config.multitenancy.TenantEnum;
import hr.mvc.employee.entity.EmployeeEntity;
import hr.mvc.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    public static ThreadLocal<String> username = new ThreadLocal<>();

    @Autowired
    EmployeeRepository employeeRepository;

    public static String getUsername() {
        return username.get();
    }

    public static void setUsername(String newUsername){
        username.set(newUsername);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeEntity entity = employeeRepository.findByUsername(username);

        if(entity==null){
            throw new UsernameNotFoundException("User not found in the database!");
        }

        else{
            if(entity.getVerified()==Boolean.FALSE){
                throw new UserIsNotValidatedException("User is not validated yet!");
            }
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(entity.getEmployeeRole().toString()));
        return new org.springframework.security.core.userdetails.User(entity.getUsername(), entity.getPassword(), authorities);
    }
}
