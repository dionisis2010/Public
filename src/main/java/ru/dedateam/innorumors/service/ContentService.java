package ru.dedateam.innorumors.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.dedateam.innorumors.data.repositories.*;

@Service
public class ContentService {
    private UserRepo userRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private VotePostRepo votePostRepo;
    private VoteCommentRepo voteCommentRepo;

    @Autowired
    public ContentService(UserRepo userRepo, PostRepo postRepo, CommentRepo commentRepo, VotePostRepo votePostRepo, VoteCommentRepo voteCommentRepo) {
        this.userRepo = userRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.votePostRepo = votePostRepo;
        this.voteCommentRepo = voteCommentRepo;
    }


}
