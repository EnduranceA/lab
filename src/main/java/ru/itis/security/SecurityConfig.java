package ru.itis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http){
        try {
            http.csrf().disable();

            http.authorizeRequests()
                    .antMatchers("/users/**").hasAuthority("ADMIN")
                    .antMatchers("/").authenticated()
                    .antMatchers("/files").authenticated()
                    .antMatchers("/signUp").permitAll()
                    .antMatchers("/confirm/**").permitAll();

            http.formLogin()
                    .loginPage("/signIn")
                    .defaultSuccessUrl("/")
                    .failureUrl("/signIn?error")
                    .usernameParameter("email")
                    .permitAll();

            http.logout()
                    .logoutSuccessUrl("/signIn");
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        try {
            auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(encoder);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}

