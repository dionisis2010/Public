package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Post;

@Repository
public interface PostRepo extends PagingAndSortingRepository<Post, Long> {


    Integer countByIsDeleted(Boolean isDeleted);

    Iterable<Post> findAllByOrderByPostedTimeDesc();

    Iterable<Post> findByIsDeletedOrderByPostedTimeDesc(Boolean isDeleted);

    Iterable<Post> findByAuthorIdAndIsDeletedOrderByPostedTimeDesc(Long id, Boolean isDeleted);

    Integer countAllByAuthorId(Long id);

    Iterable<Post> findAllByAuthorId(Long authorId);



}