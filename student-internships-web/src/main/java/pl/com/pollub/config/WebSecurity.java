package pl.com.pollub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import pl.com.pollub.user.UserType;

/**
 * Created by mmaciasz on 2016-10-26.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/students/**").hasAnyAuthority(UserType.STUDENT.name(), UserType.TUTOR.name(), UserType.ADMIN.name())
                .antMatchers("/firm/**").hasAnyAuthority(UserType.FIRM_EMPLOYEE.name(), UserType.ADMIN.name())
                .antMatchers("/practice/**").hasAnyAuthority(UserType.getAllValues())
                .antMatchers("/timetable/**").hasAnyAuthority(UserType.getAllValues())
                .antMatchers("/", "/app/**", "/assets/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and().csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new Md5PasswordEncoder());
    }
}
