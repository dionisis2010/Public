package ru.dedateam.innorumors.data.entities.content;

import lombok.Getter;
import lombok.Setter;
import ru.dedateam.innorumors.data.entities.profiles.User;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Post {

    private Long id;

    private User author;
    private Boolean isAnonymous;

    private String title;
    private String body;

    private Date postedTime;

    private Set<Tag> tags;              // хэштэги
    private List<Comment> comments;

    public Post(Long id, User author, String title, String body) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
    }
    public Post(){

    }

}
