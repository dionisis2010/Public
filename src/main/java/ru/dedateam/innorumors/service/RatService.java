package ru.dedateam.innorumors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dedateam.innorumors.data.entities.content.*;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.VoteCommentRepo;
import ru.dedateam.innorumors.data.repositories.VotePostRepo;

import java.util.stream.Stream;

@Service
public class RatService {

    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private VotePostRepo votePostRepo;
    private VoteCommentRepo voteCommentRepo;

    @Autowired
    public RatService(PostRepo postRepo, CommentRepo commentRepo, VotePostRepo votePostRepo, VoteCommentRepo voteCommentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
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

    public Integer countUserRat(Long userId) {
        int postRating = 0;

        Iterable<Post> posts = postRepo.findAllByAuthorId((userId));
        for (Post post : posts) {
            for (VotePost votePost : votePostRepo.findAllByPostId(post.getId())) {
                postRating += votePost.getVote().getRat();
            }
        }
        int commentRating = 0;
        Iterable<Comment> comments = commentRepo.findAllByAuthorId(userId);
        for (Comment comment : comments) {
            for (VoteComment voteComment : voteCommentRepo.findAllByCommentId(comment.getId())) {
                commentRating += voteComment.getVote().getRat();
            }
        }
        return postRating + commentRating / 2;

//        return votePostRepo.countVotePostByAuthorIdAndVote(userId, Vote.LIKE)
//                - votePostRepo.countVotePostByAuthorIdAndVote(userId, Vote.DISLIKE) / 2
//                + voteCommentRepo.countVoteCommentByAuthorIdAndVote(userId, Vote.LIKE)
//                - voteCommentRepo.countVoteCommentByAuthorIdAndVote(userId, Vote.DISLIKE) / 2;
    }
}
