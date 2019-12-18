package ru.dedateam.innorumors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.data.repositories.CommentRepo;
import ru.dedateam.innorumors.data.repositories.PostRepo;
import ru.dedateam.innorumors.data.repositories.UserRepo;

@Service
public class ModelService {

    @Autowired
    private static UserRepo userRepo;
    @Autowired
    private static PostRepo postRepo;
    @Autowired
    private static CommentRepo commentRepo;
    @Autowired
    private static RatService ratService;



    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    public static void putAuth(Model model){
        model.addAttribute("auth", getCurrentUser());
    }

    public static void putAllPostsWithRatingSortedByTime(Model model){
        Iterable<Post> posts = postRepo.findByIsDeletedOrderByPostedTimeDesc(false);
        initRatingInPosts(posts);
        initCountCommentsInPosts(posts);
        model.addAttribute("posts", posts);
    }

    public static void putCountCommentsInPost(Model model, Long postId){
        model.addAttribute("countCommentsInPost", commentRepo.countAllByPostIdAndIsDeleted(postId, false));

    }

    public static void initCountCommentsInPosts(Iterable<Post> posts){
        posts.forEach(post -> post.setCountComments(commentRepo.countAllByPostIdAndIsDeleted(post.getId(), false)));
    }

    public static void initRatingInPosts(Iterable<Post> posts){
        ratService.countAllPostRat(posts);
    }
}
