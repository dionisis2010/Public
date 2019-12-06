package ru.dedateam.innorumors.entities.content;


import ru.dedateam.innorumors.entities.profiles.User;

import java.util.Date;

public class Comment {

    private Long id;

    private User author;

    private String body;
    private Date postedTime;

    private Post post;          // ссылка на пост под которым написан проект
    private Comment comment;    // комент на который мы отвчемаем
}
