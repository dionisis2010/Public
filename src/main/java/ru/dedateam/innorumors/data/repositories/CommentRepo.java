package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Comment;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {

    Integer countByIsDeleted(Boolean isDeleted);

    Iterable<Comment> findAllByOrderByPostedTimeDesc();

    Iterable<Comment> findByPostIdOrderByPostedTimeDesc(Long commentId);

    Iterable<Comment> findAllByAuthorId(Long authorId);

    Iterable<Comment> findAllByPostIdAndIsDeleted(Long id, Boolean isDeleted);

    Integer countAllByPostIdAndIsDeleted(Long id, Boolean isDeleted);

    Integer countAllByAuthorIdAndIsDeleted(Long id, Boolean isDeleted);
}