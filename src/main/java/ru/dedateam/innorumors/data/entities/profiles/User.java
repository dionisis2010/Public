package ru.dedateam.innorumors.data.entities.profiles;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.WhereJoinTable;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
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

    @Column(name = "last_login", nullable = false)
    private Date lastLogIn;

    @Column(name = "gender", nullable = false)
    private boolean gender;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "registrationTime", nullable = false)
    private Date registrationTime; //время реги

    public User(String username, String password, Boolean gender, Integer age) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.registrationTime = new Date();
    }

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Post> posts;           // выложенные посты

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Comment> comments;     // оставленные комментарии

    @WhereJoinTable(clause = "vote = true")
    @ManyToMany
    @JoinTable(
            name = "PostVotes",
            joinColumns = { @JoinColumn(name = "author")},
            inverseJoinColumns = {@JoinColumn(name = "post")}
    )
    private Set<Post> favoritesPosts;       // избранные посты

    @WhereJoinTable(clause = "vote = false")
    @ManyToMany
    @JoinTable(
            name = "PostVotes",
            joinColumns = { @JoinColumn(name = "author")},
            inverseJoinColumns = {@JoinColumn(name = "post")}
    )
    private Set<Post> unFavoritesPosts;

    @WhereJoinTable(clause = "vote = true")
    @ManyToMany
    @JoinTable(
            name = "CommentVotes",
            joinColumns = { @JoinColumn(name = "author")},
            inverseJoinColumns = {@JoinColumn(name = "post")}
    )
    private Set<Comment> favoritesComments; // избранные комменты

    @WhereJoinTable(clause = "vote = false")
    @ManyToMany
    @JoinTable(
            name = "CommentVotes",
            joinColumns = { @JoinColumn(name = "author")},
            inverseJoinColumns = {@JoinColumn(name = "post")}
    )
    private Set<Comment> unFavoritesComments;
}
