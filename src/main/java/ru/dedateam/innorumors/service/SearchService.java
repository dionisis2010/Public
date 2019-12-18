package ru.dedateam.innorumors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.repositories.PostRepo;

import java.util.LinkedList;
import java.util.List;

@Service
public class SearchService {

    private PostRepo postRepo;

    @Autowired
    public void setPostRepo(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public Iterable<Post> searchByTitle(String word){
        List<Post> posts = new LinkedList<>();
        postRepo.findByIsDeletedOrderByPostedTimeDesc(false).forEach(post -> {
            if (post.getTitle().contains(word)){
                posts.add(post);
            }
        });
        return posts;
    }
}
