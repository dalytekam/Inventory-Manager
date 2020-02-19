package com.dalytekam.inventory.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

private final PasswordEncoder passwordEncoder;
@Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
/*   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.inMemoryAuthentication().withUser("user1").password(encoder.encode("1234")).roles("USER");
      auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("1234")).roles("USER","ADMIN");
        auth.inMemoryAuthentication().withUser("user2").password(encoder.encode("1234")).roles("USER");

      auth.jdbcAuthentication()
               .dataSource(dataSource)
               .usersByUsernameQuery("select login as principal, pass as credentials, active from users where login=?")
               .authoritiesByUsernameQuery("select login as principal, role as role from users_roles where login=?")
               .passwordEncoder(new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"))
               .rolePrefix("ROLE_");

    }*/

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/index").permitAll()
                    .antMatchers("/addProduct","/edit","/delete*").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
            .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true) ;



            http.exceptionHandling().accessDeniedPage("/403");
        }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
      UserDetails user1 =  User.builder()
            .username("richy")
            .password(passwordEncoder.encode("tekam"))
            .roles("USER")
            .build();
        UserDetails user3 =  User.builder()
                .username("daly")
                .password(passwordEncoder.encode("tekam"))
                .roles("USER")
                .build();

        UserDetails user2 =  User.builder()
                .username("admin")
                .password(passwordEncoder.encode("tekam"))
                .roles("ADMIN")
                .build();
return new InMemoryUserDetailsManager(user1,user2,user3);

    }
    }
