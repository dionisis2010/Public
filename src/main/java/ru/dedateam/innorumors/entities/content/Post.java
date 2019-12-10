package ru.dedateam.innorumors.entities.content;

import ru.dedateam.innorumors.entities.profiles.User;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Post {

    private Long id;
    private URL url;

    private User author;
    private Boolean isAnonymous;

    private String title;
    private String body;

    private Date postedTime;

    private Set<Tag> tags;// хэштэги


    private List<Comment> comments;
}
