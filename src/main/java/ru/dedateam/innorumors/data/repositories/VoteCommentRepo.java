package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Vote;
import ru.dedateam.innorumors.data.entities.content.VoteComment;
import ru.dedateam.innorumors.data.entities.content.VotePost;
import ru.dedateam.innorumors.data.entities.profiles.User;

@Repository
public interface VoteCommentRepo extends PagingAndSortingRepository<VoteComment, Long> {

    Boolean existsByAuthorIdAndCommentId(Long authorId, Long commentId);
    VoteComment findByAuthorIdAndCommentId(Long authorId, Long commentId);
    Integer countVotePostByCommentIdAndVote(Long commentId, Vote vote);
    Iterable<VoteComment> findAllByCommentId(Long commentId);
}