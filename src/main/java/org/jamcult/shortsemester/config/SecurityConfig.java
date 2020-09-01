package org.jamcult.shortsemester.config;

import org.jamcult.shortsemester.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AdminService adminService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorize ->
                        authorize
                                .mvcMatchers("/house").hasRole("ADMIN")
                                .mvcMatchers("/house/create").hasRole("ADMIN")
                                .mvcMatchers("/house/*/edit").hasRole("ADMIN")
                                .mvcMatchers("/house/*/delete").hasRole("ADMIN")
                                .mvcMatchers(HttpMethod.POST, "/house/*").hasRole("ADMIN")
                                .mvcMatchers("/register").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin()
                .loginPage("/login").and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminService).passwordEncoder(bCryptPasswordEncoder());
    }
}
