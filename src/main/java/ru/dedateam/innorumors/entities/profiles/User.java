package ru.dedateam.innorumors.entities.profiles;


import org.hibernate.annotations.WhereJoinTable;
import ru.dedateam.innorumors.entities.content.Comment;
import ru.dedateam.innorumors.entities.content.Post;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.net.URL;
import java.util.Date;
import java.util.Set;

public class User {

    private Long id;
    private String username;
    private String password;
    private Date lastLogIn;
    private Gender gender;
    private Integer age;
    private Integer rating;


    private Date logIn;

    private Set<Post> posts;           // выложенные посты
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
