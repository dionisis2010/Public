package ru.dedateam.innorumors.data.entities.content;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "post_votes")
public class VotePost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posts_seq")
    @SequenceGenerator(name = "posts_seq", sequenceName = "posts_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "author", nullable = false)
    private Long authorId;

    @Column(name = "post", nullable = false)
    private Long postId;

    @Column(name = "vote", nullable = false)
    @Enumerated(EnumType.STRING)
    private Vote vote;
}
