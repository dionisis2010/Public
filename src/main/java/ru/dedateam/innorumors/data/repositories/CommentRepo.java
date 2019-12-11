package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Comment;

@Repository
public interface CommentRepo extends CrudRepository<Comment, Long> {
}