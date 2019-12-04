package ru.deda.team.innorumors.model.content.dto;

import ru.deda.team.innorumors.model.content.type.Comment;
import ru.deda.team.innorumors.model.content.type.DisLike;
import ru.deda.team.innorumors.model.content.type.Like;
import ru.deda.team.innorumors.model.content.type.Post;
import ru.deda.team.innorumors.model.profile.type.User;

import java.util.Date;
import java.util.Set;

public class CommentDTO {
    Long id;
    String body;
    User author;
    Date postedTime;

    Post post;          // ссылка на пост под которым написан проект
    Set<User> users;      // юзеры которых мы призываем в комменте
    Comment comment;    // комент на который мы отвчемаем


    Set<Like> likes;
    Set<DisLike> disLikes;

    Set<User> usersThatLike;
    Set<User> usersThatDisLike;

    public boolean loadById(Integer id) {
        return false;
    }
}
