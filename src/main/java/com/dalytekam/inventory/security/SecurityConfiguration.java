package com.dalytekam.inventory.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import org.springframework.security.crypto.password.PasswordEncoder;



import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    @Autowired
    private DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     /*   auth.inMemoryAuthentication().withUser("user1").password(encoder.encode("1234")).roles("USER");
      auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("1234")).roles("USER","ADMIN");
        auth.inMemoryAuthentication().withUser("user2").password(encoder.encode("1234")).roles("USER");
*/
      auth.jdbcAuthentication()
               .dataSource(dataSource)
               .usersByUsernameQuery("select login as principal, pass as credentials, active from users where login=?")
               .authoritiesByUsernameQuery("select login as principal, role as role from users_roles where login=?")
               .passwordEncoder(new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"))
               .rolePrefix("ROLE_");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
http.formLogin();
http.authorizeRequests().antMatchers("/").hasRole("USER")
.antMatchers("/index","/delete","/edit*","/save","/addProduct").hasRole("ADMIN")
        .anyRequest().authenticated().and().formLogin();

    }
}
