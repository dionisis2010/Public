package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.content.Vote;
import ru.dedateam.innorumors.data.entities.content.VotePost;
import ru.dedateam.innorumors.data.entities.profiles.User;

@Repository
public interface VotePostRepo extends PagingAndSortingRepository<VotePost, Long> {


    Boolean existsByAuthorIdAndPostId(Long authorId, Long postId);

    VotePost findByAuthorIdAndPostId(Long authorId, Long postId);

    Integer countVotePostByPostIdAndVote(Long postId, Vote vote);

    Iterable<VotePost> findAllByPostId(Long postId);

    Integer countVotePostByAuthorIdAndVote(Long authorId, Vote vote);

}