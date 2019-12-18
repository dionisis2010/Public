package ru.dedateam.innorumors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.content.Vote;
import ru.dedateam.innorumors.data.repositories.VoteCommentRepo;
import ru.dedateam.innorumors.data.repositories.VotePostRepo;

@Service
public class RatService {

    private VotePostRepo votePostRepo;
    private VoteCommentRepo voteCommentRepo;

    @Autowired
    public RatService(VotePostRepo votePostRepo, VoteCommentRepo voteCommentRepo) {
        this.votePostRepo = votePostRepo;
        this.voteCommentRepo = voteCommentRepo;
    }

    public Integer countLikesPost(Long postId) {
        return votePostRepo.countVotePostByPostIdAndVote(postId, Vote.LIKE);
    }

    public Integer countDisLikesPost(Long postId) {
        return votePostRepo.countVotePostByPostIdAndVote(postId, Vote.DISLIKE);
    }

    public Integer countRatingPost(Long postId) {
        return countLikesPost(postId) - countDisLikesPost(postId);
    }


    public Integer countLikesComment(Long commentId) {
        return voteCommentRepo.countVoteCommentsByCommentIdAndVote(commentId, Vote.LIKE);
    }

    public Integer countDisLikesComment(Long commentId) {
        return voteCommentRepo.countVoteCommentsByCommentIdAndVote(commentId, Vote.DISLIKE);
    }

    public Integer countRatingComments(Long postId) {
        return countLikesComment(postId) - countDisLikesComment(postId);
    }

    public Iterable<Post> countAllPostRat(Iterable<Post> posts) {
        posts.forEach(post -> post.setRat(countRatingPost(post.getId())));
        return posts;
    }

    public Iterable<Comment> countAllCommentRat(Iterable<Comment> comments) {
        comments.forEach(comment -> comment.setRat(countRatingComments(comment.getId())));
        return comments;
    }

    public Integer countUserRat(Long authorId) {
        return votePostRepo.countVotePostByAuthorIdAndVote(authorId, Vote.LIKE)
                - votePostRepo.countVotePostByAuthorIdAndVote(authorId, Vote.DISLIKE) / 2
                + voteCommentRepo.countVoteCommentByAuthorIdAndVote(authorId, Vote.LIKE)
                - voteCommentRepo.countVoteCommentByAuthorIdAndVote(authorId, Vote.DISLIKE) / 2;
    }
}
