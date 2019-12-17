package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.VoteComment;
import ru.dedateam.innorumors.data.entities.profiles.User;

@Repository
public interface VoteCommentRepo extends PagingAndSortingRepository<VoteComment, Long> {
//    Iterable<Comment> findAllByAuthorIdAndVoteLikeEquals(Long id);
//    Iterable<Comment> findAllByAuthorIdAndVoteDislikeEquals(Long id);
//    Iterable<User> findAllByCommentIdAndVoteLikeEquals(Long id);
//    Iterable<User> findAllByCommentIdAndVoteDislikeEquals(Long id);
}