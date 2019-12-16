package ru.dedateam.innorumors.data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.dedateam.innorumors.data.entities.content.Vote;

@Repository
public interface VotePostRepo extends CrudRepository<Vote, Long> {
}
