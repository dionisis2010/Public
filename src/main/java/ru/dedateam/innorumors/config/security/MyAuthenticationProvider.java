package ru.dedateam.innorumors.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.UserRepo;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    UserRepo userRepo;

    @Autowired
    public MyAuthenticationProvider(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<User> byUsername = userRepo.findByUsername(authentication.getPrincipal().toString());
        Boolean isPasswordValid = byUsername.map(user -> user.getPassword().equals(authentication.getCredentials()))
                .orElse(false);

        if (isPasswordValid) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    authentication.getPrincipal(), // Юзернейм
                    authentication.getCredentials(), // Пароль
                    Collections.singletonList(new SimpleGrantedAuthority(byUsername.get().getRole().getName()))
            );
            auth.setDetails(byUsername.get());
            return  auth;
        } else {
            throw new BadCredentialsException("Invalid!!!");
        }
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
