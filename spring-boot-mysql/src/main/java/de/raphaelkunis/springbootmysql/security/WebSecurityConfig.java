package de.raphaelkunis.springbootmysql.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;

//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${springbootmysql.pwd_plain}")          // gets the property from application.properties, plain test
    private String pwd;
    //@Value("${springbootmysql.pwd_adm_plain}")
    @Value("${springbootmysql.pwd_adm_bcrypt}")     // for bcrypt test
    private String pwd_adm;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // implemented with the snippet from Sebastian
    @Autowired
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
             .withUser("user").password(pwd).roles("USER").and()
             .withUser("admin").password(pwd_adm).roles("USER","ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()                           // disable CSRF - otherwise POST will not work for the simple example
                .authorizeRequests()
                .antMatchers("/demo/hello").permitAll()       // for pages without authentication put this at first
                .anyRequest().authenticated()           // the rest needs authentication
                .and().formLogin()
                .and().httpBasic();
    }

        /* example from https://www.javainuse.com/spring/sprboot_sec
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("USER")
                    .and().formLogin().loginPage("/login.jsp")
                    .failureUrl("/login.jsp?error=1").loginProcessingUrl("/login")
                    .permitAll().and().logout()
                    .logoutSuccessUrl("/listEmployees.html");
        }*/
}