package ru.dedateam.innorumors.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.dedateam.innorumors.data.entities.profiles.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MySuccesHandler mySuccesHandler;

    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()           // нужно ли оставить дизабл
                .authenticationProvider(myAuthenticationProvider)
                .formLogin().loginPage("/login")
                .loginProcessingUrl("/do_login")
                .successHandler(mySuccesHandler)
                .and()
                .logout().logoutUrl("/do_logout")
                .and()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/login",
                        "/do_login",
                        "/registration",
                        "/do_registration",
                        "/deda/**",
                        "/css/**",
                        "/fonts/**",
                        "/js/**",
                        "/image/**").permitAll()
                .antMatchers("/admin/**", "/admin").hasRole("ADMIN")
                .anyRequest().hasAnyRole("USER", "ADMIN");
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
