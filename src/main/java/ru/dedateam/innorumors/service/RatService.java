package ru.dedateam.innorumors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dedateam.innorumors.data.entities.content.*;
import ru.dedateam.innorumors.data.entities.profiles.User;

@Service
public class RatService {

    private Data data;

    @Autowired
    public RatService(Data data) {
        this.data = data;
    }

    public Integer countLikesPost(Long postId) {
        return data.postVotes().countVotePostByPostIdAndVote(postId, Vote.LIKE);
    }

    public Integer countDisLikesPost(Long postId) {
        return data.postVotes().countVotePostByPostIdAndVote(postId, Vote.DISLIKE);
    }

    public Integer countRatingPost(Long postId) {
        return countLikesPost(postId) - countDisLikesPost(postId);
    }


    public Integer countLikesComment(Long commentId) {
        return data.commentVotes().countVoteCommentsByCommentIdAndVote(commentId, Vote.LIKE);
    }

    public Integer countDisLikesComment(Long commentId) {
        return data.commentVotes().countVoteCommentsByCommentIdAndVote(commentId, Vote.DISLIKE);
    }

    public Integer countRatingComments(Long postId) {
        return countLikesComment(postId) - countDisLikesComment(postId);
    }

    public Iterable<Post> initPostsRating(Iterable<Post> posts) {
        posts.forEach(post -> post.setRat(countRatingPost(post.getId())));
        return posts;
    }

    public Iterable<Comment> initCommentsRating(Iterable<Comment> comments) {
        comments.forEach(comment -> comment.setRat(countRatingComments(comment.getId())));
        return comments;
    }

    public Integer countUserRating(Long userId) {
        int postRating = 0;

        Iterable<Post> posts = data.posts().findAllByAuthorId((userId));
        for (Post post : posts) {
            for (VotePost votePost : data.postVotes().findAllByPostId(post.getId())) {
                postRating += votePost.getVote().getRat();
            }
        }
        int commentRating = 0;
        Iterable<Comment> comments = data.comments().findAllByAuthorId(userId);
        for (Comment comment : comments) {
            for (VoteComment voteComment : data.commentVotes().findAllByCommentId(comment.getId())) {
                commentRating += voteComment.getVote().getRat();
            }
        }
        return postRating + commentRating;
    }

    public Iterable<User> initUsersRating(Iterable<User> users) {
        users.forEach(user -> user.setRating(countUserRating(user.getId())));
        return users;
    }

}
