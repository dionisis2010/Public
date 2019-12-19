package ru.dedateam.innorumors.data.entities.content;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.service.DateFormater;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "posted_time", nullable = false)
    private LocalDateTime postedTime;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Transient
    private Integer rat = 0;

    @Transient
    private Integer countComments = 0;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
        this.postedTime = LocalDateTime.now();
        this.isDeleted = false;
    }

    public String getFormatPostedTime() {
        return DateFormater.format(postedTime);
    }
}
