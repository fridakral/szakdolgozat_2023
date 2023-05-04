package hr.config.security;

import hr.config.filters.CustomAuthorizationFilter;
import hr.config.filters.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/employee/login");
        http.cors();
        http.csrf().disable();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/employee/login").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/employee/register").permitAll();

        //Az endpointokra érkező kérések role-ok szerinti authentikálása:

        http.authorizeRequests()
                .antMatchers("/employee/deleteUser/**").hasAnyAuthority("ROLE_BOSS")
                .antMatchers("/employee/verifyUser/**").hasAnyAuthority("ROLE_BOSS")
                .antMatchers("/project/create/**").hasAnyAuthority("ROLE_BOSS")
                .antMatchers("/notification/unverified").hasAnyAuthority("ROLE_BOSS")
                .antMatchers("/task/delete").hasAnyAuthority("ROLE_BOSS", "ROLE_MANAGER")
                .antMatchers("/project/addEmployeeToProject").hasAnyAuthority("ROLE_BOSS", "ROLE_MANAGER");

        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
