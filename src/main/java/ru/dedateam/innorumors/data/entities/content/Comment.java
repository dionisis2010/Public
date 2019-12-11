package ru.dedateam.innorumors.data.entities.content;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dedateam.innorumors.data.entities.profiles.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
@Data
@NoArgsConstructor
public class Comment {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comments_seq")
    @SequenceGenerator(name = "comments_seq", sequenceName = "comments_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "posted_time", nullable = false)
    private Date postedTime;

    @OneToOne
    @JoinColumn(name = "comment")
    private Comment comment;    // комент на который мы отвчемаем

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post")
    private Post post;          // ссылка на пост под которым написан проект

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

    public Comment(User author, String body, Post post) {
        this.author = author;
        this.body = body;
        this.post = post;
        this.postedTime = new Date();
    }

    public Comment(User author, String body, Comment comment) {
        this.author = author;
        this.body = body;
        this.comment = comment;
        this.postedTime = new Date();
    }
}
