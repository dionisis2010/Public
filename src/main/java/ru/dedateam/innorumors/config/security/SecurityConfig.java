package ru.dedateam.innorumors.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MySuccesHandler mySuccesHandler;

    @Autowired
    MyAuthenticationProvider myAuthenticationProvider;

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
                .anyRequest().authenticated();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication()
//                    .withUser("denis").password("123").roles("user")
//                    .and()
//                    .withUser("admin").password("admin").roles("admin");
//    }
}
