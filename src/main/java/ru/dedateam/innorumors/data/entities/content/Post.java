package ru.dedateam.innorumors.data.entities.content;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dedateam.innorumors.data.entities.profiles.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @SequenceGenerator(name = "posts_seq", sequenceName = "posts_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "is_anonymous", nullable = false)
    private Boolean isAnonymous;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "posted_time", nullable = true)
    private LocalDateTime postedTime;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Comment> comments;

    public Post(User author, String title, String body, Boolean isAnonymous) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.isAnonymous = isAnonymous;
        this.postedTime = LocalDateTime.now();

    }

    public User getAuthor() {
        return author;
    }
}
