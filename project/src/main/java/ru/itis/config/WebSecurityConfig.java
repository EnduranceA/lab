package ru.itis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

//включение безопасности
@EnableWebSecurity
//включение проверки безопасности через аннотации
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackages = "ru.itis")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Configuration
    @Order(1)
    public static class ApiTokenSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthenticationProvider authenticationProvider;

        @Autowired
        @Qualifier(value = "jwtAuthenticationFilter")
        private GenericFilterBean jwtAuthenticationFilter;

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/api/signIn");
        }

        @Override
        protected void configure(HttpSecurity http) {
            try {
                http.antMatcher("/api/**");
                http.csrf().disable();
                http.formLogin().disable();
                http.logout().disable();
                http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                http.addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        @Autowired
        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(authenticationProvider);
        }
    }

    @Configuration
    @Order(2)
    public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private PersistentTokenRepository persistentTokenRepository;

        @Autowired
        @Qualifier("customUserServiceImpl")
        private UserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) {
            try {
                http.rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository);

                http.formLogin().loginPage("/signIn")
                        .failureUrl("/signIn?error")
                        .defaultSuccessUrl("/profile")
                        .usernameParameter("email");

                http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/signIn")
                        .deleteCookies("JSESSIONID", "remember-me")
                        .invalidateHttpSession(true);

            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        @Autowired
        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            try {
                auth.userDetailsService(userDetailsService)
                        .passwordEncoder(passwordEncoder);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

    }

}

