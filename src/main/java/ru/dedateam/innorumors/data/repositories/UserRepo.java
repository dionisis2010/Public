package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Comment;
import ru.dedateam.innorumors.data.entities.profiles.User;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

}
