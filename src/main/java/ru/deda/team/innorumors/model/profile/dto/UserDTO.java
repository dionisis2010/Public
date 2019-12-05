package ru.deda.team.innorumors.model.profile.dto;

import ru.deda.team.innorumors.model.content.type.Comment;
import ru.deda.team.innorumors.model.content.type.Post;
import ru.deda.team.innorumors.model.profile.type.AccessLvl;
import ru.deda.team.innorumors.model.profile.type.Gender;

import java.util.Date;
import java.util.Set;

public class UserDTO {
    Long id;
    String nickName;
    String password;
    AccessLvl accessLvl;

    Date logIn;
    Date lastLogIn;

    Integer rating;
    Gender gender;
    Integer age;

    Set<Post> posts;           // выложенные посты
    Set<Comment> comments;     // оставленные комментарии

    Set<Post> favoritesPosts;       // избранные посты
    Set<Post> unFavoritesPosts;

    Set<Comment> favoritesComments; // избранные комменты
    Set<Comment> unFavoritesComments;
}
