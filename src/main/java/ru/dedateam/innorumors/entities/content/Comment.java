package ru.dedateam.innorumors.entities.content;


import ru.dedateam.innorumors.entities.profiles.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq")
    @SequenceGenerator(name = "comments_seq", sequenceName = "comments_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private Date postedTime;



    @OneToOne
    @JoinColumn(name = "comment")
    private Comment comment;    // комент на который мы отвчемаем

    @ManyToOne
    @JoinColumn(name = "post")
    private Post post;          // ссылка на пост под которым написан проект


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;




    public Comment() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPostedTime(Date postedTime) {
        this.postedTime = postedTime;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Date getPostedTime() {
        return postedTime;
    }

    public Comment getComment() {
        return comment;
    }

    public Post getPost() {
        return post;
    }

    public User getAuthor() {
        return author;
    }

    public Comment(User author, String body, Post post) {
        this.author = author;
        this.body = body;
        this.post = post;
    }

    public Comment(User author, String body, Comment comment) {
        this.author = author;
        this.body = body;
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
