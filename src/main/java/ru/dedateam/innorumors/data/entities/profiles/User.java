package ru.dedateam.innorumors.data.entities.profiles;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.WhereJoinTable;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
//@Data
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

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

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Post> posts;           // выложенные посты

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Comment> comments;     // оставленные комментарии

    @WhereJoinTable(clause = "vote = true")
    @ManyToMany
    @JoinTable(
            name = "post_votes",
            joinColumns = {@JoinColumn(name = "author")},
            inverseJoinColumns = {@JoinColumn(name = "post")}
    )
    private Set<Post> favoritesPosts;       // избранные посты

    @WhereJoinTable(clause = "vote = false")
    @ManyToMany
    @JoinTable(
            name = "post_votes",
            joinColumns = {@JoinColumn(name = "author")},
            inverseJoinColumns = {@JoinColumn(name = "post")}
    )
    private Set<Post> unFavoritesPosts;

    @WhereJoinTable(clause = "vote = true")
    @ManyToMany
    @JoinTable(
            name = "comment_votes",
            joinColumns = {@JoinColumn(name = "author")},
            inverseJoinColumns = {@JoinColumn(name = "post")}
    )
    private Set<Comment> favoritesComments; // избранные комменты

    @WhereJoinTable(clause = "vote = false")
    @ManyToMany
    @JoinTable(
            name = "comment_votes",
            joinColumns = {@JoinColumn(name = "author")},
            inverseJoinColumns = {@JoinColumn(name = "post")}
    )
    private Set<Comment> unFavoritesComments;
}
