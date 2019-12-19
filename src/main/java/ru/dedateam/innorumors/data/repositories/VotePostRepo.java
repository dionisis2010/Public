package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Vote;
import ru.dedateam.innorumors.data.entities.content.VotePost;

@Repository
public interface VotePostRepo extends PagingAndSortingRepository<VotePost, Long> {



    VotePost findByAuthorIdAndPostId(Long authorId, Long postId);

    Integer countVotePostByPostIdAndVote(Long postId, Vote vote);

    Iterable<VotePost> findAllByPostId(Long postId);
}