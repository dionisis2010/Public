package ru.dedateam.innorumors.data.entities.profiles;


import lombok.Getter;
import lombok.Setter;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;

import java.net.URL;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class User {

    private Long id;

    private String nickName;
    private String password;

    private Date logIn;
    private Date lastLogIn;

    private Gender gender;
    private Integer age;

    private Integer rating;

    private Set<Post> posts;           // выложенные посты
    private Set<Comment> comments;     // оставленные комментарии

    private Set<Post> favoritesPosts;       // избранные посты
    private Set<Post> unFavoritesPosts;

    private Set<Comment> favoritesComments; // избранные комменты
    private Set<Comment> unFavoritesComments;

    public User(Long id, String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
        this.id = id;
    }

    public User() {
    }
}
