package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.dedateam.innorumors.data.entities.content.Vote;
import ru.dedateam.innorumors.data.entities.content.VoteComment;
import ru.dedateam.innorumors.data.entities.content.VotePost;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.service.Data;
import ru.dedateam.innorumors.service.ModelService;

@Controller
@RequestMapping(path = "rat")
public class RatingController {
    private Data data;

    @Autowired
    public RatingController(Data data) {
        this.data = data;
    }

    @PostMapping(path = "likePost")
    public String likePost(@RequestParam(name = "postId") Long postId) {
        User user = ModelService.getCurrentUser();
        VotePost vote = data.postVotes().findByAuthorIdAndPostId(user.getId(), postId);
        if (vote == null) {
            VotePost votePost = new VotePost(user.getId(), postId, Vote.LIKE);
            data.postVotes().save(votePost);
            return "deda/deda_like";
        } else {
            switch (vote.getVote()) {
                case LIKE:
                    data.postVotes().delete(vote);
                    return "deda/deda_drop_like";
                case DISLIKE:
                    vote.setVote(Vote.LIKE);
                    data.postVotes().save(vote);
                    return "deda/deda_like";
            }
            return CastomErrorController.ERROR;
        }
    }

    @PostMapping(path = "dislikePost")
    public String disLikePost(@RequestParam(name = "postId") Long postId) {
        User user = ModelService.getCurrentUser();
        VotePost vote = data.postVotes().findByAuthorIdAndPostId(user.getId(), postId);
        if (vote == null) {
            VotePost votePost = new VotePost(user.getId(), postId, Vote.DISLIKE);
            data.postVotes().save(votePost);
            return "deda/deda_dislike";
        } else {
            switch (vote.getVote()) {
                case DISLIKE:
                    data.postVotes().delete(vote);
                    return "deda/deda_drop_dislike";
                case LIKE:
                    vote.setVote(Vote.DISLIKE);
                    data.postVotes().save(vote);
                    return "deda/deda_dislike";
            }
            return CastomErrorController.ERROR;
        }
    }

    @PostMapping(path = "likeComment")
    public String likeComment(@RequestParam(name = "commentId") Long commentId) {
        User user = ModelService.getCurrentUser();
        VoteComment vote = data.commentVotes().findByAuthorIdAndCommentId(user.getId(), commentId);
        if (vote == null) {
            VoteComment voteComment = new VoteComment(user.getId(), commentId, Vote.LIKE);
            data.commentVotes().save(voteComment);
        } else {
            switch (vote.getVote()) {
                case LIKE:
                    data.commentVotes().delete(vote);
                    break;
                case DISLIKE:
                    vote.setVote(Vote.LIKE);
                    data.commentVotes().save(vote);
            }
        }
        return "deda";
    }

    @PostMapping(path = "dislikeComment")
    public String disLikeComment(@RequestParam(name = "commentId") Long commentId) {
        User user = ModelService.getCurrentUser();
        VoteComment vote = data.commentVotes().findByAuthorIdAndCommentId(user.getId(), commentId);
        if (vote == null) {
            VoteComment voteComment = new VoteComment(user.getId(), commentId, Vote.DISLIKE);
            data.commentVotes().save(voteComment);
        } else {
            switch (vote.getVote()) {
                case DISLIKE:
                    data.commentVotes().delete(vote);
                    break;
                case LIKE:
                    vote.setVote(Vote.DISLIKE);
                    data.commentVotes().save(vote);
            }
        }
        return "deda";
    }

}
