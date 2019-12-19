package ru.dedateam.innorumors.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;
import ru.dedateam.innorumors.service.Data;
import ru.dedateam.innorumors.service.ModelService;
import ru.dedateam.innorumors.service.RatService;

@Controller
@RequestMapping(path = "post")
public class PostController {

    private Data data;
    private RatService ratService;

    @Autowired
    public PostController(Data data, RatService ratService) {
        this.data = data;
        this.ratService = ratService;
    }

    @GetMapping(path = "create")
    public String getCreatPostPage(Model model) {
        ModelService.putAuth(model);
        return "new_post";
    }

    @PostMapping(path = "create")
    public String createPost(@RequestParam(name = "title") String title,
                             @RequestParam(name = "body") String body,
                             Model model) {
        if (title.equals("") || body.equals("")) {
            CastomErrorController.setErrorDescription(model,
                    CastomErrorController.ERROR_TITLE_CREATE_POST,
                    CastomErrorController.ERROR_DESCRIPTION_EMPTY_FIELD);
            return CastomErrorController.ERROR_PAGE_WITH_DESCRIPTION;
        } else {
            Post post = new Post(title, body);
            post.setAuthor(ModelService.getCurrentUser());

            data.posts().save(post);

            ModelService.putAuth(model);
            return "redirect:/home";
        }
    }

    @GetMapping(path = "{id}")
    public String getPostById(@PathVariable(name = "id") Long id,
                              Model model) {

        ModelService.putAuth(model);

        Post post = data.posts().findById(id).get();
        post.setRat(ratService.countRatingPost(post.getId()));
        model.addAttribute("post", post);
        model.addAttribute("countComments", data.comments().countAllByPostIdAndIsDeleted(id, false));

        Iterable<Comment> comments = data.comments().findAllByPostIdAndIsDeleted(id, false);
        ratService.initCommentsRating(comments);
        model.addAttribute("comments", comments);

        return "post";
    }

    @PostMapping(path = "comment/add")
    public String addComment(@RequestParam(name = "body") String body,
                             @RequestParam(name = "postId") Long postId,
                             Model model) {
        if (body.equals("")) {
            CastomErrorController.setErrorDescription(model,
                    CastomErrorController.ERROR_TITLE_CREATE_COMMENT,
                    CastomErrorController.ERROR_DESCRIPTION_EMPTY_FIELD);
            return CastomErrorController.ERROR_PAGE_WITH_DESCRIPTION;
        } else {
            Comment comment = new Comment(body);

            User author = ModelService.getCurrentUser();
            comment.setAuthor(author);
            comment.setPost(data.posts().findById(postId).get());

            data.comments().save(comment);
            return "redirect:/post/" + postId;
        }
    }


}
