package de.raphaelkunis.springbootmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String PWD =  "38183996-f260-402f-afba-46e494fa7174";
    private final static String PWD_ADM =  "38183996-f260-xf35-afba-46e494fa7179";

    @Autowired
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
             .withUser("user").password(PWD).roles("USER").and()
             .withUser("admin").password(PWD_ADM).roles("USER","ADMIN");
        // hints: did not work without .passwordEncoder(NoOpPasswordEncoder.getInstance())
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/demo").permitAll()   // for pages without authentication put this at first
                .anyRequest().authenticated()       // the rest needs authentication
                .and()
                .formLogin().and()
                .httpBasic();
    }
}