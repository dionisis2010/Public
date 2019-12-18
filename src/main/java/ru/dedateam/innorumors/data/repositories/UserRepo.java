package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Post;
import ru.dedateam.innorumors.data.entities.profiles.User;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    Integer countByIsDeleted(Boolean isDeleted);
    Iterable<User> findAllByOrderById();

    Optional<User> findByUsername(String username);

    Iterable<User> findByIsDeletedOrderByUsername(Boolean isDeleted);

//    Iterable<User> findByAuthorIdAndIsDeletedOrderByPostedTimeDesc(Long id, Boolean isDeleted);
}