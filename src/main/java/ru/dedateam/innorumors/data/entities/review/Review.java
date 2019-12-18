package ru.dedateam.innorumors.data.entities.review;

import lombok.Getter;
import lombok.Setter;
import ru.dedateam.innorumors.data.entities.profiles.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviews_seq")
    @SequenceGenerator(name = "reviews_seq", sequenceName = "reviews_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "posted_time", nullable = false)
    private LocalDateTime postedTime;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public Review() {
    }

    public Review(User author, String email, String title, String body) {
        this.author = author;
        this.title = title;
        this.email = email;
        this.body = body;
        this.postedTime = LocalDateTime.now();
        this.isDeleted = false;
    }
}
