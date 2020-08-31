package org.jamcult.shortsemester.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests(authorize ->
                        authorize
                                .mvcMatchers("/house").hasRole("ADMIN")
                                .mvcMatchers("/house/*/edit").hasRole("ADMIN")
                                .mvcMatchers("/house/*/delete").hasRole("ADMIN")
                                .mvcMatchers(HttpMethod.POST, "/house/*").hasRole("ADMIN")
                                .anyRequest().permitAll()
                )
                .formLogin()
                .loginPage("/login").and().logout();
    }
}
