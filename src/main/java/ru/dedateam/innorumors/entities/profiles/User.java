package ru.dedateam.innorumors.entities.profiles;


import ru.dedateam.innorumors.entities.content.Comment;
import ru.dedateam.innorumors.entities.content.Post;

import java.net.URL;
import java.util.Date;
import java.util.Set;

public class User {

    private Long id;
    private URL profile;

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
}
