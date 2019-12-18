package ru.dedateam.innorumors.data.entities.profiles;


import lombok.Getter;
import lombok.Setter;
import ru.dedateam.innorumors.service.DateFormater;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "last_login", nullable = false)
    private LocalDateTime lastLogIn;

    @Column(name = "registration_time", nullable = false)
    private LocalDateTime registrationTime; //время реги

    @Column(name = "gender", nullable = true)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_day", nullable = true)
    private LocalDateTime birthDay;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.USER;
        this.registrationTime = LocalDateTime.now();
        this.lastLogIn = LocalDateTime.now();
        this.birthDay = LocalDateTime.now();
        this.gender = Gender.NON;
        this.rating = 0;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "\nid       : " + getId()
                + "\nusername: " + getUsername()
                + "\npassword: " + getPassword()
                + "\ngender  : " + getGender()
                + "\nage     : " + getBirthDay()
                + "\nregistrationTime: " + getRegistrationTime()
                + "\nlastLogIn       : " + getLastLogIn()
                + "\nrating  : " + getRating();
    }

    public String getFormatLastLogIn() {
        return DateFormater.format(lastLogIn);
    }

    public String getFormatRegistrationTime() {
        return DateFormater.format(registrationTime);
    }
}
