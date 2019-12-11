package ru.dedateam.innorumors.data.entities.content;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dedateam.innorumors.data.entities.profiles.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @SequenceGenerator(name = "posts_seq", sequenceName = "posts_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "isAnonymous", nullable = false)
    private Boolean isAnonymous;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "postedTime", nullable = false)
    private Date postedTime;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Comment> comments;

    public Post(User author, String title, String body, boolean isAnonymous) {
        this.author = author;
        this.title = title;
        this.body = body;
        this.isAnonymous = isAnonymous;
        this.postedTime = new Date();
    }
}
