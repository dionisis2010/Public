package ru.dedateam.innorumors.data.entities.content;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "comment_votes")
public class VoteComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_votes_seq")
    @SequenceGenerator(name = "comment_votes_seq", sequenceName = "comment_votes_id_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "author", nullable = false)
    private Long authorId;

    @Column(name = "comment", nullable = false)
    private Long commentId;

    @Column(name = "vote", nullable = false)
    @Enumerated(EnumType.STRING)
    private Vote vote;
}
