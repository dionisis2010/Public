package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.content.VotePost;
import ru.dedateam.innorumors.data.entities.profiles.User;

@Repository
public interface VotePostRepo extends PagingAndSortingRepository<VotePost, Long> {
//    Iterable<Post> findAllByAuthorIdAndVoteLikeEquals(Long id);
//    Iterable<Post> findAllByAuthorIdAndVoteDislikeEquals(Long id);
//    Iterable<User> findAllByPostIdAndVoteLikeEquals(Long id);
//    Iterable<User> findAllByPostIdAndVoteDislikeEquals(Long id);
}