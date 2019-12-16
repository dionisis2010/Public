package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.content.Post;

@Repository
public interface PostRepo extends PagingAndSortingRepository<Post, Long> {
    Iterable<Post> findByAuthorId(Long id);
}