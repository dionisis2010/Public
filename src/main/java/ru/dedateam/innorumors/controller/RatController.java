package ru.dedateam.innorumors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dedateam.innorumors.data.entities.content.VoteComment;
import ru.dedateam.innorumors.service.ModelService;
import ru.dedateam.innorumors.data.entities.content.Vote;
import ru.dedateam.innorumors.data.entities.content.VotePost;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.*;
import ru.dedateam.innorumors.service.RatService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "rat")
public class RatController {
    private PostRepo postRepo;
    private CommentRepo commentRepo;
    private UserRepo userRepo;
    private VotePostRepo votePostRepo;
    private VoteCommentRepo voteCommentRepo;
    private RatService ratService;

    public RatController(PostRepo postRepo, CommentRepo commentRepo, UserRepo userRepo, VotePostRepo votePostRepo, VoteCommentRepo voteCommentRepo, RatService ratService) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.votePostRepo = votePostRepo;
        this.voteCommentRepo = voteCommentRepo;
        this.ratService = ratService;
    }

    @PostMapping(path = "likePost")
    public String likePost(@RequestParam(name = "postId") Long postId) {
        User user = ModelService.getCurrentUser();
        VotePost vote = votePostRepo.findByAuthorIdAndPostId(user.getId(), postId);
        if (vote == null) {
            VotePost votePost = new VotePost(user.getId(), postId, Vote.LIKE);
            votePostRepo.save(votePost);
        } else {
            switch (vote.getVote()){
                case LIKE:
                    votePostRepo.delete(vote);
                    break;
                case DISLIKE:
                    vote.setVote(Vote.LIKE);
                    votePostRepo.save(vote);
            }
        }
        return "deda";
    }

    @PostMapping(path = "dislikePost")
    public String disLikePost(@RequestParam(name = "postId") Long postId) {
        User user = ModelService.getCurrentUser();
        VotePost vote = votePostRepo.findByAuthorIdAndPostId(user.getId(), postId);
        if (vote == null) {
            VotePost votePost = new VotePost(user.getId(), postId, Vote.DISLIKE);
            votePostRepo.save(votePost);
        } else {
            switch (vote.getVote()){
                case DISLIKE:
                    votePostRepo.delete(vote);
                    break;
                case LIKE:
                    vote.setVote(Vote.DISLIKE);
                    votePostRepo.save(vote);
            }
        }

        return "deda";
}

    @PostMapping(path = "/likeComment")
    public String likeComment(@RequestParam(name = "commentId") Long commentId) {
        User user = ModelService.getCurrentUser();
        VoteComment vote = voteCommentRepo.findByAuthorIdAndCommentId(user.getId(), commentId);
        if (vote == null) {
            VoteComment voteComment = new VoteComment(user.getId(), commentId, Vote.LIKE);
            voteCommentRepo.save(voteComment);
        } else {
            switch (vote.getVote()){
                case LIKE:
                    voteCommentRepo.delete(vote);
                    break;
                case DISLIKE:
                    vote.setVote(Vote.LIKE);
                    voteCommentRepo.save(vote);
            }
        }

        return "deda";
    }

    @PostMapping(path = "/dislikeComment")
    public String disLikeComment(@RequestParam(name = "commentId") Long commentId) {
        User user = ModelService.getCurrentUser();
        VoteComment vote = voteCommentRepo.findByAuthorIdAndCommentId(user.getId(), commentId);
        if (vote == null) {
            VoteComment voteComment = new VoteComment(user.getId(), commentId, Vote.DISLIKE);
            voteCommentRepo.save(voteComment);
        } else {
            switch (vote.getVote()){
                case DISLIKE:
                    voteCommentRepo.delete(vote);
                    break;
                case LIKE:
                    vote.setVote(Vote.DISLIKE);
                    voteCommentRepo.save(vote);
            }
        }

        return "deda";
    }

}
