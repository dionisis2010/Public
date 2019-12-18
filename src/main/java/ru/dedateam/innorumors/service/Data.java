package ru.dedateam.innorumors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dedateam.innorumors.data.repositories.*;

@Service
public class Data {
    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private VotePostRepo votePostRepo;
    private VoteCommentRepo voteCommentRepo;
    private ReviewsRepo reviewsRepo;

    @Autowired
    public Data(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo,
                VotePostRepo votePostRepo, VoteCommentRepo voteCommentRepo, ReviewsRepo reviewsRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.votePostRepo = votePostRepo;
        this.voteCommentRepo = voteCommentRepo;
        this.reviewsRepo = reviewsRepo;
    }

    public UserRepo users() {
        return userRepo;
    }

    public PostRepo posts() {
        return postRepo;
    }

    public CommentRepo comments() {
        return commentRepo;
    }

    public VotePostRepo postVotes() {
        return votePostRepo;
    }

    public VoteCommentRepo commentVotes() {
        return voteCommentRepo;
    }

    public ReviewsRepo reviews() {
        return reviewsRepo;
    }
}
