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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_votes_seq")
    @SequenceGenerator(name = "post_votes_seq", sequenceName = "post_votes_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "author", nullable = false)
    private Long authorId;

    @Column(name = "post", nullable = false)
    private Long postId;

    @Column(name = "vote", nullable = false)
    @Enumerated(EnumType.STRING)
    private Vote vote;

    public VotePost() {
    }

    public VotePost(Long authorId, Long postId, Vote vote) {
        this.authorId = authorId;
        this.postId = postId;
        this.vote = vote;
    }
}
