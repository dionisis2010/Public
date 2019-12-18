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

    @Autowired
    public Data(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo, VotePostRepo votePostRepo, VoteCommentRepo voteCommentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.votePostRepo = votePostRepo;
        this.voteCommentRepo = voteCommentRepo;
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

    public VoteCommentRepo CommentVotes() {
        return voteCommentRepo;
    }
}
