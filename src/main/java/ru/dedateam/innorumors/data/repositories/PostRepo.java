package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Post;

@Repository
public interface PostRepo extends CrudRepository<Post, Long> {
    Post findByBody(String body);
}