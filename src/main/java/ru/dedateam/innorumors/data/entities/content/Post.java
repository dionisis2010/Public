package ru.dedateam.innorumors.data.entities.content;

import lombok.Getter;
import lombok.Setter;
import ru.dedateam.innorumors.data.entities.profiles.User;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    public Long getId() {
        return id;
    }

    public Post setId(Long id) {
        this.id = id;
        return this;
    }

    public User getAuthor() {
        return author;
    }

    public Post setAuthor(User author) {
        this.author = author;
        return this;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public Post setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Post setBody(String body) {
        this.body = body;
        return this;
    }

    public Date getPostedTime() {
        return postedTime;
    }

    public Post setPostedTime(Date postedTime) {
        this.postedTime = postedTime;
        return this;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Post setTags(Set<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Post setComments(List<Comment> comments) {
        this.comments = comments;
        return this;
    }
}
