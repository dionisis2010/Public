package ru.dedateam.innorumors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.content.Vote;
import ru.dedateam.innorumors.data.repositories.VoteCommentRepo;
import ru.dedateam.innorumors.data.repositories.VotePostRepo;

@Component
public class RatService {

    private VotePostRepo votePostRepo;
    private VoteCommentRepo voteCommentRepo;

    @Autowired
    public RatService(VotePostRepo votePostRepo, VoteCommentRepo voteCommentRepo) {
        this.votePostRepo = votePostRepo;
        this.voteCommentRepo = voteCommentRepo;
    }

    public Integer countLikesPost(Long postId){
        return votePostRepo.countVotePostByPostIdAndVote(postId, Vote.LIKE);
    }

    public Integer countDisLikesPost(Long postId){
        return votePostRepo.countVotePostByPostIdAndVote(postId, Vote.DISLIKE);
    }

    public Integer countRatingPost(Long postId){
        return  countLikesPost(postId) - countDisLikesPost(postId);
    }



    public Integer countLikesComment(Long commentId){
        return voteCommentRepo.countVotePostByCommentIdAndVote(commentId, Vote.LIKE);
    }

    public Integer countDisLikesComment(Long commentId){
        return voteCommentRepo.countVotePostByCommentIdAndVote(commentId, Vote.DISLIKE);
    }

    public Integer countRatingComments(Long postId){
        return  countLikesComment(postId) - countDisLikesComment(postId);
    }

    public Iterable<Post> countAllPostRat(Iterable<Post> posts){
        posts.forEach(post -> post.setRat(countRatingPost(post.getId())));
        return posts;
    }
    public Iterable<Comment> countAllCommentRat(Iterable<Comment> comments){
        comments.forEach(comment -> comment.setRat(countRatingComments(comment.getId())));
        return comments;
    }
}
