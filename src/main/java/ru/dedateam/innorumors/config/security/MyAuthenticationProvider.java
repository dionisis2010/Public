package ru.dedateam.innorumors.config.security;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.dedateam.innorumors.data.entities.profiles.Role;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.UserRepo;

import java.nio.file.OpenOption;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;
import java.util.Optional;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    // Автовайрить узер репозитори тут.
    UserRepo userRepo;

    @Autowired
    public MyAuthenticationProvider(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Искать пользователя в БД тут.
//        User user = userRepo.findByUsername(String.valueOf(authentication.getPrincipal()));
//        if (user != null) {
//            if (String.valueOf(authentication.getCredentials()).equals(user.getPassword())) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        authentication.getPrincipal(), // Юзернейм
                        authentication.getCredentials(), // Пароль
//                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()))
                        Collections.singletonList(new SimpleGrantedAuthority(Role.USER.getName()))
                );
//                auth.setAuthenticated(true);
//                auth.setDetails(user.getId());
                return auth;
//            } else {
//                throw new BadCredentialsException("Не верный пароль");
//            }
//        } else {
//            throw new UserPrincipalNotFoundException("Пользователь с таким именем не зарегестрирован");
//            throw new RuntimeException("Пользователь с таким именем не зарегестрирован");
//        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
