package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Vote;
import ru.dedateam.innorumors.data.entities.content.VoteComment;

@Repository
public interface VoteCommentRepo extends PagingAndSortingRepository<VoteComment, Long> {

    VoteComment findByAuthorIdAndCommentId(Long authorId, Long commentId);

    Integer countVoteCommentsByCommentIdAndVote(Long commentId, Vote vote);

    Iterable<VoteComment> findAllByCommentId(Long commentId);
}