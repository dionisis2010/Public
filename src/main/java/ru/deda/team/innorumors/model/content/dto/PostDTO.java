package ru.deda.team.innorumors.model.content.dto;

import ru.deda.team.innorumors.model.content.type.Comment;
import ru.deda.team.innorumors.model.content.type.DisLike;
import ru.deda.team.innorumors.model.content.type.Like;
import ru.deda.team.innorumors.model.content.type.Tag;
import ru.deda.team.innorumors.model.profile.type.User;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class PostDTO {

    Long id;
    URL url;
    String title;
    String body;
    User author;
    Date postedTime;

    Set<Like> likes;
    Set<DisLike> disLikes;
    Set<User> usersThatLike;    // пользоватили которым понравился пост
    Set<User> usersThatDisLike;

    Boolean isAnonymous;
    Set<Tag> tags;              // хэштэги
    List<Comment> comments;
}
